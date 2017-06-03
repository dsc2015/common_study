package com.jd.study.common.spring_study.spring_annotation;/**
 * Created by dushuangcheng on 2016/10/15.
 */

import com.jd.study.common.spring_study.spring_annotation.impl.FunctionInrefaceImpl_1;

import java.util.Date;

/**
 * @author dushuangcheng
 * @create 2016-10-15 16:39
 *
 * 关于debug调试的相关知识：(快捷键：shift+F9)
 * 1,在断点之间一步步调试，（快捷键：F8）两个圆状的图标，如果当前断点是一个方法，那么不会进入方法体中去。这个也可以在进入到源码中的时候逐行执行
 * ，遇到调用方法的时候不会进入到方法中，但是可以用step into 进入到方法中去。
 * 2,/接上面的第一点，加入进入了断点处的方法体中，需要一步步执行的话，可以使用一个箭头一个圆圈的图标快捷键是（F7)
 * 但是这个方法中若有其他的方法，则不会进入该方法的方法体中，但是使用带红色箭头的加一个圆球状的图标可以强制进入代码中进入（Alt+shift+F7)。
 * 3，F9快捷键是快速过所有断点，把程序回复正常。
 * 4，shift+F8是从断点中跳出来，对应于一个圆球一个出去的箭头示意图。
 * 5，Drop Frame 相当于F9从方法调用中一步步跳出来。不过更厉害的是可以让运行过的代码重新再来一遍。
 *
 * 这里面需要重点理解，记忆和熟悉就是F7的也就是step into的那个图标，这个是在调试中进入一个方法体重一步步执行的重点
 *
 * 若需要进入方法中的方法的方法体就使用Force into的图标或者使用Alt +shift +F7的快捷键
 *
 * 关于F7与alt+shift+F7的区别：前者不会进入框架之类的源码中的方法，但是进入源码之后可以继续使用，
 * 而alt+shift+F7是强制进入到任何遇到的方法体中的。
 *
 *
 */
public class SpringAnnotationStudy {
    public static void main(String[] args) {
        FunctionInrefaceImpl_1 functionInrefaceImpl_1=new FunctionInrefaceImpl_1();
        functionInrefaceImpl_1.setDate(new Date());
        int[] a=new int[1];
        System.out.println(a[0]);
    }

}
