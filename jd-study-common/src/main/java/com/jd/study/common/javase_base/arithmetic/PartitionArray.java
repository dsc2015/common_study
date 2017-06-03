package com.jd.study.common.javase_base.arithmetic;

/**
 * @author dushuangcheng
 * @description 划分思想
 * @create 2017/3/28
 */
public class PartitionArray {
    /**
     * 数据
     */
    private int[] dataArray;
    /**
     * 数组中的数据项
     */
    private int nElements;

    public int[] getDataArray() {
        return dataArray;
    }

    public void setDataArray(int[] dataArray) {
        this.dataArray = dataArray;
    }

    public int getnElements() {
        return nElements;
    }

    public void setnElements(int nElements) {
        this.nElements = nElements;
    }

    /**
     * 数据划分的方法
     */
    public int partitionArray(int left, int right, int pivot) {
        //左指针
        int leftPtr = left - 1;
        //右指针
        int rightPrt = right + 1;
        while (true) {
            while (leftPtr < right && dataArray[++leftPtr] < pivot) {
                //空循环
            }
            while (rightPrt > left && dataArray[--rightPrt] > pivot) {
                //空循环
            }
            //指针相遇
            if (leftPtr >= rightPrt) {
                break;
            } else {
                int temp = dataArray[leftPtr];
                dataArray[leftPtr] = dataArray[rightPrt];
                dataArray[rightPrt] = temp;
            }

        }
        //返回分区位置
        return leftPtr;

    }

    public static void main(String[] args) {
        PartitionArray partitionArray = new PartitionArray();
        int[] data = new int[]{-5, 8, 2, 5, 1, 0};
        partitionArray.setDataArray(data);

        int i = partitionArray.partitionArray(0, 5, 4);
        System.out.println("==========================="+i);
    }
}
