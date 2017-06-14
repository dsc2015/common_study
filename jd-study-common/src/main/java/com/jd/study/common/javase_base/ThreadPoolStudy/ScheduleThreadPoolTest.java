package com.jd.study.common.javase_base.ThreadPoolStudy;

import com.sun.tools.javac.jvm.Pool;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/6/13
 */
public class ScheduleThreadPoolTest {
    public static void main(String[] args) {
        ScheduleThreadPoolTest.doWorker();
    }

    private static ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(20);

    public static void doWorker() {
        for (int i = 0; i < 6; i++) {
            pool.scheduleAtFixedRate(new Worker(), 10000L, 60000L, TimeUnit.MILLISECONDS);
            pool.scheduleWithFixedDelay(new Worker(), 10000L, 60000L, TimeUnit.MILLISECONDS);
        }
        System.out.println("+++++++++++++++++++++++++++++++");
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            int i = (int) ((Math.random() * 1000));
            System.out.println("=============================" + i);
        }
    }
}
