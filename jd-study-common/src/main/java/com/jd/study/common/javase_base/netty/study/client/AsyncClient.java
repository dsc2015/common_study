package com.jd.study.common.javase_base.netty.study.client;
/**
 * @description 
 * @author dushuangcheng
 * @create 2017/4/11
 */
public class AsyncClient {
    public static void main(String[] args) {
        new Thread(new AsyncClientHandler("127.0.0.1",8080)).start();
    }
}
