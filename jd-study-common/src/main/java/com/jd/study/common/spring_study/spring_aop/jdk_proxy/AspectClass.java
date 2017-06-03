package com.jd.study.common.spring_study.spring_aop.jdk_proxy;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author dushuangcheng
 * @create 2017-01-10 15:20
 * 切面类：主要在切入点出进行增强处理
 */
@Aspect
@Component
public class AspectClass {
    //日志打印
    public void getLogger() {
        System.out.println("方法开始调用。。。");
    }

    public void throwException() {
        System.out.println("方法调用出现异常、、、");
    }
}
