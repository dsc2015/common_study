package com.jd.study.common.spring_study.spring_aop;/**
 * Created by dushuangcheng on 2016/11/7.
 */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author dushuangcheng
 * @create 2016-11-07 10:05
 */
public class DemoClass implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return null;
    }
}
