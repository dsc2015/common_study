/**
 *  ConsumerResponseMessageListener.java Created on 2015/1/23 9:23
 *
 *  Copyright (c) 2015 by www.jd.com.
 */
package com.jd.jsf.gd.plugins.jmq;

import java.util.List;

import org.slf4j.Logger;

import com.jd.jmq.client.consumer.MessageListener;
import com.jd.jmq.common.message.Message;
import com.jd.jsf.gd.msg.ResponseListener;
import com.jd.jsf.gd.util.ClassTypeUtils;
import com.jd.jsf.gd.util.CommonUtils;
import com.jd.jsf.gd.util.JsonUtils;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Title: 客户端监听返回结果<br>
 * <p/>
 * Description: 从返回结果队列中获取返回结果<br>
 * <p/>
 * Company: <a href=www.jd.com>京东</a><br>
 *
 * @author <a href=mailto:zhanggeng@jd.com>章耿</a>
 */
public abstract class ConsumerResponseMessageListener implements MessageListener, ResponseListener {

    /**
     * slf4j Logger for this class
     */
    private final static Logger LOGGER = getLogger(ConsumerResponseMessageListener.class);

    /**
     * On message.
     *
     * @param messages
     *         the messages
     * @throws Exception
     *         the exception
     */
    @Override
    public void onMessage(List<Message> messages) throws Exception {
        if (CommonUtils.isNotEmpty(messages)) {
            // 无法保证当前客户端端发的请求就返回给当前客户端。
            // 只能通过Listener
            for (Message message : messages) {
                String topic = message.getTopic();
                LOGGER.debug("Receive response message: {}, {}, {}", new Object[]{message.getTopic(),
                        message.getText(), message.getAttributes()});
                String errorStr = message.getAttribute("error");
                String classStr = message.getAttribute("class");
                Class clazz = ClassTypeUtils.getClass(classStr);
                String resJson = message.getText();
                if (CommonUtils.isTrue(errorStr)) {
                    Throwable e = (Throwable) JsonUtils.parseObject(resJson, clazz);
                    catchException(e);
                } else {
                    Object object = JsonUtils.parseObject(resJson, clazz);
                    handleResult(object);
                }
            }
        }
    }

    @Override
    public abstract void handleResult(Object result);

    @Override
    public abstract void catchException(Throwable e);
}