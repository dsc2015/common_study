package com.jd.study.common.javase_base.ThreadPoolStudy;

import java.util.concurrent.*;

/**
 * 测试FutureTask，这个对象就是把相关的Runnable对象或者Callable对象封装为FutureTask类型的对象
 * @author dushuangcheng
 * @create 2017-06-06 19:54
 */
public class ThreadPoolTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Integer result=1;
        //将callable或者Runnable类型的对象封装为FutureTask类型的对象
        for(int i=0;i<10;i++){
            FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(new MyTask(),result);
            if(i==5){
                integerFutureTask.cancel(true);
            }
            executorService.execute(integerFutureTask);
            Integer integer = integerFutureTask.get();
            System.out.println("the result is..."+integer);
        }
        //切记得关闭线程池，释放资源
        executorService.shutdown();
    }
    static class MyTask implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行============================>");
        }
    }
}
