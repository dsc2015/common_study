package com.jd.study.common.javase_base.arithmetic;

/**
 * 由于快速排序比较复杂，衍生出了很多的算法，所以要很好的理解每一个算法的变种
 *
 * @author dushuangcheng
 * @description 三数据项居中的快速排序算法
 * @create 2017/3/29
 */
public class QuickSortWithThree {
    /**
     * 数组
     */
    private int[] data;
    /**
     * 元素的个数
     */
    private int nElements;

    /**
     * 快速排序
     *
     * @param left
     * @param right
     */
    public void quickSort(int left, int right) {
        recQuickSort(left, right);
    }

    public void recQuickSort(int left, int right) {
        //判断是否满足排序要求
        if (left >= right) {
            return;
        }
        //获取排序元素的长度
        int size = right - left + 1;
        //三数据项居中的排序算法要求至少要有4个以上的元素
        if (size <= 3) {
            //调用手动排序，若用快速排序浪费性能
            manualSort(left, right);
        }
        //调用三数据项的方法获取枢纽数据
        int pivotWithThree = getPivotWithThree(left, right);
        //获取分区索引
        int partitionIndex = getPartitionIndex(left, right, pivotWithThree);
        //左子数组递归调用
        recQuickSort(left, partitionIndex - 1);
        //右子数组递归调用
        recQuickSort(partitionIndex + 1, right);
    }

    /**
     * 所谓的手动排序方式
     *
     * @return
     */
    public void manualSort(int left, int right) {
        int size = right - left + 1;
        if (size <= 1) {
            return;
        }
        if (size == 2) {
            if (data[left] > data[right])
                swap(left, right, data);
            return;
        }
        if (size == 3) {
            if (data[left] > data[left + 1]) {
                swap(left, left + 1, data);
            }
            if (data[left] > data[right]) {
                swap(left, right, data);
            }
            if (data[right - 1] > data[right]) {
                swap(left, right, data);
            }
        }
    }

    /**
     * 获取分区索引值/指针值
     *
     * @param left
     * @param right
     * @return
     */
    public int getPartitionIndex(int left, int right, int pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;//右边不必要进行判断指针越界问题
        while (true) {
            while (data[++leftPtr] < pivot) ;
            while (data[--rightPtr] > pivot) ;
            if (rightPtr < leftPtr)
                break;
            swap(leftPtr, rightPtr, data);
        }
        //交换值，把中间值交换回来
        swap(leftPtr, right - 1, data);
        return leftPtr;


    }

    /**
     * 获取枢纽值
     *
     * @param left
     * @param right
     * @return
     */
    public int getPivotWithThree(int left, int right) {
        //获取中间值的索引位置
        int mid = (left + right) / 2;
        //对这三个数据项进行排序
        if (data[left] > data[mid]) {
            swap(left, mid, data);
        }
        if (data[mid] > data[right]) {
            swap(mid, right, data);
        }
        if (data[left] > data[right]) {
            swap(left, right, data);
        }
        //?放到最右边的前面？
        swap(mid, right - 1, data);
        //返回枢纽值
        return data[right - 1];
    }
    public void swap(int left, int right, int[] inputData) {
        int temp = inputData[left];
        inputData[left] = inputData[right];
        inputData[right] = temp;
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
}
