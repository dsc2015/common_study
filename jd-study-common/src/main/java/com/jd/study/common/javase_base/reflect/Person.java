package com.jd.study.common.javase_base.reflect;/**
 * Created by dushuangcheng on 2016/10/18.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-18 16:59
 */
public class Person {
    private Integer age;
    private String name;
    private Long distance;


    public String reflectMethod1(Car car,String name){
        Integer num=200;
        Double num1=300D;
        Double num2 = car.getNum(num, num1);
        return num2+"   "+name;
    }

    public Person() {
    }

    public Person(Integer age, String name, Long distance) {
        this.age = age;
        this.name = name;
        this.distance = distance;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}
