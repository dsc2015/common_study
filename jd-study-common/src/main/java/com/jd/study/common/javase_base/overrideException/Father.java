package com.jd.study.common.javase_base.overrideException;/**
 * Created by dushuangcheng on 2017/2/20.
 */

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author dushuangcheng
 * @create 2017-02-20 17:45
 */
public class Father {
    private int a;
    private int b;
    public double decrease(int a,int b) throws ArithmeticException, Exception {
        return a/b;
    }
}
