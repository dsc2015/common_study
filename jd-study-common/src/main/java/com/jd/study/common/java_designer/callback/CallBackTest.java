package com.jd.study.common.java_designer.callback;/**
 * Created by dushuangcheng on 2016/10/28.
 */

/**pk
 * @author dushuangcheng
 * @create 2016-10-28 10:16
 *
 * 回调函数机制
 */
public class CallBackTest {
    public static void main(String[] args) {
        Client client=new Client();
        client.setServerInterface(new Server(client));

        String message = client.getMessage(10);
        System.out.println(message);

    }
}
