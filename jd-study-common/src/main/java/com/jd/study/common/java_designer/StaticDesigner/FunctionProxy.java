package com.jd.study.common.java_designer.StaticDesigner;/**
 * Created by dushuangcheng on 2016/10/26.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-26 9:28
 * 1,弊端是每次只能代理一个对象不够灵活，因为在代码中已经写死了。可以考虑使用动态代理
 * 2,考虑功能的扩展：代理模式是能够实现功能的扩展，但是是写死的，每次只能扩展一种功能，不够灵活
 */
public class FunctionProxy implements FunctionInterface{
    //组合一个接口类型的对象方便实现代理
    private FunctionInterface functionInterface;

    //构造器中进行初始化，代理了一个消费者的对象
    public FunctionProxy() {
        this.functionInterface = new Consumer_one();
    }

    @Override
    public void findHouse(String address, double price) {
        functionInterface.findHouse(address,price);
    }

    @Override
    public String getHouseInfo() {
        return functionInterface.getHouseInfo();
    }
}
