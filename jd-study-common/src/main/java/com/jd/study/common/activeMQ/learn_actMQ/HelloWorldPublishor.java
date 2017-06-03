package com.jd.study.common.activeMQ.learn_actMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @description 发布订阅模式
 * 在这种模式下，除非消费者建立了持久订阅的模式，否则当消费者进行订阅的时候将接收不到连接之前发布的消息
 * 这点是需要注意的。而在点对点的方式中话，则可以接收到之前入队的消息的
 * @author dushuangcheng
 * @create 2017/5/8
 */
public class HelloWorldPublishor {
    public static void main(String[] args) {
        Connection connection=null;
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        try {
            connection = factory.createConnection();
            //启动连接
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建desinantion
            Destination destination = session.createTopic("my_topic");
            //创建生产者
            MessageProducer producer = session.createProducer(destination);
            //配置消息是否持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage textMessage = session.createTextMessage("hello dsc");
            //发送消息
            producer.send(textMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
