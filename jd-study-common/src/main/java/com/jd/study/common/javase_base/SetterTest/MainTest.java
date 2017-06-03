package com.jd.study.common.javase_base.SetterTest;/**
 * Created by dushuangcheng on 2016/11/17.
 */

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.ForkJoinTask;

/**
 * @author dushuangcheng
 * @create 2016-11-17 11:29
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        String format = String.format("优惠券数量不足,ruleKey:{%s}, ruleId:{%s}",
                new Object[]{"111111", new Long(11)});
        System.out.println(format);


        //ForkJoinTask
        //
        // StringReader
        // InputStreamReader
        //通过open打开一个channel连接类似于serverSocket
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //绑定监听的端口，设置为非阻塞的方式
        serverSocketChannel.socket().bind(new InetSocketAddress(8080),8080);
        //设置为非阻塞的方式
        serverSocketChannel.configureBlocking(false);
        //创建多路复用器 selector
        Selector selector=Selector.open();
        //selector.select()
        //获取就绪的key的集合
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        //遍历迭代这个集合
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()){
            SelectionKey next = iterator.next();
            //进行数据的处理
        }
        //多路复用器监听客户端的请求
        SocketChannel channel = serverSocketChannel.accept();
        //设置客户端的连接为非阻塞的方式
        channel.configureBlocking(false);
        //跟tcp的状态有关系，当tcp连接处于等待状态的时候能否重用socket连接
        channel.socket().setReuseAddress(false);
        //将新接入的客户端连接注册到Reactor上的多路复用器上监听读操作读取客户端发送的网络信息
        channel.register(selector,8080,new Object());
        //异步读取客户端的消息到缓冲区中去
        ByteBuffer byteBuffer=ByteBuffer.allocate(2048);
        int read = channel.read(byteBuffer);
        //对bytebuffer中的数据进行解码，如果有半包消息指针reset继续读取后续的报文
        Object message=null;
        while (byteBuffer.hasRemaining()){
            byteBuffer.mark();

        }

        //注册到多路复用器上 ,获取selectionKey
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Object());
        //多路复用器在循环管道中获取准备就绪的key
        //Selects a set of keys whose corresponding channels are ready for I/O operations.
        int select = selector.select();


        Integer a = 50;
        int b = 50;

        Integer c = 1000;
        int d = 1000;

        System.out.println(a.equals(b));
        System.out.println(c.equals(d));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 4; i++) {
                    System.out.println("1111111111111111111");
                }
            }
        }).start();

        System.out.println("end");
    }
}

