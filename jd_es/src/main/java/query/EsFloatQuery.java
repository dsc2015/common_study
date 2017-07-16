package query;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public class EsFloatQuery extends EsQuery<Float> {
    public EsFloatQuery(String field) {
        super(field);
    }

    public EsFloatQuery(String field, Float value) {
        super(field, value);
    }

    public EsFloatQuery(String field, Float min, Float max) {
        super(field, min, max);
    }

    public EsFloatQuery(String field, Float value, Boolean isMust, Boolean mustNot) {
        super(field, value, isMust, mustNot);
    }

    public EsFloatQuery(String field, Float value, Boolean isMust, EsQueryTypeEnum queryType) {
        super(field, value, isMust, queryType);
    }
}
