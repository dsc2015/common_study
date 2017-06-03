package com.jd.study.common.javase_base.netty.study.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/4/11
 */
public class TimeServerHandler implements Runnable {
    private int port;
    private CountDownLatch countDownLatch;
    private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public TimeServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            //绑定端口
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("the time server start...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);
        doAccept();
        try {
            //让线程在此进行等待
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, TimeServerHandler>() {
            @Override
            public void completed(AsynchronousSocketChannel result, TimeServerHandler attachment) {
                attachment.asynchronousServerSocketChannel.accept(attachment,this);
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                result.read(allocate,allocate,new ReadCompletionHandler(result));
            }

            @Override
            public void failed(Throwable exc, TimeServerHandler attachment) {
                exc.printStackTrace();
                attachment.countDownLatch.countDown();
            }
        });
    }
}
