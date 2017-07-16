package shardstudytype1.dao;/**
 * Created by dushuangcheng on 2017/7/6.
 */

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import shardstudytype1.SqlSessionFactoryContextHolder;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * @author dushuangcheng
 * @create 2017-07-06 16:58
 */
public class GenericDaoImpl implements GenericDao {

    private SqlSession sqlSession;
    /**
     * @description 子类实现主要用于路由主库的数据源
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/7/6
     */
    protected Object routerMasterSqlSessionFactoryContextHolder(Object bindParams){
        return bindParams;
    }
    /**
     * 子类覆盖方法，路由从库数据源SqlSessionFactory
     * @param bindParams
     */
    protected Object routerSlaveSqlSessionFactoryContextHolder(Object bindParams){
        //默认为基础库的从库
        SqlSessionFactoryContextHolder.setBaseDBSlaveSessionFactory(false);
        return bindParams;
    }

    public <E> E executeForObjectBySlave(String sqlID, Object bindParams, Class<E> clazz) {

        /*if (log.isDebugEnabled()) {
            log.debug("executeForObjectBySlave Start.");
        }
*/
        routerSlaveSqlSessionFactoryContextHolder(bindParams);

        E rObj = null;
        try {
            // 执行SQL：取得记录
            Object obj = sqlSession.selectOne(sqlID, bindParams);
            /*if (log.isDebugEnabled() && obj != null) {
                log.debug("Return type:" + obj.getClass().getName());
            }*/
            if (clazz != null && obj != null) {
                rObj = (E) clazz.cast(obj);
            }
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }

        /*if (log.isDebugEnabled()) {
            log.debug("executeForObjectBySlave End.");
        }*/

        return rObj;
    }

    public <E> E executeForObject(String sqlID, Object bindParams, Class<E> clazz) {

        /*if (log.isDebugEnabled()) {
            log.debug("executeForObject Start.");
        }*/

        routerMasterSqlSessionFactoryContextHolder(bindParams);

        E rObj = null;
        try {
            // 执行SQL：取得记录
            Object obj = sqlSession.selectOne(sqlID, bindParams);
           /* if (log.isDebugEnabled() && obj != null) {
                log.debug("Return type:" + obj.getClass().getName());
            }*/
            if (clazz != null && obj != null) {
                rObj = (E) clazz.cast(obj);
            }
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }

       /* if (log.isDebugEnabled()) {
            log.debug("executeForObject End.");
        }*/

        return rObj;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mooing.base.meerkat.dao.QueryDAO#executeForMap(java.lang.String,
     * java.lang.Object)
     */
    public Map<String, Object> executeForMap(String sqlID, Object bindParams) {

       /* if (log.isDebugEnabled()) {
            log.debug("executeForMap Start.");
        }*/

        //routerSqlSessionFactoryContextHolder(bindParams);

        @SuppressWarnings("unchecked")
        Map<String, Object> rObj = this.executeForObject(sqlID, bindParams, Map.class);

       /* if (log.isDebugEnabled()) {
            log.debug("executeForMap End.");
        }
*/
        return rObj;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mooing.base.meerkat.dao.QueryDAO#executeForObjectArray(java.lang.String
     * , java.lang.Object, java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<E> clazz) {

        /*if (log.isDebugEnabled()) {
            log.debug("executeForObjectArray Start.");
        }*/

        routerMasterSqlSessionFactoryContextHolder(bindParams);

        if (clazz == null) {
            throw new ClassCastException();
        }

        E[] retArray;
        try {
            // 执行SQL：取得记录
            List<E> list = sqlSession.selectList(sqlID, bindParams);
            retArray = (E[]) Array.newInstance(clazz, list.size());
            list.toArray(retArray);
        } catch (ArrayStoreException e) {
            throw new ArrayStoreException();
        }

        /*if (log.isDebugEnabled()) {
            log.debug("executeForObjectArray End.");
        }*/

        return retArray;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mooing.base.meerkat.dao.QueryDAO#executeForMapArray(java.lang.String,
     * java.lang.Object)
     */
    public Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams) {

        /*if (log.isDebugEnabled()) {
            log.debug("executeForMapArray Start.");
        }*/

        //routerSqlSessionFactoryContextHolder(bindParams);

        @SuppressWarnings("unchecked")
        Map<String, Object>[] map = executeForObjectArray(sqlID, bindParams, Map.class);

        /*if (log.isDebugEnabled()) {
            log.debug("executeForMapArray End.");
        }*/

        return map;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mooing.base.meerkat.dao.QueryDAO#executeForObjectArray(java.lang.String
     * , java.lang.Object, java.lang.Class, int, int)
     */
    @SuppressWarnings("unchecked")
    public <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<E> clazz, int beginIndex, int maxCount) {

        /*if (log.isDebugEnabled()) {
            log.debug("executeForObjectArray Start.");
        }*/

        if (clazz == null) {
            throw new ClassCastException();
        }

        routerMasterSqlSessionFactoryContextHolder(bindParams);

        E[] retArray;
        try {
            // 执行SQL：取得记录
            List<E> list = sqlSession.selectList(sqlID, bindParams, new RowBounds(beginIndex, maxCount));

            retArray = (E[]) Array.newInstance(clazz, list.size());
            list.toArray(retArray);
        } catch (ArrayStoreException e) {
            throw new ClassCastException();
        }

       /* if (log.isDebugEnabled()) {
            log.debug("executeForObjectArray End.");
        }*/

        return retArray;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mooing.base.meerkat.dao.QueryDAO#executeForMapArray(java.lang.String,
     * java.lang.Object, int, int)
     */
    public Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams, int beginIndex, int maxCount) {

        /*if (log.isDebugEnabled()) {
            log.debug("executeForMapArray Start.");
        }*/

        //routerSqlSessionFactoryContextHolder(bindParams);

        @SuppressWarnings("unchecked")
        Map<String, Object>[] map = executeForObjectArray(sqlID, bindParams, Map.class, beginIndex, maxCount);

        /*if (log.isDebugEnabled()) {
            log.debug("executeForMapArray End.");
        }*/

        return map;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mooing.base.meerkat.dao.QueryDAO#executeForObjectList(java.lang.String
     * , java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public <E> List<E> executeForObjectList(String sqlID, Object bindParams) {

       /* if (log.isDebugEnabled()) {
            log.debug("executeForObjectList Start.");
        }*/

        routerMasterSqlSessionFactoryContextHolder(bindParams);

        // 执行SQL：取得记录
        List<E> list = sqlSession.selectList(sqlID, bindParams);

       /* if (log.isDebugEnabled()) {
            log.debug("executeForObjectList End.");
        }*/

        return list;
    }

    public <E> List<E> executeForObjectListBySlave(String sqlID, Object bindParams) {

       /* if (log.isDebugEnabled()) {
            log.debug("executeForObjectList Start.");
        }*/

        routerSlaveSqlSessionFactoryContextHolder(bindParams);

        // 执行SQL：取得记录
        List<E> list = sqlSession.selectList(sqlID, bindParams);

       /* if (log.isDebugEnabled()) {
            log.debug("executeForObjectList End.");
        }*/

        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mooing.base.meerkat.dao.QueryDAO#executeForMapList(java.lang.String,
     * java.lang.Object)
     */
    public List<Map<String, Object>> executeForMapList(String sqlID, Object bindParams) {

        /*if (log.isDebugEnabled()) {
            log.debug("executeForMapList Start.");
        }*/

        //routerSqlSessionFactoryContextHolder(bindParams);

        List<Map<String, Object>> mapList = executeForObjectList(sqlID, bindParams);

       /* if (log.isDebugEnabled()) {
            log.debug("executeForMapList End.");
        }*/

        return mapList;
    }



    public int execute(String sqlID, Object bindParams) {

        /*if (log.isDebugEnabled()) {
            log.debug("execute Start.");
        }*/

        bindParams = routerMasterSqlSessionFactoryContextHolder(bindParams);

        // 执行SQL: 更新数据
        int row = sqlSession.update(sqlID, bindParams);

       /* if (log.isDebugEnabled()) {
            log.debug("execute End. success count:" + row);
        }*/

        return row;
    }

    public <E> List<E> executeForMoveDataObjectListNoRoute(String sqlID, Object bindParams) {

        /*if (log.isDebugEnabled()) {
            log.debug("executeForObjectList Start.");
        }*/

        // 执行SQL：取得记录
        List<E> list = sqlSession.selectList(sqlID, bindParams);

      /*  if (log.isDebugEnabled()) {
            log.debug("executeForObjectList End.");
        }*/

        return list;
    }


}
