package com.jd.study.common.javase_base.random;/**
 * Created by dushuangcheng on 2017/2/28.
 */

import java.util.Random;

/**
 *
 * 1，随机数带种子与不不带种子的区别
 * 带种子每次产生的随机数都是相同的
 * 2，对于随机数，有可能产生负值，这点需要注意。
 * @author dushuangcheng
 * @create 2017-02-28 11:09
 */
public class RandomTest2 {
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            Random random=new Random(10);
            int i1 = random.nextInt();
            int i2 = random.nextInt();
            int i3 = random.nextInt();
            System.out.println(i1+"_"+i2+"_"+i3);

        }
    }
}
