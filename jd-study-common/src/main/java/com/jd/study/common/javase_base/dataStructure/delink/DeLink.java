package com.jd.study.common.javase_base.dataStructure.delink;

import com.jd.study.common.javase_base.dataStructure.link.Link;

/**
 * @description 双端链表
 * 双端链表跟双向链表不是同一个概念，双端链表中第first指向last
 * 这样比单链表的好处是可以很好地在表尾部插入元素，这有利于队列的实现
 * @author dushuangcheng
 * @create 2017/3/17
 */
public class DeLink {
    /**
     * 链表头指针
     */
    private Link first;
    /**
     * 链表尾指针
     */
    private Link last;
    /**
     * 判断链表是否为空
     */
    public boolean isEmpty(){
        return first==null;
    }
    /**
     * 插入元素
     */
    public void insertNodeFirst(Link node){
        if(isEmpty()){
            last=node;
        }
        //非空的情况下
        node.setNext(first);
        first=node;
    }
    /**
     * 在结尾插入
     */
    public void insertNodeLast(Link node){
        if(isEmpty()){
            first.setNext(node);
        }
        //非空情况下
        last.setNext(node);
        last=node;
    }
    /**
     * 删除操作
     */
    public Link deleteFirst(){
        Link temp=first;
        if(first.getNext()==null){
            last=null;
        }
        first=first.getNext();
        return temp;
    }
    public Link getFirst() {
        return first;
    }

    public void setFirst(Link first) {
        this.first = first;
    }

    public Link getLast() {
        return last;
    }

    public void setLast(Link last) {
        this.last = last;
    }
}
