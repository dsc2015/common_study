package com.jd.study.common.javase_base.Generic;/**
 * Created by dushuangcheng on 2017/2/20.
 */

/**
 * @author dushuangcheng
 * @create 2017-02-20 17:33
 */
public class AbsClassTwo<E extends Object> extends AbsClassOne  {
    @Override
    public <E extends Object, K> K service() {
        return null;
    }
}
