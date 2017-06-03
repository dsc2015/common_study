package com.jd.study.common.javase_base.TimerAndTask;/**
 * Created by dushuangcheng on 2016/10/27.
 */

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dushuangcheng
 * @create 2016-10-27 11:20
 * <p/>
 * 测试的是jdk自带的两种定时的方式以及jdk中自带的时间转换工具类
 * <p/>
 * <p/>
 * 方法一种：采用的是jdk的Timer和TimerTask进行任务的定时调度
 * 方法二中：采用的是util.concurrent包下的定时任务调度，这个可以采用线程池的形式进行任务的执行，还可以构造
 * 任务队列实现任务的无阻塞和阻塞执行。
 * <p/>
 * 还是切记不要忘记关闭线程连接。
 * <p/>
 * TimeUnit：就是一个时间换算的枚举类
 */
public class TimerTest {
    public static void main(String[] args) {
        // TimeUnit timeUnit=new TimeUnit();
        TimeUnit timeUnitHours = TimeUnit.HOURS;
        long l = timeUnitHours.toDays(7200);
        System.out.println("l=====>" + l);
        ProviderFunction providerFunction = new ProviderFunction();
        System.out.println("当前的线程为：================" + Thread.currentThread().getName());
        TimerTest timerTest = new TimerTest();
        timerTest.doExecute1(providerFunction);
    }

    public void doExecute(final ProviderFunction providerFunction) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("当前的线程为：<<<<<<================>>>>>>" + Thread.currentThread().getName());
                    providerFunction.getSum(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }

    public void doExecute1(final ProviderFunction providerFunction) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("当前的线程为：<<<<<<================>>>>>>" + Thread.currentThread().getName());
                    providerFunction.getSum(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0L, 1000L, TimeUnit.SECONDS);
        //关闭线程池
        scheduledExecutorService.shutdown();


    }
}
