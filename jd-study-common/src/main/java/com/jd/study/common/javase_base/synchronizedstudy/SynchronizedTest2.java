package com.jd.study.common.javase_base.synchronizedstudy;/**
 * Created by dushuangcheng on 2017/6/22.
 */

/**
 * 线程不安全
 *
 * 1，Synchronized方法
 * 注意要理解对象监视器的概念和从线程的角度去理解互斥与同步的概念
 *
 * ①，对于synchronized修饰的非static方法，主要是为了确保多个线程访问这个类的同一个对象实例的synchronized方法的线程安全
 * 和可见性
 * 对象监视器用的是当前对象，也就是调用该方法的对象
 * 若能够保证在多个线程在操作这个方法的时候保证对象监视器是不变的，那么就能够保证线程安全性。
 * ②，对于synchronized修饰的static方法，对象监视器用的是哪个呢？有什么意义呢
 *
 * static sync 可以防止多个线程同时访问这个类的sync的代码
 *
 * ③synchronized是不能继承的，所以需要在子类中显式地加上这个关键字
 *
 *
 *
 *
 Thread-0++++10
 Thread-9++++20
 Thread-9++++30
 Thread[Thread-9,5,main]=====================50
 Thread-8++++40
 Thread-8++++50
 Thread[Thread-8,5,main]=====================90
 Thread-7++++60
 Thread-7++++70
 Thread[Thread-7,5,main]=====================130
 Thread-6++++80
 Thread-6++++90
 Thread[Thread-6,5,main]=====================170
 Thread-5++++100
 Thread-5++++110
 Thread[Thread-5,5,main]=====================210
 Thread-4++++120
 Thread-4++++130
 Thread[Thread-4,5,main]=====================250
 Thread-3++++140
 Thread-3++++150
 Thread[Thread-3,5,main]=====================290
 Thread-2++++160
 Thread-2++++170
 Thread[Thread-2,5,main]=====================330
 Thread-1++++180
 Thread-1++++190
 Thread[Thread-1,5,main]=====================370
 Thread-0++++200
 Thread[Thread-0,5,main]=====================210
 * @author dushuangcheng
 * @create 2017-06-22 21:14
 */
public class SynchronizedTest2 {
    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        //InnerClass innerClass2 = new InnerClass();
        //===========================test1=====================================
        /*TestWorker1 testWorker1 = new TestWorker1(innerClass);
        for (int i = 0; i < 10; i++) {
            new Thread(testWorker1).start();
        }*/
       //===============================test2========================================
        TestWorker2 testWorker2 = new TestWorker2(innerClass);
       /* for (int i = 0; i < 10; i++) {
            new Thread(testWorker2).start();
        }*/
        //=================================test3=======================================
        TestWorker3 testWorker3 = new TestWorker3(innerClass);
        /*for (int i = 0; i < 10; i++) {
            new Thread(testWorker3).start();
        }*/
        //==================================test4=======================================
        for (int i = 0; i < 5; i++) {
            new Thread(testWorker3).start();
            new Thread(testWorker2).start();
        }
    }

    static class InnerClass {
        private static  int a = 0;
        public synchronized int incr1() {
            for (int i = 0; i < 10; i++) {
                a++;
            }
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"incr1++++"+a);
            return a;
        }

        public static synchronized int incr2() {

            for (int i = 0; i < 10; i++) {
                a++;
            }
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"incr2++++"+a);
            return a;
        }

       /* public boolean equal(InnerClass innerClass){
            return this.equals(innerClass);
        }*/
    }
//=======================================测试任务类==================================================
    static class TestWorker1 implements Runnable {
        private InnerClass innerClass;

        public TestWorker1(InnerClass innerClass) {
            this.innerClass = innerClass;
        }

        @Override
        public void run() {
            int result = innerClass.incr1() + InnerClass.incr2();
            System.out.println(Thread.currentThread() + "===========1==========" + result);
        }
    }

    static class TestWorker2 implements Runnable {
        private InnerClass innerClass;

        public TestWorker2(InnerClass innerClass) {
            this.innerClass = innerClass;
        }

        @Override
        public void run() {
            int result = innerClass.incr1();
            System.out.println(Thread.currentThread() + "==========2===========" + result);
        }
    }

    static class TestWorker3 implements Runnable {
        private InnerClass innerClass;

        public TestWorker3(InnerClass innerClass) {
            this.innerClass = innerClass;
        }

        @Override
        public void run() {
            int result =  InnerClass.incr2();
            System.out.println(Thread.currentThread() + "===========3==========" + result);
        }
    }
}
