package com.jd.study.common.spring_study.spring_extends;/**
 * Created by dushuangcheng on 2016/11/2.
 */

import org.springframework.stereotype.Component;

/**
 * @author dushuangcheng
 * @create 2016-11-02 10:10
 */
@Component
public class SubClass1 extends AbstractFather {
    @Override
    public void init() {
        System.out.println("子类2开始初始化。。。。。");
    }
}
