package com.jd.study.common.spring_study.spring_extends;/**
 * Created by dushuangcheng on 2016/11/2.
 */

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dushuangcheng
 * @create 2016-11-02 9:51
 */

public class Father {
    private Integer age;
    private String name;
    private Date birthday;

    public String getName1(Integer age){
        return age.toString();
    }
}
