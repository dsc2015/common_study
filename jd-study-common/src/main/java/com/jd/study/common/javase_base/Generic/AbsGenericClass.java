package com.jd.study.common.javase_base.Generic;

import com.jd.study.common.javase_base.Generic.testobject.impl.GenericParam1Sub;
import com.jd.study.common.javase_base.Generic.testobject.impl.GenericParam2Sub;

/**
 * @description 泛型的抽象类
 * @author dushuangcheng
 * @create 2017/3/1
 */
public abstract class AbsGenericClass<T,K> implements GenericInterface<GenericParam1Sub,GenericParam2Sub>{
    @Override
    public void function1(GenericParam1Sub input1,GenericParam2Sub input2){

    }

}
