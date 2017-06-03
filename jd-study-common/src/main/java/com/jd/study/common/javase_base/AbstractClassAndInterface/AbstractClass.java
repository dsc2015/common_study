package com.jd.study.common.javase_base.AbstractClassAndInterface;/**
 * Created by dushuangcheng on 2016/11/2.
 */

/**
 * @author dushuangcheng
 * @create 2016-11-02 9:27
 *
 * 抽象类实现接口可不重写接口中的方法。而且可以直接调用
 */
public abstract class AbstractClass implements Function {
    protected void getMethod(){
        Integer order=5;
        this.getName(order);
    }
}
