package com.jd.study.common.java_designer.callback;/**
 * Created by dushuangcheng on 2016/10/28.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-28 10:15
 */
public class Server implements ServerInterface{
    private Client client;

    public Server(Client client) {
        this.client = client;
    }

    //模拟框架中的一个方法

    @Override
    public String callService() {
        return "begin to service...";
    }
}
