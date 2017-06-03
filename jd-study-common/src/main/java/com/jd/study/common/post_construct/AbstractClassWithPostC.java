package com.jd.study.common.post_construct;/**
 * Created by dushuangcheng on 2017/2/3.
 */

import com.google.common.collect.Maps;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author dushuangcheng
 * @create 2017-02-03 17:15
 */
public abstract class AbstractClassWithPostC {
    protected final static Map<String,Object> map= Maps.newHashMap();

    @PostConstruct
    public void init(){
        map.put("a",this);
    }

    public static Map<String, Object> getMap() {
        return map;
    }
}
