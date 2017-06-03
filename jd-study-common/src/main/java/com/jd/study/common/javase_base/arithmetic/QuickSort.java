package com.jd.study.common.javase_base.arithmetic;
/**
 * @description 基于分治，划分思想的快速排序法
 * @author dushuangcheng
 * @create 2017/3/28
 */
public class QuickSort {
    /**
     * 数据
     */
    private int[] data;
    /**
     * 数据
     */
    private int nElements;

    /**
     * 分区算法
     * pivot是一个枢纽
     *
     * 由于每次都选择右子数组的最右端的数字作为枢纽，所以需要改进分区算法
     * 改进之后的分区算法，使得分区不能包含在右子数组中
     * @return
     */
    public int getPartition(int left,int right,int pivot){
        int leftPtr=left-1;//左指针
        int rightPtr=right;//右指针,直接忽略最右边的

        while (true){
            while (data[++leftPtr]<pivot);
            while (rightPtr>0 && data[--rightPtr]>pivot);
            if(leftPtr>=rightPtr){
                break;
            }else {
                int temp=data[rightPtr];
                data[rightPtr]=data[leftPtr];
                data[leftPtr]=temp;
            }
        }
        //处理leftPtr与right位置上的两个值，交换
        int temp=data[leftPtr];
        data[leftPtr]=data[right];
        data[right]=temp;

        return leftPtr;
    }

    /**
     * 递归的方式
     * 快速排序算法
     * @return
     */
    public void quickSort(int left,int right){
        if(right<=left){
            return;
        }
        //获取枢纽值
        int pivot=data[right];
        int partitionIndex=getPartition(left,right,pivot);
        //递归调用
        quickSort(left,partitionIndex-1);
        //把枢纽位置的值排除了
        quickSort(partitionIndex+1,right);
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getnElements() {
        return nElements;
    }

    public void setnElements(int nElements) {
        this.nElements = nElements;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.setData(new int[]{5,2,6,-8,0,-10});
        quickSort.quickSort(0,5);
    }
}
