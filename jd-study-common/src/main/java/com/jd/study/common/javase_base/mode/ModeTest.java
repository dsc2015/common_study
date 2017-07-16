package com.jd.study.common.javase_base.mode;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/6/27
 */
public class ModeTest {
    public static void main(String[] args) {
        int num = 329846666;
        for (int i = 0; i < 20; i++) {
            //System.out.println("-----------------------"+(int)Math.pow(2, (i+1)));
            int k = num % ((int) (Math.pow(2, (i+1))));
            System.out.println("+++++++++++++++++++++++++"+k);
        }

        int Mode=32;
        int a=15;
        int b=31;
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%"+a%Mode);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%"+b%Mode);

    }
}
