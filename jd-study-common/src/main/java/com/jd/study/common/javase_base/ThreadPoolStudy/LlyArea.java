package com.jd.study.common.javase_base.ThreadPoolStudy;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/9
 */
public class LlyArea {
    private Integer area;
    private Long payValue;
    private Long totalPayValue;

    public LlyArea() {
    }

    public LlyArea(Integer area, Long payValue, Long totalPayValue) {
        this.area = area;
        this.payValue = payValue;
        this.totalPayValue = totalPayValue;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Long getPayValue() {
        return payValue;
    }

    public void setPayValue(Long payValue) {
        this.payValue = payValue;
    }

    public Long getTotalPayValue() {
        return totalPayValue;
    }

    public void setTotalPayValue(Long totalPayValue) {
        this.totalPayValue = totalPayValue;
    }

   /* @Override
    public int compareTo(LlyArea o) {
        if (this.payValue != null&&this.area!=null&&this.totalPayValue!=null) {
            if (this.payValue.compareTo(o.payValue) != 0)
                return this.payValue.compareTo(o.payValue);
            return this.area.compareTo(o.area);
        }
        throw new NullPointerException();
    }*/

    @Override
    public String toString() {
        return "LlyArea{" +
                "area=" + area +
                ", payValue=" + payValue +
                ", totalPayValue=" + totalPayValue +
                '}';
    }
}
