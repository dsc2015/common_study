package com.jd.study.common.spring_study;/**
 * Created by dushuangcheng on 2016/12/27.
 */

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抽象类依赖一个非抽象类的属性
 * @author dushuangcheng
 * @create 2016-12-27 17:18
 */
public abstract class AbstractClass {
    @Autowired
    private BuyService buyService;

    public abstract String getName();

    public String getAddress(){
        return buyService.getDesc();
    }

}
