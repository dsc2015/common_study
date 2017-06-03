package com.jd.study.common.spring_study;/**
 * Created by dushuangcheng on 2016/12/27.
 */

import com.jd.fastjson.JSONObject;

/**
 * @author dushuangcheng
 * @create 2016-12-27 17:27
 */
public class MainClass {
    public static void main(String[] args) {
        Integer a=3;
        Integer b=3;

        Long c=11117L;
        Long d=11117L;

        System.out.println(c==d);

        System.out.println(a==b);

      /*  String str="{\"awardFee\":10,\"gameId\":6,\"userId\":2002,\"uuid\":\"sxzTest1059158\"}";

        JSONObject object = JSONObject.parseObject(str);
        Object awardFee = object.get("awardFee");
        System.out.println(awardFee);
        System.out.println(object);
*/

        /*int i=1;
        float j=3.4f;
        double k=3.5;
        System.out.println("kkk"+System.getProperty("line.separator")+"mmm");*/
    }
}
