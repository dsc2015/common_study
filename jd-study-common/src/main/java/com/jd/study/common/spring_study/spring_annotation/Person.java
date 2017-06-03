package com.jd.study.common.spring_study.spring_annotation;/**
 * Created by dushuangcheng on 2016/10/15.
 */

import org.springframework.stereotype.Component;

import java.util.Date;

/**spring的bean的产生是否依赖于反射？
 * 提供空参数的构造器;如果没有空参数的构造器则会报错
 *
 *
 *
 */
/**
 * @author dushuangcheng
 * @create 2016-10-15 16:46
 */
@Component("person")
public class Person {
    private Integer age;
    private String name;
    private Date birthDay;

    public Person() {
    }

    public Person(Integer age, String name, Date birthDay) {
        this.age = age;
        this.name = name;
        this.birthDay = birthDay;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
