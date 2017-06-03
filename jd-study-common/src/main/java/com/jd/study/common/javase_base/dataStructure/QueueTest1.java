package com.jd.study.common.javase_base.dataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description 队列  FIFO,新元素被插在队尾巴处
 *  In a FIFO queue, all new elements are inserted at
 * the <em> tail</em> of the queue. Other kinds of queues may use
 * different placement rules.
 * 1,提供了两组方法，一个可以抛出异常，另外一个则不能抛出异常
 *
 * 2，方法列表如下：插入：add,offer
 *    删除：remove,poll
 *3,检查：element();peek()，头部元素
 *
 *
 * @author dushuangcheng
 * @create 2017/2/28
 */
public class QueueTest1 {
    private QueueTest1 queueTest1;

    public QueueTest1(QueueTest1 queueTest1) {
        this.queueTest1 = queueTest1;
    }

    public static void main(String[] args) {

        //Stack
        Queue<String> strQueue=new ArrayBlockingQueue<String>(7);
        strQueue.add("a");
        strQueue.add("b");
        strQueue.add("c");
        strQueue.add("d");

        System.out.println(strQueue.size());
        System.out.println(strQueue.element());
        System.out.println(strQueue.peek());
        System.out.println(strQueue.offer("b"));
        System.out.println(strQueue.remove("a"));
        System.out.println(strQueue.poll());

        System.out.println(strQueue);



    }
}
