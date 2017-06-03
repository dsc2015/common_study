package com.jd.study.common.javase_base.multithread.instancelock;/**
 * Created by dushuangcheng on 2017/3/24.
 */

import java.util.Random;

/**
 * @author dushuangcheng
 * @create 2017-03-24 12:38
 */
public class TestRange {
    public static void main(String[] args) {
        final NumberRange numberRange = new NumberRange();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                int i=Math.abs(new Random().nextInt(100)) ;
                @Override
                public void run() {

                    try {
                        numberRange.setUpper(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                int i=Math.abs(new Random().nextInt(100)) ;
                @Override
                public void run() {

                    try {
                        numberRange.setLower(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
