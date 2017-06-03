package com.jd.study.common.activeMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @description JMS基本概念：1，连接工厂，用来创建连接对象，2，连接，客户端与JMS提供者之间的一个连接，
 * 3，会话，回话用于创建一组生产者与消费者,和消息。回话提供了一个事务性的上下文，在这个上下文中，消息
 * 的发送和接收是原子性的。
 * 4，distination(目的地）：指定消息生产的目标对象和消费消息的来源的对象。两种消息传递域：点对点和发布订阅的模式
 * PTP的特点如下：
 *
 * 消息的生产者，消息的消费者，消息topic
 * 消息可靠性的保证：
 * @author dushuangcheng
 * @create 2017/5/5
 */
public class FirstDemo {
    public static void main(String[] args) {
        /**
         *  this factory can create QueueConnections and TopicConnections
         */
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory();
    }
}
