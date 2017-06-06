package nondistributeworker;

import nondistributeworker.domain.TaskJob;

import java.util.Map;

/**
 * 跟业务逻辑紧密相连的单任务进行的处理
 * @author dushuangcheng
 * @create 2017-06-05 15:18
 */
public class MySingleTaskProcessor {

    /**
     * @description 任务执行的主体类
     * @Author  dushuangcheng
     * @param  taskJob:任务类，param:相关的执行参数
     * @return
     * @throw
     * @date   2017/6/5
     */
    public void work(TaskJob taskJob,Map<String,Object> param){

    }
}
