package com.jd.study.common.javase_base.dataStructure.queue;
/**
 * @description 利用双端链表实现循环队列
 * @author dushuangcheng
 * @create 2017/3/18
 */
public class QueueWithLinkedNode {
    private LinkedQueueList<Integer> linkedQueue=new LinkedQueueList<>();

    public LinkedQueueList<Integer> getLinkedQueue() {
        return linkedQueue;
    }

    public void setLinkedQueue(LinkedQueueList<Integer> linkedQueue) {
        this.linkedQueue = linkedQueue;
    }
    /**
     * 判断为空
     */
    public boolean isEmpty(){
        return linkedQueue.isEmpty();
    }
    /**
     * 插入
     */
    public  void insert(Integer key){
        linkedQueue.insertLast(key);
    }
    /**
     * 队头移除
     */
    public Integer remove() throws Exception {
        return linkedQueue.moveFirst();
    }

    public static void main(String[] args) throws Exception {


        QueueWithLinkedNode queueWithLinkedNode = new QueueWithLinkedNode();
        System.out.println("--------------->"+queueWithLinkedNode.isEmpty());
        queueWithLinkedNode.insert(Integer.valueOf(10));
        queueWithLinkedNode.insert(Integer.valueOf(20));
        queueWithLinkedNode.insert(Integer.valueOf(30));
        queueWithLinkedNode.insert(Integer.valueOf(-20));
        queueWithLinkedNode.remove();
        queueWithLinkedNode.remove();
        queueWithLinkedNode.remove();
        Integer remove = queueWithLinkedNode.remove();
        System.out.println("=============================>"+remove);
        queueWithLinkedNode.remove();
    }
}
