package com.jd.study.common.javase_base.ifelse;

/**
 * @description 走了if后面的else是不会输出的
 *
 * if 语句后面可以跟 elseif…else 语句，这种语句可以检测到多种可能的情况。
  使用 if，else if，else 语句的时候，需要注意下面几点：
  if 语句至多有 1 个 else 语句，else 语句在所有的 elseif 语句之后。
  if 语句可以有若干个 elseif 语句，它们必须在 else 语句之前。
  一旦其中一个 else if 语句检测为 true，其他的 else if 以及 else 语句都将跳过执行
 * @author dushuangcheng
 * @create 2017/6/19
 */
public class IfElseTest {
    public static void main(String[] args) {
        /**
         *
         * 一旦其中一个 else if 语句检测为 true，其他的 else if 以及 else 语句都将跳过执行
         *
         */
        int a=1;
        if(a==1){
            System.out.println("aaaaa");
        }else if(a<2){
            System.out.println("bbbbbbbbbbbbbb");
        }else {
            System.out.println("ccccccccc");
        }
    }

}
