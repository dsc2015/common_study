package com.jd.study.common.javase_base.multithread.lock;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试结论：
 * 1，在写锁的获取过程中，如果当前线程没有持有写锁，而其他线程也没有持有写锁的情况下，不能获取写锁，线程休眠
 * 在没有线程获取写锁的情况下，只有等读锁线程全部释放完毕，才能获取写锁。
 * 2，当一个线程获取读锁之后接着获取写锁，然而在它获取了读锁之后，去获取写锁之前另外一个线程也去获取写锁但是没有成功
 * 因为进入队列中进行排队，而当前线程获取写锁也进入排队。
 * @author dushuangcheng
 * @description
 * @create 2017/6/24
 */
public class ReentraintReadWriteLockStudy {
    public static void main(String[] args) {
        ReentrantReadWriteLock rrw=new ReentrantReadWriteLock();
        CacheDemo cd = new CacheDemo();
        ReentrantReadWriteLock.ReadLock readLock = rrw.readLock();
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + "=======================" + "加读锁成功！");
        //================================================================
        new Thread(new TestWorker1(rrw,cd)).start();
        new Thread(new TestWorker2(rrw,cd)).start();
        //================================================================
        //产生死锁的情况
        ReentrantReadWriteLock.WriteLock writeLock = rrw.writeLock();
        //让读锁先释放掉，主线程的阻塞得以解除，因而不算死锁
       readLock.unlock();
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + "=======================" + "加写锁成功！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //readLock.unlock();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object o : cd.getCache()) {
            System.out.println(o);
        }
        writeLock.unlock();
    }


    static class CacheDemo {
        private Object[] cache = new Object[10];

        public Object[] getCache() {
            return cache;
        }

        public void setCache(Object[] cache) {
            this.cache = cache;
        }

        @Override
        public String toString() {
            return "CacheDemo{" +
                    "cache=" + Arrays.toString(cache) +
                    '}';
        }
    }

    static class TestWorker1 implements Runnable {
        private ReentrantReadWriteLock rrw;
        private CacheDemo cd;

        public TestWorker1(ReentrantReadWriteLock rrw, CacheDemo cd) {
            this.rrw = rrw;
            this.cd = cd;
        }

        @Override
        public void run() {
            ReentrantReadWriteLock.ReadLock readLock = rrw.readLock();
            try {
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + "=======================" + "加读锁成功！");
                System.out.println("==================开始读取数据====================");
                for (Object o : cd.getCache()) {
                    System.out.println(o);
                }
                System.out.println("==================读取数据完毕====================");
            } finally {
                readLock.unlock();
            }

        }
    }

    static class TestWorker2 implements Runnable {
        private ReentrantReadWriteLock rrw;
        private CacheDemo cd;

        public TestWorker2(ReentrantReadWriteLock rrw, CacheDemo cd) {
            this.rrw = rrw;
            this.cd = cd;
        }

        @Override
        public void run() {
            ReentrantReadWriteLock.WriteLock writeLock = rrw.writeLock();
            try {
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + "=======================" + "加写锁成功！");
                System.out.println("==================开始写入数据====================");
                for (int i = 0; i < cd.getCache().length; i++) {
                    cd.getCache()[i] = i;
                }
                System.out.println("==================写入数据完毕====================");
            } finally {
                writeLock.unlock();
            }
        }
    }
}
