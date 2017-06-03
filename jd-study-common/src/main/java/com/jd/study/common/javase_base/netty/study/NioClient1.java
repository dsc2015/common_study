package com.jd.study.common.javase_base.netty.study;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/10
 */
public class NioClient1 {
    public static void main(String[] args) {
        new Thread(new TimeClientHandler("127.0.0.1",8080)).start();

    }

    static class TimeClientHandler implements Runnable {
        private String host;
        private int port;
        private SocketChannel socketChannel;
        private Selector selector;
        private volatile boolean stop;

        public TimeClientHandler(String host, int port) {
            this.host = host;
            this.port = port;
            try {
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                doConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!stop){
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        handleInput(key);
                        iterator.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("----------------------------------");
            }
            if(selector!=null){
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        private void doConnection() throws IOException {
            //直接连接成功的话
            boolean connect = socketChannel.connect(new InetSocketAddress(host, port));
            if(connect){
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);
            }else {
                socketChannel.register(selector,SelectionKey.OP_CONNECT);
            }

        }

        private void doWrite(SocketChannel socketChannel){
            String queryString="get the date from server...";
            byte[] bytes = queryString.getBytes();

            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            try {
                //读取并写入数据
                socketChannel.write(writeBuffer);
                //判断tcp缓冲区是否还有数据
                if(!writeBuffer.hasRemaining()){
                    System.out.println("write data is success...");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleInput(SelectionKey s) throws IOException {
            //判断key是否是有效的，没有close或者cancel的都是有效的
            if(s.isValid()){
                SocketChannel channel = (SocketChannel) s.channel();
                if(s.isConnectable()){
                    if(channel.finishConnect()){
                        /**
                         * If the selector
                         * detects that the corresponding channel is ready for reading, has reached
                         * end-of-stream, has been remotely shut down for further reading, or has
                         * an error pending, then it will add OP_READ to the key's
                         * ready-operation set and add the key to its selected-key&nbsp;set.
                         */
                        channel.register(selector,SelectionKey.OP_READ);
                        doWrite(channel);
                    }else {
                        System.exit(1);
                    }
                }
                if(s.isReadable()){
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int read = channel.read(readBuffer);
                    if(read>0){
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body=new String(bytes);
                        System.out.println("Now is :"+body);
                        stop=true;
                    }else if(read<0){
                        s.cancel();
                        channel.close();
                    }
                }
            }
        }
    }
}
