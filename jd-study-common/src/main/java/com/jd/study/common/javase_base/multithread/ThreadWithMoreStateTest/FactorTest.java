package com.jd.study.common.javase_base.multithread.ThreadWithMoreStateTest;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/3/14
 */
public class FactorTest implements Runnable {
    private Factor factor;

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public static void main(String[] args) {
        Factor factor1 = new Factor();
        FactorTest factorTest = new FactorTest();
        factorTest.setFactor(factor1);

        //启动线程
        new Thread(factorTest).start();
    }

    @Override
    public void run() {
        Integer integer = factor.doService(100);
        System.out.println("---------------->"+integer);
    }
}
