package com.jd.study.common.javase_base.dataStructure.queue;

/**
 * @author dushuangcheng
 * @description 利用数组实现的队列, 要考虑到循环队列的实现
 * 几个属性：1，队列的最大长度
 * 2，队列的数据存储体
 * 3，队列的头，
 * 4，队列的尾部
 * 队列中实际存储的元素
 * @create 2017/3/14
 */
public class QueueStructure {
    /**
     * 队列的最大长度
     */
    private int maxSize;
    /**
     * 队列的数据存储体
     */
    private int[] queueArray;
    /**
     * 队列的队头
     */
    private int front;
    /**
     * 队列 的尾部
     */
    private int rear;
    /**
     * 队列元素
     */
    private int nElements;

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 构造器进行初始化的操作
     */
    public QueueStructure() {
        this.maxSize = 4;
        this.front = 0;
        this.rear = -1;
        this.nElements = 0;
        this.queueArray = new int[maxSize];
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return nElements == 0;
    }

    /**
     * 判断队列是否已经满了
     */
    public boolean isFull() {
        return nElements == maxSize;
    }

    /**
     * 插入元素
     */
    public void insert(int input) throws Exception {
        if (isFull()) {
            throw new Exception("队列已经满了，不能再插入元素了");
        }
        //考虑循环队列的情况
        //易错：队尾到达数组边界的时候，先把rear的指针指向front的位置
        if (rear == maxSize - 1) {
            rear = -1;
        }
        //插入元素是在队尾进行的
        ++rear;
        queueArray[rear] = input;
        nElements++;
    }

    /**
     * 队头移除元素
     */
    public int remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列是空的，不能再删除元素了");
        }
        int temp = queueArray[front++];
        //易错：考虑到循环队列的情况下,指针增长之后巧合是在最后一个元素角标增加之后
        if (front == maxSize) {
            front = 0;
        }
        nElements--;
        return temp;
    }

    /**
     * 获取队头元素的值
     */
    public int getFront() {
        return queueArray[front];
    }

    public static void main(String[] args) throws Exception {
        QueueStructure queueStructure = new QueueStructure();
        queueStructure.insert(10);
        queueStructure.insert(9);
        queueStructure.insert(8);
        queueStructure.insert(7);

        int front1 = queueStructure.getFront();
        System.out.println("队头元素为：------->" + front1);
        System.out.println("删除的队头元素为：" + queueStructure.remove());
        queueStructure.insert(6);
        System.out.println("删除的队头元素为：" + queueStructure.remove());
    }


}
