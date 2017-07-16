package query;

import java.io.Serializable;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public class EsQuery<T> implements Serializable {
    private static final long serialVersionUID = 5785487342324988757L;
    private String field = "";
    protected T value;
    protected T beginRange;
    protected T endRange;
    protected Boolean isBeginEqual;
    protected Boolean isEngEqual;
    private Boolean isMust = Boolean.valueOf(true);
    private Boolean mustNot = Boolean.valueOf(false);
    private Boolean should = Boolean.valueOf(false);
    private EsQueryTypeEnum searchType;
    private EsQueryOperatorEnum operator;
    private float boost;
    private int phraseSlop;

    public EsQuery(String field) {
        this.searchType = EsQueryTypeEnum.TERM;
        this.operator = EsQueryOperatorEnum.AND;
        this.boost = -1.0F;
        this.phraseSlop = -1;
        this.field = field;
    }

    public EsQuery(String field, T value) {
        this.searchType = EsQueryTypeEnum.TERM;
        this.operator = EsQueryOperatorEnum.AND;
        this.boost = -1.0F;
        this.phraseSlop = -1;
        this.field = field;
        this.value = value;
    }

    public EsQuery(String field, T min, T max) {
        this.searchType = EsQueryTypeEnum.TERM;
        this.operator = EsQueryOperatorEnum.AND;
        this.boost = -1.0F;
        this.phraseSlop = -1;
        this.field = field;
        this.beginRange = min;
        this.endRange = max;
        this.searchType = EsQueryTypeEnum.RANGE;
        this.isBeginEqual = Boolean.valueOf(true);
        this.isEngEqual = Boolean.valueOf(true);
    }

    public EsQuery(String field, T value, Boolean isMust, Boolean mustNot) {
        this.searchType = EsQueryTypeEnum.TERM;
        this.operator = EsQueryOperatorEnum.AND;
        this.boost = -1.0F;
        this.phraseSlop = -1;
        this.field = field;
        this.value = value;
        this.isMust = isMust;
        this.mustNot = mustNot;
    }

    public EsQuery(String field, T value, Boolean isMust, EsQueryTypeEnum queryType) {
        this.searchType = EsQueryTypeEnum.TERM;
        this.operator = EsQueryOperatorEnum.AND;
        this.boost = -1.0F;
        this.phraseSlop = -1;
        this.field = field;
        this.value = value;
        this.isMust = isMust;
        this.searchType = queryType;
    }

    public Boolean getMustNot() {
        return this.mustNot;
    }

    public EsQuery setMustNot(Boolean mustNot) {
        this.mustNot = mustNot;
        if (mustNot.booleanValue()) {
            this.isMust = Boolean.valueOf(false);
            this.should = Boolean.valueOf(false);
        }

        return this;
    }

    public Boolean getShould() {
        return this.should;
    }

    public void setShould(Boolean should) {
        this.should = should;
        if (should.booleanValue()) {
            this.isMust = Boolean.valueOf(false);
            this.mustNot = Boolean.valueOf(false);
        }

    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return this.getFormatString(this.value);
    }

    public EsQuery setValue(T value) {
        this.value = value;
        return this;
    }

    public String getBeginRange() {
        return this.getFormatString(this.beginRange);
    }

    public EsQuery setBeginRange(T beginRange) {
        this.setBeginRange(beginRange, Boolean.valueOf(true));
        return this;
    }

    public EsQuery setBeginRange(T beginRange, Boolean isEqual) {
        this.beginRange = beginRange;
        this.searchType = EsQueryTypeEnum.RANGE;
        this.isBeginEqual = isEqual;
        return this;
    }

    public String getEndRange() {
        return this.getFormatString(this.endRange);
    }

    public EsQuery setEndRange(T endRange) {
        this.setEndRange(endRange, Boolean.valueOf(true));
        return this;
    }

    public EsQuery setEndRange(T endRange, Boolean isEqual) {
        this.endRange = endRange;
        this.searchType = EsQueryTypeEnum.RANGE;
        this.isEngEqual = isEqual;
        return this;
    }

    public Boolean isBeginEqual() {
        return this.isBeginEqual;
    }

    public Boolean isEngEqual() {
        return this.isEngEqual;
    }

    public Boolean getIsMust() {
        return this.isMust;
    }

    public EsQuery setIsMust(Boolean isMust) {
        this.isMust = isMust;
        if (isMust.booleanValue()) {
            this.mustNot = Boolean.valueOf(false);
            this.should = Boolean.valueOf(false);
        }

        return this;
    }

    public EsQueryTypeEnum getSearchType() {
        return this.searchType;
    }

    public EsQuery setSearchType(EsQueryTypeEnum searchType) {
        this.searchType = searchType;
        return this;
    }

    public EsQueryOperatorEnum getOperator() {
        return this.operator;
    }

    public EsQuery setOperator(EsQueryOperatorEnum operator) {
        this.operator = operator;
        return this;
    }

    public float getBoost() {
        return this.boost;
    }

    public EsQuery setBoost(float boost) {
        this.boost = boost;
        return this;
    }

    public int getPhraseSlop() {
        return this.phraseSlop;
    }

    public EsQuery setPhraseSlop(int phraseSlop) {
        this.phraseSlop = phraseSlop;
        return this;
    }

    private String getFormatString(T param) {
        return param != null ? String.valueOf(param) : null;
    }
}
