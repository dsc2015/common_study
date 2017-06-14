package com.jd.study.common.javase_base.ThreadPoolStudy;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/6/13
 */
public class SchedulThreadPoolStudy {
    class Task implements Runnable, Comparable<Task> {
        Integer delay;
        int heapIndex = 0;

        public int getHeapIndex() {
            return heapIndex;
        }

        public void setHeapIndex(int heapIndex) {
            this.heapIndex = heapIndex;
        }

        @Override
        public void run() {
            System.out.println("========================");
        }


        @Override
        public int compareTo(Task o) {
            return this.delay.compareTo(o.delay);
        }

       /* @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delay, TimeUnit.NANOSECONDS);
        }*/
    }

    static class DelayWorkQueue extends AbstractQueue<Runnable> implements BlockingQueue<Runnable> {
        private static final int INIT_CAPACITY = 16;
        private Runnable[] queue = new Runnable[INIT_CAPACITY];
        private ReentrantLock lock = new ReentrantLock();
        private int size = 0;
        private Thread leader = null;
        private Condition available = lock.newCondition();

        private void setIndex(Runnable task, int idx) {
            ((Task) task).heapIndex = idx;
        }

        /**
         * 向上进行修正
         *
         * @param k
         * @param task
         */
        private void shiftUp(int k, Task task) {
            while (k > 0) {
                //获取父索引
                int parent = (k - 1) / 2;
                Runnable temp = queue[parent];
                Task taskTemp = (Task) temp;
                if (task.compareTo(taskTemp) >= 0)
                    break;
                //父亲下移,洞上移
                queue[k] = temp;
                setIndex(temp, k);
                k = parent;
            }
            queue[k] = task;
            setIndex(task, k);
        }

        /**
         * 向下修正,找到合适位置进行插入
         *
         * @param k
         * @param task
         */
        public void shiftDown(int k, Runnable task) {
            Task tTask = (Task) task;
            int half = size / 2;
            while (k < half) {
                int leftChild = 2 * k + 1;
                int rightChild = leftChild + 1;
                Runnable runnableLeft = queue[leftChild];
                Task leftTask = (Task) runnableLeft;
                Runnable runnableRight = queue[rightChild];
                Task rightTask = (Task) runnableRight;
                if (rightChild < size && leftTask.compareTo(rightTask) >= 0) {
                    leftChild = rightChild;
                    queue[leftChild] = queue[rightChild];
                }
                if (tTask.compareTo((Task) queue[leftChild]) <= 0) {
                    break;
                }
                //把左子节点上移
                queue[k] = runnableLeft;
                setIndex(runnableLeft, k);
                k = leftChild;
            }
            queue[k] = task;
            setIndex(task, k);
        }

        /**
         * 扩容机制
         */
        private void grow() {
            int oldCapacity = queue.length;
            int newCapacity = oldCapacity + oldCapacity / 2;
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            queue = Arrays.copyOf(queue, newCapacity);
        }

        private int indexOf(Runnable task) {
            int i = ((Task) task).heapIndex;
            if (i >= 0 && i < size && queue[i] == task) {
                return i;
            }
            for (int j = 0; j < size; j++) {
                if (queue[j].equals(task)) {
                    return j;
                }
            }
            return -1;
        }

        public boolean contains(Runnable task) {
            ReentrantLock lock1 = this.lock;
            lock1.lock();
            try {
                for (int i = 0; i < size; i++) {
                    if (task.equals(queue[i])) {
                        return true;
                    }
                }
            } finally {
                lock1.unlock();
            }
            return false;
        }

        public boolean remove(Runnable task) {
            ReentrantLock lock1 = this.lock;
            lock1.lock();
            try {
                int i = indexOf(task);
                if (i < 0) {
                    return false;
                }
                //从数列中进行移除
                setIndex(queue[i], -1);
                int s = --size;
                //按照堆的移除方式进行移除操作
                Runnable replace = queue[s];
                //方便进行GC操作
                queue[s] = null;
                if (s != i) {
                    //从索引i的位置先向下进行整理
                    shiftDown(i, replace);
                    if (queue[i] == replace) {
                        //从索引i的位置先向上进行整理
                        shiftUp(i, (Task) replace);
                    }
                }
                return true;
            } finally {
                lock1.unlock();
            }
        }

        /**
         * 插入元素
         *
         * @param runnable
         * @return
         */
        @Override
        public boolean offer(Runnable runnable) {
            if (runnable == null) {
                throw new NullPointerException("null");
            }
            ReentrantLock lock1 = this.lock;
            lock1.lock();
            try {
                int i = size;
                //容量超了之后进行扩容
                if (i >= queue.length) {
                    grow();
                }
                //容量的游标位置
                size = i + 1;
                if (i == 0) {
                    queue[0] = runnable;
                    setIndex(runnable, 0);
                } else {
                    shiftUp(i, (Task) runnable);
                }
                if (queue[0] == runnable) {
                    leader = null;
                    available.signal();
                }
            } finally {
                lock1.unlock();
            }
            return true;
        }

        /**
         * 弹出元素
         *
         * @return
         */
        @Override
        public Runnable poll() {
            ReentrantLock lock1 = this.lock;
            lock1.lock();
            try {
                Runnable runnable = queue[0];
                return fishPoll(runnable);
            } finally {
                lock1.unlock();
            }
        }

        private Runnable fishPoll(Runnable runnable) {
            int s = --size;
            Runnable replace = queue[s];
            //help GC
            queue[s] = null;
            if (s != 0) {
                shiftDown(0, replace);
            }
            setIndex(runnable, -1);
            return runnable;
        }

        @Override
        public Runnable peek() {
            ReentrantLock lock1 = this.lock;
            lock1.lock();
            Runnable runnable;
            try {
                runnable = queue[0];
            } finally {
                lock1.unlock();
            }
            return runnable;
        }

        @Override
        public void put(Runnable runnable) throws InterruptedException {

        }

        @Override
        public boolean offer(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        /**
         * 阻塞取
         *
         * @return
         * @throws InterruptedException
         */
        @Override
        public Runnable take() throws InterruptedException {
            ReentrantLock lock1 = this.lock;
            lock1.lockInterruptibly();
            try {
                for (; ; ) {
                    Runnable runnable = queue[0];
                    if (runnable == null) {
                        available.await();
                    }
                    if (leader != null) {
                        available.await();
                    } else {
                        Thread thread = Thread.currentThread();
                        leader = thread;
                    }
                }
            } finally {
                if (leader == null && queue[0] != null) {
                    available.signal();
                }
                lock1.unlock();
            }
        }

        @Override
        public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
            return null;
        }

        /**
         * 返回剩余的容量，剩余的容量永远都是Integer.MAX_VALUE
         *
         * @return
         */
        @Override
        public int remainingCapacity() {
            return Integer.MAX_VALUE;
        }

        @Override
        public int drainTo(Collection<? super Runnable> c) {
            return 0;
        }

        @Override
        public int drainTo(Collection<? super Runnable> c, int maxElements) {
            return 0;
        }

        @Override
        public Iterator<Runnable> iterator() {
            return null;
        }

        @Override
        public int size() {
            ReentrantLock lock1 = this.lock;
            lock1.lock();
            int s;
            try {
                s = size;
            } finally {
                lock1.unlock();
            }
            return s;
        }
    }
}
