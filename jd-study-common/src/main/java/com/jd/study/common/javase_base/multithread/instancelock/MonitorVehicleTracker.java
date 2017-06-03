package com.jd.study.common.javase_base.multithread.instancelock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/3/24
 */
public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    /**
     * 获取位置坐标
     */
    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    /**
     * 获取坐标点
     */
    public synchronized MutablePoint getLocation(String id) {
        MutablePoint mutablePoint = locations.get(id);
        //返回一个拷贝构造函数对象
        return mutablePoint == null ? null : new MutablePoint(mutablePoint);
    }

    public synchronized void setLocation(String id, int x, int y) throws Exception {
        //先看有没有该id
        MutablePoint mutablePoint = locations.get(id);
        if (mutablePoint == null) {
            throw new Exception("没有该id");
        }
        mutablePoint.setX(x);
        mutablePoint.setY(y);
    }

    private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m){
        Map<String,MutablePoint> result=new HashMap<>();
        for(String id:m.keySet()){
            result.put(id,m.get(id));
        }
        return Collections.unmodifiableMap(result);
    }


}
