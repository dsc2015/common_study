package com.jd.study.common.javase_base.ref;
/**
 * @description finalize()方法只被调用一次。不建议覆盖这个方法的原因是：比较耗费性能，因为会在内存中创建大量的
 * Java.lang.ref.Finalizer对象
 * @author dushuangcheng
 * @create 2017/5/5
 */
public class FinallizerFunctionTest {
    public static void main(String[] args) {
        FinallizerFunctionTest finallizerFunctionTest = new FinallizerFunctionTest();
        finallizerFunctionTest.hashCode();
        System.gc();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println("to do finalizer");
    }
}
