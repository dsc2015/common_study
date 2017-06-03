package com.jd.study.common.spring_study.spring_annotation;/**
 * Created by dushuangcheng on 2016/10/15.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dushuangcheng
 * @create 2016-10-15 17:56
 */
@Service
public class FunctionComponent {

    //组合接口一的类型
    @Autowired
    private FunctionInterface functionInterface;
    //组合接口二的类型
    @Autowired
    private FunctionInterfaces functionInterfaces;
}
