package com.jd.study.common.javase_base.IO_NIOAndFile.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/10
 */
public class BufferTest {
    public static void main(String[] args) throws IOException {
        //开辟固定大小的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(11);
        byteBuffer.put((byte) 124);
        //使用包装的buffer
        String str="dsc I love";
        ByteBuffer wrap = byteBuffer.wrap(str.getBytes());
        char aChar = wrap.getChar();
        System.out.println("--------------->"+aChar);

        //一次完整的操作过程
        ByteBuffer putByteBuffer = byteBuffer.put(str.getBytes());
        //把状态调整为读的状态，limit=position;position=0,
        putByteBuffer.flip();
        char aChar1 = putByteBuffer.getChar();
        //调用clea或者compact进行数据的整理
        putByteBuffer.compact();
        char aChar2 = putByteBuffer.getChar();

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:/netty/a.txt","rw");
        //获取文件管道
        FileChannel channel = randomAccessFile.getChannel();
        //获取buffer
        ByteBuffer allocate = ByteBuffer.allocate((int) randomAccessFile.length());
        int read = channel.read(allocate);
        while (read!=-1){
            allocate.flip();
            while (allocate.hasRemaining()){
                System.out.println("==========================>"+(char)allocate.get());
            }
            allocate.clear();
            read = channel.read(allocate);
        }
        channel.close();


    }
}
