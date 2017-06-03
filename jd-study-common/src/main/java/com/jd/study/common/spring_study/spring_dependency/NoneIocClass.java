package com.jd.study.common.spring_study.spring_dependency;/**
 * Created by dushuangcheng on 2017/1/10.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dushuangcheng
 * @create 2017-01-10 10:28
 *
 * 类未纳入容器管理，但是依赖了一个容器对象
 */

public class NoneIocClass {
    @Autowired
    private DemoService demoService;

    public String getName(){
        return demoService.getName();
    }

    public DemoService getDemoService() {
        return demoService;
    }

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    //测试
    public static void main(String[] args) {
        NoneIocClass noneIocClass=new NoneIocClass();
        String name = noneIocClass.getName();
        System.out.println("======================>"+name);
    }
}
