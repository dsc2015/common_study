package query;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public class EsDateQuery extends EsQuery<Date> {
    public EsDateQuery(String field) {
        super(field);
    }

    public EsDateQuery(String field, Date value) {
        super(field, value);
    }

    public EsDateQuery(String field, Date min, Date max) {
        super(field, min, max);
    }

    public EsDateQuery(String field, Date value, Boolean isMust, Boolean mustNot) {
        super(field, value, isMust, mustNot);
    }

    public EsDateQuery(String field, Date value, Boolean isMust, EsQueryTypeEnum queryType) {
        super(field, value, isMust, queryType);
    }

    public String getValue() {
        return this.getFormatString((Date) this.value);
    }

    public String getBeginRange() {
        return this.getFormatString((Date) this.beginRange);
    }

    public String getEndRange() {
        return this.getFormatString((Date) this.endRange);
    }

    private String getFormatString(Date param) {
        return param != null ? (new DateTime(param)).toString("yyyy-MM-dd HH:mm:ss.SSS") : null;
    }
}
