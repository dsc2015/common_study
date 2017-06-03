package com.jd.study.common.javase_base.methodhandler;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 获得方法句柄通过java.lang.invoke.MethodHandles.Lookup类来完成
 findConstructor就是查找构造器的
 findVirtual就是查找一般函数的(同invokeVirtual)
 findStatic 就是查找静态方法的(同invokeStatic)
 以及findSpecial查找私有方法的
 获取属性的话通过findGetter或者fingStaticGetter就可以了
 * @description
 * @author dushuangcheng
 * @create 2017/3/21
 */
public class BaseTest {
    public static void main(String[] args) throws Throwable {
        String a="abcd";
        MethodType mt=MethodType.methodType(String.class,int.class,int.class);
        MethodHandle handle= MethodHandles.lookup().findVirtual(String.class,"substring",mt);
        System.out.println(handle.invoke(a,1,2)); //输出b
    }
}
