package com.jd.study.common.javase_base.multithread.spuriouswakeup;
/**
 * @description 
 * @author dushuangcheng
 * @create 2017/3/8
 */
public class Test {
    private int num=2;
    //构造器
    public Test(int num) {
        this.num=num;
        System.out.println(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println( "======="+Test.this.getNum());
                Test.this.testMethod();
            }
        }).start();
    }
    public void testMethod(){
        this.num=100;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        System.out.println(new Test(33).getNum());
        SpuriousWakeup spuriousWakeup = new SpuriousWakeup();
        Thread1 thread1 = new Thread1();
        thread1.setS(spuriousWakeup);
        thread1.start();

        Thread1 thread2 = new Thread1();
        thread2.setS(spuriousWakeup);
        thread2.start();

        int i = Runtime.getRuntime().availableProcessors();
       // Runtime.getRuntime().
        System.out.println(i);
    }
}
