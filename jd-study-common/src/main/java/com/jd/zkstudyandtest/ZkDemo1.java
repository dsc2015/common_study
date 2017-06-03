package com.jd.zkstudyandtest;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author dushuangcheng
 * @description 利用客户端创建数据节点
 * @create 2017/5/15
 */
public class ZkDemo1 implements Watcher {
    private static CountDownLatch cdl = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException, KeeperException {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new ZkDemo1());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //同步创建节点
        String path = zooKeeper.create("/zk", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        Thread.sleep(8000);
        //异步创建节点
        zooKeeper.create("/zk1","test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL,new IStringCallBack(),"I'm a context");
        System.out.println("path==="+path);

        cdl.await();

    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("连接上了。。。事件类型是："+event.getType());
        if(Event.KeeperState.SyncConnected==event.getState()){
            System.out.println("------------------------------+");
            cdl.countDown();
        }
    }
    //定义的一个回调函数
   static class IStringCallBack implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("create node result is:rc,path,ctx,name"+rc+path+ctx+name);
        }
    }
    /**
     * 主要用到的接口如下：
     * 以同步的方式创建节点
     * String create（final String path,byte[] data,List<Acl> acl,CreateModel）
     *
     * 以异步的方式创建节点
     * String create（final String path,byte[] data,List<Acl> acl,CreateModel,StringCallBack callBack,Object ctx）
     *
     * path:需要创建的数据节点的路径
     * data[]:一个字节数组，这个是节点创建之后的初始内容
     * acl:节点的acl策略，也就是节点的访问权限控制，一般的情况下是不需要使用访问权限控制的
     * CreateModel:创建模式，模式有下面几种：1,持久：persistent:2,persistent_sequental 持久顺序
     * 3，临时节点：4,临时顺序节点
     *
     *4，cb,是一个用于异步回调的函数
     *
     *5，ctx:类似于一个context对象，传递一个上下文对象
     *
     * 注意创建节点的时候是不支持递归创建节点的。
     *
     * 对于节点的内容是字节数据的形式，也就是要进行序列化的，而序列化需要自己负责
     *
     *
     */


}
