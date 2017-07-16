package com.jd.study.common.javase_base.synchronizedstudy;

/**
 * @author dushuangcheng
 * @description 测试对象锁和类上的锁
 * @create 2017/6/22
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        InnerMutable innerMutable = new InnerMutable();
        for (int i = 0; i < 10; i++) {
            new Thread(new TestWorker1()).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        InnerMutableForClass mutableForClass = new InnerMutableForClass();
        for (int i = 0; i < 10; i++) {
            new Thread(new TestWorker2(mutableForClass)).start();
        }
    }

    static class InnerMutable {
        private static int mutableVariable = 4;

        public  static synchronized int incr() {
            for (int i = 0; i < 10; i++) {
                mutableVariable++;
            }
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mutableVariable;
        }
    }

    static class InnerMutableForClass {
        private static int mutableVariable = -1000;

        public  int decr() {
            synchronized (Integer.class) {
                for (int i = 0; i < 10; i++) {
                    mutableVariable--;
                }
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return mutableVariable;
            }
        }
    }

    static class TestWorker1 implements Runnable {
        private InnerMutable innerMutable;

        public TestWorker1() {
        }

        public TestWorker1(InnerMutable innerMutable) {
            this.innerMutable = innerMutable;
        }

        @Override
        public void run() {
            int incr = InnerMutable.incr();
            System.out.println(Thread.currentThread() + "=====================" + incr);
        }
    }

    static class TestWorker2 implements Runnable {
        private InnerMutableForClass innerMutableForClass;

        public TestWorker2(InnerMutableForClass innerMutableForClass) {
            this.innerMutableForClass = innerMutableForClass;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "=====================" + innerMutableForClass.decr());
        }
    }
}
