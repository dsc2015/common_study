package com.jd.component.paginated;
/** 查询接口
 * @author dushuangcheng
 * @create 2017-06-05 11:02
 */
public interface Query {
    /**
     * @description 获取查询的id
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/5
     */
    int getId();
    /**
     * @description 获取value的值
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/5
     */
    String getValue();
    /**
     * @description 起始页
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/5
     */
    int startRow();
    /**
     * @description 结束页
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/6/5
     */
    int endRow();
}
