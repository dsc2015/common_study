package com.jd.study.common.javase_base.multithread.spuriouswakeup;

/**
 * @author dushuangcheng
 * @description 虚假唤醒
 * 1,首先wait,notify是要与synchronized一起使用的，而不能单独使用
 * @create 2017/3/8
 */
public class SpuriousWakeup {
    private Object o=new Object();
    private int i=1;

    public void execute() throws InterruptedException {
        //使用的是同一个对象监视器
        synchronized (o) {
           for(int j=0;j<5;j++){
               //int i = 1;
               if(i == 1) {
                   o.notifyAll();
                   i++;
                   System.out.println("当前执行的线程是：" + Thread.currentThread().getName() + "A");
                   o.wait();
               }
               if (i == 2) {
                   o.notifyAll();
                   i--;
                   System.out.println("当前执行的线程是：" + Thread.currentThread().getName()+"B");
                   o.wait();
               }

          /*  if (i == 3) {
                System.out.println("C");
                i--;
                SpuriousWakeup.class.notifyAll();
            }*/
           }
        }
    }
}
