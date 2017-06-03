package com.jd.study.common.java_designer.callback;/**
 * Created by dushuangcheng on 2016/10/28.
 */

/**
 * @author dushuangcheng
 * @create 2016-10-28 10:15
 * <p/>
 * 模拟客户端：客户端调用服务端的方法
 */
public class Client {
    private ServerInterface serverInterface;

    public Client() {
    }

    public ServerInterface getServerInterface() {
        return serverInterface;
    }

    public void setServerInterface(ServerInterface serverInterface) {
        this.serverInterface = serverInterface;
    }

    public Client(ServerInterface serverInterface) {
        this.serverInterface = serverInterface;
    }

    //客户端调用服务端的方法
    public String getMessage(int a) {
        int sum = 0;
        for (int i = 0; i < a; i++) {
            sum += i;
        }
        return serverInterface.callService() + sum;
    }
}
