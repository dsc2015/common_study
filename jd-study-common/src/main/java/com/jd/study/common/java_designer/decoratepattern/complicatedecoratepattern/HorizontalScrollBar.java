package com.jd.study.common.java_designer.decoratepattern.complicatedecoratepattern;
/**
 * @description 装饰类1
 * @author dushuangcheng
 * @create 2017/3/22
 */
public class HorizontalScrollBar extends AbstractWindowDecorator {
    /**
     * 构造器，由子类来实现
     *
     * @param decorateWindow
     */
    public HorizontalScrollBar(Window decorateWindow) {
        super(decorateWindow);
    }
    @Override
    public void draw() {
        decorateWindow.draw();
        drawHorizontal();
    }

    @Override
    public String getDescription() {
        return decorateWindow.getDescription()+"this is horizontal window!";
    }

    private void drawHorizontal(){
        System.out.println("to draw horizontal window!");
    }
}
