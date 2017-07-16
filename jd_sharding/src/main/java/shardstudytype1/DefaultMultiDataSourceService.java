package shardstudytype1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 如果不得已要显式获取数据连接，除了使用 DataSourceUtils 获取事务上下文绑定的连接外，还可以通过 TransactionAwareDataSourceProxy 对数据源进行代理。
 数据源对象被代理后就具有了事务上下文感知的能力，通过代理数据源的 getConnection() 方法获取的连接和使用 DataSourceUtils.getConnection() 获取连接的效果是一样的。

 的作用是返回一个数据源的代理。
 * TransactionAwareDataSourceProxy
 * =========================================================================================
 * @author dushuangcheng
 * @description
 * @create 2017/6/30
 */
public class DefaultMultiDataSourceService implements ApplicationContextAware, MultiDataSourceService, InitializingBean {
    private ApplicationContext springContext;
    private Map<String, Object> dataSourceConfig;
    private Map<String, String> dataSourceMap;


    public void setSpringContext(ApplicationContext springContext) {
        this.springContext = springContext;
    }


    public void setDataSourceConfig(Map<String, Object> dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }

    @Override
    public Map<String, String> getDataSources() {
        if (dataSourceMap == null || dataSourceMap.isEmpty()) {
            this.dataSourceMap = new HashMap<>();
        }
        return Collections.unmodifiableMap(dataSourceMap);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSourceConfig == null || dataSourceConfig.isEmpty()) {
            throw new Exception("");
        }
        dataSourceMap = new HashMap<>(dataSourceConfig.size());
        for (Map.Entry<String, Object> entry : dataSourceConfig.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof DataSource) {
                throw new Exception("");
            } else if (value instanceof String) {
                String keyBean = (String) value;
                Object beanName = springContext.getBean(keyBean);
                if (beanName instanceof DataSource) {
                    dataSourceMap.put(entry.getKey(), keyBean);
                    DataSource dataSource = (DataSource) beanName;
                    if ((dataSource instanceof TransactionAwareDataSourceProxy) == false) {
                        ShardingDataSource.getInstance().addDataSource(keyBean, new TransactionAwareDataSourceProxy(dataSource));
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new Exception();
                }
            }
        }
    }
}
