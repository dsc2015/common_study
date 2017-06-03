package com.jd.study.common.javase_base.dataStructure.BinaryTree;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/15
 */
public class BinaryTreeForStr {
    public static void main(String[] args) {
        BinaryTreeForStr binaryTreeForStr = new BinaryTreeForStr();
        binaryTreeForStr.insert("+");
        binaryTreeForStr.insert("A");
        binaryTreeForStr.insert("100");
        /*binaryTreeForStr.insert("B");
        binaryTreeForStr.insert("C");*/

        binaryTreeForStr.beforeSearch(binaryTreeForStr.root);
        System.out.println("--------------------------------");
        binaryTreeForStr.middleSearch(binaryTreeForStr.root);
        System.out.println("--------------------------------");
        binaryTreeForStr.afterSearch(binaryTreeForStr.root);


       // System.out.println(binaryTreeForStr);
    }

    private Node root;

    //中序遍历
    public void middleSearch(Node origin){
        if(origin==null){
            return;
        }
        middleSearch(origin.leftChild);
        System.out.print(origin.getData());
        middleSearch(origin.rightChild);
    }

    //前序遍历
    public void beforeSearch(Node origin){
        if(origin==null){
            return;
        }
        System.out.print(origin.getData());
        middleSearch(origin.leftChild);
        middleSearch(origin.rightChild);
    }

    //后序遍历
    public void afterSearch(Node origin){
        if(origin==null){
            return;
        }
        middleSearch(origin.leftChild);
        middleSearch(origin.rightChild);
        System.out.print(origin.getData());
    }

    public void insert(String data) {
        if (data == null) {
            return;
        }
        Node node = new Node();
        node.setData(data);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        Node parent;
        while (true) {
            parent = current;
            if (current.data.compareTo(data) < 0) {
                current = current.leftChild;
                if (current == null) {
                    parent.leftChild = node;
                    return;
                }
            }
            if (current.data.compareTo(data) == 0) {
                return;
            }
            if (current.data.compareTo(data) > 0) {
                current = current.rightChild;
                if (current == null) {
                    parent.rightChild = node;
                    return;
                }
            }
        }

    }


    class Node {
        private Node leftChild;
        private Node rightChild;
        private String data;

        public Node() {
        }

        public Node(Node leftChild, Node rightChild, String data) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.data = data;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
}
