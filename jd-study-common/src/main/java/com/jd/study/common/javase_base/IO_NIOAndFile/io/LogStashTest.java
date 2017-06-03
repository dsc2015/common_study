package com.jd.study.common.javase_base.IO_NIOAndFile.io;

import java.io.*;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/3/26
 */
public class LogStashTest {
    public static void main(String[] args) throws IOException {
        File file1=new File("D:/demo.log");
        File file2=new File("D:/log_for_logstah/example-debug.log");
        FileInputStream fileInputStream=new FileInputStream(file1);
        FileOutputStream fileOutputStream=new FileOutputStream(file2);
        BufferedInputStream bis=new BufferedInputStream(fileInputStream);
        //定义一个字节数组
        BufferedOutputStream bos=new BufferedOutputStream(fileOutputStream);
        byte[] data=new byte[1024];
        while( bis.read(data,0,data.length)>=0){
            bos.write(data);
        }

        bos.close();
        bis.close();
    }
}
