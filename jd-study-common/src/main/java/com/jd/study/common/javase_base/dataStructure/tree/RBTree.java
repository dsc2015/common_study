package com.jd.study.common.javase_base.dataStructure.tree;

//TreeMap中使用的是节点类型是Entry类型的，这种类型

/**
 * Entry 继承自Map的Entry类型，键值对类型的对象。
 * <p/>
 * 根据key的比较器进行排序来构造红黑树
 */
public class RBTree<T extends Comparable<T>> {
    private Node<T> root;

    //查找
    public int find(T target) {
        //从根节点开始查找
        Node<T> current = root;
        //循环终止条件
        while (current != null) {
            if(target.compareTo(root.value)<0){
                current=current.getLeft();
            }
            if(target.compareTo(root.value)>0){
                current=current.getRight();
            }
            else {
                return 1;
            }
        }
        return -1;
    }

    static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        boolean black = true;

        public Node(Node<T> left, Node<T> right, Node<T> parent) {
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public boolean isBlack() {
            return black;
        }

        public void setBlack(boolean black) {
            this.black = black;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    ", parent=" + parent +
                    ", black=" + black +
                    '}';
        }
    }
}
