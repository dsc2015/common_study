package nondistributeworker;

import com.google.common.collect.Maps;
import nondistributeworker.domain.TaskJob;
import nondistributeworker.domain.WorkExecuteResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author dushuangcheng
 * @create 2017-06-05 14:55
 */
public class MultiTaskProcessor {
    private static final int MAX_OPER_TIME = 500;
    private static final int THREAD_WAIT_TIME = 1000;
    /**
     * @description: 通过spring注入一个服务的类，用于对每条任务的状态流转和批量的操作等，
     */
    private TaskBaseService taskBaseService;
    /**
     * @description:引入一个线程池的实例，这个线程池是引用的spring的线程池，这个线程池是可以进行配置的
     */
    private ThreadPoolTaskExecutor threadPoolTaskExecutorPool;
    /**
     * @description:注入一个单任务的处理类
     */
    private MySingleTaskProcessor mySingleTaskProcessor;
    //===================================这一通过在spring中同个getter/setter的方式进行注入============================================================
    /**
     * @description:任务的名称
     */
    private String myTaskName;
    /**
     * @description:正在执行的任务的类型
     */
    private Integer myTaskType;
    /**
     * @description:任务执行的失败次数统计
     */
    private Integer myTaskFailCount;
    /**
     * @description:任务执行的时间间隔
     */
    private Integer executeTime;
    //=============================================任务的执行方法体=======================================================

    /**
     * @param
     * @return
     * @description 任务执行的入口处
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/5
     */
    public void execute() {

    }

    //==============================================任务的立即执行,任务投递到线程池中进行执行====================================================
    public void doTaskImmediately(MyJobTask myJobTask) {
        threadPoolTaskExecutorPool.execute(myJobTask);
    }
    //===============================================任务的异步进行处理并返回结果=============================
    public WorkExecuteResult doTask(MyJobTask1 myJobTask) throws ExecutionException, InterruptedException {
        Future<WorkExecuteResult> submitResult = new ThreadPoolExecutor(10, 10, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(6)).submit(myJobTask);
        WorkExecuteResult workExecuteResult = submitResult.get();
        /**
         *
         * 相关的业务处理类
         *
         */
        return workExecuteResult;
    }

    //==================================任务执行的Runnable体,如果使用callable可以进行任务的异步执行等一些处理======================================================================
    class MyJobTask implements Runnable {
        private TaskJob taskJob;

        public MyJobTask(TaskJob taskJob) {
            this.taskJob = taskJob;
        }

        @Override
        public void run() {
            //1代表等待执行的任务状态
            if (taskJob.getStatus() == 1) {
                //定义一个参数map
                Map<String, Object> param = Maps.newHashMap();
                long startTime = System.currentTimeMillis();
                /**
                 * 这里可以为param携带一些参数进行传入
                 */
                //业务相关的处理类
                mySingleTaskProcessor.work(taskJob, param);
                long execute = System.currentTimeMillis() - startTime;
                /**
                 * 任务处理的时间间隔进行处理，超时的话打印超时信息或者进行一些其他方面的处理
                 *
                 */
            }
        }
    }

    class MyJobTask1 implements Callable<WorkExecuteResult> {
        private TaskJob taskJob;

        public MyJobTask1(TaskJob taskJob) {
            this.taskJob = taskJob;
        }


        @Override
        public WorkExecuteResult call() throws Exception {
            WorkExecuteResult<Object> workExecuteResult = new WorkExecuteResult<>();
            /**
             *
             * 进行result的相关处理
             *
             */
            if (taskJob.getStatus() == 1) {
                //定义一个参数map
                Map<String, Object> param = Maps.newHashMap();
                long startTime = System.currentTimeMillis();
                /**
                 * 这里可以为param携带一些参数进行传入
                 */
                //业务相关的处理类
                mySingleTaskProcessor.work(taskJob, param);
                long execute = System.currentTimeMillis() - startTime;
                /**
                 * 任务处理的时间间隔进行处理，超时的话打印超时信息或者进行一些其他方面的处理
                 *
                 */
            }
            return workExecuteResult;
        }
    }

    //====================================供bean的注入的时候进行调用===========================================================
    public Integer getMyTaskType() {
        return myTaskType;
    }

    public void setMyTaskType(Integer myTaskType) {
        this.myTaskType = myTaskType;
    }

    public TaskBaseService getTaskBaseService() {
        return taskBaseService;
    }

    public void setTaskBaseService(TaskBaseService taskBaseService) {
        this.taskBaseService = taskBaseService;
    }

    public ThreadPoolTaskExecutor getThreadPoolTaskExecutorPool() {
        return threadPoolTaskExecutorPool;
    }

    public void setThreadPoolTaskExecutorPool(ThreadPoolTaskExecutor threadPoolTaskExecutorPool) {
        this.threadPoolTaskExecutorPool = threadPoolTaskExecutorPool;
    }

    public MySingleTaskProcessor getMySingleTaskProcessor() {
        return mySingleTaskProcessor;
    }

    public void setMySingleTaskProcessor(MySingleTaskProcessor mySingleTaskProcessor) {
        this.mySingleTaskProcessor = mySingleTaskProcessor;
    }

    public String getMyTaskName() {
        return myTaskName;
    }

    public void setMyTaskName(String myTaskName) {
        this.myTaskName = myTaskName;
    }
}
