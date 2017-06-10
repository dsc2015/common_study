package com.jd.study.common.javase_base.ThreadPoolStudy;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用completionService来获取已经完成的任务的结果
 * 1，任务的submit,任务的提交操作，先把任务封装成为RunnableFuture对象，通过newTaskFor()来实现的，最终是通过futureTask
 * 对象来完成的，而在ExecutorCompletionService中是通过QueueingFuture（继承FutureTask，覆盖done()方法），在任务完成的时候把task放入到一个CompletionQueue
 * 的队列中去，从而实现了把已经完成的任务分离出去的特征。
 * 2，已经完成的任务的获取也是从内部队列中进行获取的。
 *
 * @author dushuangcheng
 * @create 2017-06-07 9:22
 */
public class CompletionServiceStudy {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionServiceStudy completionServiceStudy = new CompletionServiceStudy();
        List<Future<Integer>> futures = completionServiceStudy.submitWorker(10);
        Integer submitWorkerResult = completionServiceStudy.getSubmitWorkerResult(futures);

        System.out.println("================================"+submitWorkerResult);
    }
    private ExecutorCompletionService<Integer> completionService;
    private ExecutorService executorService=Executors.newFixedThreadPool(5);

    public CompletionServiceStudy() {
        this.completionService = new ExecutorCompletionService<Integer>(executorService);
    }
    /**
     * @description  提交任务
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/7
     */
    public List<Future<Integer>> submitWorker(int num){
        List<Future<Integer>> futures=new ArrayList<>(num);
        for(int i=0;i<num;i++){
            Future<Integer> submit = completionService.submit(new WorkerCompletion());
            futures.add(submit);
        }
        return futures;
    }
    /**
     * @description 获取任务执行的结果，并取消其余的正在执行的线程的任务，最后关闭线程池
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/7
     */
    public Integer getSubmitWorkerResult( List<Future<Integer>> futures) throws InterruptedException, ExecutionException {
        for(Future<Integer> future:futures){
            Integer integer = completionService.take().get();
            if(integer!=null){
                for(Future<Integer> future1:futures){
                    //取消任务的执行
                    future1.cancel(true);
                }
                //关闭线程池
                executorService.shutdown();
                return integer;
            }
        }
        return null;
    }

    static class WorkerCompletion implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep((long) (Math.random() * 10000));
            return (int) (100 * Math.random());
        }
    }
}
