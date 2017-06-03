package com.jd.study.common.javase_base.netty.study;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import io.netty.util.internal.InternalThreadLocalMap;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/4/17
 *
 * threadLocal相关的信息：首先在每个线程类中都有一个threadLocals的属性值，类型是ThreadLocalMap类型的，也就是说一个线程对应多个
 * ThreadLocal，或者说多个ThreadLocal对应多个thread类，之间是n:m的关系
 *
 * ThreadLocalMap是一种数据结构，主要存储于线程相关的变量，一个线程对应一个ThreadLocalMap类，这个类中的key是ThreadLocal类型的对象，value是存储的线程变量
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        ThreadLocal<String> threadLocal=new ThreadLocal<>();
        ThreadLocal<String> threadLocal2=new ThreadLocal<>();
        threadLocal.set("1000");
        //第二次设置把第一次的值覆盖了,多个值可以封装成一个对象放入
        /**
         *  if (k == key) {
         e.value = value;
         return;
         }
         */
        threadLocal.set("1001");
        threadLocal2.set("9999");
        String s = threadLocal.get();
        String s1 = threadLocal2.get();


        FastThreadLocal fastThreadLocal = new FastThreadLocal();
        fastThreadLocal.set(100);
        Object o = fastThreadLocal.get();
        System.out.println("===================="+o);
        threadLocalTest.run0();
    }


    class MyThread extends FastThreadLocalThread{
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            ThreadLocal<String> threadLocal = new ThreadLocal<String>();
            threadLocal.set("88888");
            System.out.println("---------------------"+ThreadLocalTest.this.getSub(threadLocal));
        }
    }

    public String getSub(ThreadLocal<String> threadLocal){
        return threadLocal.get().substring(0,2);
    }

    public  void run0(){
        MyThread myThread = new MyThread();
        InternalThreadLocalMap internalThreadLocalMap = myThread.threadLocalMap();
        System.out.println("++++++++++++++++++++++++++++++++++++++"+internalThreadLocalMap);
        myThread.start();
    }
}
