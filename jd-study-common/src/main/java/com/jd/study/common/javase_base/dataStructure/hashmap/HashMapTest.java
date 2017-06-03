package com.jd.study.common.javase_base.dataStructure.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 1,验证遍历的效率问题
 *
 * @author dushuangcheng
 * @create 2017-05-28 12:20
 */
public class HashMapTest {
    public static Map<String, Integer> map = new HashMap<>();

    static void init() {
        for(int i=0;i<1000000;i++)
        map.put("a"+i,i);
    }

    public static void main(String[] args) {
        System.out.println("======================="+HashMapTest.search1());

    }


    public static long search1(){
        long start = System.currentTimeMillis();
        HashMapTest.init();
        Map<String, Integer> map1 = HashMapTest.map;
        Set<Map.Entry<String, Integer>> entries = map1.entrySet();
        for(Map.Entry<String, Integer> entry:entries){
            entry.getKey();
            entry.getValue();
        }
        long end = System.currentTimeMillis();

        return end-start;
    }

    public static long search2(){
        long start = System.currentTimeMillis();
        HashMapTest.init();
        Map<String, Integer> map1 = HashMapTest.map;
        Set<String> strings = map1.keySet();
        for(String str:strings){
            map1.get(str);
        }
        long end = System.currentTimeMillis();
        return end-start;
    }
}
