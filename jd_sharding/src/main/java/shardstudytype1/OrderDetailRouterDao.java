package shardstudytype1;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guohaifei
 * Date: 2016/4/11
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public interface OrderDetailRouterDao {
    int batchInsert(List<TreasureOrderDetailRouter> routers);

    /**
     * 查找所有有效记录
     * @return
     */
    List<TreasureOrderDetailRouter> selectAll();
}
