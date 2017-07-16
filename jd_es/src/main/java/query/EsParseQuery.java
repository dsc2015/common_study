package query;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public class EsParseQuery {
    public EsParseQuery() {
    }


   /* public static QueryBuilder parseQuery(List<EsQuery> queryList, Map<String, String> mustFields, Map<String, List<Object>> inMustFields){
        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        return queryBuilder

    }*/

    private static QueryBuilder getQueryBuilder(List<EsQuery> queryList,QueryBuilder q,Map<String,String> mustFields,Map<String,List<Object>> inMustFields){
        int haveMust=0;
        int haveShould=0;
        int haveMustNo=0;
        BoolQueryBuilder mustBq = QueryBuilders.boolQuery().disableCoord(true);
        BoolQueryBuilder shouldBq = QueryBuilders.boolQuery().disableCoord(true);
        BoolQueryBuilder mustNoBq = QueryBuilders.boolQuery().disableCoord(true);
        Iterator<EsQuery> iterator = queryList.iterator();
        while (iterator.hasNext()){
            EsQuery next = iterator.next();
            if(next.getIsMust().booleanValue()) {
                haveMust = EsBoolEnum.MUST.getType();
                mustBq.must(parseQuery(next));
            } else if(next.getMustNot().booleanValue()) {
                haveMustNo = EsBoolEnum.MUSTNOT.getType();
                mustNoBq.mustNot(parseQuery(next));
            } else {
                haveShould = EsBoolEnum.SHOULD.getType();
                shouldBq.should(parseQuery(next));
            }
        }
        Map.Entry entry1;
        if(MapUtils.isNotEmpty(inMustFields)) {
            Iterator<Map.Entry<String, List<Object>>> iterator1 = inMustFields.entrySet().iterator();

            while(iterator1.hasNext()) {
                entry1 = (Map.Entry)iterator1.next();
                mustBq.must(QueryBuilders.termsQuery((String)entry1.getKey(), (Collection)entry1.getValue()));
            }
        }

        if(MapUtils.isNotEmpty(mustFields)) {
            Iterator<Map.Entry<String, String>> iterator1 = mustFields.entrySet().iterator();

            while(iterator1.hasNext()) {
                entry1 = (Map.Entry)iterator1.next();
                mustBq.must(QueryBuilders.termQuery((String)entry1.getKey(), (String)entry1.getValue()));
            }
        }

        int allValue1 = haveMust + haveMustNo + haveShould;
        if(allValue1 == 0) {
            q = mustBq;
        } else {
            if(EsBoolEnum.isIncludeMust(allValue1)) {
                q = mustBq;
            }

            if(EsBoolEnum.isIncludeMustNo(allValue1)) {
                q = mustBq.must(mustNoBq);
            }

            if(EsBoolEnum.isIncludeShould(allValue1)) {
                q = mustBq.must(shouldBq);
            }
        }
        return (QueryBuilder)q;
    }

    private static QueryBuilder parseQuery(EsQuery sq) {
        if(EsQueryTypeEnum.TERM.getType() == sq.getSearchType().getType()) {
            return QueryBuilders.termQuery(sq.getField(), sq.getValue()).boost(sq.getBoost());
        } else if(EsQueryTypeEnum.FUZZY.getType() == sq.getSearchType().getType()) {
            return QueryBuilders.fuzzyQuery(sq.getField(), sq.getValue()).boost(sq.getBoost());
        } else if(EsQueryTypeEnum.QUERY_STRING.getType() == sq.getSearchType().getType()) {
            return EsQueryOperatorEnum.OR.getType() == sq.getOperator().getType()?QueryBuilders.queryStringQuery(sq.getValue()).defaultField(sq.getField()).boost(sq.getBoost()).phraseSlop(sq.getPhraseSlop()).defaultOperator(QueryStringQueryBuilder.Operator.OR):QueryBuilders.queryStringQuery(sq.getValue()).defaultField(sq.getField()).boost(sq.getBoost()).phraseSlop(sq.getPhraseSlop()).defaultOperator(QueryStringQueryBuilder.Operator.AND);
        } else if(EsQueryTypeEnum.RANGE.getType() == sq.getSearchType().getType()) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(sq.getField());
            if(StringUtils.isNotBlank(sq.getBeginRange())) {
                rangeQuery.gte(sq.getBeginRange());
                if(!sq.isBeginEqual().booleanValue()) {
                    rangeQuery.gt(sq.getBeginRange());
                }
            }

            if(StringUtils.isNotBlank(sq.getEndRange())) {
                rangeQuery.lte(sq.getEndRange());
                if(!sq.isEngEqual().booleanValue()) {
                    rangeQuery.lt(sq.getEndRange());
                }
            }

            return rangeQuery;
        } else {
            return EsQueryTypeEnum.EXISTS.getType() == sq.getSearchType().getType()?QueryBuilders.existsQuery(sq.getField()):null;
        }
    }

}
