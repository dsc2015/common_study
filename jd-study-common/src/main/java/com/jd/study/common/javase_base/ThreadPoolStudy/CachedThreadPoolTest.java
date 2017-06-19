package com.jd.study.common.javase_base.ThreadPoolStudy;

import java.util.concurrent.*;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/6/15
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        SynchronousQueue<Runnable> queue = new SynchronousQueue<Runnable>(true);
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 20, 60, TimeUnit.SECONDS,queue );
        // ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<9;i++){
            executorService.execute(new CacheWorker());
        }

        System.out.println("");

        executorService.shutdown();
    }

    static class CacheWorker implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"+++++++++++++++++++");
        }
    }
}
