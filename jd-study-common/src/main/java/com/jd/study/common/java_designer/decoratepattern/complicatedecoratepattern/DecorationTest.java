package com.jd.study.common.java_designer.decoratepattern.complicatedecoratepattern;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/3/22
 */
public class DecorationTest {
    static void printInfo(Window window){
        System.out.println("description"+window.getDescription());
    }
    public static void main(String[] args) {
        SimpleWindow simpleWindow = new SimpleWindow();
        printInfo(simpleWindow);

        HorizontalScrollBar horizontalScrollBar = new HorizontalScrollBar(simpleWindow);
        printInfo(horizontalScrollBar);

        VerticalWindow verticalWindow = new VerticalWindow(simpleWindow);
        printInfo(verticalWindow);
    }
}
