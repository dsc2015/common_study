package shardstudytype1;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/6
 */
public class IRouteFunctionImpl implements IRouteFunction {

    private ShardRouterUtil shardRouterUtil;

    @Override
    public String getDsSerialNumByItemId(String logicTableName, long itemId) {
        return String.valueOf(ShardRouterUtil.getDsSerialNumByItemId(itemId));
    }

    @Override
    public String getDsSerialNumById(String logicTableName, long id) throws Exception {
        return String.valueOf(ShardRouterUtil.getDsSerialNumById(logicTableName,id));
    }

    @Override
    public String getTableNameByItemIdAndIssueId(String logicTableName, long itemId, long issueId) {
        return shardRouterUtil.getTableNameByItemIdAndIssueId(logicTableName,itemId,issueId);
    }

    @Override
    public String getTableNameById(String logicTableName, long id) {
        return shardRouterUtil.getTableNameById(logicTableName,id);
    }

    @Override
    public String getTableNameByHash(String logicTableName, long shardCondition) {
        return shardRouterUtil.getTableNameByHash(logicTableName,shardCondition);
    }

    @Override
    public String getTableNameByHash(String logicTableName, String shardCondition) {
        return shardRouterUtil.getTableNameByHash(logicTableName,shardCondition);
    }

    @Override
    public long getDsSerialNumByRouteId(long routeId) throws Exception {
        return ShardRouterUtil.getDsSerialNumById(null,routeId);
    }
}
