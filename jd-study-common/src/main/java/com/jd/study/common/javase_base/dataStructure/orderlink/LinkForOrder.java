package com.jd.study.common.javase_base.dataStructure.orderlink;

public class LinkForOrder<T> {
    /**
     * 首节点指针
     */
    private LinkNode<T> first;

    class LinkNode<T> {
        /**
         * node节点上的数据
         */
        private T data;
        /**
         * 下一个链节点
         */
        private LinkNode next;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public LinkNode getNext() {
            return next;
        }

        public void setNext(LinkNode next) {
            this.next = next;
        }
    }

    /**
     * 比较大小的方法
     */
    public boolean isBigThan(T key, LinkNode<T> linkNode) {
        if (key instanceof Integer) {
            Integer key1 = (Integer) key;
            Integer key2 = (Integer) linkNode.getData();
            return key1 > key2;
        }
        return false;
    }

    /**
     * 插入，并保持有序
     */
    public void insert(T key) {
        LinkNode<T> linkNode = new LinkNode<>();
        linkNode.setData(key);
        //当前节点
        LinkNode<T> current = first;
        //之前的节点
        LinkNode<T> previous = null;
        //判断大小
        while ((current != null && isBigThan(key, current))) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            linkNode.setNext(first);
            first = linkNode;

        } else {
            //插入到这个位置中
            previous.setNext(linkNode);
            linkNode.setNext(current);
        }
    }

    /**
     * 删除节点,首节点
     */
    public LinkNode<T> remove() {
        LinkNode<T> temp = first;
        first.setNext(temp.next);
        return temp;
    }

    /**
     * 遍历元素
     */
    public void display() {
        LinkNode<T> current = first;
        while (current != null) {
            System.out.println("-------------------------->data=" + current.getData());
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        LinkForOrder<Integer> integerLinkForOrder = new LinkForOrder<>();
        integerLinkForOrder.insert(10);
        integerLinkForOrder.insert(20);
        integerLinkForOrder.insert(30);
        integerLinkForOrder.insert(40);
        integerLinkForOrder.insert(25);
        integerLinkForOrder.insert(15);
        integerLinkForOrder.insert(1);
        integerLinkForOrder.insert(-20);
        integerLinkForOrder.insert(-18);

        integerLinkForOrder.display();
    }
}
