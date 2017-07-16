package query;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public enum EsQueryOperatorEnum {
    AND(1, "AND"),
    OR(2, "OR");

    EsQueryOperatorEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }

    private int type;
    private String value;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
