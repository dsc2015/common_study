package com.jd.study.common.javase_base.ref;

import sun.misc.Unsafe;

/**
 * @description 使用finalize方法容易造成OOM的异常的主要作用是对象的回收速度赶不上对象的创建的速度导致内存中的
 * 对象越来越多。
 * @author dushuangcheng
 * @create 2017/5/6
 */
public class DemoFinallizer {
    private long address;
    private Unsafe unsafe=Unsafe.getUnsafe();

    /**
     * @description 分配内存
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/5/6
     */
    public DemoFinallizer(){
        address=unsafe.allocateMemory(2*1024*1024);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        unsafe.freeMemory(address);
    }

    public static void main(String[] args) {
        while (true){
            DemoFinallizer demoFinallizer = new DemoFinallizer();
            System.out.println("-------------------"+demoFinallizer.address);
        }
    }
}
