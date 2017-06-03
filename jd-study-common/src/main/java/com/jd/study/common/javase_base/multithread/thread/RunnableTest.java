package com.jd.study.common.javase_base.multithread.thread;/**
 * Created by dushuangcheng on 2017/3/8.
 */

/**
 * @author dushuangcheng
 * @create 2017-03-08 14:15
 */
public class RunnableTest {
    public static void main(String[] args) {
        RunnableThread runnableThread = new RunnableThread();
        new Thread(runnableThread).start();
        new Thread(runnableThread).start();
        new Thread(runnableThread).start();
    }
}
