package com.jd.study.common.javase_base.IntegerTest;/**
 * Created by dushuangcheng on 2016/10/31.
 */

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dushuangcheng
 * @create 2016-10-31 10:05
 */
public class IntegerReflect {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //获取内部类
        Class cache = Integer.class.getDeclaredClasses()[0];
        //获取内部类中的名字叫做cache的域
        Field cache1 = cache.getDeclaredField("cache");
        //设置为可访问的
        cache1.setAccessible(true);
        //从反射的对象中取出该属性对应的属性值。
        Integer[]  integerCache =(Integer[]) cache1.get(cache);
        System.out.println(integerCache[132]);
        integerCache[132]=integerCache[133];
        System.out.println(integerCache[133]);
        int a=2;
        int b=a+a;
        System.out.printf("%d + %d = %d", a, a, b);


        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(3);
        Integer[] integers = list.toArray(new Integer[list.size()]);

    }
}
