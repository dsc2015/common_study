package shardstudytype1;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/6/30
 */
public class SqlSessionFactoryContextHolder {
    private static final ThreadLocal<String> contextHolder=new ThreadLocal<>();
    public static void setContextHolder(String contextType){
        contextHolder.set(contextType);
    }
    public static String getContextHolder(){
       return contextHolder.get();
    }

    public static void clearContextType(){
        contextHolder.remove();
    }

    public static void setSessionFactory(boolean isMaster,String dsSerialNum){
        //根据dsSerialNum路由到sqlSessionFactory
        if(isMaster){
            //要注意是基础主库还是各个分库的主库
            SqlSessionFactoryContextHolder.setContextHolder("sqlSessionFactoryMaster_" + dsSerialNum);
        }else {
            //要注意是基础从库还是各个分库的从库
            SqlSessionFactoryContextHolder.setContextHolder("sqlSessionFactorySlave_"+dsSerialNum);
        }
    }
    /**
     * @description 默认是基础库的主库
     * 如果不是主库则设置数据源为基础库的从库
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/30
     */
    public static void setBaseDBSlaveSessionFactory(boolean isMaster){
        if(!isMaster){
            SqlSessionFactoryContextHolder.setContextHolder("sqlSessionFactorySlave");
        }
    }

}
