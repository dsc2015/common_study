package com.jd.study.common.jsf_study;/**
 * Created by dushuangcheng on 2016/10/23.
 */

import com.jd.fastjson.JSON;
import com.jd.fastjson.JSONArray;
import com.jd.fastjson.JSONObject;

import java.util.*;

/**
 * @author dushuangcheng
 * @create 2016-10-23 18:46
 */
public class JsonDemo {
    public static void main(String[] args) {

        JSONArray jsonArray=new JSONArray();
        JSONObject object = JSONArray.parseObject("{a:\"1\",b:\"c\"}");
        JSONObject object1 = JSONArray.parseObject(" { a:[ {name:\"dsc\",age:\"20\"},{name:\"dgc\",age:\"24\"}] }");
        System.out.println(object1);
        System.out.println(object);
        jsonArray.add("dsc");
        jsonArray.add("dgc");
        System.out.println(jsonArray);
        //i$是什么意思?
      /*  for(int i$=0;i$<100;i$++){
            System.out.println("i$"+i$);
        }*/

        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("username","dsc");
        paramMap.put("age",3);

       /* //将map转化为json数据
        Object o = JSON.toJSON(paramMap);
        String oStr= (String) o;
        System.out.println("o======="+o+"<<<<<<<<<<<<<<<<<"+oStr);
        JSONObject object2 = JSONObject.parseObject(oStr);
        System.out.println("object2"+object2);*/
        //将map转换为json字符串的形式
        String s = JSON.toJSONString(paramMap);
        System.out.println("s=========="+s);
    }

}
