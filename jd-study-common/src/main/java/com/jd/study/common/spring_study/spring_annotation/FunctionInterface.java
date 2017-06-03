package com.jd.study.common.spring_study.spring_annotation;/**
 * Created by dushuangcheng on 2016/10/15.
 */

import java.util.Date;

/**
 * @author
 * @create 2016-10-15 17:41
 */
//定义一组的功能，由不同的实现类来实现
public interface FunctionInterface {
    public String getName();
    public void  setDate(Date date);
}
