package com.jd.study.common.javase_base.multithread.collectioncontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/3/23
 */
public class CollectionCationer {
    private List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>(10));
    //private List<Integer> list= new ArrayList<Integer>(10);

    public void setData() {
        list.add(Integer.valueOf(1));
    }

    public int incrementData() {
        Integer integer = list.get(0);
        Integer newInt = integer++;
        list.add(0,newInt);
        return integer++;
    }

    public static void main(String[] args) {
        final CollectionCationer collectionCationer = new CollectionCationer();
        collectionCationer.setData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int intValue = 0;
                for (int i = 0; i < 1000; i++) {
                    intValue = collectionCationer.incrementData();
                }
                System.out.println("================" + intValue);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int intValue = 0;
                for (int i = 0; i < 1000; i++) {
                    intValue = collectionCationer.incrementData();
                }
                System.out.println("================" + intValue);
            }
        }).start();

        Thread.yield();

        System.out.println(collectionCationer.list);

    }
}
