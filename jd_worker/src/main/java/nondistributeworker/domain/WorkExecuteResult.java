package nondistributeworker.domain;


/**
 *任务执行的结果 ,实现future接口，进行结果的异步返回等处理
 * @author dushuangcheng
 * @create 2017-06-05 15:48
 */
public class WorkExecuteResult<T>  {
    /**
     * @description:任务执行是否是成功的
     */
    public boolean isSuccess;
    /**
     * @description:任务执行过程中的一些信息打印
     */
    public String msg;
    /**
     * @description:任务执行过程中的相关的数据处理
     */
    public T data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WorkExecuteResult{" +
                "isSuccess=" + isSuccess +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
