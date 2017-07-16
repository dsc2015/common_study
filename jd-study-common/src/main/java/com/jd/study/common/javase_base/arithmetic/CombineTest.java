package com.jd.study.common.javase_base.arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dushuangcheng
 * @description 测试组合算法
 * @create 2017/7/10
 */
public class CombineTest {
    private Integer[] arr1 = {1, 2, 3, 4};
    private Integer[] arr2 = {5, 6};
    private Integer[] arr3 = {7, 8};
    private Integer[] arr4 = {9, 10};
    private List<Integer[]> arrays = new ArrayList<>();


    public static void combiantion0(List<Integer[]> arrays) {
        if (arrays == null || arrays.size() == 0) {
            return;
        }
        List<Integer[]> list = new ArrayList();
        //i跟数目有关系，因为可以确定需要进行几次循环
        for (int i = 1; i <= arrays.size(); i++) {
            combine0(arrays, 0, i, list);

        }
    }
    /**
     * @description
     * @Author  dushuangcheng
     * @param  arrays:目标要操作的数据
     * @param  begin：开始的索引位置,开始从0开始
     * @param  number：要选择的数目，开始从1开始
     * @param  list：存放中间结果的list
     * @return
     * @throw
     * @date   2017/7/11
     */
    public static void combine0(List<Integer[]> arrays, int begin, int number, List<Integer[]> list) {
        /**
         * [0,1,4],[5,7],[9,10]
         *
         *
         * 假设我们想在长度为n的字符串中求m个字符的组合。我们先从头扫描字符串的第一个字符。针对第一个字符，
         * 我们有两种选择：一是把这个字符放到组合中去，接下来我们需要在剩下的n-1个字符中选取m-1个字符；
         * 二是不把这个字符放到组合中去，接下来我们需要在剩下的n-1个字符中选择m个字符。这两种选择都很容易用递归实现
         *
         */
        if (number == 0) {
           // System.out.println(list);
            return;
        }
        if (begin == arrays.size()) {
            return;
        }
        list.add(arrays.get(begin));
        //加完之后进行处理
        //加入到组合中去
        combine0(arrays, begin + 1, number - 1, list);
        list.remove(arrays.get(begin));
        //没有加入到组合中去
        combine0(arrays, begin + 1, number, list);
    }


    public static void combiantion(char chs[]) {

        if (chs == null || chs.length == 0) {
            return;
        }
        List<Character> list = new ArrayList();
        for (int i = 1; i <= chs.length; i++) {
            combine(chs, 0, i, list);
        }
    }

    //从字符数组中第begin个字符开始挑选number个字符加入list中
    public static void combine(char[] cs, int begin, int number, List<Character> list) {
        if (number == 0) {
            //进行数据处理
            System.out.println(list.toString());
            return;
        }
        if (begin == cs.length) {
            return;
        }
        list.add(cs[begin]);
        combine(cs, begin + 1, number - 1, list);
        list.remove((Character) cs[begin]);
        combine(cs, begin + 1, number, list);
    }

    public static void main(String args[]) {
        for(;;){
            Random random = new Random();
            int k = random.nextInt(11)%11;
            System.out.println(k);
        }


        /*char[] cs = {'a', 'b', 'c', 'd'};
        //combiantion(cs);
        Integer[] arr1 = {1, 2, 3, 4};
        Integer[] arr2 = {5, 6};
        Integer[] arr3 = {7, 8};
        Integer[] arr4 = {9, 10};
        List<Integer[]> arrays = new ArrayList<>();
        arrays.add(arr1);
        arrays.add(arr2);
        arrays.add(arr3);
        arrays.add(arr4);
        combiantion0(arrays);*/
    }

    public void get(){
       // this.getClass().getClassLoader().getResource();
    }
}
