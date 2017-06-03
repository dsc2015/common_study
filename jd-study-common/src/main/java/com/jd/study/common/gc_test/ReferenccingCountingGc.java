package com.jd.study.common.gc_test;

/**
 * @author dushuangcheng
 * @description 相互引用的GC策略
 * System.gc();会触发一次fullGC操作
 * @create 2017/3/31
 */
public class ReferenccingCountingGc {
    static {
        System.out.println("++++++++++++++++=" + Thread.currentThread().getName());
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-----" + Runtime.getRuntime().availableProcessors() + "-----------------------------JVM------------------");
            }
        }));
    }

    static class A {
        private B objB;

        public B getObjB() {
            return objB;
        }

        public void setObjB(B objB) {
            this.objB = objB;
        }
    }

    static class B {
        private A objA;

        public A getObjA() {
            return objA;
        }

        public void setObjA(A objA) {
            this.objA = objA;
        }
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[10240];

        A a = new A();
        B b = new B();

        a.setObjB(b);
        b.setObjA(a);

        a = null;
        b = null;
//
        System.gc();
        System.out.println("================================");
    }
}
