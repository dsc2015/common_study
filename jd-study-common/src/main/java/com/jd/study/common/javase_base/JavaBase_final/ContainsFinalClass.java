package com.jd.study.common.javase_base.JavaBase_final;/**
 * Created by dushuangcheng on 2017/1/16.
 */

import java.util.Map;

/**
 * @author dushuangcheng
 * @create 2017-01-16 16:16
 */
public class ContainsFinalClass {
    public static final Integer a = 15;
    public final int b = 34;
    public static long c = 100;
    public volatile long d=2;

    public long getIncrease() {
        return c++;
    }

    public long getD() {
        return d;
    }

    public void setD(long d) {
        this.d = d;
    }

    public static Integer getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public static long getC() {
        return c;
    }

    public static void setC(long c) {
        ContainsFinalClass.c = c;
    }
}
