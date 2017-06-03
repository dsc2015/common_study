package com.jd.study.common.javase_base.arithmetic;
/**
 * @description 冒泡排序算法
 * 特点是：相邻的进行比较，这样第一趟会把最大或者最大的数交换到最后，在比较次数上跟选择排序差不多，然后
 * 比较次数上，冒泡排序的比较次数显然比较多。
 * 时间复杂度为n的平方级别。交换次数也比较多
 *
 * 选择排序：第一个依次与后面的进行比较，把大的或者小的拍到前面，从而第一趟会筛选出最大的数
 * 循环里面嵌套循环
 * @author dushuangcheng
 * @create 2017/3/3
 */
public class BubbleSortTest {
    private int[] intArray=new int[]{12,34,5,0,-5,-9,98,-56,100};

    //选择排序算法
    public int[] getSortedIntArray(int[] inputArray){
        int length=inputArray.length;
        for(int j=0;j<length-1;j++){
            for(int i=j;i<length-1;i++){
                if(inputArray[j]<inputArray[i+1]){
                    //则进行交换
                    int temp=inputArray[j];
                    inputArray[j]=inputArray[i+1];
                    inputArray[i+1]=temp;
                }
            }
        }
        return inputArray;
    }
    //冒泡排序法,相邻的进行比较，把最大的或者最小的沉到尾部，正好跟选择排序方法相反
    public int[] getSortedIntArrayBySel(int[] inputArray){
        int length=inputArray.length;
        for(int i=0;i<length-1;i++){
            for(int j=0;j<length-1-i;j++){
                //相邻的进行交换
                if(inputArray[j]<inputArray[j+1]){
                    int temp=inputArray[j];
                    inputArray[j]=inputArray[j+1];
                    inputArray[j+1]=temp;
                }
            }
        }
        return inputArray;
    }

    public static void main(String[] args) {
        BubbleSortTest bubbleSortTest = new BubbleSortTest();
       /* int[] sortedIntArray = bubbleSortTest.getSortedIntArray(bubbleSortTest.intArray);
        for(int k:sortedIntArray){
            System.out.println(k);
        }*/

        int[] sortedIntArray1 = bubbleSortTest.getSortedIntArrayBySel(bubbleSortTest.intArray);
        for(int k:sortedIntArray1){
            System.out.println(k);
        }
    }
}
