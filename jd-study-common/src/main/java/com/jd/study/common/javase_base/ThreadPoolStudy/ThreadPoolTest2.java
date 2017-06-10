package com.jd.study.common.javase_base.ThreadPoolStudy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author dushuangcheng
 * @create 2017-06-06 15:27
 */
public class ThreadPoolTest2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        SubmitTaskWorker submitTaskWorker = new SubmitTaskWorker(19855);
        new Thread(submitTaskWorker).start();
        //Thread.sleep(12000);
        for(int i=0;i<10;i++){
            if(i==5){
                continue;
                //System.out.println("break...");
            }
            //new Thread(new ClientRequest()).start();
            submitTaskWorker.pool.execute(new ClientRequest());
        }

        Thread.sleep(12000);
        StaticWorker staticWorker = new StaticWorker();
        staticWorker.setPool(submitTaskWorker.pool);
        new Thread(staticWorker).start();

    }

    /**
     * 提交需要执行任务的线程，专门用于提交线程
     */
    static class SubmitTaskWorker implements Runnable{
        private final ExecutorService pool;
        private final ServerSocket serverSocket;

        public SubmitTaskWorker(int port) throws IOException {
            //this.pool = Executors.newFixedThreadPool(2);
            this.pool = new ThreadPoolExecutor(5,6,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(4));
            this.serverSocket = new ServerSocket(port);
        }
        @Override
        public void run() {
            for(;;){
                try {
                    pool.execute(new RequestWorker(serverSocket.accept()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class RequestWorker implements Runnable{
        private final Socket socket;

        public RequestWorker(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            InputStream inputStream=null;
            try {
                inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                inputStream.read(bytes,0,bytes.length);
                System.out.println(new String(bytes));
                System.out.println("get connection....");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                assert inputStream != null;
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class ClientRequest implements Runnable{
        private final Socket socket;

        public ClientRequest() throws IOException {
            this.socket = new Socket("127.0.0.1",19855);
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = socket.getOutputStream();
                String mes="hello world!";
                outputStream.write(mes.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class StaticWorker implements Runnable{
        private ExecutorService pool;

        public ExecutorService getPool() {
            return pool;
        }

        public void setPool(ExecutorService pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            if(pool instanceof ThreadPoolExecutor){
                ThreadPoolExecutor poo11= (ThreadPoolExecutor) pool;
                //活跃的线程数目
                int activeCount = poo11.getActiveCount();
                //已经执行完毕的线程数目
                long completedTaskCount = poo11.getCompletedTaskCount();
                //总的任务数目
                long taskCount = poo11.getTaskCount();

                System.out.println("活跃的线程数目:="+activeCount+"已经执行完毕的线程数目:="+completedTaskCount+"总的任务数目:="+taskCount);
                poo11.shutdown();
                List<Runnable> runnables = poo11.shutdownNow();
                System.out.println("=====================>"+runnables.size());
            }
        }
    }
}
