package com.jd.study.common.javase_base.netty.study;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/10
 */
public class NioServer1 {
    public static void main(String[] args) {
        MultiplexerServer multiplexerServer = new MultiplexerServer(8080);
        //创建线程进行处理
        new Thread(multiplexerServer).start();
    }

    static class MultiplexerServer implements Runnable {
        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private volatile boolean stop;

        public MultiplexerServer(int port) {
            try {
                //1,创建多路复用器
                selector = Selector.open();
                //创建服务端的socket通道
                serverSocketChannel = ServerSocketChannel.open();
                // 设置为非阻塞的状态
                serverSocketChannel.configureBlocking(false);
                //socket通道绑定端口
                serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
                //把服务通道注册到多路复用器上，设置为accept事件
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("the time server is start,port=" + port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            this.stop = true;
        }

        @Override
        public void run() {
            while (!stop) {
                SelectionKey key = null;
                try {
                    //没隔一秒中扫描selector上已经ready的key
                    selector.select(1000);
                    //在循环中获取监听就绪的key，这些key就是客户端的接入事件
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        //处理输入的请求
                        handleInput(key);
                        iterator.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (key != null) {
                        key.cancel();
                        if (key.channel() != null) {
                            try {
                                key.channel().close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
                System.out.println("-----------------------------");
            }
        }

        private void handleInput(SelectionKey selectionKey) throws IOException {
            if (selectionKey.isValid()) {
                //处理新接入的请求
                if (selectionKey.isAcceptable()) {
                    //为客户端创建一个通道
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    ByteBuffer allocateByteBuffer = ByteBuffer.allocate(1024);
                    //把请求的信息读入到buffer中去
                    int read = socketChannel.read(allocateByteBuffer);
                    if (read > 0) {
                        allocateByteBuffer.flip();
                        //从ByteBuffer中读取消息
                        byte[] bytes = new byte[allocateByteBuffer.remaining()];
                        //读取到buffer中去
                        allocateByteBuffer.get(bytes);
                        String body = new String(bytes);
                        System.out.println("The query string is:" + body);

                        //给客户端发送信息
                        String currentTime = new Date(System.currentTimeMillis()).toString();
                        ByteBuffer writeBuffer = ByteBuffer.allocate(currentTime.getBytes().length);
                        writeBuffer.put(currentTime.getBytes());
                        writeBuffer.flip();
                        socketChannel.write(writeBuffer);
                    } else if (read < 0) {
                        //关闭链路
                        selectionKey.cancel();
                        socketChannel.close();
                    }
                }
            }
        }
    }


}
