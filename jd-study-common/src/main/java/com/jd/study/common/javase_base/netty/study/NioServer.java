package com.jd.study.common.javase_base.netty.study;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) throws IOException {
        //1.构建一个连接通道
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //2,绑定监听的端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //设置为非阻塞的模式
        serverSocketChannel.configureBlocking(false);
        //3,创建reactor线程,创建多路复用器
        Selector selector=Selector.open();
        new Thread(new ReactorTask()).start();
        //4.注册到多路复用器selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //5，多路复用器在run方法中轮询就绪的key
        int select = selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()){
            SelectionKey next = iterator.next();
            //处理io时间
        }
        // 6:多路复用器监听有新的客户端的接入，处理新的请求
        SocketChannel accept = serverSocketChannel.accept();
        //7,设置客户端为非阻塞的模式
        accept.configureBlocking(false);
        //8,将新接入的客户端注册到reactor线程的多路复用器上，监听读操作
        accept.register(selector,SelectionKey.OP_READ);
        //9,异步读取客户端的消息到缓冲区
        accept.read(ByteBuffer.allocate(100));
        //10,对byteBuffer信息进行解码
        //11，



    }
    static class ReactorTask implements Runnable{

        @Override
        public void run() {

        }
    }
}
