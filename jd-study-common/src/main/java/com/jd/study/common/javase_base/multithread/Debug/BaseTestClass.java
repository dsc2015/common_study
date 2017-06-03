package com.jd.study.common.javase_base.multithread.Debug;/**
 * Created by dushuangcheng on 2016/10/20.
 */

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dushuangcheng
 * @create 2016-10-20 16:37
 */
public class BaseTestClass {
    private Integer num;
    private String[] name;
    private Date date;

    //测试线程变量
    private static volatile Integer var=100;
   // private static AtomicInteger var=new AtomicInteger(100);

    //a method
    public String[] getStr(Integer num){
        int n=0;
        String[] str=new String[num];
        if(num>=0){
            for (int i=0;i<num;i++){
                str[i]=i+"";
                n++;
            }
        }else {
            return  null;
        }
        var++;
        //int k=var.incrementAndGet();
        System.out.println("n的当前数值为：====================》"+n+"var==="+var);
        return str;

    }
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //查看线程安全的list的实现原理
    public static void main(String[] args) {
        List<Object> objects = Collections.synchronizedList(new ArrayList<Object>());
        Set<String> setDemo=new HashSet<>();
        setDemo.toString();
        Queue<String> queueDemo1=new ConcurrentLinkedQueue<>();
        Queue<String> queueDemo2=new BlockingQueue<String>() {
            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean offer(String s) {
                return false;
            }

            @Override
            public void put(String s) throws InterruptedException {

            }

            @Override
            public boolean offer(String s, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public String take() throws InterruptedException {
                return null;
            }

            @Override
            public String poll(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public int remainingCapacity() {
                return 0;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public int drainTo(Collection<? super String> c) {
                return 0;
            }

            @Override
            public int drainTo(Collection<? super String> c, int maxElements) {
                return 0;
            }

            @Override
            public String remove() {
                return null;
            }

            @Override
            public String poll() {
                return null;
            }

            @Override
            public String element() {
                return null;
            }

            @Override
            public String peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
    }

}
