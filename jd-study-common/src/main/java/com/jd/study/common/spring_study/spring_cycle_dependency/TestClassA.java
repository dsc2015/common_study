package com.jd.study.common.spring_study.spring_cycle_dependency;/**
 * Created by dushuangcheng on 2017/1/10.
 */

import org.springframework.stereotype.Component;

/**
 * @author dushuangcheng
 * @create 2017-01-10 10:50
 */
@Component
public class TestClassA {
    private TestClassB testClassB;


    public void a() {
        testClassB.b();
    }


    public TestClassB getTestClassB() {
        return testClassB;
    }

    public void setTestClassB(TestClassB testClassB) {
        this.testClassB = testClassB;
    }
}
