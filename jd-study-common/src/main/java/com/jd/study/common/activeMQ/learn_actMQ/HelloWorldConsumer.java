package com.jd.study.common.activeMQ.learn_actMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/5/8
 */
public class HelloWorldConsumer {
    public static void main(String[] args) throws JMSException {
        //初始化连接
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        //创建连接
        Connection connection = factory.createConnection();
        connection.start();
        //创建回话session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建消息目标
        Destination destination = session.createQueue("queue_session");
        MessageConsumer consumer = session.createConsumer(destination);
        //配置监听
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage= (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
