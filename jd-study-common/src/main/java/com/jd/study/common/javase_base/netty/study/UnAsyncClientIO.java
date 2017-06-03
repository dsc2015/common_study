package com.jd.study.common.javase_base.netty.study;

import java.io.*;
import java.net.Socket;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/10
 */
public class UnAsyncClientIO {
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        Socket s = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            s = new Socket("127.0.0.1", port);
            //发送请求
            String queryString = "get the current datetime...";
            while (true){
                System.out.println(queryString);
                pw = new PrintWriter(s.getOutputStream());
                pw.println(queryString);
                pw.flush();
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String s1 = br.readLine();
                System.out.println("get the server response..." + s1);

                Thread.sleep(2000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (pw != null) {
                pw.close();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
