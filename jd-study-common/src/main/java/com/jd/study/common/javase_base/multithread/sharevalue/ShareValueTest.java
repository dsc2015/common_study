package com.jd.study.common.javase_base.multithread.sharevalue;
/**
 * @description 测试共享变量
 * @author dushuangcheng
 * @create 2017/3/15
 */
public class ShareValueTest {
    private static int num;
    private static boolean flag;

    static class TestThread implements Runnable{
        @Override
        public void run() {
            while (!flag){
                //使当前线程从执行状态（运行状态）变为可执行态（就绪状态）
                //Thread.yield();
            }
            System.out.println(num);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new TestThread()).start();
        Thread.sleep(4000);
         num=100;
        flag=true;
    }
}
