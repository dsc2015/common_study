package nondistributeworker;

import nondistributeworker.domain.MyTaskQuery;
import nondistributeworker.domain.TaskJob;

import java.util.List;

/** 定义了一系列的任务处理的相关类
 * @author dushuangcheng
 * @create 2017-06-05 14:58
 */
public interface TaskBaseService {

   // ==============================1,对任务状态进行更改部分的操作=============================
    /**
     * 一般来说任务的状态分为：1，等待执行的状态，2，锁定的状态，3 执行失败的状态，4 ，无效的状态，5，执行完成
     * 任务最初在创建的时候都是等待执行的状态，之后在任务框架的执行状态中变为锁定的状态，之后任务进行执行，
     * 执行成功之后任务的，任务的状态流转为已经完成的状态
     */
    /**
     * @description 对任务进行解锁，按照id进行解锁操作
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/5
     */
    int unlockTaskById(TaskJob taskJob );
    /**
     * @description  更新任务为成功状态
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/5
     */
    int updateTaskToSuccess(TaskJob taskJob);

    /**
     *
     * 锁定任务，任务在执行的状态有一个锁定的状态
     */
    int lockMyTask(TaskJob taskJob);
    /**
     *
     * ======================================2,批量操作任务的处理===================================
     *
     */
    /**
     * @description 根据条件查询任务的总的记录数
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/5
     */
    int getTaskJobByCondition(MyTaskQuery myTaskQuery);


    /**
     * @description 根据条件查询任务的列表
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/5
     */
    List<TaskJob> getTaskJobListByCondition(MyTaskQuery myTaskQuery);

}
