package com.jd.component.paginated;

import java.util.List;

/**
 * 提取出来的的分页的功能接口
 * 接口为了匹配不同的对象，引用泛型
 * 分页的关键因素：1，总的页数，totalPageNumber 2,分页的每页显示的条目数：pageSize 3,总的记录数，这是计算页数的关键
 * 4，当前页：index，5，标记起始页码数的数据，主要对应于数据库中的分页查询中的limit a,b 中的起始，注意起始页从1开始的。
 *
 *
 * @author dushuangcheng
 * @create 2017-02-07 11:56
 */
public interface Paginated<T> extends List<T> {
    /**
     * @description 获取当前页，以及设置当前页
     * @author dushuangcheng
     * @create 2017/2/7
     */
    public int getIndex();
    public void setIndex();
    /**
     * @description 是否是首页/末页
     * @author dushuangcheng
     * @create 2017/2/7
     */
    public boolean isFirst();
    public boolean isLast();
    /**
     * @description 是否是前一页/后一页
     * @author dushuangcheng
     * @create 2017/2/7
     */
    public boolean isNextPage();
    public boolean isPreviousPage();

    /**
     * @description 设置每页中显示的条目数
     * @author dushuangcheng
     * @create 2017/2/7
     */
    public int getPageSize();
    public void setPageSize(int pageSize);

    /**
     * @description 获取总的条目数
     * @author dushuangcheng
     * @create 2017/2/7
     */
    public int getTotalItem();
    public void setTotalItem(int totalItem);



}
