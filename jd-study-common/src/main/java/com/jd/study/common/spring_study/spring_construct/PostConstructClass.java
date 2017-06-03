package com.jd.study.common.spring_study.spring_construct;/**
 * Created by dushuangcheng on 2017/1/19.
 */

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author dushuangcheng
 * @create 2017-01-19 18:26
 */
//@Component
public class PostConstructClass implements InitializingBean {
    @PostConstruct
    public void init(){
        System.out.println("postConstruct begin...");
    }

    public void init2 (){
        System.out.println("init method....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println( "afterPropertiesSet...");
    }
}
