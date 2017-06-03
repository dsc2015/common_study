package com.jd.study.common.javase_base.dataStructure.link;
/**
 * @description link是一个链表中的数据节点类
 * 在单链表中这个类主要包含两部分内容：①，链表节点上的数据；②，指向下一个节点的指针
 * @author dushuangcheng
 * @create 2017/3/16
 */
public class Link {
    private int data;
    /**
     * 包含了一个指向自身的自引用
     */
    private Link next;

    public Link(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Link{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }
}
