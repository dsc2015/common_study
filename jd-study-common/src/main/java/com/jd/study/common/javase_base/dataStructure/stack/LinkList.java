package com.jd.study.common.javase_base.dataStructure.stack;

/**
 * @author dushuangcheng
 * @description 用于实现栈的单链表结构
 * @create 2017/3/18
 */
public class LinkList {
    private Node first;

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 在头部插入
     */
    public void insertFirst(int data) {
        Node node = new Node();
        node.setData(data);
        node.setNext(first);
        first = node;
    }

    /**
     * 在first处删除
     */
    public Node deleteFirst() throws Exception {
        if(isEmpty()){
            throw new Exception("没有可以删除的元素");
        }
        Node temp = first;
        first=temp.getNext();
        return temp;
    }

    /**
     * 获取栈顶元素
     */
    public Node peek(){
        if(isEmpty()){
            return null;
        }
        return first.getNext();
    }


     class Node {
        /**
         * 节点的数据
         */
        private int data;
        /**
         * 指向下一个节点的指针
         */
        private Node next;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
