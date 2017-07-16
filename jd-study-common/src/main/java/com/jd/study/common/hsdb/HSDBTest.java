package com.jd.study.common.hsdb;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/14
 */
public class HSDBTest {
    public static void main(String[] args) {
        HSDBTest ht = new HSDBTest();
        ht.fn();
    }
    static Test2 t1 = new Test2();
    Test2 t2 = new Test2();

    public void fn() {
        Test2 t3 = new Test2();
    }

    static class Test2 {

    }
}
