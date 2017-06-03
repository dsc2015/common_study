package com.jd.study.common.spring_study.spring_aop.jdk_proxy;/**
 * Created by dushuangcheng on 2017/1/10.
 */

/**
 * @author dushuangcheng
 * @create 2017-01-10 15:17
 */
public class FunctionImpl implements Function {
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return "dsc";
    }

    @Override
    public void say(String str) {
        System.out.println(str);
    }

    @Override
    public int calculate(int i, int k) {
        return i/k;
    }


}
