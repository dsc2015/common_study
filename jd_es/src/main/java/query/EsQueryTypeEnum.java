package query;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public enum EsQueryTypeEnum {
    TERM(1, "完全匹配"),

    RANGE(2, "范围查询"),

    QUERY_STRING(3, "全文分词匹配"),

    PREFIX(4, "前缀查询"),

    FUZZY(5, "模糊查询"),

    EXISTS(6, "存在查询");

    private int type;
    private String msg;

    EsQueryTypeEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
