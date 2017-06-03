package com.jd.study.common.javase_base.overrideException;/**
 * Created by dushuangcheng on 2017/2/20.
 */

import java.io.IOException;

/**
 * @author dushuangcheng
 * @create 2017-02-20 17:47
 */
public class Child extends Father {
    private int c;
    private int d;

    @Override
    public double decrease(int a,int b) throws Exception{
        return a/b;
    }
}
