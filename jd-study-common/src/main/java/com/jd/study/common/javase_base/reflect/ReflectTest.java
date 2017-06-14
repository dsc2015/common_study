package com.jd.study.common.javase_base.reflect;

import java.lang.reflect.Array;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/6/14
 */
public class ReflectTest {
    public static void main(String[] args) {
        //测试通过反射生成定长的数组
        String[] o = (String[]) Array.newInstance(String.class, 10);
        o[1]="dsc";
        System.out.println(o);
    }
}
