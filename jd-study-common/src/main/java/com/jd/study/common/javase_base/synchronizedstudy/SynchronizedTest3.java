package com.jd.study.common.javase_base.synchronizedstudy;

/**
 * @author dushuangcheng
 * @description
 * 线程1访问A.staticWrite(true)方法时，线程2能访问A.staticRead()方法吗？
 * 线程1访问new A().staticWrite(false)方法时，线程2能访问new A().staticRead()方法吗？
 * 线程1访问A.staticWrite(false)方法时，线程2能访问new A().staticRead()方法吗?
 * 答案是都不能。因为无论A创建多少个对象或实例，任一对象调用staticWrite()，或类直接调用staticWrite()，
 * 锁对象的引用都是A.class。
 * 也就是只要调用staticWrite方法，都会对A.class加锁，而staticRead()需要获得的锁对象也正是A.class，因此会出现阻塞。
 * 所以线程2无法访问A.staticRead()方法。
 * <p/>
 * A a=new A(); 线程1访问a.write(false)方法，线程2能访问a.read()方法吗？
 * A a=new A(); A b=new A();线程1访问a.write(false)方法，线程2能访问b.read()方法吗？
 * 题目1答案是不可以，理由与上面类似。a.write()对类A的对象a加了锁，而a.read()需要获得的锁对象也刚好是a，
 * 所以线程2无法访问a.read()
 * <p/>
 * 题目2答案是可以，a.write()对类A的对象加了锁，而b.read()需要获得的锁对象则是b，两者无冲突。b.read()能顺利获得锁，
 * 并访问read()方法。
 * <p/>
 * 再次总结：
 * <p/>
 * 对于实例同步方法，锁是当前实例对象。
 * 对于静态同步方法，锁是当前对象的Class对象。
 * 对于同步方法块，锁是Synchonized括号里配置的对象
 * @create 2017/6/23
 */
public class SynchronizedTest3 {

    static class A {

        private static boolean isTrue;

        public static synchronized void staticWrite(boolean b) {
            isTrue = b;
        }

        public static synchronized boolean staticRead() {
            return isTrue;
        }

        public synchronized void write(boolean b) {
            isTrue = b;
        }

        public synchronized boolean read() {
            return isTrue;
        }
    }
}
