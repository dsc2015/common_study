package com.jd.study.common.javase_base.multithread.instancelock;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dushuangcheng
 * @description 带有上下界的非线程安全的类
 * @create 2017/3/24
 */
public class NumberRange {
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) throws Exception {
        //典型的先检查后运行的错误
        if (i > upper.get()) {
            System.out.println(upper.get() + "-----------------" + lower.get());
            throw new Exception("下界不能大于上界");
        }
        lower.set(i);
    }

    public void setUpper(int i) throws Exception {
        System.out.println(upper.get() + "-----------------" + lower.get());
        if (i < lower.get()) {
            throw new Exception("上界不能小于下界");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return i >= lower.get() && i <= upper.get() ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(Math.abs(new Random().nextInt(100)));
    }
}
