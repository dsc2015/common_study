package com.jd.study.common.javase_base.Generic;

import com.jd.study.common.javase_base.Generic.testobject.GenericParam1;
import com.jd.study.common.javase_base.Generic.testobject.GenericParam2;

/**
 * @description 泛型接口
 * @author dushuangcheng
 * @create 2017/3/1
 *
 * 测试1：抽象类继承泛型接口，看能否不指定泛型
 * 情况1，当泛型没有指定边界的情况下，抽象类可以不用指明泛型
 * 情况2，当泛型存在边界的情况下，抽象类继承之后，必须指明泛型
 *
 * extends GenericParam1
 */
public interface GenericInterface<T extends GenericParam1 ,K extends GenericParam2> {
    public void function1(T input1,K input2);
}
