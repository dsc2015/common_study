package com.jd.study.common.javase_base.multithread.thread;
public class ThreadTest {
    public static int num=100;

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        Thread1 thread1 = new Thread1();
        thread1.setThreadTest(threadTest);
        thread1.start();

        Thread2 thread2 = new Thread2();
        thread2.setThreadTest(threadTest);
        thread2.start();
    }
}
