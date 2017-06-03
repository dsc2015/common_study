package com.jd.study.common.javase_base.IO_NIOAndFile.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/4/7
 */
public class FileTest {
    private Integer a=10;

    public int Method1(){
        int k=0;
        try{
            System.out.println("1当前线程为："+Thread.currentThread().getName());
            k=a/0;
        } catch (Exception e){
            System.out.println("2当前线程为："+Thread.currentThread().getName());
            print();
        }
        return k;
}
    public void print(){
        System.out.println("3当前线程为："+Thread.currentThread().getName());
        System.out.println("catch exception...");
    }
    public static void main(String[] args) {
        //new FileTest().Method1();
       /* File file = new File("D:/a_io_test/a.txt");
        URL resource = FileTest.class.getResource("/");
        String file1 = resource.getFile();
        System.out.println(file1);*/
        ByteBuffer allocate = ByteBuffer.allocate(3);
        byte[] bytes = new byte[]{1,2,3,4,5};
        ByteBuffer wrap = allocate.wrap(bytes);
        wrap.put((byte) 100);
       // wrap.mark();
       // wrap.clear();
       // wrap.flip();
        byte b = wrap.get();
        System.out.println(b);
        wrap.compact();
        byte b1 = wrap.get();
        System.out.println(b1);


    }
}
