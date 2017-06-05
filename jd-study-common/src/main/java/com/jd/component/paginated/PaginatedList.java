package com.jd.component.paginated;

import java.util.ArrayList;

/**
 * 这个是分页的主体类，部分功能提取到了接口中，并没有在这个类中添加过多的方法，这样的好处是易于扩展，之前的编程过程中
 * 喜欢把这些功能集合到这个类中，造成代码的臃肿，可扩展性比较差。
 *
 * @author dushuangcheng
 * @create 2017-02-07 12:23
 */
public class PaginatedList<T> extends ArrayList<T> implements Paginated<T> {
    /**
     * @description 默认的分页数
     * @author dushuangcheng
     * @create 2017/2/7
     */
    public static final int DEFAULT_PAGE_SIZE = 20;
    /**
     * @description:每页的大小
     */
    private int pageSize;
    /**
     * @description:当前页
     */
    private int index;
    /**
     * @description:总的记录数
     */
    private int totalItem;
    /**
     * @description:总的页数
     */
    private int totalPage;
    /**
     * @description:分页后记录开始的地方
     */
    private int startRow;
    /**
     * @description:分页的记录的结束的地方
     */
    private int endRow;


    /**
     * @param
     * @return
     * @description 默认的构造方法，在方法中进行重新分页的处理
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/5
     */
    public PaginatedList() {
        rePaged();
    }

    public PaginatedList(int pageSize, int index) {
        this.pageSize = pageSize;
        this.index = index;
        // 重新进行分页前的矫正
        rePaged();
    }

    /**
     * 重新分页，用来矫正分页过程中出现的偏差，以及初始化一些默认的数值
     */
    private void rePaged() {
        //如果设置的pageSize比1小的话，这个时候设置为默认的值
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (index < 1) {
            //恢复到第一页中
            index = 1;
        }
        //对其中的参数进行计算
        if (totalItem > 0) {
            //1,计算total page
            totalPage = totalItem / pageSize + (totalItem % pageSize == 0 ? 1 : 0);
            //再次矫正当前页，如果输入的当前页比总的页数还大，则还原为计算出来的总的页数的值
            if (index > totalPage) {
                index = totalPage;
            }
            //计算每一页的终止值
            endRow = pageSize * index;
            //计算起始值
            startRow = endRow - pageSize + 1;
            //主要用于最后一页的情况，最后一页不是很满的情况下进行的修正
            if (endRow > totalItem) {
                endRow = totalItem;
            }
        }
    }

    public static int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    public void setIndex(int index) {
        this.index = index;
        rePaged();
    }

    @Override
    public int getIndex() {
        return index;
    }


    @Override
    public boolean isFirst() {
        return index <= 1;
    }

    @Override
    public boolean isLast() {
        return index >= totalPage;
    }

    @Override
    public boolean isNextPage() {
        return !isLast();
    }

    @Override
    public boolean isPreviousPage() {
        return !isFirst();
    }

    @Override
    public int getNextPage() {
        if (isLast()) {
            return totalPage;
        }
        return index + 1;
    }

    @Override
    public int getPreviousPage() {
        if (isFirst()) {
            return 1;
        }
        return index - 1;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        rePaged();
    }

    @Override
    public int getTotalItem() {
        return totalItem;
    }

    @Override
    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
        rePaged();
    }

    @Override
    public int getTotalPage() {
        return totalPage;
    }

    @Override
    public int getStartRow() {
        return startRow;
    }

    @Override
    public int getEndRow() {
        return endRow;
    }
}
