package com.jd.study.common.gc_test;
/**
 * @description 对象只能被放入引用队列一次，下次直接被GC回收，而不会被finalize放到引用队列中等待被清除
 * @author dushuangcheng
 * @create 2017/3/31
 */
public class FinalizeEscapeGc {
    //开始为null
    public static FinalizeEscapeGc FINALIZE_ESCAPE_GC_HOOK;
    public void isAlive(){
        System.out.println("yes,I'm still alive...");
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("finalize method is executed....");
        this.FINALIZE_ESCAPE_GC_HOOK=this;
    }

    public static void main(String[] args) throws InterruptedException {
        FINALIZE_ESCAPE_GC_HOOK=new FinalizeEscapeGc();

        FINALIZE_ESCAPE_GC_HOOK=null;
        System.gc();
        Thread.sleep(1000);

        if(FINALIZE_ESCAPE_GC_HOOK!=null){
            FINALIZE_ESCAPE_GC_HOOK.isAlive();
        }else {
            System.out.println("no I'm dead...");
        }
        //第一次GC后对象存活，但是第二次GC后对象就没有存活了
    //----------------------------------------------------------------------------------
        FINALIZE_ESCAPE_GC_HOOK=null;
        System.gc();
        Thread.sleep(1000);

        if(FINALIZE_ESCAPE_GC_HOOK!=null){
            FINALIZE_ESCAPE_GC_HOOK.isAlive();
        }else {
            System.out.println("no I'm dead...");
        }

    }
}
