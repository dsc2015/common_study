package com.jd.study.common.javase_base.multithread.thread;/**
 * Created by dushuangcheng on 2017/3/8.
 */

/**
 * @author dushuangcheng
 * @create 2017-03-08 14:32
 */
public class RunnableThread implements Runnable {
    private int num=1;
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println("当前线程为： "+Thread.currentThread().getName()+" num=="+num++);
        }
    }
}
