/**
 *  ConsumerMQProducerFilter.java Created on 2013年10月29日 上午10:30:50
 *
 *  Copyright (c) 2013 by www.jd.com.
 */
package com.jd.jsf.gd.plugins.jmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.jmq.client.producer.MessageProducer;
import com.jd.jmq.common.message.Message;
import com.jd.jsf.gd.error.InitErrorException;
import com.jd.jsf.gd.error.RpcException;
import com.jd.jsf.gd.filter.AbstractFilter;
import com.jd.jsf.gd.msg.Invocation;
import com.jd.jsf.gd.msg.MessageBuilder;
import com.jd.jsf.gd.msg.RequestMessage;
import com.jd.jsf.gd.msg.ResponseMessage;
import com.jd.jsf.gd.util.Constants;
import com.jd.jsf.gd.util.JSFContext;
import com.jd.jsf.gd.util.JsonUtils;

/**
 * Title: 调用MQ过滤器<br>
 * <p/>
 * Description: 将异步调用数据发给MQ，不再调用<br>
 * <p/>
 * Company: <a href=www.jd.com>京东</a><br>
 *
 * @author <a href=mailto:zhanggeng@jd.com>章耿</a>
 */
public class ConsumerMQProducerFilter extends AbstractFilter {

    /**
     * slf4j logger for this class
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ConsumerMQProducerFilter.class);

    /**
     * The Producer.
     */
    private MessageProducer producer;

    /**
     * 请求 生产topic
     */
    private String reqTopic;

    /**
     * 返回结果 消费topic，需要返回值可配置
     */
    private String resTopic;

    /**
     * Instantiates a new Consumer mQ producer filter.
     */
    protected ConsumerMQProducerFilter() {

    }

    /**
     * 构造函数，如果不需要返回值
     *
     * @param producer
     *         the producer
     * @param reqTopic
     *         the req topic
     */
    public ConsumerMQProducerFilter(MessageProducer producer, String reqTopic) {
        this(producer, reqTopic, null);
    }

    /**
     * 构造函数
     *
     * @param producer
     *         the producer
     * @param reqTopic
     *         the reqTopic
     * @param resTopic
     *         the resTopic
     */
    public ConsumerMQProducerFilter(MessageProducer producer, String reqTopic, String resTopic) {
        if (producer == null) {
            throw new InitErrorException("Producer is not specified");
        }
        if (reqTopic == null) {
            throw new InitErrorException("Request topic is not specified");
        }
        this.producer = producer;
        this.reqTopic = reqTopic;
        this.resTopic = resTopic;
        JsonUtils.registerTemplate();
    }

    /**
     * Invoke response message.
     *
     * @param requestMessage
     *         the request message
     * @return the response message
     * @see com.jd.jsf.gd.filter.Filter#invoke(com.jd.jsf.gd.msg.RequestMessage)
     */
    @Override
    public ResponseMessage invoke(RequestMessage requestMessage) {

        Invocation invocation = requestMessage.getInvocationBody();
        String methodName = invocation.getMethodName();
        ResponseMessage responseMessage = MessageBuilder.buildResponse(requestMessage);
        boolean async = super.getBooleanMethodParam(methodName, Constants.CONFIG_KEY_ASYNC, false);
        if (!async) {
            responseMessage.setException(new RpcException("MQProducerFilter only invoke async request " +
                    "message, please set <jsf:consumer async=\"true\" />."));
        } else {
            try {
                if (producer == null) {
                    throw new RpcException("Producer is not specified");
                }
                if (reqTopic == null) {
                    throw new RpcException("Request topic is not specified");
                }
                // 其它属性，暂时放个String，服务端转为InetSocketAddress
                invocation.addAttachment(Constants.INTERNAL_KEY_REMOTE, JSFContext.getLocalHost());
                invocation.addAttachment("_reqTopic", reqTopic);
                if (resTopic != null) { // 需要返回值
                    invocation.addAttachment("_resTopic", resTopic);
                }
                // 序列化
                String messagebody = JsonUtils.toJSONString(invocation);
                Message message = new Message().topic(reqTopic).text(messagebody);
                // 调用MQ，不再调用子链，直接返回
                producer.send(message);
            } catch (Exception e) {
                responseMessage.setException(e);
            }
        }
        return responseMessage;
    }

    /**
     * Gets producer.
     *
     * @return the producer
     */
    public MessageProducer getProducer() {
        return producer;
    }

    /**
     * Sets producer.
     *
     * @param producer the producer
     */
    public void setProducer(MessageProducer producer) {
        this.producer = producer;
    }

    /**
     * Sets req topic.
     *
     * @param reqTopic
     *         the req topic
     */
    public void setReqTopic(String reqTopic) {
        this.reqTopic = reqTopic;
    }

    /**
     * Gets req topic.
     *
     * @return the req topic
     */
    public String getReqTopic() {
        return reqTopic;
    }

    /**
     * Sets res topic.
     *
     * @param resTopic
     *         the res topic
     */
    public void setResTopic(String resTopic) {
        this.resTopic = resTopic;
    }

    /**
     * Gets res topic.
     *
     * @return the res topic
     */
    public String getResTopic() {
        return resTopic;
    }
}
