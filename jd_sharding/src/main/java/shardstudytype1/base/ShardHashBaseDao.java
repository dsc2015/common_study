package shardstudytype1.base;

import com.jd.treasure.dao.shard.template.ShardHashTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyingjie1 on 2016/3/20.
 */
public abstract class ShardHashBaseDao extends ShardHashTemplate {


    /**
     * 返回带有hash字段 及 逻辑表名 的map参数
     *
     * @return
     */
    public Map<String, Object> routeHashMapWrapper() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shardField", getShardField());
        params.put("logicTableName", getLogicTableName());
        return params;
    }


    /**
     * 包装map参数
     *
     * @param map
     * @return
     */
    public Map<String, Object> routeWithMap(Map<String, Object> map) {
        Map<String, Object> routeHashMap = routeHashMapWrapper();
        routeHashMap.putAll(map);
        return routeHashMap;
    }

}
