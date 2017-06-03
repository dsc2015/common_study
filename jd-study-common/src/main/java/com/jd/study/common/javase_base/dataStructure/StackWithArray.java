package com.jd.study.common.javase_base.dataStructure;

/**
 * @author dushuangcheng
 * @description 用数组实现的栈的抽象数据结构
 * 1，栈是一种数据结构，ADT,是一种访问受限的数据结构。这种数据结构在访问的时候只能访问
 * 或者操作一端的数据结构。
 * 2，实现的形式有两种1，数组的形式；2，链表的形式,   两种形式的特点，前者的容量是不可以扩展的，用链表实现的栈结构是可以
 * 进行扩展的。
 * @create 2017/3/7
 */
public class StackWithArray {
    private long maxSize;
    //栈的初始下标
    private long top = -1L;
    private long[] stackArray;

    //pop方法，用于弹栈操作,弹栈的时候先把栈指针下移一位，把值返回
    public long pop() throws Exception {
        //'判断是否为空？
        if (stackArray.length <= 0) {
            System.out.println("栈为空，不能弹出数据。。。");
            throw new Exception("栈为空，不能弹出数据。。。");
        }
        return stackArray[(int) (top--)];
    }

    //push；用于入栈操作,先把栈指针上移一位
    public void push(long input) throws Exception {
        if (top + 1 > maxSize) {
            System.out.println("栈空间满了，不能加入数据。。。");
            throw new Exception("栈空间满了，不能加入数据。。。");
        }
        top++;
        stackArray[((int) (top))] = input;
    }

    public long peek() throws Exception {
        //'判断是否为空？
        if (top < 0) {
            System.out.println("栈为空，不能弹出数据。。。");
            throw new Exception("栈为空，不能弹出数据。。。");
        }
        return stackArray[((int) (top))];
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public long getTop() {
        return top;
    }

    public void setTop(long top) {
        this.top = top;
    }

    public long[] getStackArray() {
        return stackArray;
    }

    public void setStackArray(long[] stackArray) {
        this.stackArray = stackArray;
    }

    public static void main(String[] args) throws Exception {
        StackWithArray stackWithArray = new StackWithArray();
        stackWithArray.setMaxSize(5);
        stackWithArray.setStackArray(new long[5]);

        for (int i = 0; i < 4; i++) {
            stackWithArray.push(3L);
        }
        stackWithArray.push(9L);
        //stackWithArray.pop();

        System.out.println("===================" + stackWithArray.pop());
        System.out.println("===================" + stackWithArray.pop());
        for (long value : stackWithArray.getStackArray()) {
            System.out.println(value);
        }


    }
}
