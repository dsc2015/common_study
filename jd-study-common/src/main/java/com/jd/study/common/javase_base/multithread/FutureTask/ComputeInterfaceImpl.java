package com.jd.study.common.javase_base.multithread.FutureTask;

import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * @description 使用一个future来模拟缓存的使用
 * @author dushuangcheng
 * @create 2017/2/24
 */
public class ComputeInterfaceImpl<I,O> implements ComputeInterface<I,O> {
    private final ConcurrentHashMap<I,Future<O>> cache=new ConcurrentHashMap<>();
    //1.对于final类型的变量要么初始化，要么提供相关的构造器，否则会报错
    private final ComputeInterface computeInterface;

    public ComputeInterfaceImpl(ComputeInterface computeInterface) {
        this.computeInterface = computeInterface;
    }

    @Override
    public O expensiveFunction(final I input) {
        while (true){
            //1,先从缓存map中获取
            Future<O> future = cache.get(input);
            if(future==null){
                Callable<O> callable = new Callable<O>() {
                    @Override
                    public O call() throws Exception {
                        //return computeInterface.expensiveFunction(input);
                        return null;
                    }
                };
                //构造一个futureTask对象,以future作为入参
                FutureTask<O> futureTask = new FutureTask<O>(callable);
                future=cache.putIfAbsent(input,futureTask);
                if(future==null){
                    future=futureTask;
                    futureTask.run();
                }
                try {
                    return futureTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
