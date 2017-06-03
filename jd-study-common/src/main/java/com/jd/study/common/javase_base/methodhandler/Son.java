package com.jd.study.common.javase_base.methodhandler;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/3/21
 */
public class Son extends Father {
    public void tell() throws Throwable {
        MethodType methodType = MethodType.methodType(Void.class);
        MethodHandle mh = MethodHandles.lookup().findVirtual(Father.class, "tell", methodType);
        mh.invoke(this);
        System.out.println("I'm son!");
    }
}
