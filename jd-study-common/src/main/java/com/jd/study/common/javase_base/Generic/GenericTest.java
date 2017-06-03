package com.jd.study.common.javase_base.Generic;/**
 * Created by dushuangcheng on 2016/10/18.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 测试关于泛型数组的知识
 * 数组的类型不可以是类型变量，除非是采用通配符的形式,但是需要进行强制类型转换
 * 声明为非泛型的话，可以不用强制装换。
 * @author dushuangcheng
 * @create 2016-10-18 20:17
 */

public class GenericTest<T> {
    private static Integer b;
    //private static T generic;
    public static void main(String[] args) {
       // List<String>[] arrayLists=new ArrayList<String>[3];
       // List<String>[] arrayLists=new ArrayList[3];
       List<?>[] arrayLists=new ArrayList<?>[3];
        Object o=arrayLists;
        Object[] oa=(Object[])o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Unsound, but passes run time store check
        //String s =  arrayLists[1].get(0); // Run-time error: ClassCastException
        String s = (String) arrayLists[1].get(0);
        System.out.println(s);
    }
}
