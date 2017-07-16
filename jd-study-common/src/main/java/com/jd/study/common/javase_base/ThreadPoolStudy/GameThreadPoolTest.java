package com.jd.study.common.javase_base.ThreadPoolStudy;

import com.jd.rd.game.api.app.param.LlyStartParam;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/9
 */
public class GameThreadPoolTest {
    public static long[] generateLongArray(){
        long[] arrayLong=new long[11];
        long[] target={10,20,0,0,10,10,20,0,10,0,10};
        for(int i=0;i<11;i++){
            //arrayLong[i]=(int)(Math.random()*10)*10;
            Random random = new Random();
            int k = random.nextInt(11)%11;
            arrayLong[i]=target[k];
        }
        return arrayLong;
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 20, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000));
        for(int j=0;j<1000000;j++){
            CountDownLatch countDownLatch = new CountDownLatch(20);
            GameLogic gameLogic = new GameLogic();
            long[] longAreaBet =GameThreadPoolTest.generateLongArray();
            LlyStartParam llyStartParam = new LlyStartParam();
            llyStartParam.setAreaBet(longAreaBet);

            for (int i = 0; i < 20; i++) {
                try{
                    pool.execute(new WorkerPool(countDownLatch, gameLogic, llyStartParam, longAreaBet,pool));
                    countDownLatch.countDown();
                }catch (Exception e){
                    pool.shutdownNow();
                }

            }
            Thread.sleep(200);
        }

        pool.shutdown();
    }

    private static class WorkerPool implements Runnable {
        private CountDownLatch countDownLatch;
        private GameLogic gameLogic;
        private LlyStartParam llyStartParam;
        private long[] areaBet;
        private ThreadPoolExecutor pool;

        public WorkerPool(CountDownLatch countDownLatch, GameLogic gameLogic, LlyStartParam llyStartParam, long[] areaBet, ThreadPoolExecutor pool) {
            this.countDownLatch = countDownLatch;
            this.gameLogic = gameLogic;
            this.llyStartParam = llyStartParam;
            this.areaBet = areaBet;
            this.pool = pool;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
                pool.shutdownNow();
                System.exit(0);
            }
            long start = System.currentTimeMillis();
            gameLogic.execute(llyStartParam, areaBet);
            long end = System.currentTimeMillis();
            System.out.println("-------------------------------------------算法耗时=" + (end - start));
        }
    }
}
