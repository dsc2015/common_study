package query;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @description 
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class EsSortFieldBean implements Serializable {
    private static final long serialVersionUID = 6824696599306734253L;
    private String field;
    private EsSortOrderEnum order;

    public EsSortFieldBean(String field, EsSortOrderEnum order) {
        if(!StringUtils.isBlank(field) && order != null) {
            this.field = field;
            this.order = order;
        } else {
            throw new RuntimeException();
        }
    }

    public EsSortOrderEnum getOrder() {
        return this.order;
    }

    public String getField() {
        return this.field;
    }
}
