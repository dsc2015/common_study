package shardstudytype1;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import shardstudytype1.dao.GenericDaoImpl;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/6
 */
public abstract class SharedHashTemplete extends GenericDaoImpl implements InitializingBean {
    private IRouteFunction routerFunction;


    protected Object routerMasterSqlSessionFactoryContextHolder(Object parameterObj) {
        return routerSqlSessionFactoryContextHolder(parameterObj, true);
    }

    protected Object routerSlaveSqlSessionFactoryContextHolder(Object parameterObj) {
        return routerSqlSessionFactoryContextHolder(parameterObj, false);
    }

    //按照Hash分表不分库、只需要处理逻辑表名即可
    private Object routerSqlSessionFactoryContextHolder(Object parameterObj, boolean isMaster) {

        //路由只需要回写物理表名就可以
        if (parameterObj instanceof List) {

            List<Object> shardList = (List<Object>) parameterObj;
            if (CollectionUtils.isEmpty(shardList)) {
                throw new RuntimeException("parameter cannot be empty !");
            }

            Object shardObj = shardList.get(0);
            if (shardObj instanceof AbstractShardParameter) {
                AbstractShardParameter sharding = (AbstractShardParameter) shardObj;
                doRoute(parameterObj, sharding);
            } else if (shardObj instanceof IShardParameter) {
                IShardParameter sharding = (IShardParameter) shardObj;
                doRoute(parameterObj, sharding);
            } else {
                throw new RuntimeException("不支持的路由参数类型:" + shardObj.getClass().getName());
            }
        } else if (parameterObj instanceof Map) {
            Map<String, Object> shardMap = (Map<String, Object>) parameterObj;
            if (shardMap.containsKey(ShardConstants.DEFAULT_SHARDING_FIELD) && shardMap.containsKey(ShardConstants.DEFAULT_TABLE_NAME)) {
                doRoute(parameterObj, shardMap);
            } else {
                throw new RuntimeException("Map参数缺少路由字段");
            }
        } else if (parameterObj instanceof AbstractShardParameter) {
            AbstractShardParameter sharding = (AbstractShardParameter) parameterObj;
            doRoute(parameterObj, sharding);
        } else {
            throw new RuntimeException("不支持的路由参数类型:" + parameterObj.getClass().getName());
        }

        return parameterObj;
    }

    private String doRoute(final Object parameterObj, Map<String, Object> shardMap) {
        String logicTableName = (String) shardMap.get(ShardConstants.DEFAULT_TABLE_NAME);
        String routeField = (String) shardMap.get(ShardConstants.DEFAULT_SHARDING_FIELD);
        if (StringUtils.isEmpty(routeField)) {
            throw new RuntimeException("路由字段为空");
        }
        try {
            Object routeBean = shardMap.get(routeField);
            if ((routeBean instanceof Long) || (routeBean instanceof Integer)) {
                long shardCondition = Long.parseLong(String.valueOf(routeBean));

                String routedTableName = routerFunction.getTableNameByHash(logicTableName, shardCondition);
                shardMap.put(ShardConstants.DEFAULT_TABLE_NAME_FIELD, routedTableName);
            } else if (routeBean instanceof String) {
                String routedTableName = routerFunction.getTableNameByHash(logicTableName, String.valueOf(routeBean));
                shardMap.put(ShardConstants.DEFAULT_TABLE_NAME_FIELD, routedTableName);
            } else
                throw new RuntimeException("不支持的路由条件");
        } catch (Exception e) {
            throw new RuntimeException("路由信息错误", e);
        }
        return "";
    }

    private String doRoute(final Object parameterObj, IShardParameter shardingParameter) {
        String logicTableName = getLogicTableName();
        String routeField = getShardField();
        if (StringUtils.isEmpty(routeField)) {
            throw new RuntimeException("路由字段为空");
        }
        try {
            Object routeBean = BeanPropertyAccessUtil.getPropertyValue(routeField, parameterObj);

            if ((routeBean instanceof Long) || (routeBean instanceof Integer)) {
                long shardCondition = Long.parseLong(String.valueOf(routeBean));

                String routedTableName = routerFunction.getTableNameByHash(logicTableName, shardCondition);
                shardingParameter.setPhysicTableName(routedTableName);
            } else if (routeBean instanceof String) {
                String routedTableName = routerFunction.getTableNameByHash(logicTableName, String.valueOf(routeBean));
                shardingParameter.setPhysicTableName(routedTableName);
            } else {
                throw new RuntimeException("不支持的路由条件");
            }

        } catch (Exception e) {
            throw new RuntimeException("路由信息错误", e);
        }

        return "";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (routerFunction == null) {
            throw new RuntimeException("ShardHashTemplate 中路由函数为空 routerFunction=null");
        }
    }

    /**
     * 子类实现 指定hash字段值
     *
     * @return
     */
    public abstract String getShardField();

    /**
     * 子类实现 指定逻辑表名
     *
     * @return
     */
    public abstract String getLogicTableName();

}
