package com.jd.study.common.javase_base.multithread;/**
 * Created by dushuangcheng on 2016/10/20.
 */

/**
 * 对象逃逸的案例，在这个例子中对象通过构造函数逃逸
 * @author dushuangcheng
 * @create 2016-10-20 16:35
 */
public class Base {
    private int num=1;
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public Base() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                Base.this.setNum(1000);
                System.out.println(Base.this+"---"+num);
            }
        }).start();
    }
    public static void main(String[] args) {
        System.out.println( new Base().getNum());
    }
}
