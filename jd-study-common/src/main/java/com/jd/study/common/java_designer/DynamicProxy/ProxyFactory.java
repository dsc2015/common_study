package com.jd.study.common.java_designer.DynamicProxy;/**
 * Created by dushuangcheng on 2016/10/26.
 */

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.lang.reflect.Proxy;

/**
 * @author dushuangcheng
 * @create 2016-10-26 10:26
 */
public class ProxyFactory {
    //目标对象
    private Object object;

    public ProxyFactory(Object object) {
        this.object = object;
    }
    public  Object getProxyInstance() {
        return Proxy.newProxyInstance(this.object.getClass().getClassLoader(),this.object.getClass().getInterfaces(),new DynamicProxy(object));
    }
}
