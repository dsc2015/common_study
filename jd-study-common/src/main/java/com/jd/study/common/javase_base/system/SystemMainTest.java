package com.jd.study.common.javase_base.system;/**
 * Created by dushuangcheng on 2016/10/18.
 */

import java.util.Properties;
import java.util.Set;

/**
 * @author dushuangcheng
 * @create 2016-10-18 18:34
 * //测试通过system获取系统的所有的属性
 */
public class SystemMainTest {
    public static void main(String[] args) {

        Properties properties = System.getProperties();
        for(Object properties1:properties.keySet()){
            System.out.println("key="+properties1+"  "+properties.get(properties1));
        }
        //System.out.println("================="+System.getProperties());
    }
}
