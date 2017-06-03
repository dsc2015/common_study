package com.jd.study.common.activeMQ;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.command.ActiveMQMapMessage;

import javax.jms.*;


/**
 * @description 发布：定义topic并给这些topic发送消息
 * @author dushuangcheng
 * @create 2017/5/8
 */
public class Publisher {
    private String brokerUrl;
    private Session session;
    public Publisher() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
        //获取连接？什么形式的连接，通信协议是什么？
        Connection connection = factory.createConnection();

        connection.start();
        //获取一个session
         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //获取消息的生产者
        MessageProducer producer = session.createProducer(null);

    }

    public void setTopics(String[] stocks){
       //new Destination(stocks.length);
    }
   /* public Message createMessage(String stock,Session session) throws JMSException {
        ActiveMQMapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("name", stock);
        mapMessage.setInt("price", 100);
        return mapMessage;
    }*/

}
