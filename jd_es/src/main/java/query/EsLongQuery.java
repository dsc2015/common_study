package query;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class EsLongQuery extends EsQuery<Long> {
    public EsLongQuery(String field) {
        super(field);
    }

    public EsLongQuery(String field, Long value) {
        super(field, value);
    }

    public EsLongQuery(String field, Long min, Long max) {
        super(field, min, max);
    }

    public EsLongQuery(String field, Long value, Boolean isMust, Boolean mustNot) {
        super(field, value, isMust, mustNot);
    }

    public EsLongQuery(String field, Long value, Boolean isMust, EsQueryTypeEnum queryType) {
        super(field, value, isMust, queryType);
    }
}
