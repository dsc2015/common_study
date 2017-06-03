package com.jd.jsf.gd.plugins.jmq;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.fastjson.JSON;
import com.jd.jmq.client.producer.MessageProducer;
import com.jd.jmq.common.exception.JMQException;
import com.jd.jmq.common.message.Message;
import com.jd.jsf.gd.error.RpcException;
import com.jd.jsf.gd.monitor.Monitor;
import com.jd.jsf.gd.monitor.MonitorFactory;
import com.jd.jsf.gd.msg.Invocation;
import com.jd.jsf.gd.msg.RequestMessage;
import com.jd.jsf.gd.msg.ResponseMessage;
import com.jd.jsf.gd.server.BaseServerHandler;
import com.jd.jsf.gd.server.Invoker;
import com.jd.jsf.gd.util.ClassTypeUtils;
import com.jd.jsf.gd.util.CommonUtils;
import com.jd.jsf.gd.util.Constants;
import com.jd.jsf.gd.util.JSFContext;

/**
 * Created by wangsonglin on 2015/1/22.
 */
public class JMQTask implements Callable<ResponseMessage> {

    private static Logger logger = LoggerFactory.getLogger(JMQTask.class);
    private Message message;
    private CountDownLatch latch;
    private MessageProducer producer;
    public JMQTask(Message mes, CountDownLatch latch, MessageProducer producer) {
        this.message = mes;
        this.latch = latch;
        this.producer = producer;
    }

    @Override
    public ResponseMessage call() throws Exception {

        ResponseMessage responseMessage;
        try {
            System.out.println("ssssss++++++++++++");
            //反序列化
            Invocation invoke = JSON.parseObject(message.getText(), Invocation.class);
            //invoke
            responseMessage  = handlerInvocation(invoke);
        }catch (Exception e) {
            logger.error("jmq invoke error:" + e.getMessage(), e);
            responseMessage = new ResponseMessage();
            responseMessage.setException(new Throwable("service invoke error:" + e.getMessage(), e));
            return responseMessage;
        } finally {
            latch.countDown();
        }
        return responseMessage;
    }

    private ResponseMessage handlerInvocation(Invocation invocation) throws JMQException {
        //返回值处理
        Invoker invoker = BaseServerHandler.getInvoker(invocation.getClazzName(), invocation.getAlias());
        if (invoker == null) {
            throw new RpcException("Cannot found invoker of " + invocation.getClazzName()
                    + ":" + invocation.getAlias() + " in JMQ invoke");
        }

        InetSocketAddress add = new InetSocketAddress(JSFContext.getLocalHost(), 10000);
        invocation.addAttachment(Constants.INTERNAL_KEY_LOCAL, add);

        String remoteIp = (String)invocation.getAttachment(Constants.INTERNAL_KEY_REMOTE);
        InetSocketAddress remote = new InetSocketAddress(remoteIp, 10000);
        invocation.addAttachment(Constants.INTERNAL_KEY_REMOTE, remote);

        RequestMessage msg = new RequestMessage();
        msg.setInvocationBody(invocation);
        ResponseMessage responseMessage = invoker.invoke(msg);

        writeResponse(invocation, responseMessage);

        //标识消费完成一个消息
        addMonitor(invocation, responseMessage);

        return responseMessage;
    }

    private void writeResponse(Invocation invocation, ResponseMessage responseMessage) throws JMQException {

        String resTopic = (String) invocation.getAttachment("_resTopic");
        if (resTopic != null) {
            if (producer == null) {
                logger.error("Response topic is specified, but producer of response is null");
            } else {
                if (responseMessage.isError()) {
                    Throwable throwable = responseMessage.getException();
                    String result = JSON.toJSONString(throwable);
                    Message message = new Message(resTopic, result, null);
                    message.setAttribute("class", ClassTypeUtils.getTypeStr(throwable.getClass()));
                    message.setAttribute("error", "true");
                    producer.send(message);
                } else {
                    Object response = responseMessage.getResponse();
                    String result = JSON.toJSONString(response);
                    Message message = new Message(resTopic, result, null);
                    message.setAttribute("class", ClassTypeUtils.getTypeStr(response.getClass()));
                    producer.send(message);
                }
            }
        }
    }

    private void addMonitor(Invocation invocation, ResponseMessage responseMessage) {
        if (!CommonUtils.isFalse((String) invocation.getAttachment(Constants.INTERNAL_KEY_MONITOR))
                && MonitorFactory.isMonitorOpen(invocation.getClazzName(), invocation.getMethodName())) {
            String ip = JSFContext.getLocalHost();
            //JMQ调用时，设置port为-1
            int port = -1;
            //Monitor monitor = MonitorFactory.getMonitor(invocation.getClazzName(), invocation.getMethodName(), ip, port);
            Monitor monitor = MonitorFactory.getMonitor(0, invocation.getMethodName(), ip, port);
            if (monitor != null) { // 需要记录日志
                boolean iserror = responseMessage.isError();
                invocation.addAttachment(Constants.INTERNAL_KEY_INPUT, message.getSize());
                // 报文长度+magiccode(2) + totallength(4)
                String result = JSON.toJSONString(responseMessage.getResponse());
                if (result.equals("null")) {
                    invocation.addAttachment(Constants.INTERNAL_KEY_OUTPUT, 0);
                } else {
                    invocation.addAttachment(Constants.INTERNAL_KEY_OUTPUT, result.length());
                }
                invocation.addAttachment(Constants.INTERNAL_KEY_RESULT, !iserror);
                invocation.addAttachment(Constants.INTERNAL_KEY_PROTOCOL, "jmq");
                if (iserror) { // 失败
                    monitor.recordException(invocation, responseMessage.getException());
                } else { // 成功
                    monitor.recordInvoked(invocation);
                }
            }
        }
    }

}
