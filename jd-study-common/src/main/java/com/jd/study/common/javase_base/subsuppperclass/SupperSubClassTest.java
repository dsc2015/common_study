package com.jd.study.common.javase_base.subsuppperclass;
/**
 *测试的目的：测试子类继承父类之后，子类super调用父类的方法中有部分方法被子类所覆盖之后，super中方法如何执行？
 *
 *猜想：这些方法有可能调用父类的？这些方法调用子类的？
 *
 *
 * 结论：这些方法调用子类的

 * @description
 * @author dushuangcheng
 * @create 2017/6/28
 */
public class SupperSubClassTest {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        int test = subClass.method0(888, "test");
        System.out.println("in test, the result is:"+test);
    }
}
