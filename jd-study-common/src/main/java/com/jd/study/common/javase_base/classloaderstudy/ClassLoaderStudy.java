package com.jd.study.common.javase_base.classloaderstudy;/**
 * @description 测试由jvm提供的启动类加载器，以及java api中提供的classLoader
 *
 * //1,第一个为null
 * //2,第二个为：sun.misc.Launcher$AppClassLoader，也就是用launcher中的AppClassLoader来加载的
 * //3,第三个为ext包下的类，是由ExtClassLoader进行加载的。
 *
 * @author dushuangcheng
 * @create 2017/6/28
 */
public class ClassLoaderStudy {
    public ClassLoaderStudy(int i, int i1) {

    }

    public static void main(String[] args) {
        ClassLoader c1 = String.class.getClassLoader();
        System.out.println("the classLoader of String is ==== "+c1);

        ClassLoader c2 = ClassLoaderStudy.class.getClassLoader();
        System.out.println("the classLoader of this class is ==== "+c2);



    }
}
