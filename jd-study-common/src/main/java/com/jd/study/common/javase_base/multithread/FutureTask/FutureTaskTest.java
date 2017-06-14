package com.jd.study.common.javase_base.multithread.FutureTask;

import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 什么时候会被用到呢？
 * 当通过线程池submit任务之后，任务会立即返回并不会阻塞提交任务的线程，而当结果返回之前，需要该结果的线程future.get()会被阻塞并
 * 封装在数据结构中去。
 *
 * 验证：当call方法中的任务执行延时或者遭到阻塞之后，并不会影响后续的执行流程，但是Future.get()会阻塞。
 *
 * 那么将来任务执行完成之后的结果是如何对应到提交的线程呢？这个是通过一个数据结构，类似栈类型的数据结构，所有需要改执行结果的
 * 线程都会被封装称为一个WaiterNode存放在一个栈类型的数据结构中，当结果返回之后会清除所有在此Future上等待结果的线程。
 *
 * @author dushuangcheng
 * @description
 * @create 2017/6/13
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        //开启一个服务端socket
      //  FutureTaskTest.getAnServerSocket();
       // List<Future> futures = FutureTaskTest.doWork();
        //System.out.println("=========================任务提交完毕");
       /* if(futures.size()>0){
            FutureTaskTest.logResult(futures);
        }*/
        FutureTaskTest.doWorker1();
        System.out.println("---------------------------------------");
    }
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);
    private static final ExecutorService pool1 = Executors.newFixedThreadPool(3);
    private static final int threads = 3;

    public static void doWorker1(){
        for (int i=0;i<threads;i++){
            pool1.execute(new WorkerComposite());
        }

       /* if(pool.)
        pool.shutdown();
        pool1.shutdown();*/
    }
    public static List<Future> doWork() {
        List<Future> futures = new ArrayList<Future>(threads);
        for (int i = 0; i < threads; i++) {
            Worker worker = new Worker();
            //FutureTask<Integer> futureTask = new FutureTask<Integer>(worker);
            Future<Integer> submit = pool.submit(worker);
            futures.add(submit);
        }
       // pool.shutdown();
        return futures;
    }

    public static void logResult(List<Future> futures) throws ExecutionException, InterruptedException {
        for(Future future:futures){
            System.out.println("==================================="+future.get());
        }
    }

    public static void getAnServerSocket() throws IOException {
        ServerSocket serverSocket=new ServerSocket(8080);
        while (true){
            Socket accept = serverSocket.accept();
            accept.getInputStream().read();
        }
    }

    static class Worker implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            /*Socket socket=new Socket("127.0.0.1",8080);
            InputStream inputStream = socket.getInputStream();
            inputStream.read();*/
            Thread.sleep(10000);
            return (int) (Math.random() * 1000);
        }
    }

    static class WorkerComposite implements Runnable{
        @Override
        public void run()  {
            System.out.println("=======================开始执行异步任务=====================");
            List<Future> futures = FutureTaskTest.doWork();
            System.out.println("=======================执行异步任务执行完毕=====================");
            for(Future future:futures){
                try {
                    FutureTaskTest.logResult(futures);
                    //future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("=======================执行完毕=====================");
        }
    }
}
