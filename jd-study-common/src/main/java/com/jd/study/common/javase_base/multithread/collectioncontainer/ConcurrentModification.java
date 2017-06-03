package com.jd.study.common.javase_base.multithread.collectioncontainer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author dushuangcheng
 * @description 测试toString, 可能导致的并发问题
 * @create 2017/4/18
 */
class ConcurrentModification {
    public static void main(String[] args) {
        ConcurrentModification concurrentModification = new ConcurrentModification();
        //concurrentModification.addTenThing();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        //并发测试
        new Thread(new ThreadOne(concurrentModification, countDownLatch)).start();
        new Thread(new ThreadTwo(concurrentModification, countDownLatch)).start();
       /* countDownLatch.countDown();
        countDownLatch.countDown();*/
/*

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

    }

    private final Set<Integer> data = new HashSet<>();

    public synchronized void add(Integer element) {
        data.add(element);
    }

    public synchronized void remove(Integer i) {
        data.remove(i);
    }

    public void addTenThing() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            add(random.nextInt());
        }

        System.out.println("========================" + data);

    }

    static class ThreadOne implements Runnable {
        private final ConcurrentModification concurrentModification;
        private CountDownLatch countDownLatch;

        public ThreadOne(ConcurrentModification concurrentModification, CountDownLatch countDownLatch) {
            this.concurrentModification = concurrentModification;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            try {
                countDownLatch.await(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++)
                concurrentModification.add(100 + i);
            System.out.println("++++++++++++++++++++++++++++++++");
            countDownLatch.countDown();

        }
    }

    static class ThreadTwo implements Runnable {
        private final ConcurrentModification concurrentModification;
        private CountDownLatch countDownLatch;

        public ThreadTwo(ConcurrentModification concurrentModification, CountDownLatch countDownLatch) {
            this.concurrentModification = concurrentModification;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            try {
                countDownLatch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            concurrentModification.addTenThing();
            System.out.println("------------------------------");
        }
    }
}
