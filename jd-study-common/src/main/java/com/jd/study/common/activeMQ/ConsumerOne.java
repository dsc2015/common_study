package com.jd.study.common.activeMQ;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.broker.Connection;
import org.apache.activemq.memory.list.MessageList;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author dushuangcheng
 * @description consumer端1，初始化资源，2，接收消息，3，关闭的时候清除资源
 * //接收消息有同步和异步两种方式。异步的方式通过listener的方式接收消息
 * @create 2017/5/8
 */
public class ConsumerOne {
    private ActiveMQConnectionFactory factory;
    private Connection connection;
    private ActiveMQSession session;

    public ConsumerOne() throws JMSException {
        factory = new ActiveMQConnectionFactory(new String("a broker url"));
        //connection=factory.createTopicConnection();
        //connection.start();

    }

    class MessageListen implements MessageListener{

        @Override
        public void onMessage(Message message) {

        }
    }
}
