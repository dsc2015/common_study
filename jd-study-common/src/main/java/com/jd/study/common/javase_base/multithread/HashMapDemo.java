package com.jd.study.common.javase_base.multithread;/**
 * Created by dushuangcheng on 2017/3/20.
 */

/**
 * @author dushuangcheng
 * @create 2017-03-20 11:41
 */
public class HashMapDemo implements Runnable {

    public static Integer integer = new Integer(0);

    static  HashMapDemo hashMapDemo = new HashMapDemo();

    @Override
    public void run() {
        for (int i = 0 ; i<1000000;i++){
            synchronized (this){
                integer++;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(hashMapDemo);
        Thread t2 = new Thread(hashMapDemo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(integer);
    }
}
