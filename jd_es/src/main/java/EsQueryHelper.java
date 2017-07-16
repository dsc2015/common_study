import org.apache.commons.collections.CollectionUtils;
import query.EsQuery;
import query.EsSortFieldBean;
import query.StatisticDateBean;
import query.StatisticSumBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description 
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class EsQueryHelper implements Serializable {
    private static final long serialVersionUID = 7972440391064331946L;
    private List<EsQuery> paramList = null;
    private List<EsSortFieldBean> sortFields;
    private List<String> groupByFields;
    private StatisticDateBean statisticDateBean;
    private List<StatisticSumBean> statisticSumFields;
    private Map<String, List<Object>> inMustFields;
    private Map<String, String> mustFields;
    public EsQueryHelper() {
    }

    public EsQueryHelper(List<EsQuery> paramList) {
        this.paramList = paramList;
    }

    public EsQueryHelper(List<EsQuery> paramList, List<EsSortFieldBean> sortFields) {
        this.paramList = paramList;
        this.sortFields = sortFields;
    }

    public EsQueryHelper(List<EsQuery> paramList, List<EsSortFieldBean> sortFields, Map<String, List<Object>> inMustFields) {
        this.paramList = paramList;
        this.sortFields = sortFields;
        this.inMustFields = inMustFields;
    }

    public List<EsQuery> getParamList() {
        return paramList;
    }

    public boolean hasSort() {
        return CollectionUtils.isNotEmpty(this.sortFields);
    }

    public void setParamList(List<EsQuery> paramList) {
        this.paramList = paramList;
    }

    public List<EsSortFieldBean> getSortFields() {
        return sortFields;
    }

    public void setSortFields(List<EsSortFieldBean> sortFields) {
        this.sortFields = sortFields;
    }

    public List<String> getGroupByFields() {
        return groupByFields;
    }

    public void setGroupByFields(List<String> groupByFields) {
        this.groupByFields = groupByFields;
    }

    public StatisticDateBean getStatisticDateBean() {
        return statisticDateBean;
    }

    public void setStatisticDateBean(StatisticDateBean statisticDateBean) {
        this.statisticDateBean = statisticDateBean;
    }

    public List<StatisticSumBean> getStatisticSumFields() {
        return statisticSumFields;
    }

    public void setStatisticSumFields(List<StatisticSumBean> statisticSumFields) {
        this.statisticSumFields = statisticSumFields;
    }

    public Map<String, List<Object>> getInMustFields() {
        return inMustFields;
    }

    public void setInMustFields(Map<String, List<Object>> inMustFields) {
        this.inMustFields = inMustFields;
    }

    public Map<String, String> getMustFields() {
        return mustFields;
    }

    public void setMustFields(Map<String, String> mustFields) {
        this.mustFields = mustFields;
    }
}
