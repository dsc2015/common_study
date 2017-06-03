package com.jd.study.common.javase_base.netty.study.server;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/11
 */
public class AsynchServer {
    public static void main(String[] args) {
        new Thread(new TimeServerHandler(8080)).start();
    }
}
