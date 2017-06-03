package com.jd.study.common.java_designer.StaticDesigner;/**
 * Created by dushuangcheng on 2016/10/26.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-26 9:26
 */
public class Consumer_two implements FunctionInterface {
    @Override
    public void findHouse(String address, double price) {
        System.out.println("....I want to find a house at:"+address+"the price about:"+price);
    }

    @Override
    public String getHouseInfo() {
        return "need a very beautiful house";
    }
}
