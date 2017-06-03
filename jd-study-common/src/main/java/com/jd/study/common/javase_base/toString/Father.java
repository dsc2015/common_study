package com.jd.study.common.javase_base.toString;/**
 * Created by dushuangcheng on 2016/10/20.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-20 21:31
 */
public abstract class Father {
    protected Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Father{" +
                "age=" + age +
                '}';
    }
}
