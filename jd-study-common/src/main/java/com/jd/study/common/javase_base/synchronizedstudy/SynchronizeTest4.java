package com.jd.study.common.javase_base.synchronizedstudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dushuangcheng
 * @description ①，Synchronized代码块中的对象监视器可以是从一个方法传入进来的。
 * @create 2017/6/23
 */
public class SynchronizeTest4 {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        InnerClass innerClass = new InnerClass();
        for (int i = 0; i < 10; i++) {
            pool.execute(new TestWorker(innerClass));
        }
        pool.shutdown();
    }

    static class InnerClass {
        private Object lock = new Object();
        private int a = 0;

        public  int incr() {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    a++;
                }

                try {
                    Thread.sleep((long) (Math.random()*0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return a;
            }
        }
    }

    static class TestWorker implements Runnable {
        private InnerClass innerClass;

        public TestWorker() {
        }

        public TestWorker(InnerClass innerClass) {
            this.innerClass = innerClass;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "=====================================" + innerClass.incr());
        }
    }
}
