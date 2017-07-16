package shardstudytype1;

import java.util.Map;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/6/30
 */
public interface MultiDataSourceService {
    //获取所有的数据源
    public Map<String, String> getDataSources();
}
