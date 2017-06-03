package com.jd.study.common.javase_base.multithread.threadsafe;
/**
 * @description 构造函数中对象的逸出
 * //ObjectEscape@436在构造函数中逸出，调用方法的this就是逸出的对象
 * @author dushuangcheng
 * @create 2017/3/23
 */
public class ObjectEscape {
    public ObjectEscape(EventSource eventSource){
        new FunctionFace(){
            @Override
            public void onEvent(Object e) {
                System.out.println("======"+this);
                print();
            }
        };
    }
    public void print(){
        System.out.println("构造器中的匿名内部类。。。。");
    }

    public static void main(String[] args) {
        ObjectEscape objectEscape = new ObjectEscape(new EventSource());
    }
}
