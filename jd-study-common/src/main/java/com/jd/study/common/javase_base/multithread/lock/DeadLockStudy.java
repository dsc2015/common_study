package com.jd.study.common.javase_base.multithread.lock;

/**
 * 死锁检测
 * 对于使用Sync的代码，死锁比较容易通过命令 jstack -l pid发现，而api的锁则不容易检测出来
 * "t2" prio=6 tid=0x000000000e60c000 nid=0x3724 waiting for monitor entry [0x000000000f1af000]
 java.lang.Thread.State: BLOCKED (on object monitor)
 at com.jd.study.common.javase_base.multithread.lock.DeadLockStudy$2.run(DeadLockStudy.java:37)
 - waiting to lock <0x00000007d615bac0> (a java.lang.Object)
 - locked <0x00000007d615bad0> (a java.lang.Object)

 Locked ownable synchronizers:
 - None

 "t1" prio=6 tid=0x000000000e60b800 nid=0x4600 waiting for monitor entry [0x000000000f03f000]
 java.lang.Thread.State: BLOCKED (on object monitor)
 at com.jd.study.common.javase_base.multithread.lock.DeadLockStudy$1.run(DeadLockStudy.java:23)
 - waiting to lock <0x00000007d615bad0> (a java.lang.Object)
 - locked <0x00000007d615bac0> (a java.lang.Object)

 Locked ownable synchronizers:
 - None

 "Monitor Ctrl-Break" daemon prio=6 tid=0x000000000e605000 nid=0x36b4 runnable [0x000000000ee9f000]
 java.lang.Thread.State: RUNNABLE
 at java.net.DualStackPlainSocketImpl.accept0(Native Method)
 at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
 at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:398)
 at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
 - locked <0x00000007d60af528> (a java.net.SocksSocketImpl)
 at java.net.ServerSocket.implAccept(ServerSocket.java:530)
 at java.net.ServerSocket.accept(ServerSocket.java:498)
 at com.intellij.rt.execution.application.AppMain$1.run(AppMain.java:85)
 at java.lang.Thread.run(Thread.java:745)

 Locked ownable synchronizers:
 - None

 "Service Thread" daemon prio=6 tid=0x000000000c612800 nid=0x4548 runnable [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 Locked ownable synchronizers:
 - None

 "C2 CompilerThread1" daemon prio=10 tid=0x000000000c60d000 nid=0xcb0 waiting on condition [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 Locked ownable synchronizers:
 - None

 "C2 CompilerThread0" daemon prio=10 tid=0x000000000b54f800 nid=0x3e94 waiting on condition [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 Locked ownable synchronizers:
 - None

 "Attach Listener" daemon prio=10 tid=0x000000000b54e800 nid=0x1f30 waiting on condition [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 Locked ownable synchronizers:
 - None

 "Signal Dispatcher" daemon prio=10 tid=0x000000000c606000 nid=0x1e60 runnable [0x0000000000000000]
 java.lang.Thread.State: RUNNABLE

 Locked ownable synchronizers:
 - None

 "Finalizer" daemon prio=8 tid=0x000000000b53d000 nid=0x25e4 in Object.wait() [0x000000000d94f000]
 java.lang.Thread.State: WAITING (on object monitor)
 at java.lang.Object.wait(Native Method)
 - waiting on <0x00000007d5c047f8> (a java.lang.ref.ReferenceQueue$Lock)
 at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:135)
 - locked <0x00000007d5c047f8> (a java.lang.ref.ReferenceQueue$Lock)
 at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:151)
 at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

 Locked ownable synchronizers:
 - None

 "Reference Handler" daemon prio=10 tid=0x000000000b534800 nid=0x1a58 in Object.wait() [0x000000000d65f000]
 java.lang.Thread.State: WAITING (on object monitor)
 at java.lang.Object.wait(Native Method)
 - waiting on <0x00000007d5c04410> (a java.lang.ref.Reference$Lock)
 at java.lang.Object.wait(Object.java:503)
 at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:133)
 - locked <0x00000007d5c04410> (a java.lang.ref.Reference$Lock)

 Locked ownable synchronizers:
 - None

 "VM Thread" prio=10 tid=0x000000000b52f800 nid=0x26c4 runnable

 "GC task thread#0 (ParallelGC)" prio=6 tid=0x00000000026e6000 nid=0x42cc runnable

 "GC task thread#1 (ParallelGC)" prio=6 tid=0x00000000026e7800 nid=0x4a38 runnable

 "GC task thread#2 (ParallelGC)" prio=6 tid=0x00000000026e9000 nid=0x2610 runnable

 "GC task thread#3 (ParallelGC)" prio=6 tid=0x00000000026eb000 nid=0xb74 runnable

 "GC task thread#4 (ParallelGC)" prio=6 tid=0x00000000026ee000 nid=0x395c runnable

 "GC task thread#5 (ParallelGC)" prio=6 tid=0x00000000026ef800 nid=0x35e0 runnable

 "GC task thread#6 (ParallelGC)" prio=6 tid=0x00000000026f1000 nid=0x4dec runnable

 "GC task thread#7 (ParallelGC)" prio=6 tid=0x00000000026f5000 nid=0x3cf4 runnable

 "VM Periodic Task Thread" prio=10 tid=0x000000000c65f000 nid=0x34fc waiting on condition

 JNI global references: 137


 Found one Java-level deadlock:
 =============================
 "t2":
 waiting to lock monitor 0x000000000b5398a8 (object 0x00000007d615bac0, a java.lang.Object),
 which is held by "t1"
 "t1":
 waiting to lock monitor 0x000000000b53ac98 (object 0x00000007d615bad0, a java.lang.Object),
 which is held by "t2"

 Java stack information for the threads listed above:
 ===================================================
 "t2":
 at com.jd.study.common.javase_base.multithread.lock.DeadLockStudy$2.run(DeadLockStudy.java:37)
 - waiting to lock <0x00000007d615bac0> (a java.lang.Object)
 - locked <0x00000007d615bad0> (a java.lang.Object)
 "t1":
 at com.jd.study.common.javase_base.multithread.lock.DeadLockStudy$1.run(DeadLockStudy.java:23)
 - waiting to lock <0x00000007d615bad0> (a java.lang.Object)
 - locked <0x00000007d615bac0> (a java.lang.Object)

 Found 1 deadlock.
 * @author dushuangcheng
 * @description
 * @create 2017/6/24
 */
public class DeadLockStudy {
    final static Object obj_1 = new Object();
    final static Object obj_2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread("t1") {
            public void run() {
                synchronized (obj_1) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("lock obj_1");
                    synchronized (obj_2) {
                        System.out.println("thread t1 done.");
                    }
                }


            }
        };

        Thread t2 = new Thread("t2") {
            public void run() {
                synchronized (obj_2) {

                    System.out.println("lock obj_2");
                    synchronized (obj_1) {
                        System.out.println("thread t2 done.");
                    }

                }

            }
        };
        t1.start();
        t2.start();
    }
}
