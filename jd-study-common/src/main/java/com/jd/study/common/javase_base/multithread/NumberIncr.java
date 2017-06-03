package com.jd.study.common.javase_base.multithread;

import com.jd.study.common.javase_base.random.RandomTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/3/24
 */
public class NumberIncr {
    private static int a = 0;

    public synchronized int incr() {
        //int a=10;
        return a++;
    }

    public static void main(String[] args) throws InterruptedException {
        // RandomTest
       final NumberIncr numberIncr = new NumberIncr();
        myThread myThread = new myThread();
        myThread.setNumberIncr(numberIncr);
        int i=0;
        for (i = 0; i < 1000; i++) {
            new Thread(myThread).start();
        }
       /* final CountDownLatch countDownLatch = new CountDownLatch(100);
        final CountDownLatch countDownLatch2 = new CountDownLatch(100);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                   // countDownLatch.countDown();
                   // try {
                      //  countDownLatch.await();
                   // } catch (InterruptedException e) {
                       // e.printStackTrace();
                   // }
                    numberIncr.incr();
                    System.out.println("---------------------"+Thread.currentThread().getName());
                   // incr();
                }
            }).start();*/
          /*  countDownLatch2.countDown();
            countDownLatch2.await();*/


       // Thread.sleep(3000);
        System.out.println(a);
    }

    static class myThread implements Runnable{
        private  NumberIncr numberIncr ;

        public NumberIncr getNumberIncr() {
            return numberIncr;
        }

        public void setNumberIncr(NumberIncr numberIncr) {
            this.numberIncr = numberIncr;
        }

        @Override
        public void run() {
            numberIncr.incr();
            System.out.println("---------------------"+Thread.currentThread().getName());
        }
    }
}

