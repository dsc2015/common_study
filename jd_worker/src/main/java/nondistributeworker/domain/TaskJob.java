package nondistributeworker.domain;
/**
 * @author dushuangcheng
 * @create 2017-06-05 15:00
 */
public class TaskJob {
    /**
     * @description:任务的执行状态
     */
    private Integer status;
    private Long mytTaskId;
    /**
     * @description:任务的名称
     */
    private String myTaskName;
    /**
     * @description:任务的类型
     */
    private Integer myTaskType;
    /**
     * @description:与业务相关id标识符
     */
    private String businessId;
    /**
     * @description:存储业务数据相关的
     */
    private String data;

    public Long getMytTaskId() {
        return mytTaskId;
    }

    public void setMytTaskId(Long mytTaskId) {
        this.mytTaskId = mytTaskId;
    }

    public String getMyTaskName() {
        return myTaskName;
    }

    public void setMyTaskName(String myTaskName) {
        this.myTaskName = myTaskName;
    }

    public Integer getMyTaskType() {
        return myTaskType;
    }

    public void setMyTaskType(Integer myTaskType) {
        this.myTaskType = myTaskType;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskJob{" +
                "status=" + status +
                ", mytTaskId=" + mytTaskId +
                ", myTaskName='" + myTaskName + '\'' +
                ", myTaskType=" + myTaskType +
                ", businessId='" + businessId + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
