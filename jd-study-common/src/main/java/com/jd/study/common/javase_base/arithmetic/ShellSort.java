package com.jd.study.common.javase_base.arithmetic;

import java.util.Arrays;
import java.util.List;

/**
 * @author dushuangcheng
 * @description 希尔排序
 * @create 2017/3/22
 */
public class ShellSort {
    private int[] data;

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    /**
     *
     * [2,5,4,-5,6,-1,7,1]
     */
    public void sort(int[] inputData) {
        int inner, outer;
        int temp;
        //获取步长
        int h = 1;
        while (h <= inputData.length / 3) {
            h = h * 3 + 1;//4,7
        }
        while (h > 0) {
            for (outer = h; outer < inputData.length; outer++) {
                temp = inputData[outer];
                inner = outer;
                while ((inner > h - 1) && (inputData[inner - h] >= temp)) {
                    inputData[inner] = inputData[inner - h];
                    inner -= h;
                }
                inputData[inner] = temp;
            }
            h=(h-1)/3;
        }
    }

    /**
     * 100,4,-2,8,3,-13,-30
     *
     * i=4,temp=3;-->j=0,inputData[0]=100>temp?(inputData[4]=100,j=0-h=-4;input[0]=temp=3;   3,4,-2,8,100,-13,-30
     * i=5,temp=-13;---->j=1,4>-13?inputData[5]=4,j=1-h=-3;input[1]=temp=-13 ;               3,-13,-2,8,100,4,-30
     *i=6,temp=-30;----->j=2,-2>-30?inputData[6]=-2,j=-2;input[2]=temp=-30                   3,-13,-30,8,100,4,-2
     *h=1
     *
     *i=1,temp=-13---->j=0,3>-13,                                                            -13,3,-30,8,100,4,-2
     *i=2.temp=-30------>j=1,                                                                -13,3,3,8,100,4,-2
     *i=2.temp=-30------>j=0,                                                                -13,-13,3,8,100,4,-2
     *i=2.temp=-30------>                                                                    -30,-13,3,8,100,4,-2
     *
     *
     *
     *
     *
     *
     * @param inputData
     */

    //步长为1退化为插入排序
    public void sort2(int[] inputData){
        //定义初始步长为1
        int h=1;
        int i,j;
        int length=inputData.length;
        int temp;
        //获取最大可用的步长
        while (h<length/3){
            h=3*h+1;
        }
        while(h>0){
            for(i=h;i<length;i++){
                temp=inputData[i];
                //取最大步长位置处的值为temp，然后从起始位置处按步长间隔进行数值的比较
                //比较之后
                for(j=i-h;j>=0&&inputData[j]>temp;j-=h){
                    inputData[h+j]=inputData[j];
                }
                inputData[h+j]=temp;
            }
            h/=3;
        }
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        shellSort.setData(new int[]{100,4,-2,8,3,-13,-30});
        shellSort.sort2(shellSort.getData());
        //int a=(1 << (Integer.MAX_VALUE-3)) - 1;
        //System.out.println(a);
    }
}
