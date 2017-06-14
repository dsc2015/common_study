package com.jd.study.common.javase_base.multithread.concurrentqueue;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dushuangcheng
 * @description 一个队列模型
 * @create 2017/6/14
 */
public class MyArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E> {
    /**
     * @description:队列中的数组元素
     */
    private Object[] items;
    /**
     * @description:下一次取元素的index
     */
    int takeIndex;
    /**
     * @description:下一次放的index
     */
    int putIndex;
    /**
     * @description:队列中的元素数量
     */
    int count;

    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    //基于公平策略的构造器
    public MyArrayBlockingQueue(int capacity,boolean fair){
        if(capacity<0){
            throw new IllegalStateException("容量参数不合法");
        }
        this.lock=new ReentrantLock(fair);
        this.notEmpty=lock.newCondition();
        this.notFull=lock.newCondition();
        items=new Object[capacity];
    }

    //直接指定容量的构造器
    public MyArrayBlockingQueue(int capacity){
        this(capacity,false);
    }

    public MyArrayBlockingQueue(int capacity,boolean fair,Collection<?extends E> c){
        this(capacity,fair);
        ReentrantLock lock1 = this.lock;
        lock1.lock();
        try{
            int i=0;
            for(E e:c){
                items[i++]=e;
            }
            count=i;
            //已经满了的情况下，折返为0
            if(i==capacity){
                putIndex=0;
            }else {
                putIndex=i;
            }
            putIndex=i+1;
        }finally {
            lock1.unlock();
        }
    }



    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void put(E e) throws InterruptedException {

    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public E take() throws InterruptedException {
        return null;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
