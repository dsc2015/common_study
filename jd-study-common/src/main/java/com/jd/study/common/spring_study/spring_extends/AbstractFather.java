package com.jd.study.common.spring_study.spring_extends;/**
 * Created by dushuangcheng on 2016/11/2.
 */

import javax.annotation.PostConstruct;

/**
 * @author dushuangcheng
 * @create 2016-11-02 10:01
 */
public abstract class AbstractFather {
   /* @PostConstruct
    public void init(){
        System.out.println("抽象父类初始化开始==================>");
    }*/

    @PostConstruct
    public abstract void init();
}
