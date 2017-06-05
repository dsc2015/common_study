package com.jd.sqcune;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/** 基于表的sequence
 * @author dushuangcheng
 * @create 2017-06-05 12:01
 */
public class Sequence {
    private int blockSize=5;
    private long startValue=0;
    private DataSource dataSource;
    private final static String GET_SQL="select id from sequence_value where name=?";
    private final static String NEW_SQL="insert into sequence_value (id,name) values(?,?)";
    private final static String UPDATE_SQL="update sequence_value set id=? where name=? and id=?";
    /**
     *步长map
     */
    private Map<String ,Step> sepMap=new HashMap<>();

    public synchronized long get(String sequenceName){
        Step step = sepMap.get(sequenceName);
        if(step==null){
            step=new Step(startValue,startValue+blockSize);
            sepMap.put(sequenceName,step);
        }else {
            //直接从缓存中拿到
            if(step.currentValue<step.endValue){
                return step.incrementAndGet();
            }
        }
        for(int i=0;i<blockSize;i++){
            if(getNextBlock(sequenceName,step)){
                return step.incrementAndGet();
            }
        }
        throw new RuntimeException("error...");
    }

    private boolean getNextBlock(String sequenceName, Step step) {
        //从数据库中查询出值，只有在有的情况下才会有值的
        Long value = getPersistenceValue(sequenceName);
        if (value == null) {//如果没有，就初始化
            try {
                value = newPersistenceValue(sequenceName); //初始化。返回初始化的值
            } catch (Exception e) {
                //log.error("newPersistenceValue error!");
                value = getPersistenceValue(sequenceName); //如果初始化失败，说明有程序先初始化了
            }
        }
        //更新步长为当前值加上步长，然后组成一个步长对象
        boolean b = saveValue(value,sequenceName) == 1;
        if (b) {
            step.setCurrentValue(value);
            step.setEndValue(value+blockSize);
        }
        return b;
    }

    private int saveValue(long value, String sequenceName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UPDATE_SQL);
            statement.setLong(1, value + blockSize);
            statement.setString(2, sequenceName);
            statement.setLong(3, value);
            return statement.executeUpdate();
        } catch (Exception e) {
           // log.error("newPersistenceValue error!", e);
            throw new RuntimeException("newPersistenceValue error!", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                   // log.error("close statement error!", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                   // log.error("close connection error!", e);
                }
            }
        }
    }

    private Long getPersistenceValue(String sequenceName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(GET_SQL);
            statement.setString(1, sequenceName);
            resultSet = statement.executeQuery();
            //数据库自增的主键返回
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (Exception e) {
            //log.error("getPersistenceValue error!", e);
            throw new RuntimeException("getPersistenceValue error!", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //log.error("close resultset error!", e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //log.error("close statement error!", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //log.error("close connection error!", e);
                }
            }
        }
        return null;
    }

    //创建持久化值，id的初始化值为0
    private Long newPersistenceValue(String sequenceName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(NEW_SQL);
            statement.setLong(1, startValue);
            statement.setString(2, sequenceName);
            statement.executeUpdate();
        } catch (Exception e) {
            //log.error("newPersistenceValue error!", e);
            throw new RuntimeException("newPersistenceValue error!", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                  // log.error("close statement error!", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                   // log.error("close connection error!", e);
                }
            }
        }
        return startValue;
    }


    static  class Step{
        private long currentValue;
        private long endValue;

        public Step(long currentValue, long endValue) {
            this.currentValue = currentValue;
            this.endValue = endValue;
        }

        public void setCurrentValue(long currentValue) {
            this.currentValue = currentValue;
        }

        public void setEndValue(long endValue) {
            this.endValue = endValue;
        }

        /**
         * 递增获取得到当前的数值
         * @return
         */
        public long incrementAndGet(){
            return ++currentValue;
        }
    }
}
