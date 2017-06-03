package com.jd.study.common.javase_base.multithread.thread;/**
 * Created by dushuangcheng on 2017/4/4.
 */

/**
 * @author dushuangcheng
 * @create 2017-04-04 17:31
 */
public class VolatilTest {
    int a = 0;
    volatile Boolean flag ;

    public void write() {
        a = 1;
        flag = true;
    }

    public void read() {
        if (flag) {
            System.out.println(a);
        } else {
            System.out.println("----------------------------------->"+a);
            a = 2;
            System.out.println("================================" + a);
        }
    }

    static class Volatile {
        private VolatilTest volatilTest;

        public Volatile() {
            VolatilTest volatilTest1 = new VolatilTest();
            volatilTest1.flag=false;
            this.volatilTest = volatilTest1;
        }

        public void start() {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    volatilTest.write();
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    volatilTest.read();
                }
            });
            t1.start();
            t2.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Volatile aVolatile = new Volatile();
            aVolatile.start();
        }
       /* final VolatilTest volatilTest = new VolatilTest();
        for (int i = 0; i < 1000; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    volatilTest.write();
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    volatilTest.read();
                }
            });
            t1.start();
            t2.start();
        }
    }*/
    }
}
