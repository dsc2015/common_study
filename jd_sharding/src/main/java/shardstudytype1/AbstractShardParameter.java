package shardstudytype1;


/**
 * Created by wangyingjie1 on 2016/3/18.
 */
public abstract class AbstractShardParameter implements IShardParameter {

    protected String logicTableName = ShardConstants.DEFAULT_TABLE_NAME;

    protected String shardField;

    private String physicTableName = ShardConstants.DEFAULT_TABLE_NAME_FIELD;

    private String dataSourceName = ShardConstants.DEFAULT_DATASOURCE_NAME;


    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    @Override
    public String getLogicTableName() {
        return logicTableName;
    }

    @Override
    public String getShardField() {
        return shardField;
    }

    public void setShardField(String shardField) {
        this.shardField = shardField;
    }

    @Override
    public String getPhysicTableName() {
        return physicTableName;
    }

    @Override
    public void setPhysicTableName(String physicTableName) {
        this.physicTableName = physicTableName;
    }

    public void setLogicTableName(String logicTableName) {
        this.logicTableName = logicTableName;
    }

    /**子类设置逻辑表名**/
    public abstract void setLogicTableName();

}
