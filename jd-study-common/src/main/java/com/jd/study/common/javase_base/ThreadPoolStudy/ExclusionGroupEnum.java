package com.jd.study.common.javase_base.ThreadPoolStudy;/**
 * Created by dushuangcheng on 2017/7/10.
 */

import java.util.Arrays;

/**
 * @author
 * @create 2017-07-10 12:04
 */
public enum ExclusionGroupEnum {
    EXCLUSION_GROUP0(0, new Integer[]{0, 6}),
    EXCLUSION_GROUP1(1, new Integer[]{2, 6}),
    EXCLUSION_GROUP2(2, new Integer[]{1, 5}),
    EXCLUSION_GROUP3(3, new Integer[]{3, 5});
    private Integer key;
    private Integer[] areas;

    ExclusionGroupEnum(Integer key, Integer[] areas) {
        this.key = key;
        this.areas = areas;
    }

    public static boolean isEqual(Integer[] areas) {
        if (areas == null || areas.length < 2) {
            return false;
        }
        for (ExclusionGroupEnum exclusionGroupEnum : ExclusionGroupEnum.values()) {
            Integer[] areas1 = exclusionGroupEnum.areas;
            if (Arrays.asList(areas).containsAll(Arrays.asList(areas1))) {
                return true;
            }
        }
        return false;
    }
}
