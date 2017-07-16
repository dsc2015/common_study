package com.jd.study.common.javase_base.collection_test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dushuangcheng
 * @description java.lang.IllegalStateException异常
 * @create 2017/6/22
 */
public class ConcurrentModeficationTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        new Thread(new IteratorThread(iterator)).start();
        new Thread(new IteratorThread(iterator)).start();
    }

    static class IteratorThread implements Runnable {
        private Iterator iterator;

        public IteratorThread(Iterator iterator) {
            this.iterator = iterator;
        }

        @Override
        public void run() {
            while (iterator.hasNext()) {
                System.out.println(Thread.currentThread().getName() + "======================" + iterator.next());
                iterator.remove();
                try {
                    Thread.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
