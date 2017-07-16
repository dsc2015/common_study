package shardstudytype1;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/6/30
 */
public class ShardingDataSource {
    private static ShardingDataSource instance = new ShardingDataSource();

    private ShardingDataSource() {

    }

    private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    public static ShardingDataSource getInstance() {
        return instance;
    }

    public synchronized void addDataSource(String dataSourceName, DataSource dataSource) {
        if (dataSourceMap.containsKey(dataSourceName) == false) {
            dataSourceMap.put(dataSourceName, dataSource);
        }
    }

    public DataSource getDataSource(String name) {
        return dataSourceMap.get(name);
    }
}
