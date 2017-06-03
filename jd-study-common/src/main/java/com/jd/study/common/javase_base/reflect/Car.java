package com.jd.study.common.javase_base.reflect;/**
 * Created by dushuangcheng on 2016/10/18.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-18 17:02
 */
public class Car {
    private Integer num;
    private String name;
    private Double weight;

    public  Double getNum(Integer num,Double weight){
        return  num+weight;
    }

    public Car() {
    }

    public Car(Integer num, String name, Double weight) {
        this.num = num;
        this.name = name;
        this.weight = weight;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Car{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
