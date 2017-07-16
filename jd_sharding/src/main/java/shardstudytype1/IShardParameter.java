package shardstudytype1;

/**
 * Created by wangyingjie1 on 2016/3/18.
 */
public interface IShardParameter {

    /**
     * 获取逻辑表名
     *
     * @return 逻辑表名
     */
    public String getLogicTableName();

    /**
     * 获取分表键，分表键必须是对象中存在的属性
     *
     * @return 分表键
     */
    public String getShardField();

    /**
     * 获取物理表名，是分表计算之后拼接的表名
     *
     * @return 物理表名
     */
    public String getPhysicTableName();

    /**
     * 设置物理表名，计算后的表名通过反射设置，设置的值可以通过getPhysicTableName()方法获取
     *
     * @param physicTableName
     */
    public void setPhysicTableName(String physicTableName);

}
