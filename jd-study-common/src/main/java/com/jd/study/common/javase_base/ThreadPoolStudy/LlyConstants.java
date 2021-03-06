package com.jd.study.common.javase_base.ThreadPoolStudy;

/**
 * Created by zhanglihua on 2016/9/28.
 */
public class LlyConstants {
    /**
     * 扑克数值
     */
    public static final int[] CARDDATA = new int[]{
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D,    //方块 A - K
            0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D,    //梅花 A - K
            0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D,    //红桃 A - K
            0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D    //黑桃 A - K
            //0x41,0x42
    };
    /**
     * 扑克数值去掉7的
     */
    public static final Integer[] CARDDATA1 = new Integer[]{
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D,    //方块 A - K
            0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D,    //梅花 A - K
            0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D,    //红桃 A - K
            0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D    //黑桃 A - K
            //0x41,0x42
    };
    public static final String GROUP0 = "group_0";
    public static final String GROUP1 = "group_1";
    public static final String GROUP2 = "group_2";
    public static final String GROUP3 = "group_3";
    public static final String GROUP4 = "group_4";
    public static final Integer[] AREA0 = new Integer[]{
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
            0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D};
    public static final Integer[] AREA1 = new Integer[]{
            0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
            0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D};
    public static final Integer[] AREA2 = new Integer[]{
            0x21, 0x22, 0x23, 0x24, 0x25, 0x26,
            0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D};
    public static final Integer[] AREA3 = new Integer[]{
            0x31, 0x32, 0x33, 0x34, 0x35, 0x36,
            0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D};
    public static final Integer[] AREA4 = new Integer[]{
            0x07, 0x17, 0x27, 0x37};
    public static final Integer[] AREA5 = new Integer[]{
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
            0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D,
            0x21, 0x22, 0x23, 0x24, 0x25, 0x26,
            0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D};
    public static final Integer[] AREA6 = new Integer[]{
            0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
            0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D,
            0x31, 0x32, 0x33, 0x34, 0x35, 0x36,
            0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D};
    public static final Integer[] AREA7 = new Integer[]{
            0x01, 0x03, 0x05, 0x09, 0x0B, 0x0D,
            0x11, 0x13, 0x15, 0x19, 0x1B, 0x1D,
            0x21, 0x23, 0x25, 0x29, 0x2B, 0x2D,
            0x31, 0x33, 0x35, 0x39, 0x3B, 0x3D};
    public static final Integer[] AREA8 = new Integer[]{
            0x02, 0x04, 0x06, 0x08, 0x0A, 0x0C,
            0x12, 0x14, 0x16, 0x18, 0x1A, 0x1C,
            0x22, 0x24, 0x26, 0x28, 0x2A, 0x2C,
            0x32, 0x34, 0x36, 0x38, 0x3A, 0x3C};
    public static final Integer[] AREA9 = new Integer[]{
            0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D,
            0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D,
            0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D,
            0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D};
    public static final Integer[] AREA10 = new Integer[]{
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
            0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
            0x21, 0x22, 0x23, 0x24, 0x25, 0x26,
            0x31, 0x32, 0x33, 0x34, 0x35, 0x36};

    /**
     * 花色掩码
     */
    public static final int LOGIC_MASK_COLOR = 0xF0;

    /**
     * 数值掩码
     */
    public static final int LOGIC_MASK_VALUE = 0x0F;

    public static final int LOGIC_DIAMOND = 0x00;//方块
    public static final int LOGIC_CLUB = 0x10;//梅花
    public static final int LOGIC_HEART = 0x20;//红桃
    public static final int LOGIC_SPADE = 0x30;//黑桃
    //    public static final int LOGIC_JOKER             = 0x40;
    public static final int LOGIC_SEVEN = 0x07;
    public static final int LOGIC_RED = 0x50;
    public static final int LOGIC_BLACK = 0x60;
    public static final int LOGIC_SINGLE = 0x70;
    public static final int LOGIC_DOUBLE = 0x80;
    public static final int LOGIC_BIG = 0x90;
    public static final int LOGIC_SMALL = 0xA0;

    public static final int LOGIC_NULL = 0xF0;

    //    下注区域标识
    public static final int AREA_DIAMOND = 0;
    public static final int AREA_CLUB = 1;
    public static final int AREA_HEART = 2;
    public static final int AREA_SPADE = 3;
    //    public static final int AREA_JOKER             = 4;
    public static final int AREA_SEVEN = 4;
    public static final int AREA_RED = 5;
    public static final int AREA_BLACK = 6;
    public static final int AREA_SINGLE = 7;
    public static final int AREA_DOUBLE = 8;
    public static final int AREA_BIG = 9;
    public static final int AREA_SMALL = 10;

    //   赔付区域赔率
    public static final int[] PAY_RATE = new int[]{4, 4, 4, 4, 11, 2, 2, 2, 2, 2, 2};

    //计算每个区域上可中奖的牌值
    //区域:0：1-6 8-13;
    //区域1：17-22 24-29
    //区域2：33-38 40-45
    //区域3：49-54 56-61
    //区域4：7 23 39 55
    //区域5：0+2 红
    //区域6：1+3 黑
    //区域7: 单列
    //区域8: 双列
    //区域9：大六列
    //区域10：小六列

}
