package com.jd.study.common.spring_study.spring_extends;/**
 * Created by dushuangcheng on 2016/11/2.
 */

import org.springframework.stereotype.Component;

/**
 * @author dushuangcheng
 * @create 2016-11-02 10:04
 */
@Component
public class SubClass extends AbstractFather {
    @Override
    public void init(){
        System.out.println("子类初始化开始。。。。");
    }
}
