package query;

import org.apache.commons.lang.StringUtils;

/**
 * @description 
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class StatisticSumBean {
    private String alias;
    private String field;

    public StatisticSumBean(String alias, String field) {
        if(!StringUtils.isBlank(alias) && !StringUtils.isBlank(field)) {
            this.alias = alias;
            this.field = field;
        } else {
            throw new RuntimeException();
        }
    }

    public String getAlias() {
        return this.alias;
    }

    public String getField() {
        return this.field;
    }
}
