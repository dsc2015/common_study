package shardstudytype1.base;


import shardstudytype1.SqlSessionFactoryContextHolder;

/**
 * Created by wangyingjie1 on 2016/3/17.
 *
 * @see GenericBaseDaoImpl
 */
public abstract class ShardDBandTabMergeBaseDao extends ShardDBandTableBaseDao {

    /**
     * 由于Merge表在基础库不需要路由，该方法覆盖了父类设置数据源的功能
     *
     * Warning: 非常重要
     *
     * @param isMaster
     * @param dsSerialNum
     */
    //@Override
    protected void setSessionFactory(boolean isMaster, String dsSerialNum) {
        // 默认为基础库的主库
         SqlSessionFactoryContextHolder.setBaseDBSlaveSessionFactory(isMaster);
    }

    protected boolean isMergeTable(){
        return true;
    }

}
