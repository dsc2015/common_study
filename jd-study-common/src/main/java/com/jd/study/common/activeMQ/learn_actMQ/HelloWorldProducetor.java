package com.jd.study.common.activeMQ.learn_actMQ;/**
 * Created by dushuangcheng on 2017/5/8.
 */

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;

/** P2P的方式发送消息
 * @author dushuangcheng
 * @create 2017-05-08 12:57
 */
public class HelloWorldProducetor {
    public static void main(String[] args) throws JMSException {
        //
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        //开始连接
        connection.start();
        //创建的是否支持事务
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建消息目标,目标的broker
        Destination destination = session.createQueue("queue_session");
        //创建生产者
        MessageProducer producer = session.createProducer(destination);
        //配置消息是否持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //初始化要发送的消息
        TextMessage textMessage = session.createTextMessage("hello world");
        producer.send(textMessage);
        connection.close();
        //关于事务的处理

    }
}
