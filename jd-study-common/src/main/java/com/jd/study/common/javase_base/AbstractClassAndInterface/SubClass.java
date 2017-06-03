package com.jd.study.common.javase_base.AbstractClassAndInterface;/**
 * Created by dushuangcheng on 2016/11/2.
 */

/**
 * @author dushuangcheng
 * @create 2016-11-02 9:30
 * 子类继承抽象类同时不仅要重写抽象类中的抽象方法而且也要重写
 * 抽象类实现接口的时候未有重写的方法。
 * subclass 子类
 */
public class SubClass extends AbstractClass {
    @Override
    public String getName(Integer order) {
        return null;
    }
}
