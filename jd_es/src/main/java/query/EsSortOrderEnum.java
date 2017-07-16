package query;

import org.slf4j.Logger;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public enum EsSortOrderEnum {
    DESC,
    ASC;


    private EsSortOrderEnum() {
    }

    public static EsSortOrderEnum getEsSortOrderEnum(String sortType) {
        String var1 = sortType.toUpperCase();
        byte var2 = -1;
        switch (var1.hashCode()) {
            case 65105:
                if (var1.equals("ASC")) {
                    var2 = 0;
                }
                break;
            case 2094737:
                if (var1.equals("DESC")) {
                    var2 = 1;
                }
        }

        switch (var2) {
            case 0:
                return ASC;
            case 1:
                return DESC;
            default:
                //logger.error("getEsSortOrderEnum enum is null,sortType is{}. ", sortType);
                return ASC;
        }
    }
}

