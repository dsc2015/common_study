package shardstudytype1;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 这个类主要依托spring的事务管理，继承Transactionmanger
 *
 * @author dushuangcheng
 * @description 多数据源的事务处理
 * @create 2017/6/30
 */
public class MultiDataSourceTransactionManger extends AbstractPlatformTransactionManager implements InitializingBean {
    //默认的多数据源
    private DefaultMultiDataSourceService defaultMultiDataSourceServiced;
    //一系列是事务管理器
    private List<PlatformTransactionManager> transactionMangers = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Validate.notNull(defaultMultiDataSourceServiced);
        //获取配置文件中所有的数据库的集合
        Set<String> dataSourceNames = getDefaultMultiDataSourceServiced().getDataSources().keySet();
        ShardingDataSource shardingDataSource = ShardingDataSource.getInstance();
        //根据数据源的名称创建DataSourceTransactionManger
        for (String dataSourceName : dataSourceNames) {
            DataSource dataSource = shardingDataSource.getDataSource(dataSourceName);
            DataSourceTransactionManager dataSourceTanansactionManger = getDataSourceTanansactionManger(dataSource);
            //将数据源的事务管理器放入到事务管理器的集合中去
            transactionMangers.add(dataSourceTanansactionManger);
        }
    }

    /**
     * @param
     * @return
     * @description 根据数据源获取事务管理器
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/6
     */
    private DataSourceTransactionManager getDataSourceTanansactionManger(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        return new ArrayList<DefaultTransactionStatus>();
    }

    /**
     * @param
     * @return
     * @description 实现抽象类absTransaction中方法
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/6
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        List<DefaultTransactionStatus> list = (List<DefaultTransactionStatus>) transaction;
        // Do rollback when exception to ensure release any resource or restore any status during doBegin on succeeded data source.
        try {
            for (PlatformTransactionManager transactionManager : transactionMangers) {
                DefaultTransactionStatus element = (DefaultTransactionStatus) transactionManager
                        .getTransaction(definition);
                list.add(element);
            }
        } catch (TransactionException e) {
            logger.error("Caught an exception during doBegin on multiple data sources", e);
            //Construct a new TransactionStatus with success transactionStatus List and then rollback them.
            boolean newSynchronization = (getTransactionSynchronization() != SYNCHRONIZATION_NEVER);
            TransactionStatus transactionStatus = newTransactionStatus(definition, list, true, newSynchronization, logger.isDebugEnabled(), null);
            rollback(transactionStatus);
            throw e;
        }
    }

    /**
     * @param
     * @return
     * @description 事务提交的本质是通过借助于Transaction提交TransactionStatus
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/6
     */
    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        //获取事务的status的集合
        List<DefaultTransactionStatus> list = (List<DefaultTransactionStatus>) status.getTransaction();
        //校验长度
        Validate.isTrue(list.size() <= this.transactionMangers.size());
        TransactionException transactionException = null;

        for (int i = list.size() - 1; i >= 0; i--) {
            PlatformTransactionManager platformTransactionManager = this.getTransactionMangers().get(i);
            DefaultTransactionStatus localTransactionStatus = list.get(i);
            try {
                //提交的是事务的状态
                platformTransactionManager.commit(localTransactionStatus);
            } catch (TransactionException t) {
                transactionException = t;
            }
            if (transactionException != null) {
                throw transactionException;
            }
        }
    }

    /**
     * @param
     * @return
     * @description 在集合中逐个回滚？
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/6
     */
    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        List<TransactionStatus> list = (List<TransactionStatus>) status.getTransaction();
        Validate.isTrue(list.size() <= this.transactionMangers.size());
        TransactionException lastException = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            PlatformTransactionManager transactionManager = this.getTransactionMangers().get(i);
            TransactionStatus localTransactionStatus = list.get(i);

            try {
                transactionManager.rollback(localTransactionStatus);
            } catch (TransactionException e) {
                // Log exception and try to complete rollback
                lastException = e;
                logger.error("error occured when rolling back the transaction. \n{}", e);
            }
        }

        if (lastException != null) {
            throw lastException;
        }
    }

    public DefaultMultiDataSourceService getDefaultMultiDataSourceServiced() {
        return defaultMultiDataSourceServiced;
    }

    public void setDefaultMultiDataSourceServiced(DefaultMultiDataSourceService defaultMultiDataSourceServiced) {
        this.defaultMultiDataSourceServiced = defaultMultiDataSourceServiced;
    }

    public List<PlatformTransactionManager> getTransactionMangers() {
        return transactionMangers;
    }

    public void setTransactionMangers(List<PlatformTransactionManager> transactionMangers) {
        this.transactionMangers = transactionMangers;
    }
}
