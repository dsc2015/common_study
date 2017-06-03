package com.jd.study.common.javase_base.number_test;
/**
 * @author dushuangcheng
 * @create 2017-01-06 16:02
 * 数据会越界，越界之后按照计算机补码的运算结果
 * 在设计加法的时候一定要注意这个问题
 *
 * 那么对于Integer.parse()中出现超出范围的情况，jdk的源代码中是如何解决的呢？
 */
public class NumberBeyondLimit {
    public static void main(String[] args) {
        Integer a=Integer.MAX_VALUE;
       //转化为二进制为； 1111111111111111111111111111111   2^31-1  按照计算机补码运算为：01111111111111111111111111111111
        //加上1之后：     1000000000000000000000000000000，最高位为符号位：按照计算机补码运算结果为-2147483648
        //这样就会出现 a+1<a的情况了
        //
        System.out.println(a);
        System.out.println(a+1);
        //越界
        System.out.println(a<a+1);//false
        System.out.println((a+1)==Integer.MIN_VALUE);//true

        int c = Integer.parseInt("2147483647");
        System.out.println(c);

        //越界的情况 直接抛出异常NumberFormatException
        int d = Integer.parseInt("2147483649");
        System.out.println(d);
    }


}
