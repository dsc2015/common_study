package com.jd.study.common.javase_base.equalstudy;
/**
 * @description :
 * 测试1：
 * this对象跟这个类的对象的任何一个实例都是相等的？
 * 结论：
 * 是的，在方法的内部的this就是指代当前对象的这个实例，所以是相等的。
 * @author dushuangcheng
 * @create 2017/6/23
 */
public class EqualStudy {
    public static void main(String[] args) {
        EqualStudy equalStudy = new EqualStudy(2,3L);
        System.out.println(equalStudy.equal(equalStudy));
    }

    public EqualStudy() {
    }

    public EqualStudy(Integer a, Long c) {
        this.a = a;
        this.c = c;
    }

    private Integer a=1;
    private Long c;
    public boolean equal(EqualStudy e){
        return this.equals(e);
    }
}
