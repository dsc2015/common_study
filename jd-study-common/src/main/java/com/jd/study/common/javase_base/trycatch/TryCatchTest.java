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

    public static void main(String[] args) {
        TryCatchTest tryCatchTest = new TryCatchTest();
        String method = tryCatchTest.method();
        System.out.println(method);
    }
}
