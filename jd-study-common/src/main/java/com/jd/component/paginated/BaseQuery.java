package com.jd.component.paginated;

/**
 * 基础的查询类
 *
 * @author dushuangcheng
 * @create 2017-06-05 11:04
 */
public class BaseQuery implements Query {
    private int id;
    private String value;
    private int startRow;
    private int endRow;

    public BaseQuery() {
    }

    /**
     * 通过构造方法来获取这个值
     * @param paginated
     */
    public BaseQuery(Paginated paginated) {
        this.startRow = paginated.getStartRow();
        this.endRow = paginated.getEndRow();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public int startRow() {
        return 0;
    }

    @Override
    public int endRow() {
        return 0;
    }
}
