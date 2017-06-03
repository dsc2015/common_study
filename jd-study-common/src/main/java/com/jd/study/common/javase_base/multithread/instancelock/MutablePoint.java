package com.jd.study.common.javase_base.multithread.instancelock;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/3/24
 */
public class MutablePoint {
    private  int x, y;

    public MutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 拷贝构造函数
     * @return
     */
    public MutablePoint(MutablePoint mutablePoint){
        this.x=mutablePoint.getX();
        this.y=mutablePoint.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
