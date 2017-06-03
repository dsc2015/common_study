package com.jd.study.common.javase_base.multithread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/5/16
 */
public class MyMutex implements Lock{

    // The sync object does all the hard work. We just forward to it.
    private final Syn sync = new Syn();

    @Override
    public void lock() {
        sync.acquire(1);
    }
    //可以中断的获取锁的方式
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
    //是否获取了锁
    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }

  /*  public void lock()                { sync.acquire(1); }
    public boolean tryLock()          { return sync.tryAcquire(1); }
    public void unlock()              { sync.release(1); }
    public Condition newCondition()   { return sync.newCondition(); }
    public boolean isLocked()         { return sync.isHeldExclusively(); }
    public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    public boolean tryLock(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }*/
    //定义一个内部类
    class Syn extends AbstractQueuedSynchronizer{
        //判断是否持有锁的状态:1,表示持有锁，0：表示没有持有锁，这是锁的状态标识
        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }
        /**
         * @description 尝试获取锁
         * @Author  dushuangcheng
         * @param
         * @return
         * @throw
         * @date   2017/5/16
         */
        @Override
        protected boolean tryAcquire(int acquires) {
            if(acquires==1){
                return true;
            }
            //如果是0的话
            if(compareAndSetState(0,1)){
                //把锁的线程持有者设置为当前的线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        /**
         * @description 尝试释放锁
         * @Author  dushuangcheng
         * @param
         * @return
         * @throw
         * @date   2017/5/16
         */
        @Override
        protected boolean tryRelease(int release) {
            if(release==1){
                return true;
            }
            if (getState() == 0) throw new IllegalMonitorStateException();
            //清除当前线程持有者
            setExclusiveOwnerThread(null);
            //把状态设置为0，方便其他线程来获取锁
            setState(0);
            return true;
        }
        //一个condition条件
        Condition newCondition(){
            return new ConditionObject();
        }

        private void readObject(ObjectInputStream s)
                throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // 重置为解锁状态
        }
    }
}
