package query;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class EsStringQuery extends EsQuery<String>{
    public EsStringQuery(String field) {
        super(field);
    }

    public EsStringQuery(String field, String value) {
        super(field, value);
    }

    public EsStringQuery(String field, String min, String max) {
        super(field, min, max);
    }

    public EsStringQuery(String field, String value, Boolean isMust, Boolean mustNot) {
        super(field, value, isMust, mustNot);
    }

    public EsStringQuery(String field, String value, Boolean isMust, EsQueryTypeEnum queryType) {
        super(field, value, isMust, queryType);
    }

    public String getValue() {
        return (String)this.value;
    }

    public String getBeginRange() {
        return (String)this.beginRange;
    }

    public String getEndRange() {
        return (String)this.endRange;
    }
}
