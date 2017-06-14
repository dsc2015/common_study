package com.jd.study.common.javase_base.multithread.treiberstack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dushuangcheng
 * @description 非阻塞算法
 * @create 2017/6/11
 */
public class TreiBerStack<T> {
    public static void main(String[] args) {
        TreiBerStack<Integer> stack = new TreiBerStack<Integer>();
        ExecutorService pool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            pool.execute(new WorkerConsumer(stack));
            pool.execute(new WorkerProduct(stack));
            pool.execute(new WorkerConsumer(stack));
        }
        pool.shutdown();
    }

    static class WorkerProduct implements Runnable {
        private final TreiBerStack<Integer> treiBerStack;

        public WorkerProduct(TreiBerStack<Integer> treiBerStack) {
            this.treiBerStack = treiBerStack;
        }

        @Override
        public void run() {
            treiBerStack.push((int) (Math.random() * 1000));
            System.out.println("push=======================");
        }
    }

    static class WorkerConsumer implements Runnable {
        private final TreiBerStack<Integer> treiBerStack;

        public WorkerConsumer(TreiBerStack<Integer> treiBerStack) {
            this.treiBerStack = treiBerStack;
        }

        @Override
        public void run() {
            Integer pop = treiBerStack.pop();
            if (pop != null) {
                System.out.println("pop=======================" + pop.toString());
            }
        }
    }

    //栈顶元素
    private AtomicReference<Node<T>> top = new AtomicReference<>(new Node<T>());

    /**
     * @param element，一个新的入栈节点
     * @return
     * @description 入栈操作
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/11
     */
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        Node<T> oldNode;
        do {
            oldNode = top.get();
            newNode.next = oldNode;
        } while (!top.compareAndSet(oldNode, newNode));
    }

    public T pop() {
        Node<T> oldNode;
        Node<T> newNode;
        do {
            oldNode = top.get();
            if (oldNode == null) {
                return null;
            }
            newNode = oldNode.next;
        } while (!top.compareAndSet(oldNode, newNode));
        return oldNode.element;
    }

    static class Node<T> {
        //元素所包含的值
        public T element;
        //指向下一个元素的指针
        public Node<T> next;

        public Node() {
        }

        public Node(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
