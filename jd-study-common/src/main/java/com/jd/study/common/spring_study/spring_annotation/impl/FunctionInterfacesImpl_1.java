package com.jd.study.common.spring_study.spring_annotation.impl;/**
 * Created by dushuangcheng on 2016/10/15.
 */

import com.jd.study.common.spring_study.spring_annotation.FunctionInterfaces;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dushuangcheng
 * @create 2016-10-15 18:01
 */
@Component("functionInterfaces")
public class FunctionInterfacesImpl_1 implements FunctionInterfaces {
    @Override
    public Date getAge() {
        return new Date();
    }

    @Override
    public void setName() {
        System.out.println("name=========");
    }
}
