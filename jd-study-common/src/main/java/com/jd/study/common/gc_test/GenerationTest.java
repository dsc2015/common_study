package com.jd.study.common.gc_test;
/**
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * @author dushuangcheng
 * @create 2017-06-02 12:10
 */
public class GenerationTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1200000);
        // 1M的空间
        byte[] byte1 = new byte[1024 * 1024];
        System.out.println("1111111111111111===========================================");
        Thread.sleep(5000);
        byte[] byte2 = new byte[1024 * 1024];
        System.out.println("2222222222222222===========================================");
        Thread.sleep(5000);
        byte[] byte3 = new byte[1024 * 1024];
        System.out.println("3333333333333333===========================================");
        Thread.sleep(5000);
        byte[] byte4 = new byte[1024 * 1024];
        System.out.println("4444444444444444===========================================");
        Thread.sleep(20000);

    }
}
