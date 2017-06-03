package com.jd.study.common.java_designer.DynamicProxy;/**
 * Created by dushuangcheng on 2016/10/26.
 */

import com.jd.study.common.java_designer.StaticDesigner.FunctionInterface;

/**
 * @author dushuangcheng
 * @create 2016-10-26 9:22
 */
public class Consumer_one implements FunctionInterface {
    @Override
    public void findHouse(String address, double price) {
        System.out.println("I want to find the house at:"+address+"price is  :"+price);
    }

    @Override
    public String getHouseInfo() {
        String info="This house must be  very fixed for me!";
        return info;
    }
}
