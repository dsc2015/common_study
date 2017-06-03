package com.jd.study.common.javase_base.dataStructure.queue;

import java.util.Scanner;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/3/18
 */
public class LinkedQueueList<T> {
    /**
     * 双端链表的头
     */
    private QueueNode<T> first;

    /**
     * 双端链表的尾
     *
     */
    private QueueNode<T> last;
    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        return first==null||last==null;
    }

    /**
     * 链尾插入的方式
     *
     */
    public void insertLast(T data){
        QueueNode<T> node = new QueueNode<T>();
        node.setData(data);
        if(first==null){
            first=node;
        }
        if(last!=null){
            last.setNext(node);
        }
        last=node;
    }

    /**
     * 链头删除的方法
     */
    public T  moveFirst() throws Exception {
        Scanner scanner=new Scanner(System.in);
        if(isEmpty()){
            throw new Exception("数据为空，不能删除了！");
        }
        QueueNode<T> temp=first;
        first=temp.getNext();
        if(first==null){
            last=null;
        }
        return temp.getData();
    }


    public QueueNode<T> getFirst() {
        return first;
    }

    public void setFirst(QueueNode<T> first) {
        this.first = first;
    }

    public QueueNode<T> getLast() {
        return last;
    }

    public void setLast(QueueNode<T> last) {
        this.last = last;
    }

    class QueueNode<T>{
        private T data;
        private QueueNode<T> next;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public QueueNode<T> getNext() {
            return next;
        }

        public void setNext(QueueNode<T> next) {
            this.next = next;
        }
    }
}
