package com.jd.study.common.javase_base.netty.study;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/10
 */
public class UnAsyncServerIO {
    public static void main(String[] args){
        //创建一个socket
        int port = 8080;
        ServerSocket serverSocket = null;
        Socket s = null;
        try {
            serverSocket = new ServerSocket(port);
            //等待客户端连接
            TimeServerExecutorPool timeServerExecutorPool = new TimeServerExecutorPool(10, 20);
            //构建线程池
            while (true) {
                s = serverSocket.accept();
                System.out.println("进入线程池。。。");
            /*    InputStream inputStream = s.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
                String s1 = br.readLine();
                System.out.println(s1);*/
               // Executors.newFixedThreadPool(10).execute(new TimeServerHandler(s));
               timeServerExecutorPool.execute(new TimeServerHandler(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(s!=null){
                try {
                    //s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TimeServerExecutorPool {
        private ExecutorService executorService;

        public TimeServerExecutorPool(int maxPoolSize, int queueSize) {
            executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
        }

        public void execute(Runnable task) {
            executorService.execute(task);
        }
    }

    static class TimeServerHandler implements Runnable {
        private Socket socket;

        public TimeServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                //读取客户端的请求
                out = new PrintWriter(socket.getOutputStream());
                while (true){
                    String queryBody=in.readLine();
                    if(queryBody==null){
                        break;
                    }
                    System.out.println("the client query body is:"+queryBody);
                    //应答客户端
                    String currentDate= new Date(System.currentTimeMillis()).toString();
                    System.out.println("response :"+currentDate);
                    //out.print(currentDate.getBytes());
                    out.println(currentDate);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(in!=null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(out!=null){
                    out.close();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
