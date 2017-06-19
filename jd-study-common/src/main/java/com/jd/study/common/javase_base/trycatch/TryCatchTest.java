package com.jd.study.common.javase_base.trycatch;
/**
 * @description 先执行finally后return
 * @author dushuangcheng
 * @create 2017/3/16
 */
public class TryCatchTest {
    private boolean flag;

    public String method(){
        try {
            if(!flag){
                return "qqq";
            }
            System.out.println("tttt");
            return "kkkk";

        }catch (Exception e){
            return "aaa";
        }finally {
            System.out.println("1000");
        }


    }
    /**
     * @description 抛异常之后catch里面的return执行
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/15
     */
    public String method1(){
        try {
            int k=1/0;

        }catch (Exception e){
            return "aaa";
        }
        return "return";
    }

    public String method2(){
        try {
            int k=1/3;

        }finally {
            System.out.println("000000000000000");
        }
        System.out.println("1111111111");
        return "return";
    }

    public static void main(String[] args) {
        TryCatchTest tryCatchTest = new TryCatchTest();
        String method = tryCatchTest.method2();
        System.out.println(method);
    }
}
