/**
 *  ProviderRequestMessageListener.java Created on 2015/1/23 9:23
 *
 *  Copyright (c) 2015 by www.jd.com.
 */
package com.jd.jsf.gd.plugins.jmq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.jmq.client.consumer.MessageListener;
import com.jd.jmq.client.producer.MessageProducer;
import com.jd.jmq.common.message.Message;
import com.jd.jsf.gd.msg.ResponseMessage;
import com.jd.jsf.gd.transport.CallbackUtil;

/**
 * Title: 服务端消费请求队列<br>
 * <p/>
 * Description: 服务端从请求队列中获取请求消息，进行执行，支持成功写入返回队列<br>
 * <p/>
 * Company: <a href=www.jd.com>京东</a><br>
 *
 * @author <a href=mailto:wangsonglin@jd.com>王松林</a>
 */
public class ProviderRequestMessageListener implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(ProviderRequestMessageListener.class);
    private MessageProducer producer;
    private ThreadPoolExecutor pool = CallbackUtil.getCallbackThreadPool();

    @Override
    public void onMessage(List<Message> messages) throws Exception {
        if (messages == null || messages.isEmpty()) {
            return;
        }
        List<Future<ResponseMessage>> result = new ArrayList<Future<ResponseMessage>>();
        CountDownLatch latch = new CountDownLatch(messages.size());
        for (Message m : messages) {
            JMQTask task = new JMQTask(m, latch, producer);
            //丢到callbackbizpool执行
            Future<ResponseMessage> fut = pool.submit(task);
            result.add(fut);
        }
        latch.await();
        //如果没有error
        for (Future<ResponseMessage> f : result) {
            /*// 正常调用失败
            ResponseMessage res = f.get();
            if (res.isError()) {
                logger.error("jmq invoke error:" + res.getException().getMessage(), res.getException());
                throw new RpcException("invoke provider error through jmq");
            }*/
        }
    }

    public void setProducer(MessageProducer producer) {
        this.producer = producer;
    }

    public MessageProducer getProducer() {
        return producer;
    }
}