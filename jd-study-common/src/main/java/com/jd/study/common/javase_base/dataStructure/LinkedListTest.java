package com.jd.study.common.javase_base.dataStructure;

import java.util.LinkedList;

/**
 * @description linkedTest
 * @author dushuangcheng
 * @create 2017/2/28
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> strList=new LinkedList<>();
        strList.add("aaa");
        strList.add("bbb");
        strList.add("ccc");
        strList.add("ddd");

        System.out.println(strList);

        int a=2147483647;int b=3;
        System.out.println(a+b);
    }
}
