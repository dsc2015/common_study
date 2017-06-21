package com.jd.study.common.javase_base.collection_test;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @description:这段代码主要测试hashMap的的put方法的执行过程
 * 在put方法是执行过程中链表的产生主要是新来是节点位于链表的头部，
 * 也就是非常靠近链表的位置进行存放，而不是往后面去，注意这是之前的认知误区！
 *
 * 2,删除的时候table数组并不会移动，只是把这个位置上的值置为null
 * @author dushuangcheng
 * @create 2017/6/20
 */
public class HashMapDome {
    public static void main(String[] args) throws NoSuchFieldException {
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("a",1);
        map1.put("b",2);
        map1.put("c",3);
        map1.put("4",4);
        map1.remove("c");
        System.out.println();
        HashMap<Person, String> map = new HashMap<Person, String>(3);
        map.put(new Person(1,"aaa"),"a");
        map.put(new Person(2,"bbb"),"b");
        map.put(new Person(3,"ccc"),"c");
        /*Field table = map.getClass().getDeclaredField("table");
        table.setAccessible(true);
        System.out.println(table);*/
        System.out.println(map);
    }

    static class Person{
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        /*@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (age != person.age) return false;
            if (name != null ? !name.equals(person.name) : person.name != null) return false;

            return true;
        }*/

        @Override
        public int hashCode() {
            return 100;
        }
    }
}
