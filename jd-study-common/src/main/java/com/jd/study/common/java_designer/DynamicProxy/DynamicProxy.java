package com.jd.study.common.java_designer.DynamicProxy;/**
 * Created by dushuangcheng on 2016/10/26.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author dushuangcheng
 * @create 2016-10-26 9:52
 */
public class DynamicProxy implements InvocationHandler {
    //被代理的目标对象
    private  Object objectTarget;

    public Object getObjectTarget() {
        return objectTarget;
    }

    public void setObjectTarget(Object objectTarget) {
        this.objectTarget = objectTarget;
    }
    //构造器
    public DynamicProxy(Object objectTarget) {
        this.objectTarget = objectTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //注意这边不是参数里面的proxy
        //前后可以做一些功能的扩展
        System.out.println("before");
        Object result = method.invoke(this.objectTarget, args);
        System.out.println("after");
        return result;
    }
}
