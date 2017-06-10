package com.jd.study.common.javase_base.ThreadPoolStudy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/6/8
 */
public class ThreadPoolStudy {
    public static void main(String[] args) {
        System.out.println("========================="+~CAPACITY);
        ThreadPoolStudy ts = new ThreadPoolStudy();
       // Integer it=new Integer(1);
        System.out.println("cont_bits= "+ThreadPoolStudy.COUNT_BITS);
        System.out.println();

    }
    //====================================form source code，to test the bits value==============================
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    //29
    private static final int COUNT_BITS = Integer.SIZE - 3;
        //5 3687 0911 29位，各个位上都是1
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    //-5 3687 0912
    //1110000000000000000...0:高三位全是1
    private static final int RUNNING    = -1 << COUNT_BITS;
    //0
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    //5 3687 0912  30位，第30位上的数字是1，其余位上的数字是0
    private static final int STOP       =  1 << COUNT_BITS;
    //1073741824 31位，第31位上的数字是1，其余位上的数字是0
    private static final int TIDYING    =  2 << COUNT_BITS;
    // 1610612736 31位，第31位和30位上的数字为1，其余位上的数字为0
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public AtomicInteger getCtl() {
        return ctl;
    }

    public static int getCountBits() {
        return COUNT_BITS;
    }

    public static int getCapacity() {
        return CAPACITY;
    }

    public static int getRunning() {
        return RUNNING;
    }

    public static int getShutdown() {
        return SHUTDOWN;
    }

    public static int getStop() {
        return STOP;
    }

    public static int getTidying() {
        return TIDYING;
    }

    public static int getTerminated() {
        return TERMINATED;
    }

    //====================================form source code==============================
}
