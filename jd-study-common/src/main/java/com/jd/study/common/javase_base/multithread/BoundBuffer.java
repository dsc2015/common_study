package com.jd.study.common.javase_base.multithread;

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
    private Lock lock = new ReentrantLock(true);
    //与锁关联的条件对象
    private Condition notFull = lock.newCondition();
    //与锁关联的条件对象
    private Condition notEmpty = lock.newCondition();

    private Object[] items = new Object[100];
    private int putPtr;
    private int takePtr;
    private int count;

    public void put(Object obj) throws InterruptedException {
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
