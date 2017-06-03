package com.jd.study.common.javase_base.reflect;/**
 * Created by dushuangcheng on 2016/10/18.
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author dushuangcheng
 * @create 2016-10-18 17:06
 * <p/>
 * 利用反射调用方法
 */
public class TestMain {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        Car car = new Car();
        car.setName("宝马");
        car.setNum(100111111);
        car.setWeight(1000D);
        //先获取Class对象
        Class personCls = Person.class;
        //获取方法
        Method reflectMethod1 = personCls.getMethod("reflectMethod1", Car.class, String.class);
        Object invoke = reflectMethod1.invoke(person, car, "hello world!");
        System.out.println("==========================>" + invoke);
    }
}
