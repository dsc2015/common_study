package com.jd.study.common.javase_base.arithmetic;
/**
 * @description 二分法算法
 * 前提是排好顺序
 * @author dushuangcheng
 * @create 2017/2/28
 */
public class BinarySearchTest {
    private int[] intArray=new int[]{-100,-120,-20,0,1,2,3,4,5,6,7,8,9};

    public int getIndex1(int target,int[] input){
        int lowBound=0;
        int highBound=input.length-1;

        while (lowBound<=highBound){
            //中间结果
            int searchIndex=(highBound+lowBound)/2;
            if(target==input[searchIndex]){
                return searchIndex;
            }
            if(target>input[searchIndex]){
                lowBound=searchIndex+1;
            }
            if(target<input[searchIndex]){
                highBound=searchIndex-1;
            }
        }
        //没有找到
        return -1;

    }


    public int getIndex(int searchKey){
        int lowBound=0;
        int highBound=intArray.length-1;

        int searchIndex=0;
        while(true){
            //取中间值
            searchIndex=(lowBound+highBound)/2;
            //如果相等
            if(intArray[searchIndex]==searchKey){
                return searchIndex;

                //分割完毕
            }else if(lowBound>highBound){
                return intArray.length;
            }
            //如果不等，继续切分
            if(intArray[searchIndex]>searchKey){
                //重新设置边界
                highBound=searchIndex-1;
            }
            if(intArray[searchIndex]<searchKey){
                //重新设置边界
                lowBound=searchIndex+1;
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTest binarySearchTest = new BinarySearchTest();
        int index = binarySearchTest.getIndex(9);
        System.out.println(index+"======================================="+binarySearchTest.intArray[index]);

        int index1 = binarySearchTest.getIndex1(9000, binarySearchTest.intArray);
        System.out.println("++++++++++++++++++"+index1);

        String abc="avd"+Integer.toString(11)+"fvs"+1+"dsc";
    }

}
