package com.jd.study.common.javase_base.toString;/**
 * Created by dushuangcheng on 2016/10/20.
 */

import sun.applet.Main;

/**
 * @author dushuangcheng
 * @create 2016-10-20 21:33
 */
public class Test {
    public static void main(String[] args) {
        Child child=new Child();
        child.setName("dsc");
        child.setWeight(100);
        child.setAge(25);

        System.out.println(child);
    }
}
