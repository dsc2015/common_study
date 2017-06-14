package com.jd.study.common.javase_base.multithread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dushuangcheng
 * @description
 * 1，跟Object中相关的方法的区别：可以按照条件进行唤醒，可以按照顺序，稍微灵活一些
 * @create 2017/5/16
 */
public class BoundBuffer {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 100000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(5));
        BoundBuffer boundBuffer = new BoundBuffer();
        for(int i=0;i<4;i++){
            Consumer consumer = new Consumer();
            consumer.setBoundBuffer(boundBuffer);
            Product product = new Product();
            product.setBoundBuffer(boundBuffer);
            pool.execute(consumer);
            //pool.execute(consumer);
            pool.execute(product);
        }
        System.out.println(Thread.currentThread().getId());
        pool.shutdown();
    }
    static class Product implements Runnable{
        private BoundBuffer boundBuffer;

        public BoundBuffer getBoundBuffer() {
            return boundBuffer;
        }

        public void setBoundBuffer(BoundBuffer boundBuffer) {
            this.boundBuffer = boundBuffer;
        }

        @Override
        public void run() {
            Integer a=(int)(Math.random()*1000);
            try {
                boundBuffer.put(a);
                System.out.println("put+++++++++++++++++++++++"+a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Consumer implements Runnable{
        private BoundBuffer boundBuffer;

        public BoundBuffer getBoundBuffer() {
            return boundBuffer;
        }

        public void setBoundBuffer(BoundBuffer boundBuffer) {
            this.boundBuffer = boundBuffer;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                Object take = boundBuffer.take();
                System.out.println("consumer...="+take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private Lock lock = new ReentrantLock(true);
    //与锁关联的条件对象
    private Condition notFull = lock.newCondition();
    //与锁关联的条件对象
    private Condition notEmpty = lock.newCondition();

    private Object[] items = new Object[10];
    private int putPtr;
    private int takePtr;
    private int count;
    Integer i;

    public void put(Object obj) throws InterruptedException {

        /*synchronized (i){
            System.out.println("===========================");
        }*/
        lock.lock();
        //这里注意要用while循环来判断，在满了的情况下，
        try {
            //用循环来避免虚假唤醒
            while (items.length == count) {
                //条件等待
                notFull.await();
            }
            items[putPtr] = obj;
            //满了？重置为0
            if (++putPtr == items.length) {
                //置为0，从开始去取
                putPtr = 0;
            }
            ++count;
            //通知去取
            notEmpty.signal();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    /**
     * 取的时候，在空的情况下是不能取的，需要等待
     *
     * @return
     */
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object temp = items[takePtr];
            //重置为0，类似于队列转到开始的地方
            if (++takePtr == items.length) {
                takePtr = 0;
            }
            count--;
            //唤醒放的线程
            notFull.signal();
            return temp;
        } finally {
            lock.unlock();
        }
    }
}
