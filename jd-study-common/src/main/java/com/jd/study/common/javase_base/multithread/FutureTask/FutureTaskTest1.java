package com.jd.study.common.javase_base.multithread.FutureTask;


import java.util.concurrent.*;

/**测试最先等待的线程超时时间比较短的情况，在前者已经压栈的情况下如何弹出？
 * @author dushuangcheng
 * @description
 * @create 2017/6/14
 */
public class FutureTaskTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Worker());
        new Thread(futureTask).start();
        Object o = null;
        try {
            o = futureTask.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        new Thread(new GetValue(futureTask)).start();
        System.out.println(Thread.currentThread().getName()+"======================"+o);
    }

    static class Worker implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(10000);
            //System.out.println("++++++++++++++++++++");
            return (int) (Math.random() * 10000);
        }
    }

    static class GetValue implements Runnable{
        private FutureTask futureTask;

        public GetValue(FutureTask futureTask) {
            this.futureTask = futureTask;
        }

        @Override
        public void run() {
            try {
                Object o = futureTask.get();
                System.out.println(Thread.currentThread().getName()+"====get value======="+o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
