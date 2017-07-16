package com.jd.study.common.javase_base.classloaderstudy;

/**
 * 这个构造器在执行个过程中并没有调用构造器进行初始化的操作
 *
 * @author dushuangcheng
 * @description 主要测试类的加载顺序问题
 * @create 2017/6/28
 */
public class ClassLoaderOrderStudy {
    public static void main(String[] args) {
        System.out.println("after init a=" + a);
        System.out.println("after init b=" + b);
    }

    static ClassLoaderOrderStudy c = new ClassLoaderOrderStudy();
    static int a;
    static int b = 0;

    public ClassLoaderOrderStudy() {
        a = 10;
        b = a;
        System.out.println("before init a=" + a);
        System.out.println("before init b=" + b);
    }


    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        ClassLoaderOrderStudy.a = a;
    }

    public static int getB() {
        return b;
    }

    public static void setB(int b) {
        ClassLoaderOrderStudy.b = b;
    }


}
