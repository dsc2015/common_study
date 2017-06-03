package com.jd.component.paginated;

import java.util.ArrayList;

/**
 * 这个是分页的主体类，部分功能提取到了接口中，并没有在这个类中添加过多的方法，这样的好处是易于扩展，之前的编程过程中
 * 喜欢把这些功能集合到这个类中，造成代码的臃肿，可扩展性比较差。
 * @author dushuangcheng
 * @create 2017-02-07 12:23
 */
public class PaginatedList<T> extends ArrayList<T> implements Paginated<T> {
    /**
     * @description 默认的分页数
     * @author dushuangcheng
     * @create 2017/2/7
     */
    public static final int DEFAULT_PAGE_SIZE=20;



    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void setIndex() {

    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public boolean isLast() {
        return false;
    }

    @Override
    public boolean isNextPage() {
        return false;
    }

    @Override
    public boolean isPreviousPage() {
        return false;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public void setPageSize(int pageSize) {

    }

    @Override
    public int getTotalItem() {
        return 0;
    }

    @Override
    public void setTotalItem(int totalItem) {

    }
}
