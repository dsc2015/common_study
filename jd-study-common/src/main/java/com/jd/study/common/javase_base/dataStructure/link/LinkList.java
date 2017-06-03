package com.jd.study.common.javase_base.dataStructure.link;

import java.util.concurrent.ForkJoinPool;

/**
 * @author dushuangcheng
 * @description 链表数据结构
 * 链表是一种数据结构，这种数据结构中包含的是链节点；
 * 在Java中的list类中所有的链节点都是包含在其内部作为一个内部类而存在
 * @create 2017/3/16
 */
public class LinkList {
    private Link first;

    public LinkList(Link first) {
        this.first = null;
    }

    public Link getFirst() {
        return first;
    }

    public void setFirst(Link first) {
        this.first = first;
    }

    /**
     * 插入元素的操作,在first位置执行插入操作
     */
    public void insert(Link newLink) {
        //newLink的下一个节点指向first所指的元素
        newLink.setNext(first);
        first = newLink;
    }

    /**
     * 删除链节点的方法,并返回被删除的链节点
     */
    public Link delete() {
        Link temp = first;
        first = first.getNext();
        return temp;
    }

    /**
     * 遍历链表的操作
     */
    public void display(LinkList linkList) throws Exception {
        //从开头开始进行遍历
        if (linkList.first == null) {
            throw new Exception("链表已经到达末尾");
        }
        Link current = linkList.first;
        while (current != null) {
            System.out.println("data=" + current.getData());
            current = current.getNext();
        }
    }

    /**
     * 查找指定的链节点
     */
    public Link find(LinkList linkList, int data) throws Exception {
        //从开头开始遍历
        if (linkList.getFirst() == null) {
            throw new Exception("链表已经到达末尾");
        }
        Link current = linkList.first;
        while (current != null) {
            if (current.getData() == data) {
                return current;
            }
            current = current.getNext();
        }
        System.out.println("没有找到链表节点");
        return null;
    }

    /**
     * 删除指定的链节点
     */
    public boolean deleteNode(LinkList linkList, int data) {
        //从开头开始遍历
        if (linkList.getFirst() == null) {
            return false;
        }
        Link current = linkList.first;
        Link privous = linkList.first;
        while (current != null) {
            current = current.getNext();
            if (current.getData() == data) {
                privous.setNext(current.getNext());
                return true;
            }
            if(current==null){
                System.out.println("没有找到指定的链节点,无法删除");
                return false;
            }
            privous = current;
        }
        System.out.println("没有找到指定的链节点");
        return false;
    }
}
