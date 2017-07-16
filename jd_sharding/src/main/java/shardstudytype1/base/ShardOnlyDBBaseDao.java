package shardstudytype1.base;

import com.jd.treasure.dao.shard.template.ShardOnlyDBTemplate;
import com.jd.treasure.domain.shard.IShardParameter;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyingjie1 on 2016/3/17.
 *
 * @see com.jd.treasure.dao.base.GenericBaseDaoImpl
 * <p/>
 * 该类的是对父类 GenericBaseDaoImpl 中所有方法的一个静态代理
 * 主要作用
 * 1、对父类方法的参数对象包装路由信息，以供 ShardOnlyDBTemplate 使用
 * 2、兼容业务表/业务Merge表共用一套sql的功能
 */
public abstract class ShardOnlyDBBaseDao extends ShardOnlyDBTemplate {

    private static final Logger log = LoggerFactory.getLogger(ShardOnlyDBBaseDao.class);

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
     * 判断参数对象中是否包含 itemid  or  主键id  or  shardId
     *
     * @param parameterObj
     * @return
     */
    protected Object routeWrapper(Object parameterObj) {
        Long routeId = null;
        try {
            if (parameterObj instanceof Long) {
                routeId = (Long) parameterObj;
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(ID, routeId);
                return map;

            } else if (parameterObj instanceof List) {

                List<Object> shardList = (List<Object>) parameterObj;

                if (CollectionUtils.isEmpty(shardList)) {
                    throw new RuntimeException("parameter cannot be empty !");
                }

                Object shardObj = shardList.get(0);

                routeId = getRouteId(shardObj);

                //包含Merge表的情况，sqlMap 里面的表名是动态的 故在此处需设置物理表名
                if (isHaveMergeTable(shardObj)) {
                    setPhysicTableName((IShardParameter) shardObj);
                }

            } else if (parameterObj instanceof Map) {

                routeId = getRouteIdFromMap((Map<String, Object>) parameterObj);

            } else {
                routeId = getRouteId(parameterObj);

                if (isHaveMergeTable(parameterObj)) {
                    setPhysicTableName((IShardParameter) parameterObj);
                }
            }
        } catch (Exception e) {
            log.error("routeId checkup exception:" + e.getMessage(), e);
        }

        if (!isMergeTable()) {
            // 如果是分库的表需要检查路由Id
            Assert.notNull(routeId, "SQL parameter missing necessary route filed itemId or primarykey id,  Get itemId/id is error!");
        }

        return parameterObj;
    }

    /**
     * 包含业务表包含Merge表 sqlMap 共用，故表名为动态的
     * <p/>
     * 如：treasure_issue  与  treasure_issue_merge 共用一套sqlMap
     *
     * @param parameterObj
     */
    private void setPhysicTableName(IShardParameter parameterObj) {
        IShardParameter sharding = parameterObj;
        sharding.setPhysicTableName(getPhysicTableName());
    }


    /**
     * 包含Merge表的业务表查询对象需要为 IShardParamter 对象
     *
     * @param parameterObj
     * @return
     */
    private boolean isHaveMergeTable(Object parameterObj) {
        return parameterObj instanceof IShardParameter;
    }

}
