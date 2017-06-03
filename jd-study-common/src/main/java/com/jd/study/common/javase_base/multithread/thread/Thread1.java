package com.jd.study.common.javase_base.multithread.thread;
/**
 * @description 
 * @author dushuangcheng
 * @create 2017/3/8
 */
public class Thread1 extends Thread {
    private ThreadTest threadTest;

    public ThreadTest getThreadTest() {
        return threadTest;
    }

    public void setThreadTest(ThreadTest threadTest) {
        this.threadTest = threadTest;
    }

    public void run(){
        for(int i=0;i<100;i++){
            System.out.println("当前线程为："+Thread.currentThread().getName()+"  num=="+threadTest.num--);
        }
    }
}
