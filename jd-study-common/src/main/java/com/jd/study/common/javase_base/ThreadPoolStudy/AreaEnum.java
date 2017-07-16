package com.jd.study.common.javase_base.ThreadPoolStudy;/**
 * Created by dushuangcheng on 2017/7/9.
 */

/**
 * @author
 * @create 2017-07-09 22:32
 */
public enum AreaEnum {
    AREA_0(0, LlyConstants.AREA0),
    AREA_1(1, LlyConstants.AREA1),
    AREA_2(2, LlyConstants.AREA2),
    AREA_3(3, LlyConstants.AREA3),
    AREA_4(4, LlyConstants.AREA4),
    AREA_5(5, LlyConstants.AREA5),
    AREA_6(6, LlyConstants.AREA6),
    AREA_7(7, LlyConstants.AREA7),
    AREA_8(8, LlyConstants.AREA8),
    AREA_9(9, LlyConstants.AREA9),
    AREA_10(10, LlyConstants.AREA10);
    private Integer area;
    private Integer[] areaCards;

    AreaEnum(Integer area, Integer[] areaCards) {
        this.area = area;
        this.areaCards = areaCards;
    }

    public static Integer[] getValues(Integer area) {
        for (AreaEnum areaEnum : AreaEnum.values()) {
            if (areaEnum.area.equals(area)) {
                return areaEnum.areaCards;
            }
        }
        return null;
    }
}
