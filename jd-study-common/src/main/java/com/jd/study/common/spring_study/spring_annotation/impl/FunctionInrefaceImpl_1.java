package com.jd.study.common.spring_study.spring_annotation.impl;/**
 * Created by dushuangcheng on 2016/10/15.
 */

import com.jd.study.common.spring_study.spring_annotation.FunctionInterface;
import com.jd.study.common.spring_study.spring_annotation.Person;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dushuangcheng
 * @create 2016-10-15 17:45
 */
@Component("functionInterface")
//("functionInterface1")("functionInterface")
public class FunctionInrefaceImpl_1 implements FunctionInterface {
    @Override
    public String getName() {
        return "spring test";
    }

    @Override
    public void setDate(Date date) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        Person person=new Person();
        person.setName("xiao");
        System.out.println("date......."+format);
    }
}
