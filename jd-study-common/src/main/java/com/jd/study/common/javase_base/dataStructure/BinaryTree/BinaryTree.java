package com.jd.study.common.javase_base.dataStructure.BinaryTree;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/3/29
 */
public class BinaryTree {
    public Node root;

    //前序遍历
    public void beforeSearch(Node origin) {
        if(origin==null){
            return;
        }
        //先遍历自身
        System.out.println("前序遍历+++++++++++++---->"+origin);
        //再遍历节点的左子节点
        beforeSearch(origin.leftChild);
        //再遍历节点的右子节点
        beforeSearch(origin.rightChild);
    }

    //中序遍历
    public void middleSearch(Node localNode) {
        if (localNode == null) {
            return;
        }
        //调用自身遍历左子树
        middleSearch(localNode.leftChild);
        //遍历当前的节点
        System.out.println("====================>" + localNode);
        //遍历右子树
        middleSearch(localNode.rightChild);

    }

 /*   //删除
    public boolean delete(Integer key){

    }*/

    public Node find(Integer key) {
        Node current = root;
        while (!current.getData().equals(key)) {
            //判断去哪个分支
            if (key < current.getData()) {
                //去左子树
                current = current.leftChild;
            }
            if (key > current.getData()) {
                //去右子树
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void insert(Integer data) {
        Node node = new Node();
        node.setData(data);
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            //parent是为了保留上一次遍历到的节点，防止到最后的null处，currnt节点没办法返回，
            //所以保留了一个这样的节点，用于指向下一次遍历到的current节点。
            Node parent;
            while (true) {
                parent = current;
                if (data < current.getData()) {
                    current = current.leftChild;
                    //到底部了
                    if (current == null) {
                        parent.leftChild = node;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = node;
                        return;
                    }
                }
            }
        }

    }


    class Node {
        private Integer data;
        private Node leftChild;
        private Node rightChild;

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
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

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(Integer.valueOf(4));
        binaryTree.insert(Integer.valueOf(3));
        binaryTree.insert(Integer.valueOf(8));
        binaryTree.insert(Integer.valueOf(9));
        binaryTree.insert(Integer.valueOf(7));
        binaryTree.insert(Integer.valueOf(-5));
        binaryTree.insert(Integer.valueOf(121));
        binaryTree.insert(Integer.valueOf(111));
        binaryTree.insert(Integer.valueOf(-30));

        Node node = binaryTree.find(-30);
        // System.out.println(node);

        //binaryTree.middleSearch(binaryTree.root);
        binaryTree.beforeSearch(binaryTree.root);
    }

}
