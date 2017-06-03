package com.jd.study.common.javase_base.selfreference;

import com.jd.study.common.javase_base.Enum.Main;

/**
 * @description
 * 自引用的话，就是一个成员变量的类型是该类的类型，注意这个类型不能使用new，否则会造成栈溢出的错误
 * @author dushuangcheng
 * @create 2017/3/9
 */
public class SelfReference {

    class Node{
        private int num;
        private Node nextNode;

        public Node() {
        }

        public Node(int num, Node nextNode) {
            this.num = num;
            this.nextNode = nextNode;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }

    public Node getNode(){
        return new Node();
    }

    public static void main(String[] args) {
        new SelfReference().getNode();
    }
}
