package com.jd.study.common.javase_base.multithread.DataRaceAndRaceCondition;/**
 * Created by dushuangcheng on 2017/2/24.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author dushuangcheng
 * @create 2017-02-24 10:53
 *
 * 使用ArrayList会报错，因为竞态条件会报错*/
public class DataRace {
   // public static List<String> list=new CopyOnWriteArrayList<>();
    public static List<String> list=new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new WriteTask());
        executorService.execute(new WriteTask());
        executorService.execute(new WriteTask());
        executorService.execute(new WriteTask());
        executorService.execute(new WriteTask());

        executorService.awaitTermination(20, TimeUnit.SECONDS);
    }

    static class WriteTask implements Runnable {
        @Override
        public void run() {
            for(int i=0;i<25;i++){
                list.add("str_"+i);
            }
        }
    }
}

