package shardstudytype1;

import java.util.List;

/**
 * 订单路由表dao
 * User: guohaifei
 * Date: 2016/4/11
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public interface OrderRouterDao {
    /**
     * 插入单条数据
     * @param orderRouter
     * @return
     */
    int insert(TreasureOrderRouter orderRouter);

    /**
     * 批量插入
     * @param routers
     * @return
     */
    int batchInsert(List<TreasureOrderRouter> routers);

    /**
     * 查找所有有效记录
     * @return
     */
    List<TreasureOrderRouter> selectAll();
}
