package shardstudytype1;

import com.jd.common.util.SequenceUtil;

/**
 * Created by wangyingjie1 on 2016/4/16.
 * <p/>
 * 获取生成sequenceId 生成器信息
 */
public interface SeqIdInfoGenerator {

    /**
     * 获取生成sequenceId 生成器信息
     *
     * @param sequenceUtil
     * @param shardRouterUtil
     * @param logicTableName
     * @param itemId
     * @param issueId
     * @return
     */
    public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception;

}


class SeqIdInfo {
    long id = 0;
    long routeId = -1;
    long dsSerialNum = 0;//数据库编号

    public SeqIdInfo() {
    }

    public SeqIdInfo(long id, long routeId, long dsSerialNum) {
        this.id = id;
        this.routeId = routeId;
        this.dsSerialNum = dsSerialNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getDsSerialNum() {
        return dsSerialNum;
    }

    public void setDsSerialNum(long dsSerialNum) {
        this.dsSerialNum = dsSerialNum;
    }
}
