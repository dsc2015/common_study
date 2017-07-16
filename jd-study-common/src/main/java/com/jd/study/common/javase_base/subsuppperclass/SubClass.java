package com.jd.study.common.javase_base.subsuppperclass;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/6/28
 */
public class SubClass  extends SupperClass{

    private int a=100;
    private String b="xxxx";

    public int  method0(int var1,String var2){
        super.method0(var1,var2);
        System.out.println("================================");
        System.out.println("sub-class invoke end...");
        return a+10000000;
    }

    int method1(int a){
        return a+1000;
    }

    String method2(String b){
        return b+"YYY";
    }
}
