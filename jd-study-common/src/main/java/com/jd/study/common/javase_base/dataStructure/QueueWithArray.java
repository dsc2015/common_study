package com.jd.study.common.javase_base.dataStructure;
/**
 * @description 队列分队头和队尾，只允许队头移除，队尾添加元素
 * 队列只允许在后端（称为rear）进行插入操作，在前端（称为front）进行删除操作
 * @author dushuangcheng
 * @create 2017/3/8
 */
public class QueueWithArray {
    private int maxSize=10;
    private int[] data=new int[10];
    /**
     * 队头
     */
    private int front=0;
    /**
     * 队尾
     */
    private int rear=-1;

    private int usedSize=0;







    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public int getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(int usedSize) {
        this.usedSize = usedSize;
    }
}
