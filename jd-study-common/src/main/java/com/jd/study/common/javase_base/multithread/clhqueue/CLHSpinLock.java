package com.jd.study.common.javase_base.multithread.clhqueue;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description 实现的自旋锁 谓自旋锁简单来说就是线程通过循环来等待而不是睡眠
 * 关键不同就是next指针，这是因为AQS中线程不是一直在自旋的，而可能会反复的睡眠和唤醒，
 * 这就需要前继释放锁的时候通过next 指针找到其后继将其唤醒，
 * 也就是AQS的等待队列中后继是被前继唤醒的。AQS结合了自旋和睡眠/唤醒两种方法的优点。
 *
 *
 *
 * 当一个线程需要获取锁时，会创建一个新的QNode，将其中的locked设置为true表示需要获取锁，然后线程对tail域
 * 调用getAndSet方法，使自己成为队列的尾部，同时获取一个指向其前趋的引用myPred,然后该线程就在前趋结点
 * 的locked字段上旋转，直到前趋结点释放锁。当一个线程需要释放锁时，将当前结点的locked域设置为false，同时回收前趋结点。
 * 如下图所示，线程A需要获取锁，其myNode域为true，些时tail指向线程A的结点，然后线程B也加入到线程A后面，
 * tail指向线程B的结点。然后线程A和B都在它的myPred域上旋转，一量它的myPred结点的locked字段变为false，它就可以获取锁扫行。
 * 明显线程A的myPred locked域为false，此时线程A获取到了锁。
 *
 *
 *
 * @author dushuangcheng
 * @create 2017/6/11
 */
public class CLHSpinLock {
    public static void main(String[] args) throws InterruptedException {
        final CLHSpinLock lock = new CLHSpinLock();
        lock.lock();
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId()+"===="+lock.node.get());
                    System.out.println(Thread.currentThread().getId() + " begin lock!");
                    lock.lock();
                    System.out.println(Thread.currentThread().getId() + " acquired the lock!");
                    lock.unLock();
                    System.out.println(Thread.currentThread().getId() + " release the lock!");
                }
            }).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("main thread unlock!");
        lock.unLock();

       // Thread.sleep(12000);
    }
    private final ThreadLocal<Node> prev;
    private final ThreadLocal<Node> node;
    private final AtomicReference<Node> tail=new AtomicReference<>(new Node());

    public CLHSpinLock() {
        this.prev=new ThreadLocal<Node>(){
            @Override
            protected Node initialValue() {
                return null;
            }
        };

        this.node=new ThreadLocal<Node>(){
            @Override
            protected Node initialValue() {
                return new Node();
            }
        };
    }

    public void lock(){
        //获取当前线程上的node,之前在构造方法里面已经进行初始化了，所以可以直接获取到值的，每一个线程上的值都是false
        Node node1 = this.node.get();
        //将锁标记设置为已经获得锁
        node1.locked=true;
        //将尾部节点设置为当前节点，也就是让尾节点指向当前线程，让当前线程成为尾节点
        // 并返回tail原来指向的节点
        Node pred = this.tail.getAndSet(node1);
        //将前任节点设置为尾节点的旧值,前任已经获取锁的情况下，这个值是true,所以下面进入自旋等待中
        //前任节点指向尾节点之前指向的节点
        this.prev.set(pred);
        while (pred.locked){
            //进入自旋
           // System.out.println("======================"+i++);
        }
    }
    public void unLock(){
        //当前线程获取node
        Node node1 = this.node.get();
        node1.locked=false;
        this.node.set(this.prev.get());
    }
    static class Node{
        private volatile boolean locked;

       @Override
        public String toString() {
            return "Node{" +
                    "locked=" + locked +
                    '}';
        }
    }
}
