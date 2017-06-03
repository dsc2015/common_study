package com.jd.study.common.spring_study.spring_cycle_dependency;/**
 * Created by dushuangcheng on 2017/1/10.
 */

import org.springframework.stereotype.Component;

/**
 * @author dushuangcheng
 * @create 2017-01-10 10:50
 */
@Component
public class TestClassB {
    private TestClassC testClassC;

    public void b() {
        testClassC.c();
    }

    public TestClassC getTestClassC() {
        return testClassC;
    }

    public void setTestClassC(TestClassC testClassC) {
        this.testClassC = testClassC;
    }
}
