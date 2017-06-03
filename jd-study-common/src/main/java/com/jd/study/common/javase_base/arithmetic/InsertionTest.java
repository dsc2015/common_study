package com.jd.study.common.javase_base.arithmetic;

/**
 * @author dushuangcheng
 * @description 插入排序的算法, 局部有序，局部无序部分，类似于打牌的时候对牌进行排序
 * 首先标记一个未排过序的最左端的一个队员，出列；这个队员对应的值被放在一个临时变量中，如下面代码中
 * 所示的temp变量，这个变量的值是不会改变的。
 * 出列之后，后面的队员对应的值只要比temp的值大，那么第一个这样的这个值就放到出列队员空出的位置上，
 * 之后空出的位置的值用temp代替，后面的重复执行这样的比较过程即可。
 * 后面的依次
 * 类推。
 * 分成两个部分：1 ，有序部分，2，未排序的部分，指定一个边界基准
 *
 * 这种排序算法的时间复杂度为：O(n*n)，适用于基本有序的数据之间进行排序
 * @create 2017/3/6
 */
public class InsertionTest {
    private int[] intArray = new int[]{2, 4, 3, 7, 5, -10, -4, 1, -11};

    public int[] sort(int[] input) {
        //外循环主要控制有序范围的调整
        for (int i = 1; i < input.length; i++) {
            int orderIndex = i;
            int unOrderIndex = orderIndex;
            int temp = input[orderIndex];
            //内循环主要控制引入一个无序的数，与有序部分数逐个进行比较以确定它的插入位置
            while (unOrderIndex > 0 && input[unOrderIndex - 1] <= temp) {
                input[unOrderIndex] = input[unOrderIndex - 1];
                --unOrderIndex;
            }
            input[unOrderIndex] = temp;
        }
        return input;
    }

    public int[] sort1(int[] input) {
        //外循环依次将有序和无序的部分范围进行扩大，缩小
        int out, in;
        for (in = 1; in < input.length; in++) {
            //取出的值
            int temp = input[in];
            out = in;
            while (in > 0 && input[in - 1] >= temp) {
                input[in] = input[in - 1];
                //进一步缩小有序范围
                --in;
            }
            input[in]=temp;
        }
        return input;
    }

    /**
     * 显式地进行交换数据方式
     * @param input
     * @return
     */
    public int[] sort2(int[] input) {
        //第一个数不要进行下面的步骤，故i<length-1
        for(int i=0;i<input.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(input[j-1]<=input[j]){
                    //循环结束
                    break;
                }
                //交换两个数值
                int temp=input[j];
                input[j]=input[j-1];
                input[j-1]=temp;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        InsertionTest insertionTest = new InsertionTest();
        int[] sort = insertionTest.sort2(insertionTest.intArray);
        for (int intNum : sort) {
            System.out.println("==============" + intNum);
        }
    }
}
