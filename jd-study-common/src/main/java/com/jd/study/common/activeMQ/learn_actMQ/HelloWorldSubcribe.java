package com.jd.study.common.activeMQ.learn_actMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author dushuangcheng
 * @description 发布订阅模式中的消费者
 * @create 2017/5/8
 */
public class HelloWorldSubcribe {
    public static void main(String[] args) {
        Connection connection = null;
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        //
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //
            Destination destination = session.createTopic("my_topic");
            //根据session获取消费者
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage){
                        TextMessage message1= (TextMessage) message;
                        try {
                            System.out.println("接收到的消息为："+message1.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
