package shardstudytype1.base;

/**
 * Created by wangyingjie1 on 2016/3/20.
 * <p/>
 * 如果业务表有业务Merge表（数据冗余表，系统降级使用）;
 * 则其 Dao需要从此继承，可以实现 业务表/业务Merge表共用一套 sqlMap
 * <p/>
 * 如： treasure_issue/treasure_issue_merge
 */
public abstract class ShardOnlyDBMergeBaseDao extends ShardOnlyDBBaseDao {


    /**
     * 该方法覆盖了父类的方法，父类的默认实现返回：false
     *
     * @return
     */
    @Override
    protected boolean isMergeTable() {
        return true;
    }

}
