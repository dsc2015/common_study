package com.jd.study.common.java_designer.decoratepattern.complicatedecoratepattern;

/**
 * 一个未经过装饰的类
 * @author dushuangcheng
 * @create 2017-03-21 22:15
 */
public class SimpleWindow implements Window {
    @Override
    public void draw() {
        //doDraw;
    }
    @Override
    public String getDescription() {
        return "this is a simple window!";
    }
}
