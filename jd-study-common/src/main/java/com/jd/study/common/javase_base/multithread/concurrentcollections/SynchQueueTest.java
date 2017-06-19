package com.jd.study.common.javase_base.multithread.concurrentcollections;/**
 * Created by dushuangcheng on 2017/6/15.
 */

import java.util.concurrent.SynchronousQueue;

/**
 * @author dushuangcheng
 * @create 2017-06-15 17:28
 */
public class SynchQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);
        boolean offer = queue.offer(2);
        Integer poll = queue.poll();
        System.out.println(poll);
        System.out.println(offer);
        boolean offer1 = queue.offer(3);
        System.out.println(offer1);
    }
}
