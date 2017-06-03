package com.jd.study.common.java_designer.decoratepattern;
/**
 * @description 具体的装饰类
 * 装饰的方式是通过构造函数进行的，通过在构造函数中进行包裹
 * 装饰器设计模式可以在运行时增强相关的功能
 * @author dushuangcheng
 * @create 2017/3/21
 */
public class DecorateClass<T> implements TargetInterface {
    /**
     * 一个包含接口类型的委托类
     */
    private TargetInterface delegate;
    /**
     * 扩展字段1
     */
    private Integer num;
    /**
     * 扩展字段2
     */
    private String name;
    /**
     * 扩展字段3
     */
    private T data;

    //一般通过构造器进行扩展和构造

    /**
     * 主要通过其余的字段和一个包含目标类的对象一起织入构造器中去
     * @param t
     * @param num
     * @param name
     */
    public DecorateClass(TargetInterface t,Integer num,String name){
        this.delegate=t;
        this.name=name;
        this.num=num;
    }

    /**
     * 这个方法的扩展既可以通过继承一个类之后对原有类的方法进行扩展，还可以以接口的形式进行扩展
     * @param a
     * @param b
     */
    @Override
    public void function0(int a, String b) {
        System.out.println(a+"方法进行扩展"+b);
    }
}
