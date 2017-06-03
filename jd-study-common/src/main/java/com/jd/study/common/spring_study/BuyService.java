package com.jd.study.common.spring_study;/**
 * Created by dushuangcheng on 2016/12/27.
 */

import org.springframework.stereotype.Service;

/**
 * @author dushuangcheng
 * @create 2016-12-27 17:19
 */
@Service("buyService")
public class BuyService {
    public  String getDesc(){
        System.out.println("=================================>抽象类调用到我。。。。。");
        return "success";
    }
}
