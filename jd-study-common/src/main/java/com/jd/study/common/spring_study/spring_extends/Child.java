package com.jd.study.common.spring_study.spring_extends;/**
 * Created by dushuangcheng on 2016/11/2.
 */

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dushuangcheng
 * @create 2016-11-02 9:54
 */
@Component
public class Child extends Father {
    private Integer childAge;
    private String childName;
    private Date childDate;
}
