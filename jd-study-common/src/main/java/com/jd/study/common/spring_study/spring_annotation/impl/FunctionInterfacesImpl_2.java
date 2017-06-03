package com.jd.study.common.spring_study.spring_annotation.impl;/**
 * Created by dushuangcheng on 2016/10/15.
 */

import com.jd.study.common.spring_study.spring_annotation.FunctionInterfaces;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dushuangcheng
 * @create 2016-10-15 18:06
 */
@Component("functionInterfaces2")
public class FunctionInterfacesImpl_2 implements FunctionInterfaces {
    @Override
    public Date getAge() {
        return new Date();
    }

    @Override
    public void setName() {
        System.out.println("FunctionInterfacesImpl_2================name=============");
    }
}
