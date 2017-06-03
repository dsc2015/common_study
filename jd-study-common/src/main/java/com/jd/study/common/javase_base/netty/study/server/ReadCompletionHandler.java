package com.jd.study.common.javase_base.netty.study.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/4/11
 */
public class ReadCompletionHandler implements CompletionHandler<Integer,ByteBuffer> {
    private AsynchronousSocketChannel asc;

    public ReadCompletionHandler(AsynchronousSocketChannel asc) {
        this.asc = asc;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
        String request=new String(bytes);
        System.out.println("the request is:"+request);
        //发送消息给客户端
        String currentTime=new Date(System.currentTimeMillis()).toString();
        byte[] bytes1 = currentTime.getBytes();

        final ByteBuffer writeBuffer = ByteBuffer.allocate(bytes1.length);
        writeBuffer.put(bytes1);
        //写入通道中
        asc.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if(attachment.hasRemaining()){
                    asc.write(writeBuffer,writeBuffer,this);
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    asc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.asc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
