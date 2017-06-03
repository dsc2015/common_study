package com.jd.study.common.javase_base.multithread.ThreadWithMoreStateTest;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description 一种可以分解为两个相等整数的类
 * @author dushuangcheng
 * @create 2017/3/14
 */
public class Factor {
    private final AtomicReference<Integer> lastNumber=new AtomicReference<>();
    private final AtomicReference<Integer[]> bigIntegerFactor=new AtomicReference<>();

    public Integer doService(Integer num){
        if(num.equals(lastNumber.get())){
            Integer[] integers = bigIntegerFactor.get();
            return integers[0];
        }
        Integer factor = getFactor(num);
        Integer[] integers = bigIntegerFactor.get();
        integers[0]=factor;
        lastNumber.set(num);
        return factor;

    }
    private Integer getFactor(Integer number){
        if(number%2==0){
            return number/2;
        }
        return (number/2)+1;
    }
}
