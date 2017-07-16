package shardstudytype1;

/**
 * @author dushuangcheng
 * @description 数据源的路由方式
 * @create 2017/7/6
 */
public interface IRouteFunction {
    /**
     * @param
     * @return
     * @description 根据期次获取数据库的编号
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/6
     */
    String getDsSerialNumByItemId(String logicTableName, long itemId);
    /**
     * @description 根据主键id来获取数据库的编号
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/7/6
     */
    String getDsSerialNumById(String logicTableName, long id) throws Exception;
    /**
     * 获取表的名称
     */
    String getTableNameByItemIdAndIssueId(String logicTableName, long itemId, long issueId);

    String getTableNameById(String logicTableName, long id);


    String getTableNameByHash(String logicTableName, long shardCondition);

    String getTableNameByHash(String logicTableName, String shardCondition);

    /**
     * 根据路由id获取数据源编号
     * @param routeId
     * @return
     */
    long getDsSerialNumByRouteId(long routeId) throws Exception;
}
