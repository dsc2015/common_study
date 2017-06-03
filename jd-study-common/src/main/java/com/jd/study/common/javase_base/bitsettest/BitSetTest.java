package com.jd.study.common.javase_base.bitsettest;

import java.util.BitSet;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/5/18
 */
public class BitSetTest {
    public static void main(String[] args) {
        BitSet bs=new BitSet();
        bs.set(10);
        bs.set(200,false);
        System.out.println("bs==="+bs);
    }
}
