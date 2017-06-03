package com.jd.study.common.javase_base.multithread.executor;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @description 线程池
 * @author dushuangcheng
 * @create 2017/2/27
 *
 *
 * However, the <tt>Executor</tt> interface does not strictly
 * require that execution be asynchronous. In the simplest case, an
 * executor can run the submitted task immediately in the caller's
 * thread:
 *在调用者的线程内执行
 * <pre>
 * class DirectExecutor implements Executor {
 *     public void execute(Runnable r) {
 *         r.run();
 *     }
 * }</pre>
 *
 * More typically, tasks are executed in some thread other
 * than the caller's thread.  The executor below spawns a new thread
 * for each task.
 *在线程池内执行
 * <pre>
 * class ThreadPerTaskExecutor implements Executor {
 *     public void execute(Runnable r) {
 *         new Thread(r).start();
 *     }
 * }</pre>
 *
 *
 * 创建线程的两种方式所带来的区别：Runnable和Thread的区别，典型的买火车票程序。
 *
 * When a Java Virtual Machine starts up, there is usually a single
 * non-daemon thread (which typically calls the method named
 * main.jvm启动的时候回创建一个非守护线程称为main
 *
 *
 */

public class ExecutorTest {
    private Executor e= Executors.newCachedThreadPool();
    private Thread t=new Thread();
    private Runnable r;

    private static void testMethod(){
        System.out.println(";;;;");
    }

    public static void main(String[] args) {
        ((ExecutorTest)null).testMethod();
        for(int i=0;i<3;i++){
            Random random=new Random(1000);
            int i1 = random.nextInt();
            int i2 = random.nextInt();
            int i3 = random.nextInt();
            System.out.println(i1+"_"+i2+"_"+i3);

        }
    }
}
