package query;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class StatisticDateBean implements Serializable{
    private static final long serialVersionUID = -3454965473912045058L;
    private String field;
    private EsDateTypeEnum esDateTypeEnum;

    public StatisticDateBean(String field, EsDateTypeEnum esDateTypeEnum) {
        if(!StringUtils.isBlank(field) && esDateTypeEnum != null) {
            this.field = field;
            this.esDateTypeEnum = esDateTypeEnum;
        } else {
            throw new RuntimeException();
        }
    }

    public EsDateTypeEnum getEsDateTypeEnum() {
        return this.esDateTypeEnum;
    }

    public String getField() {
        return this.field;
    }

    private enum EsDateTypeEnum{
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        WEEK,
        MONTH,
        QUARTER,
        YEAR;

        private EsDateTypeEnum() {
        }
    }
}
