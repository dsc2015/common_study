package com.jd.study.common.javase_base.multithread.spuriouswakeup;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/3/8
 */
public class Thread1 extends Thread {
    private SpuriousWakeup s;

    public SpuriousWakeup getS() {
        return s;
    }

    public void setS(SpuriousWakeup s) {
        this.s = s;
    }

    public void run(){
        try {
            s.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
