package com.jd.study.common.java_designer.decoratepattern.complicatedecoratepattern;

/**装饰类2
 * @author dushuangcheng
 * @description
 * @create 2017/3/22
 */
public class VerticalWindow extends AbstractWindowDecorator {
    /**
     * 构造器，由子类来实现
     *
     * @param decorateWindow
     */
    public VerticalWindow(Window decorateWindow) {
        super(decorateWindow);
    }
    @Override
    public void draw() {
        decorateWindow.draw();
        //super.draw();
        drawVerticalWindow();

    }
    @Override
    public String getDescription() {
        return decorateWindow.getDescription() + "draw window description!";
    }
    /**
     * 新添加的方法
     */
    private void drawVerticalWindow() {
        System.out.println("draw vertical window!");
    }
}
