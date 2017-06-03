package com.jd.study.common.javase_base.multithread.Debug;/**
 * Created by dushuangcheng on 2016/10/20.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用提供的debug功能进行多线程的调试实验
 *
 *
 ExecuteService 服务的关闭
 当使用 ExecutorService 完毕之后，我们应该关闭它，这样才能保证线程不会继续保持运行状态。
 举例来说，如果你的程序通过 main() 方法启动，并且主线程退出了你的程序，如果你还有壹個活动的 ExecutorService 存在于你的程序中，那么程序将会继续保持运行状态。存在于 ExecutorService 中的活动线程会阻止Java虚拟机关闭。
 为了关闭在 ExecutorService 中的线程，你需要调用 shutdown() 方法。ExecutorService 并不会马上关闭，而是不再接收新的任务，壹但所有的线程结束执行当前任务，ExecutorServie 才会真的关闭。所有在调用 shutdown() 方法之前提交到 ExecutorService 的任务都会执行。
 如果你希望立即关闭 ExecutorService，你可以调用 shutdownNow() 方法。这個方法会尝试马上关闭所有正在执行的任务，并且跳过所有已经提交但是还没有运行的任务。但是对于正在执行的任务，是否能够成功关闭它是无法保证的，有可能他们真的被关闭掉了，
 也有可能它会壹直执行到任务结束。这是壹個最好的尝试。
 *
 * 注意把线程关闭的问题
 *
 *
 * @author dushuangcheng
 * @create 2016-10-20 16:36
 */
public class ThreadDebug {
    private static final Logger log= LoggerFactory.getLogger(ThreadDebug.class);
    private static final ExecutorService executorService= Executors.newFixedThreadPool(5);
    public void doExecute(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                BaseTestClass baseTestClass=new BaseTestClass();
                String[] str = baseTestClass.getStr(10);
                for(int i=0;i<str.length;i++){
                    //log.info("====================");
                    System.out.println("当前线程为："+Thread.currentThread().getName()+"       字符数组的内容为：=================="+str[i]);
                }
            }
        });
        //非常重要，如果不关闭线程池，程序那么会一直处于等待状态
       // executorService.shutdownNow();
    }

    public static Logger getLog() {
        return log;
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDebug threadDebug=new ThreadDebug();
        for(int i=0;i<10;i++){
            threadDebug.doExecute();
            //Thread.sleep(100);
            //Thread.sleep(100);
       }
        //Thread.sleep(5000);
        threadDebug.getExecutorService().shutdown();

    }
}
