package com.jd.study.common.javase_base.multithread.threadsafe;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/3/23
 */
public class EventSource {
    private int age;
    private String str;


    /**
     * 提供一个方法
     * @return
     */
    public void registerListener(FunctionFace functionFace){
        functionFace.onEvent(new Object());
        System.out.println("调用functionFace");
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
