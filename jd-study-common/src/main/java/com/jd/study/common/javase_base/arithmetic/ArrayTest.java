package com.jd.study.common.javase_base.arithmetic;

import java.util.HashMap;

/**
 * @author dushuangcheng
 * @create 2017-05-28 10:34
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] array=new int[]{1,3,5,6,8,100};
        int[] reverse = reverse(array);
        System.out.println(reverse);

        //测试hashMap的扩容机制
        HashMap<Integer, String> map = new HashMap<Integer, String>(4);
        //
        map.put(1,"200");
        map.put(2,"300");
        map.put(3,"400");
        map.put(4,"500");
        map.put(5,"600");



    }
    //数组翻转
    public static int[] reverse(int[] input){
        for(int i=0,j=input.length-1;i<j;i++,j--){
            int temp=input[i];
            input[i]=input[j];
            input[j]=temp;
        }
        return input;
    }
}
