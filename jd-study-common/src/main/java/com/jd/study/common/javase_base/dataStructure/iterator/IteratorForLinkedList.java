package com.jd.study.common.javase_base.dataStructure.iterator;

/**
 * 起始的时候迭代器中的previous指向-1
 * current指向first的位置。
 *
 * @author dushuangcheng
 * @description
 * @create 2017/3/21
 */
public class IteratorForLinkedList<T> {
    /**
     * 对应的链表
     */
    private LinkList myLinkList;
    /**
     * 当前节点
     */
    private Node<T> current;
    /**
     * 之前的节点
     */
    private Node<T> previous;

    public IteratorForLinkedList(LinkList myLinkList) {
        this.myLinkList = myLinkList;
    }

    /**
     * 重置迭代器的位置
     */
    public void rest() {
        current = myLinkList.first;
        previous = null;
    }

    /**
     * 判断是否在链表的末尾
     */
    public boolean isEnd() {
        return current.next == null;
    }

    /**
     * 下一个节点
     */
    public void nextNode() {
        previous = current;
        current = current.next;
    }

    /**
     * 在current元素之后插入元素
     */
    public void insertAfter(T key) {
        Node<T> node = new Node<T>();
        node.setData(key);
        //空的话
        if (myLinkList.isEmpty()) {
            myLinkList.setFirst(node);
            current = node;
        } else {
            node.next = current.next;
            current.next = node;
            nextNode();//指向下一个节点
        }
    }

    /**
     * 在current元素之前插入元素,这里使用的是前向指针来指向下一个元素
     */
    public void insertBefore(T key) {
        //先构造节点
        Node<T> node = new Node<T>();
        node.setData(key);

        if (previous == null) {
            node.next = myLinkList.getFirst();
            myLinkList.setFirst(node);
            rest();//重置迭代器
        } else {
            node.next = previous.next;
            previous.next = node;
            current = node;
        }
    }

    /**
     * 删除当前节点处的元素
     */
    public T deleteCurrent() {
        T data = current.getData();
        //在链表开始的位的时候
        if(previous==null){
            myLinkList.setFirst(current.getNext());
            rest();
        }else {
            previous.next=current.next;
            if(isEnd()){
                rest();
            }else {
                current=current.next;
            }
        }
        return data;
    }

    /**
     * 对应的链表类
     */
    class LinkList {
        private Node<T> first;

        public Node<T> getFirst() {
            return first;
        }

        public void setFirst(Node<T> first) {
            this.first = first;
        }

        /**
         * 获取迭代器
         */
        public IteratorForLinkedList<T> getIterator() {
            return new IteratorForLinkedList<>(this);
        }
        /**
         * 遍历操作
         */
        public void display() {
            Node<T> current = first;
            while (current != null) {
                System.out.println(current.getData());
                current = current.next;
            }
        }

        /**
         * 判断是否为空
         */
        public boolean isEmpty() {
            return first == null;
        }

    }

    /**
     * 对应的节点类
     */
    class Node<T> {
        /**
         * 节点上存储的数据
         */
        private T data;
       /* *//**
         * 节点的前向指针
         *//*
        private Node<T> previous;*/
        /**
         * 节点的后向指针
         */
        private Node<T> next;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public LinkList getMyLinkList() {
        return myLinkList;
    }

    public void setMyLinkList(LinkList myLinkList) {
        this.myLinkList = myLinkList;
    }

    public Node<T> getCurrent() {
        return current;
    }

    public void setCurrent(Node<T> current) {
        this.current = current;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}
