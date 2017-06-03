package demo;

import com.jd.fastjson.JSONArray;
import com.jd.fastjson.JSONObject;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/4/27
 */
public class DubboInterfaceImpl  implements  DubboInterface{
    public static void main(String[] args) {
        System.out.println("\n");

        JSONObject object = JSONArray.parseObject("{\"name\":dsc}");
    }
}
