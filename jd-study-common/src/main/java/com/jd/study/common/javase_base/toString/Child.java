package com.jd.study.common.javase_base.toString;/**
 * Created by dushuangcheng on 2016/10/20.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-20 21:32
 */
public class Child extends Father {
    private String name;
    private double weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
