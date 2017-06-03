package com.jd.study.common.java_designer.decoratepattern.complicatedecoratepattern;

/**
 * 抽象是装饰类，目的是为了扩展多个装饰类
 * @author dushuangcheng
 * @create 2017-03-21 22:17
 */
public abstract class AbstractWindowDecorator implements Window {
    /**
     *
     */
    protected  Window decorateWindow;

    /**
     * 构造器，由子类来实现
     */
    public AbstractWindowDecorator(Window decorateWindow) {
        this.decorateWindow = decorateWindow;
    }
}
