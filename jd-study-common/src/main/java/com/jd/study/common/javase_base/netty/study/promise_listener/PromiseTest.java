package com.jd.study.common.javase_base.netty.study.promise_listener;

import io.netty.util.concurrent.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/4/17
 */
public class PromiseTest {
    public static void main(String[] args) throws Exception {
        testListenerNotifyOrder();
    }
    public static void testListenerNotifyOrder() throws Exception {
        EventExecutor executor = new TestEventExecutor();
        try {
            final BlockingQueue<FutureListener<Void>> listeners = new LinkedBlockingQueue<FutureListener<Void>>();
            int runs = 100000;

            for (int i = 0; i < runs; i++) {
                final Promise<Void> promise = new DefaultPromise<Void>(executor);
                final FutureListener<Void> listener1 = new FutureListener<Void>() {
                    @Override
                    public void operationComplete(Future<Void> future) throws Exception {
                        listeners.add(this);
                    }
                };
                final FutureListener<Void> listener2 = new FutureListener<Void>() {
                    @Override
                    public void operationComplete(Future<Void> future) throws Exception {
                        listeners.add(this);
                    }
                };
                final FutureListener<Void> listener4 = new FutureListener<Void>() {
                    @Override
                    public void operationComplete(Future<Void> future) throws Exception {
                        listeners.add(this);
                    }
                };
                final FutureListener<Void> listener3 = new FutureListener<Void>() {
                    @Override
                    public void operationComplete(Future<Void> future) throws Exception {
                        listeners.add(this);
                        future.addListener(listener4);
                    }
                };

                GlobalEventExecutor.INSTANCE.execute(new Runnable() {
                    @Override
                    public void run() {
                        //在这里listener开始执行
                        promise.setSuccess(null);
                    }
                });

                promise.addListener(listener1).addListener(listener2).addListener(listener3);

              /*  assertSame("Fail 1 during run " + i + " / " + runs, listener1, listeners.take());
                assertSame("Fail 2 during run " + i + " / " + runs, listener2, listeners.take());
                assertSame("Fail 3 during run " + i + " / " + runs, listener3, listeners.take());
                assertSame("Fail 4 during run " + i + " / " + runs, listener4, listeners.take());
                assertTrue("Fail during run " + i + " / " + runs, listeners.isEmpty());*/
            }
        } finally {
            executor.shutdownGracefully(0, 0, TimeUnit.SECONDS).sync();
        }
    }

    private static final class TestEventExecutor extends SingleThreadEventExecutor {
        TestEventExecutor() {
            super(null, Executors.defaultThreadFactory(), true);
        }

        @Override
        protected void run() {
            for (;;) {
                Runnable task = takeTask();
                if (task != null) {
                    task.run();
                    updateLastExecutionTime();
                }

                if (confirmShutdown()) {
                    break;
                }
            }
        }
    }
}
