package com.jd.zkstudyandtest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author dushuangcheng
 * @description:关于zk 0：建立起来的默认端口号是2181：所以要测试这个端口号
 * 1,一旦跟server建立connection创建client,则会被分配一个sessionId
 * 2,当客户端失去心跳的之后，这个sessionId就会过期的,客户端必须重新建立client，并重新分配sessionId
 * 3,当服务端挂掉之后，客户端在sessionId过期之前去会尝试去连接其他的服务端。
 * @create 2017/5/15
 */
public class ZkDemo {
    //声明一个栅栏信号
    private static CountDownLatch semphonre = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        ZkDemo zkDemo = new ZkDemo();
        long sessionId = zkDemo.zooKeeper.getSessionId();
        byte[] sessionPasswd = zkDemo.zooKeeper.getSessionPasswd();

        //复用这两个参数重新建立跟zookeeper的连接
        new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
            //如果使用错误的参数在建立的连接的时候会返回expire过期的状态标识 path:null:SyncConnected：路径显示是空的，状态是异步的
            //也就是客户端在建立连接的时候是异步的。而不是同步的
            @Override
            public void process(WatchedEvent event) {
                System.out.println("再次连接拿到的状态是：path:"+event.getPath()+":"+event.getState());
                if (Event.KeeperState.SyncConnected == event.getState()) {
                    System.out.println("又重新建立的一个连接。。。");
                }
            }
        }, sessionId, sessionPasswd);
        System.out.println("连接已经建立起来了。。。。sessionId:" + sessionId);

        try {
            semphonre.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ZooKeeper zooKeeper;

    public ZkDemo() throws IOException {
        /**connectString:表示zookeeper服务器的列表，表示为：host:port表示的序列，每一个代表了一个一个zookeeper服务器
         * 还可以指定一个根目录，指定一个节点：
         *timeout：代表了一个心跳时间，主要用于检测会话超时时间
         *
         * watcher：这个是zookeeper watcher机制的核心，当节点发生变更的时候会通知节点的变更事件
         *
         * canbereadonly:用于指明该会话是否是否支持只读的模式
         *
         * sessionId:用于指定会话的id
         * sessionPassword 会话的秘钥，这个两个参数加在一起可以唯一地确定一个客户端，这样当下次需要重新连接的时候
         * 可以把这两个参数作为入参
         *
         */
        this.zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("receive the event....");
                if (Event.KeeperState.SyncConnected == event.getState()) {
                    System.out.println("*****************************");
                    semphonre.countDown();
                }
            }
        });

        System.out.println("+++++++++++++++++++++++++++++" + zooKeeper.getState());
    }
}
