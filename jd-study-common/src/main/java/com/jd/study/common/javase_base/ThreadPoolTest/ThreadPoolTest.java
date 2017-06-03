package com.jd.study.common.javase_base.ThreadPoolTest;

import java.util.concurrent.*;

/**
 * 1，超过5个线程的时候但是小于(5+2)的情况下先进行排队；
 * 2，超过7个线程，但是小于10个线程的情况下，new 线程
 * 3，超过最大线程数+队列长度的情况下就进行拒绝。
 *
 * 线程池中的线程数目等于：任务数目-排队的线程数目（队列的长度）
 * @author dushuangcheng
 * @create 2017-05-28 18:37
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolTest.worker();
    }
    private static ExecutorService threadPool= new ThreadPoolExecutor(5, 10, 3, TimeUnit.SECONDS, new LinkedBlockingQueue(2), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                //r.run();
                System.out.println("太多任务。。。"+executor.awaitTermination(0,TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    private static final int threads=130;
    private static CountDownLatch latch=new CountDownLatch(1);

    public static void worker(){
        for(int i=0;i<threads;i++){
            threadPool.execute(new WorkerTask());
        }
        System.out.println("线程准备就绪！");
        latch.countDown();

        threadPool.shutdown();
    }

    static class WorkerTask implements Runnable{
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=======+++++++++++++=============");

        }
    }

}
