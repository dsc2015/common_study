package query;
/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/7
 */
public enum  EsBoolEnum {
    MUST(1, "必须"),
    MUSTNOT(2, "必须不"),
    MUST_MUSTNOT(3, "必须+必须不"),
    SHOULD(4, "应该"),
    MUST_SHOULD(5, "必须+应该"),
    MUSTNOT_SHOULD(6, "必须不+应该");

    private int type;
    private String value;

    private EsBoolEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int Type) {
        this.type = Type;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static boolean isIncludeMust(int isMust) {
        return (isMust & 1) > 0;
    }

    public static boolean isIncludeMustNo(int mustNo) {
        return (mustNo & 2) > 0;
    }

    public static boolean isIncludeShould(int should) {
        return (should & 4) > 0;
    }

}
