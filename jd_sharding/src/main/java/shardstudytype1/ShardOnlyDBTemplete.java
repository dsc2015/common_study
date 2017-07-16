package shardstudytype1;/**
 * Created by dushuangcheng on 2017/7/7.
 */

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;
import shardstudytype1.dao.GenericDaoImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**从参数对象中路由数据源，设置dao操作的表名
 * @author dushuangcheng
 * @create 2017-07-07 9:41
 */
public abstract class ShardOnlyDBTemplete extends GenericDaoImpl {
    private static final String ID="id";
    private static final String ITEM_ID="itemId";
    private static final String SHARD_ID="shardId";
    private IRouteFunction routerFunction;
    protected Object routerMasterSqlSessionFactoryContextHolder(Object parameterObj) {
        return routerSqlSessionFactoryContextHolder(parameterObj, true);
    }

    protected Object routerSlaveSqlSessionFactoryContextHolder(Object parameterObj) {
        return routerSqlSessionFactoryContextHolder(parameterObj, false);
    }

    private Object routerSqlSessionFactoryContextHolder(Object parameterObj, boolean isMaster) {

        Object routeId;
        try {

            if (isMergeTable()) {
                // Merge表默认在基础库，可不路由数据源
                return setMergeTableName(parameterObj);
            }

            //如果参数是基本类型，参数必须而且一定是主键 id or itemId
            if (parameterObj instanceof Long) {
                routeId = parameterObj;
            } else if (parameterObj instanceof List) {
                List<Object> shardList = (List<Object>) parameterObj;

                if (CollectionUtils.isEmpty(shardList)) {
                    throw new RuntimeException("parameter cannot be empty !");
                }
                Object shardObj = shardList.get(0);
                routeId = getRouteId(shardObj);

                //防止入错库，List 所有对象必须同库
                checkListIsInSameDB(routeId, shardList);

                if (shardObj instanceof IShardParameter) {
                    parameterObj = changeParameterObj(parameterObj, (IShardParameter)shardObj);
                }

            } else if (parameterObj instanceof Map) {
                routeId = getRouteIdFromMap((Map<String, Object>) parameterObj);
            } else {
                routeId = getRouteId(parameterObj);
            }
        } catch (Throwable t) {
            //log.error("Illegal route parameter， route info error!!!");
            throw new IllegalArgumentException("Illegal route parameter， 路由信息错误");
        }

        Assert.notNull(routeId, "Get routeId error!  itemId and primary key id and shardId is null");

        // 基础库任务兼容
        String dsSerialNum = routerFunction.getDsSerialNumByItemId("", (Long) routeId);

        //dsSerialNum ==0 不设置数据源，默认取基础库数据源
        if (Integer.valueOf(dsSerialNum) > 0) {
            //设置到当前线程的变量中去
            SqlSessionFactoryContextHolder.setSessionFactory(isMaster, dsSerialNum);
        }

        return parameterObj;
    }

    /**
     * 将业务代码传入的List参数修改为Map参数传入到 sqlMap 中进行路由表名，否则无法支持批量的更新操作
     *
     * @param parameterObj
     * @param sharding
     * @return
     */
    private Map<String, Object>  changeParameterObj(Object parameterObj, IShardParameter sharding) {
        //支持批量更新操作
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("physicTableName", sharding.getPhysicTableName());
        map.put("list", new ArrayList((List) parameterObj));
        return map;
    }


    // 检查list的对象是否在同一个库不在一个库抛异常
    private void checkListIsInSameDB(Object routeId, List<Object> shardList) throws Exception {
        if (routeId != null && shardList.size() > 1) {
            Long dsNum = routerFunction.getDsSerialNumByRouteId((Long) routeId);
            for (int i = 1; i < shardList.size(); i++) {//i=1 过滤掉第一个对象

                Object shardObject = shardList.get(i);
                Long everyRouteId = getRouteId(shardObject);

                Long everyDsNum = routerFunction.getDsSerialNumByRouteId(everyRouteId);
                if (!dsNum.equals(everyDsNum)) {
                    throw new IllegalArgumentException(String.format("批量保存的对象必须同库，List中第：%s 个对象不再 %s 库中", i, dsNum));
                }
            }
        }
    }

    /**
     * Merge 表位于基础库，直接设置物理表名，不用路由数据源
     * Merge 表基本都是查询操作，故仅仅支持 对象、Map 两种参数类型
     *
     * @param parameterObj
     * @return
     */
    private Object setMergeTableName(Object parameterObj) {
        if (parameterObj instanceof Map) {
            Map<String, Object> shardMap = (Map<String, Object>) parameterObj;
            shardMap.put(ShardConstants.DEFAULT_TABLE_NAME_FIELD, getPhysicTableName());
        } else if (parameterObj instanceof IShardParameter) {
            IShardParameter sharding = (IShardParameter) parameterObj;
            sharding.setPhysicTableName(getPhysicTableName());
        } else {
            throw new RuntimeException("不支持的路由参数类型:" + parameterObj.getClass().getName());
        }
        return parameterObj;
    }

    /**
     * 默认不是Merge表Dao操作
     * <p/>
     * 子类 ShardOnlyDBMergeBaseDao 将覆盖此方法
     * <p/>
     * 虽然设计上有点不Beauty! 但是实现了业务表/业务Merge表 共用一套 sqlMap; 降低了两套sqlMap的维护成本
     *
     * @return
     */
    protected boolean isMergeTable() {
        return false;
    }

    /**
     * 子类重写物理表名
     *
     * @return
     */
    protected abstract String getPhysicTableName();

    /**
     * 若参数类型为Map，则从Map里面获取路由字段
     *
     * @param parameterObj
     * @return
     */
    protected Long getRouteIdFromMap(Map<String, Object> parameterObj) {
        Object routeId = null;

        // 为了兼容业务表与Merge表共用一套sql，此处将物理表名放到Map里面
        parameterObj.put(ShardConstants.DEFAULT_TABLE_NAME_FIELD, getPhysicTableName());

        if (parameterObj.containsKey(ID)) {
            routeId = parameterObj.get(ID);
        }

        if (routeId == null && parameterObj.containsKey(ITEM_ID)) {
            routeId = parameterObj.get(ITEM_ID);
        }

        if (routeId == null && parameterObj.containsKey(SHARD_ID)) {
            routeId = parameterObj.get(SHARD_ID);
        }

        return routeId != null ? (Long) routeId : null;
    }


    /**
     * 根据参数对象获取路由Id，只分库的表路由Id可以是   itemId  or  主键 id
     *
     * @param shardObj
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected Long getRouteId(Object shardObj) throws InvocationTargetException, IllegalAccessException {

        //先取到主键Id路由到库
        Long routeId = (Long) BeanPropertyAccessUtil.getPropertyValue(ID, shardObj);
        if (routeId != null) {
            return routeId;
        }

        //未取到主键Id 则取项Id路由到库
        routeId = (Long) BeanPropertyAccessUtil.getPropertyValue(ITEM_ID, shardObj);
        if (routeId != null) {
            return routeId;
        }

        //Id & 项Id 均未取到 则取shardId路由到库
        routeId = (Long) BeanPropertyAccessUtil.getPropertyValue(SHARD_ID, shardObj);

        //如经过以上 3 个条件都未获取到路由Id则通过断言抛异常
        if (!isMergeTable()) {
            //Merge table 不校验 routeId
            Assert.notNull(routeId, "Get routeId error!  itemId and primary key id and shardId is null");
        }

        return routeId;
    }


}
