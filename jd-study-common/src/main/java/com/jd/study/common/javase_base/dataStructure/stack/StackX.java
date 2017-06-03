package com.jd.study.common.javase_base.dataStructure.stack;
/**
 * @description 定义一个用数组实现的栈
 * @author dushuangcheng
 * @create 2017/3/14
 */
public class StackX {
    /**
     * 定义栈的容量
     */
    private int maxSize;
    /**
     * 定义栈体
     */
    private char[] stackArray;
    /**
     * 定义栈的栈顶指针
     */
    private int top;

    /**
     * 初始化构造器
     */
    public StackX(int maxSize, char[] stackArray) {
        this.maxSize = maxSize;
        this.stackArray = stackArray;
        this.top = -1;
    }
    /**
     * 数据压栈的方法
     *
     */
    public void  push(char s){
        stackArray[++top]=s;
    }
    /**
     * 数据弹栈操作
     */
    public char pop(){
        return  stackArray[top--];
    }
    /**
     * 获取栈顶的操作
     */
    public char getTop(){
        return stackArray[top];
    }
    /**
     * 判断栈是否满了？
     */
    public boolean isFull(){
        return stackArray.length==maxSize;
    }
    /***
     * 判断栈是否为空
     */
    public boolean isEmpty(){
        return top==-1;
    }
}
