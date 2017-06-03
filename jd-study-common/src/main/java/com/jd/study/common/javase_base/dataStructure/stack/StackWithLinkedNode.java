package com.jd.study.common.javase_base.dataStructure.stack;

/**
 * @author dushuangcheng
 * @description 基于链表实现的栈
 * @create 2017/3/18
 */
public class StackWithLinkedNode {
    /**
     * 栈顶指针
     */
    private int top=-1;
    private LinkList linkList=new LinkList();

    /**
     * 入栈操作
     */
    public void push(int key) {
        top++;
        linkList.insertFirst(key);
    }

    /**
     * 出栈操作
     */
    public int pop() throws Exception {
        LinkList.Node node = linkList.deleteFirst();
        top--;
        return node.getData();
    }

    /**
     * 查看栈顶元素
     */
    public int peek() {
        LinkList.Node peek = linkList.peek();
        return peek == null ? 0 : peek.getData();
    }

    /**
     *
     */
    public boolean isEmpty(){
        return linkList.isEmpty();
    }
    /**
     * 总的元素数目
     */
    public int totalElement(){
        return top+1;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public LinkList getLinkList() {
        return linkList;
    }

    public void setLinkList(LinkList linkList) {
        this.linkList = linkList;
    }

    public static void main(String[] args) throws Exception {
        StackWithLinkedNode stackWithLinkedNode = new StackWithLinkedNode();
        LinkList linkList1 = new LinkList();
        stackWithLinkedNode.setLinkList(linkList1);

        stackWithLinkedNode.push(100);
        stackWithLinkedNode.push(200);
        stackWithLinkedNode.push(50);
        System.out.println("===================================>"+stackWithLinkedNode.getTop()+"-----"+stackWithLinkedNode.totalElement());

        int pop = stackWithLinkedNode.pop();
        System.out.println("=================>"+pop);
        stackWithLinkedNode.pop();
        stackWithLinkedNode.pop();
        stackWithLinkedNode.pop();

    }


}
