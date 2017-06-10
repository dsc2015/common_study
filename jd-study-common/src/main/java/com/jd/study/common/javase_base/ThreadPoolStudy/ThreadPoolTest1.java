package com.jd.study.common.javase_base.ThreadPoolStudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 测试线程池创建的时候线程池用有多少线程
 * 参数如何调配
 * @author dushuangcheng
 * @create 2017-06-06 12:31
 */
public class ThreadPoolTest1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("====================================");
    }
}
