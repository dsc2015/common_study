package com.jd.study.common.javase_base.breakandcotinue;
/**
 * break，continue 后面的也不会执行
 *
 * 嵌套循环的break 到外层，导致内外循环都终止
 *
 * @author dushuangcheng
 * @create 2017-06-06 17:23
 */
public class BreakAndContinue {
    public static void main(String[] args) {
       /* for(int j=0;j<10;j++){
            if(j==6){
                break;
            }
            System.out.println("========="+j);
        }

        for(int i=0;i<10;i++){
            if(i==6){
                continue;
            }
            System.out.println("+++++++++++++++++++++++"+i);
        }*/

        A:for(int i=0;i<10;i++){
            System.out.println("==================outer=="+i);
            B:for(int j=0;j<10;j++){
                System.out.println("++++++++++++++++++++++++inner=="+j);
                if(j==3){
                    break A;
                }
                System.out.println("=======================inner=="+j);
            }

            System.out.println("++++++++++++++++++++outer++"+i);
        }
    }
}
