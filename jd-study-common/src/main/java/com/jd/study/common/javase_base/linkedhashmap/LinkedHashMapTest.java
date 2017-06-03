package com.jd.study.common.javase_base.linkedhashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dushuangcheng
 * @create 2017-05-28 15:29
 */
public class LinkedHashMapTest {
    private static Map<String,Integer> map=new LinkedHashMap<>(20,0.75f,true);

    public static  void init(){

        for(int i=0;i<20;i++){
            map.put("i"+i,i);
        }
    }
    public static void main(String[] args) {
        LinkedHashMapTest.init();
        Map<String, Integer> map1 = LinkedHashMapTest.map;
        Integer i3 = map1.get("i3");
        Integer i31 = map1.get("i3");
        System.out.println("++++++"+i31);
        System.out.println("-----------"+map1.get("i3"));

    }
}
