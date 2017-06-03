package com.jd.study.common.javase_base.TimerAndTask;/**
 * Created by dushuangcheng on 2016/10/27.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-27 11:20
 */
public class ProviderFunction {
    private String name;
    private Integer age;

    public ProviderFunction(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public ProviderFunction() {
    }

    //第一个功能：用于线程的测试
    public long getSum(Integer age) throws InterruptedException {
        long sum=0L;
        for(int i=0;i<1000000;i++){
            sum+=(i+age);
            Thread.sleep(100);
        }
        return  sum;
    }
}
