package com.jd.study.common.spring_study;/**
 * Created by dushuangcheng on 2016/12/27.
 */

import org.springframework.stereotype.Service;

/**
 * @author dushuangcheng
 * @create 2016-12-27 17:26
 */
@Service("subAbstractClass")
public class SubAbstractClass extends AbstractClass {
    @Override
    public String getName() {
        return super.getAddress();
    }
}
