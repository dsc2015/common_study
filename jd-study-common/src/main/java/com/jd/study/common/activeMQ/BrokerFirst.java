package com.jd.study.common.activeMQ;

import org.apache.activemq.broker.BrokerService;

/**
 * @description  创建一个broker实例
 * @author dushuangcheng
 * @create 2017/5/8
 */
public class BrokerFirst {
    public void createBroker() throws Exception {
        BrokerService bs=new BrokerService();
        bs.addConnector("");
        bs.start();
    }


}
