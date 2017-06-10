package com.jd.study.common.javase_base.arithmetic;


/**
 * 堆排序算法：插入N个数据项，然后再进行N次的删除操作
 * 步骤：1,先把数组类型的数据结构，按照堆类型的数据结构进行初步的排序，使之符合堆类型的数据
 * 结构，然后按照堆类型的数据结构的删除方法进行排序，原理是堆的根节点一般都是最大或者最小类型的数据类型进行
 * 排序。
 *
 * 对于堆类型的数据结构：插入的时候是向上回溯的算法，要插入的数据项先放在数组的第一个空的单元的位置，然后进行向上
 * 回溯到合适的位置；
 * 删除操作的时候，总是删除根部最大的数据项，然后把最后一个数据项移动到根部，然后进行向下的回溯算法
 * 直到该数据结构符合堆类型的数据结构。
 *
 * 时间复杂度为：o(nlgN)
 * @author dushuangcheng
 * @create 2017-06-06 9:23
 */
public class HeapSort<T extends Comparable<T>> {
    public static void main(String[] args) {
        Heap<Integer> heapArray = new Heap<Integer>(11);
        //先进行赋值的操作
        for(int i=0;i<heapArray.maxSize;i++){
            Integer randomInt= (int)(Math.random()*100);
            Node<Integer> node=new Node<>();
            node.setData(randomInt);
            heapArray.heapArray[i]=node;
            heapArray.currentSize++;
        }
        System.out.println("==================start=================");
       /* for(int j=0;j<heapArray.currentSize/2;j++){
            heapArray.remove();
        }*/
        //进行排序使之形成符合堆的抽象的数据结构要求的数组
        for(int j=(heapArray.currentSize/2-1);j>=0;j--){
            heapArray.tickleDown(j);
        }
        //利用删除进行排序,删除之后的元素放在同一个数组之中
        for(int j=heapArray.currentSize-1;j>=0;j--){
            //值最大的节点
            Node<Integer> biggestNode = heapArray.remove();
            heapArray.heapArray[j]=biggestNode;
        }

        //输出数组的内容
        for(int i=0;i<heapArray.maxSize;i++){
            System.out.println("order================="+heapArray.heapArray[i].getData());
        }

        System.out.println("==================end====================");


    }

    static class Node<T extends Comparable<T>> implements Comparable<T> {
        private T data;
        private Node<T> leftChild;
        private Node<T> rightChild;
        private Node<T> parent;

        public Node(T data) {
            this.data = data;
        }

        public Node() {
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    /**
     * @description 复写comparable方法
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/6
     */
        @Override
        public int compareTo(T o) {
            return this.data.compareTo(o);
        }
    }

    /**
     * @param
     * @description heap本质上是一个node数组，采用一个堆的抽象的数据类型进行排序
     * @Author dushuangcheng
     * @return
     * @throw
     * @date 2017/6/6
     */
    static class Heap<T extends Comparable<T>> {
        /**
         * @description:堆数组
         */
        private Node<T>[] heapArray;
        /**
         * @description:数组最大长度
         */
        private int maxSize;
        /**
         * @description:当前的长度
         */
        private int currentSize;

        public Heap(int maxSize) {
            this.heapArray = new Node[maxSize];
            this.maxSize = maxSize;
            //当前的长度初始化为0
            this.currentSize = 0;
        }

        /**
         * @description 节点的删除操作，在这里主要是堆的根节点的删除操作
         * @Author  dushuangcheng
         * @return
         * @throw
         * @date   2017/6/6
         */
        public Node<T>  remove(){
            if(currentSize<=0){
                System.out.println("this array is empty!....");
                return null;
            }
            //把根保存在一个临时的变量中进行保存
            Node<T> root=heapArray[0];
            //把最后一个位置的数据移动到根节点处
            heapArray[0]=heapArray[--currentSize];
            //调用向下回溯法,把节点向下进行移动
            tickleDown(0);

            return root;
        }
        /**
         * @description
         * @Author  dushuangcheng
         * @param  index :要进行回溯操作的索引位置，对应二叉堆上的节点对应的下标索引的位置
         * @return
         * @throw
         * @date   2017/6/6
         */
        private void tickleDown(int index){
            //1，寻找最大的子孩子的下标索引，根据算法导论上的描述，最大索引的选择应该是：
            //heapArray[i],leftChild[j],rightChild[k];这三个中的一个，但是我们知道其实对于
            //叶子节点或者说是终端节点来说，它们是没有孩子的，因此本身就是一个不违反堆定义的规则的
            //树形结构。索引只需要在0----currentSize/2的范围内进行查找即可。
            int largestIndex;
            //保存index节点位置处的值
            Node<T> temp=heapArray[index];
            //循环进行查找
            while(index<currentSize/2){
                //当前节点的左孩子的索引位置:2*index+1
                int leftChild=2*index+1;
                //当前节点的右孩子的索引位置：2*index+2;
                int rightChild=leftChild+1;
                //进行大小比较
                if(leftChild<currentSize&&(heapArray[leftChild].getData().compareTo(heapArray[rightChild].getData())>0)){
                    largestIndex=leftChild;
                }else {
                    largestIndex=rightChild;
                }
                //已经有序了，后面的就不用执行
                if(temp.getData().compareTo(heapArray[largestIndex].getData())>0){
                    break;
                }
                //找到了孩子中较大值的那一个，就需要进行交换操作了,使得较大的节点位置的值进行上移覆盖当前节点
                //位置上的值,一直进行覆盖操作，最后空出的合适的位置就是要放temp值的位置。
                heapArray[index]=heapArray[largestIndex];
                index=largestIndex;
                //heapArray[largestIndex]=heapArray[index];
            }//循环结束
            heapArray[index]=temp;

        }

    }


}
