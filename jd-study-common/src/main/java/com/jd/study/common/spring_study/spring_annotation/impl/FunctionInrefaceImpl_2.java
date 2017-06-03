package com.jd.study.common.spring_study.spring_annotation.impl;/**
 * Created by dushuangcheng on 2016/10/15.
 */

import com.jd.study.common.spring_study.spring_annotation.FunctionInterface;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dushuangcheng
 * @create 2016-10-15 17:48
 */
@Component("functionInterface2")
public class FunctionInrefaceImpl_2 implements FunctionInterface {
    @Override
    public String getName() {
        return "spring annotation....";
    }

    @Override
    public void setDate(Date date) {
        System.out.println("date============>"+date.after(new Date()));
    }
}
