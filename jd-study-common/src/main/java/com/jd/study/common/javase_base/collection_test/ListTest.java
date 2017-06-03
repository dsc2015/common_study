package com.jd.study.common.javase_base.collection_test;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dushuangcheng
 * @create 2017-01-06 15:30
 * List源码：关于list的结构，底层实现
 *1,可以存入null元素
 *
 * List测试1.关于remove方法
 */
public class ListTest {
    public static void main(String[] args) {
        //构造一个list集合
        List<Integer> list=new ArrayList<>();

        //
        Integer a=new Integer(1);
        Integer b=new Integer(3);
        Integer c=new Integer(4);
        Integer d=new Integer(3);
        Integer e=new Integer(3);
        Integer f=new Integer(5);

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(null);

        List<String> strings = Arrays.asList("a", "b", "c");
        //strings.add("k");
        System.out.println(list.size());


    }
}
