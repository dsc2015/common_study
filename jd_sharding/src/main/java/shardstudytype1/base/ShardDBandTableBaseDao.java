package shardstudytype1.base;

import com.jd.treasure.dao.shard.template.ShardDBandTableTemplate;
import com.jd.treasure.domain.shard.condtion.ShardCondition;
import com.jd.treasure.domain.shard.constants.ShardConstants;
import com.jd.treasure.domain.shard.factory.OrderShardConditionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyingjie1 on 2016/3/17.
 *
 * @see com.jd.treasure.dao.base.GenericBaseDaoImpl
 * <p/>
 * 该类的是对父类 GenericBaseDaoImpl 中所有方法的一个静态代理
 * 主要作用是对父类方法的参数对象包装路由信息，以供 ShardDBandTableTemplate 使用
 */
public abstract class ShardDBandTableBaseDao extends ShardDBandTableTemplate {

    private static final Logger log = LoggerFactory.getLogger(ShardDBandTableBaseDao.class);

    // 父类接口实现
    public <E> E executeForObjectWithRouter(String statementName, Object bindParams, Class<E> clazz) {
        return this.executeForObject(statementName, routeWrapper(bindParams), clazz);
    }

    public Map<String, Object> executeForMapWithRouter(String statementName, Object bindParams) {
        return executeForMap(statementName, routeWrapper(bindParams));
    }

    public <E> E[] executeForObjectArrayWithRouter(String statementName, Object bindParams, Class<E> clazz) {
        return executeForObjectArray(statementName, routeWrapper(bindParams), clazz);
    }

    public Map<String, Object>[] executeForMapArrayWithRouter(String statementName, Object bindParams) {
        return executeForMapArray(statementName, routeWrapper(bindParams));
    }

    public <E> E[] executeForObjectArrayWithRouter(String statementName, Object bindParams, Class<E> clazz, int beginIndex, int maxCount) {
        return executeForObjectArray(statementName, routeWrapper(bindParams), clazz, beginIndex, maxCount);
    }

    public Map<String, Object>[] executeForMapArrayWithRouter(String statementName, Object bindParams, int beginIndex, int maxCount) {
        return executeForMapArray(statementName, routeWrapper(bindParams), beginIndex, maxCount);
    }

    public <E> List<E> executeForObjectListWithRouter(String statementName, Object bindParams) {
        return executeForObjectList(statementName, routeWrapper(bindParams));
    }

    public List<Map<String, Object>> executeForMapListWithRouter(String statementName, Object bindParams) {
        return executeForMapList(statementName, routeWrapper(bindParams));
    }

    public int executeWithRouter(String statementName, Object bindParams) {
        return execute(statementName, routeWrapper(bindParams));
    }

    public <E> E executeForObjectBySlaveWithRouter(String statementName, Object bindParams, Class<E> clazz) {
        return executeForObjectBySlave(statementName, routeWrapper(bindParams), clazz);
    }

    public <E> List<E> executeForObjectListBySlaveWithRouter(String statementName, Object bindParams) {
        return executeForObjectListBySlave(statementName, routeWrapper(bindParams));
    }

    /**
     * 包装路由参数
     *
     * @param parameterObj
     * @return
     */
    protected Object routeWrapper(Object parameterObj) {

        try {
            if (parameterObj instanceof Long) {
                return routeWithId(parameterObj, (Long) parameterObj);
            } else if (parameterObj instanceof Map) {
                Map<String, Object> shardMap = (Map<String, Object>) parameterObj;
                String routeIdName = getRouteIdName();
                if (shardMap.containsKey(routeIdName)) {
                    return routeWithId(shardMap, (Long) shardMap.get(routeIdName));
                }
                if (shardMap.containsKey("itemId") && shardMap.containsKey("issueId")) {
                    return routeWithItemIdAndIssueId(shardMap, (Long) shardMap.get("itemId"), (Long) shardMap.get("issueId"));
                }
            }
        } catch (Exception e) {
            log.error("route exception:" + e.getMessage(), e);
        }
        return parameterObj;
    }

    private Object routeWithId(Object parameterObj, Long routeId) {
        String routeIdName = getRouteIdName();
        try {
            if (parameterObj instanceof Long) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(ShardConstants.DEFAULT_SHARDING_FIELD, "shardCondition");
                map.put(ShardConstants.DEFAULT_TABLE_NAME, getPhysicTableName());
                map.put("shardCondition", getConditionById(routeId));
                map.put(routeIdName, routeId);
                return map;
            } else if (parameterObj instanceof Map) {
                Map<String, Object> shardMap = (Map<String, Object>) parameterObj;
                shardMap.put(ShardConstants.DEFAULT_SHARDING_FIELD, "shardCondition");
                shardMap.put(ShardConstants.DEFAULT_TABLE_NAME, getPhysicTableName());
                shardMap.put("shardCondition", getConditionById(routeId));
                if (!shardMap.containsKey(routeIdName)) {
                    shardMap.put(routeIdName, routeId);
                }
                return shardMap;
            }
        } catch (Exception e) {
            log.error("routeWithId exception:" + e.getMessage(), e);
        }
        return parameterObj;
    }

    private ShardCondition getConditionById(Long routeId) {
        return OrderShardConditionFactory.getIdCondition().setId(routeId);
    }

    private Object routeWithItemIdAndIssueId(Object parameterObj, Long itemId, Long issueId) {
        try {
            if (parameterObj instanceof Long) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(ShardConstants.DEFAULT_SHARDING_FIELD, "shardCondition");
                map.put(ShardConstants.DEFAULT_TABLE_NAME, getPhysicTableName());
                map.put("shardCondition", getConditionByItemIdAndIssueId(itemId, issueId));
                map.put("itemId", itemId);
                map.put("issueId", issueId);
                map.put("parameterId", parameterObj);
                return map;
            } else if (parameterObj instanceof Map) {
                Map<String, Object> shardMap = (Map<String, Object>) parameterObj;
                shardMap.put(ShardConstants.DEFAULT_SHARDING_FIELD, "shardCondition");
                shardMap.put(ShardConstants.DEFAULT_TABLE_NAME, getPhysicTableName());
                shardMap.put("shardCondition", getConditionByItemIdAndIssueId(itemId, issueId));
                if (!shardMap.containsKey("itemId")) {
                    shardMap.put("itemId", itemId);
                }
                if (!shardMap.containsKey("issueId")) {
                    shardMap.put("issueId", issueId);
                }
                return shardMap;
            }
        } catch (Exception e) {
            log.error("routeWithLotteryTypeIdAndIssueName exception:" + e.getMessage(), e);
        }
        return parameterObj;
    }

    private ShardCondition getConditionByItemIdAndIssueId(Long itemId, Long issueId) {
        return OrderShardConditionFactory.getItemCondition().setIssueId(issueId).setItemId(itemId);
    }

}
