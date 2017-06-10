package com.jd.study.common.javase_base.volitalestudy;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/6/10
 */
public class VolitaleTest1 {
    public static void main(String[] args) {
        VolitaleTest1 volitaleTest1 = new VolitaleTest1();
        volitaleTest1.setValue();
    }
    static class Dome{
        volatile int status=1;
    }

    public void setValue(){
        Dome dome = new Dome();
        int a= dome.status;
        a++;
        if(dome.status==2){
            System.out.println("right!===================");
        }
    }

}
