package com.jd.study.common.javase_base.multithread.FutureTask;
/**
 * @description 一个计算的接口
 * @author dushuangcheng
 * @create 2017/2/24
 */
public interface ComputeInterface<I,O> {
    /**
     * @description 
     * @Author  dushuangcheng
     * @param  input,一个入参;O返回值，出参
     * @return 
     * @throw  
     * @date   2017/2/27
     */  
       
    public O expensiveFunction(I input);
    
}
