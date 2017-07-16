package com.jd.study.common.javase_base.subsuppperclass;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/6/28
 */
public class SupperClass {
    private int a=10;
    private String b="dsc";
    public int  method0(int var1,String var2){
        System.out.println("begin to invoke Supper");
        int i = method1(var1);
        String s = method2(var2);
        System.out.println("+++++++"+i+"&&&&&&&"+s);
        return i;
    }

    int method1(int a){
        return a++;
    }

    String method2(String b){
        return b+"dgc";
    }

}
