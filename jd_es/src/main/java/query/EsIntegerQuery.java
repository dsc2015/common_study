package query;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class EsIntegerQuery extends EsQuery<Integer> {
    public EsIntegerQuery(String field) {
        super(field);
    }

    public EsIntegerQuery(String field, Integer value) {
        super(field, value);
    }

    public EsIntegerQuery(String field, Integer min, Integer max) {
        super(field, min, max);
    }

    public EsIntegerQuery(String field, Integer value, Boolean isMust, Boolean mustNot) {
        super(field, value, isMust, mustNot);
    }

    public EsIntegerQuery(String field, Integer value, Boolean isMust, EsQueryTypeEnum queryType) {
        super(field, value, isMust, queryType);
    }
}
