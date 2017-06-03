package com.jd.study.common.javase_base.arithmetic;

/**
 * @author dushuangcheng
 * @description 归并排序，分治算法的体现
 * 思想是先把要排序的数组分成两半，再把各自的两半再分，一直分，然后一直分到一个数据项为之，这个就是
 * 基值条件。
 * @create 2017/3/22
 */
public class MergerSort {
    private int[] intArray;

    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    public void sort2(int[] inputData, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int len = end - start;
        int mid = (len >> 1) + start;

        int start1 = start;
        int end1 = mid;

        int start2 = mid + 1;
        int end2 = end;

        //两半分别递归
        sort2(inputData, result, start1, end1);
        sort2(inputData, result, start2, end2);
        //合并
        /**
         * 1,  N  N   N  N  N   N N N N N
         * 2,  3  6   2  7  4   8 0 -4 5 9
         *
         *     3,6 ,2,4,7
         *     2 4  3 6 7
         */
        int k = start;
        /**
         * 最后一个start1:0;end1:0  start2:1,end2:1,这个时候递归结束
         *
         *
         */
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = inputData[start1] < inputData[start2] ? inputData[start1++] : inputData[start2++];
        }
        while (start1 <= end1) {
            result[k++] = inputData[start1++];
        }
        while (start2 <= end2) {
            result[k++] = inputData[start2++];
        }
        //交换数据
        for (k = start; k < end; k++) {
            inputData[k] = result[k];
        }
    }
    public void sort(int[] inputData, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        //长度
        int len = end - start;
        //中间值
        int mid = (len >> 1) + start;
        //
        int start1 = start;
        int end1 = mid;
        //
        int start2 = mid + 1;
        int end2 = end;
        //对第一半进行排序
        sort(inputData, result, start1, end1);
        //对第二半进行排序
        sort(inputData, result, start2, end2);
        //合并
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = inputData[start1] < inputData[start2] ? inputData[start1++] : inputData[start2++];
        }
        while (start1 <= end1) {
            result[k++] = inputData[start1++];
        }
        while (start2 <= end2) {
            result[k++] = inputData[start2++];
        }
        //交换数据
        for (k = start; k <= end; k++) {
            inputData[k] = result[k];
        }
        System.out.println("-------------------------------------");

    }
    //归并排序
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        internalMergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void internalMergeSort(int[] a, int[] b, int left, int right) {
        //当left==right的时，已经不需要再划分了
        if (left < right) {
            int middle = (left + right) / 2;
            internalMergeSort(a, b, left, middle);          //左子数组
            internalMergeSort(a, b, middle + 1, right);       //右子数组
            mergeSortedArray(a, b, left, middle, right);    //合并两个子数组
        }
    }
    // 合并两个有序子序列 arr[left, ..., middle] 和 arr[middle+1, ..., right]。temp是辅助数组。
    private static void mergeSortedArray(int arr[], int temp[], int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= middle) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        //把数据复制回原数组
        for (i = 0; i < k; ++i) {
            arr[left + i] = temp[i];
        }
    }
    public static void main(String[] args) {
        MergerSort mergerSort = new MergerSort();
        mergerSort.setIntArray(new int[]{100, 30, 40,-20,15});
        int[] result = new int[5];
       // mergerSort.mergeSort(mergerSort.getIntArray());
        mergerSort.sort(mergerSort.getIntArray(), result, 0, 4);
    }
}
