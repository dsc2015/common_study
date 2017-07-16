package com.jd.study.common.javase_base.ThreadPoolStudy;

import com.google.common.collect.Maps;
import com.jd.rd.game.api.app.param.LlyStartParam;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/9
 */
public class GameLogic {
    /**
     * 扑克数值
     */
    public static final Integer[] CARDDATA = new Integer[]{
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D,    //方块 A - K
            0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1A, 0x1B, 0x1C, 0x1D,    //梅花 A - K
            0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2A, 0x2B, 0x2C, 0x2D,    //红桃 A - K
            0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D    //黑桃 A - K
            //0x41,0x42
    };

    /**
     * @param
     * @return
     * @description
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/9
     */
    public void execute(LlyStartParam llyStartParam, long[] areaBet) {
        llyStartParam.setAreaBet(areaBet);
        LlyArea[] areaLlyBet = getAreaBet(areaBet);
        Map<String, LlyArea[]> groupMap = splitArea(areaLlyBet);
        int winResult = getWinResult(groupMap, llyStartParam, areaBet);
        long[] longs1 = payOperation(winResult, areaBet);

        //多余的部分
        long sum = 0;
        for (int i = 0; i < longs1.length; i++) {
            sum += longs1[i];
        }
        long totalBet = getTotalBet(areaBet);
        System.out.println("sum= " + sum + "----------------------" + "totalBet= " + totalBet);
        if (sum > totalBet) {
            System.out.println(areaBet[0]+"_"+areaBet[1]+"_"+areaBet[2]+"_"+"_"+areaBet[3]+"_"+areaBet[4]+"_"
                    +areaBet[5]+"_"+areaBet[6]+"_"+areaBet[7]+"_"+areaBet[8]+"_"+areaBet[9]+"_"+areaBet[10]
                    +"注意有错误=================================================================================" + winResult+"******"+totalBet);
        }
    }

    private int getWinResult(Map<String, LlyArea[]> groupMap, LlyStartParam llyStartParam, long[] areaBet) {
        List<LlyArea[]> canWinArea = getCanWinArea(groupMap, llyStartParam);
        int cardResult = 0;
        //有7的情况
        //如果投的有7的话，特殊处理
        LlyArea[] llyAreas0 = groupMap.get(LlyConstants.GROUP0);
        if (llyAreas0 == null) {
            cardResult = getCardResult(canWinArea, false);
        } else {
            //计算7处的赔付总和
            Long totalPayValue7 = llyAreas0[0].getTotalPayValue();
            long totalBet = getTotalBet(areaBet);
            //不能出7，剔除7，剩余的区域组合走按个流程
            cardResult = totalPayValue7 > totalBet ? getCardResult(canWinArea, true) : getCardResult(canWinArea, false);
        }
        return cardResult;
    }

    /**
     * @param
     * @return
     * @description 获取一个投注额排序的数组，
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/9
     */
    private LlyArea[] getAreaBet(long[] areaBet) {
        List<LlyArea> llyAreas = new ArrayList<>();
        for (int i = 0; i < areaBet.length; i++) {
            if (areaBet[i] <= 0)
                continue;
            llyAreas.add(new LlyArea(i, areaBet[i], areaBet[i] * LlyConstants.PAY_RATE[i]));
        }
        return llyAreas.toArray(new LlyArea[]{});
    }

    /**
     * @param
     * @return
     * @description 划分区域组
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/9
     */
    private Map<String, LlyArea[]> splitArea(LlyArea[] llyAreas) {
        List<LlyArea> llyAreas0 = new ArrayList<>();
        List<LlyArea> llyAreas1 = new ArrayList<>();
        List<LlyArea> llyAreas2 = new ArrayList<>();
        List<LlyArea> llyAreas3 = new ArrayList<>();
        List<LlyArea> llyAreas4 = new ArrayList<>();
        for (int i = 0; i < llyAreas.length; i++) {
            if (llyAreas[i].getArea() == 4) {
                llyAreas0.add(llyAreas[i]);
            }
            if (llyAreas[i].getArea() >= 0 && llyAreas[i].getArea() <= 3) {
                llyAreas1.add(llyAreas[i]);
            }
            if (llyAreas[i].getArea() >= 5 && llyAreas[i].getArea() <= 6) {
                llyAreas2.add(llyAreas[i]);
            }
            if (llyAreas[i].getArea() >= 7 && llyAreas[i].getArea() <= 8) {
                llyAreas3.add(llyAreas[i]);
            }
            if (llyAreas[i].getArea() >= 9 && llyAreas[i].getArea() <= 10) {
                llyAreas4.add(llyAreas[i]);
            }
        }
        HashMap<String, LlyArea[]> map = Maps.newHashMap();
        if (llyAreas0.size() > 0)
            map.put(LlyConstants.GROUP0, llyAreas0.toArray(new LlyArea[]{}));
        if (llyAreas1.size() > 0)
            map.put(LlyConstants.GROUP1, llyAreas1.toArray(new LlyArea[]{}));
        if (llyAreas2.size() > 0)
            map.put(LlyConstants.GROUP2, llyAreas2.toArray(new LlyArea[]{}));
        if (llyAreas3.size() > 0)
            map.put(LlyConstants.GROUP3, llyAreas3.toArray(new LlyArea[]{}));
        if (llyAreas4.size() > 0)
            map.put(LlyConstants.GROUP4, llyAreas4.toArray(new LlyArea[]{}));
        return map;
    }

    /**
     * @param
     * @return
     * @description
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/9
     */
    //todo:在调用这个方法前确保只投7的情况以及被过滤了，调用该方法的都是不只是包含7的情况
    private List<LlyArea[]> getCanWinArea(Map<String, LlyArea[]> llyAreaGroupMap, LlyStartParam llyStartParam) {
        List<LlyArea[]> llyWinAreaLists = new ArrayList<>();
        //获取总的投注额
        long totalBet = getTotalBet(llyStartParam.getAreaBet());
        List<LlyArea[]> llyAreaLists = new ArrayList<>();
        for (Map.Entry<String, LlyArea[]> entry : llyAreaGroupMap.entrySet()) {
            //记得把4区剔除在外
            if (entry.getValue().length > 0 && entry.getKey() != LlyConstants.GROUP0) {
                llyAreaLists.add(entry.getValue());
            }
        }
        int t = llyAreaLists.size();
        if (t <= 0) {
            return null;
        }
        //计算单个的,可以进行优化，二分查找，但是优化的作用比较小，数值比较少
        for (int i = 0; i < t; i++) {
            LlyArea[] llyAreas = llyAreaLists.get(i);
            for (int j = 0; j < llyAreas.length; j++) {
                if ((llyAreas[j].getTotalPayValue() - totalBet) > 0) {
                    llyWinAreaLists.add(new LlyArea[]{llyAreas[j]});
                }
            }
        }
        //todo:存在bug
        //计算2组合的
        if (t >= 2) {
            for (int i = 0; i < t; i++) {
                for (int j = i + 1; j < t; j++) {
                    LlyArea[] llyAreasArray0 = llyAreaLists.get(i);
                    LlyArea[] llyAreasArray1 = llyAreaLists.get(j);
                    combine2(llyWinAreaLists, llyAreasArray0, llyAreasArray1, totalBet);
                }
            }
        }
        //计算3组合的，有1种或者最多有4种组合情况
        if (t == 3) {
            //只计算三种情况的话
            LlyArea[] llyAreasArr0 = llyAreaLists.get(0);
            LlyArea[] llyAreasArr1 = llyAreaLists.get(1);
            LlyArea[] llyAreasArr2 = llyAreaLists.get(2);
            combine3(llyWinAreaLists, llyAreasArr0, llyAreasArr1, llyAreasArr2, totalBet);
        }
        //计算t=4的时候出现的4种3个组合情况+1种全4组合的情况
        if (t == 4) {
            LlyArea[] llyAreas4Arr0 = llyAreaLists.get(0);
            LlyArea[] llyAreas4Arr1 = llyAreaLists.get(1);
            LlyArea[] llyAreas4Arr2 = llyAreaLists.get(2);
            LlyArea[] llyAreas4Arr3 = llyAreaLists.get(3);
            combine3(llyWinAreaLists, llyAreas4Arr0, llyAreas4Arr1, llyAreas4Arr2, totalBet);
            combine3(llyWinAreaLists, llyAreas4Arr0, llyAreas4Arr1, llyAreas4Arr3, totalBet);
            combine3(llyWinAreaLists, llyAreas4Arr1, llyAreas4Arr2, llyAreas4Arr3, totalBet);
            combine3(llyWinAreaLists, llyAreas4Arr0, llyAreas4Arr2, llyAreas4Arr3, totalBet);
            combine4(llyWinAreaLists, llyAreas4Arr0, llyAreas4Arr1, llyAreas4Arr2, llyAreas4Arr3, totalBet);
        }
        return llyWinAreaLists;
    }


    //根据得到的区域反计算出不赢的牌值
    private int getCardResult(List<LlyArea[]> canWinAreas, boolean remove7) {
        Set<Integer> sets = new HashSet<>();
        for (LlyArea[] arr : canWinAreas) {
            //遍历单个数组，计算出牌值并放入到集合中去
            Set<Integer> set0 = new HashSet<>();
            for (int i = 0; i < arr.length; i++) {
                Integer[] values = AreaEnum.getValues(arr[i].getArea());
                List<Integer> integers = Arrays.asList(values);
                Set<Integer> set1 = new HashSet<>();
                set1.addAll(integers);
                //初始化
                if (set0.isEmpty())
                    set0.addAll(set1);
                set0.retainAll(set1);
            }
           // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!+set0==="+set0);
            sets.addAll(set0);
        }
        //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%+sets==="+sets);
        Set<Integer> setsTotal = new HashSet<>();
        if (remove7) {
            setsTotal.addAll(Arrays.asList(LlyConstants.CARDDATA1));
        } else {
            setsTotal.addAll(Arrays.asList(CARDDATA));
        }
        setsTotal.removeAll(sets);
        //注意要考虑如果数组是空的？
        Integer[] integers = setsTotal.toArray(new Integer[]{});
        if(integers.length<=0){
            return LlyConstants.CARDDATA1[(int) (Math.abs(LlyConstants.CARDDATA1.length * (Math.random())))];
        }
        int index = (int) (Math.abs(integers.length * (Math.random())));
      /*  Integer idx=0;
        try{
            idx = integers[index];
        }catch (Exception e){
            System.out.println("==============================================   "+sets);
        }*/
        return integers[index];
    }

    /**
     * @param
     * @return
     * @description 循环简化
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/9
     */
    private void combine4(List<LlyArea[]> llyWinAreaLists, LlyArea[] arr1, LlyArea[] arr2, LlyArea[] arr3, LlyArea[] arr4, long totalBet) {
        for (int m = 0; m < arr1.length; m++) {
            for (int n = 0; n < arr2.length; n++) {
                for (int k = 0; k < arr3.length; k++) {
                    for (int p = 0; p < arr4.length; p++) {
                        if ((arr1[m].getTotalPayValue() + arr2[n].getTotalPayValue() +
                                arr3[k].getTotalPayValue() + arr4[p].getTotalPayValue()) > totalBet) {
                            LlyArea[] llyAreas = {arr1[m], arr2[n], arr3[k], arr4[p]};
                            if(isExclusion(llyAreas))
                                continue;
                            llyWinAreaLists.add(llyAreas);
                        }
                    }
                }
            }
        }
    }

    /**
     * @param
     * @return
     * @description 循环简化
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/9
     */
    private void combine3(List<LlyArea[]> llyWinAreaLists, LlyArea[] arr1, LlyArea[] arr2, LlyArea[] arr3, long totalBet) {
        for (int m = 0; m < arr1.length; m++) {
            for (int n = 0; n < arr2.length; n++) {
                for (int k = 0; k < arr3.length; k++) {
                    if ((arr1[m].getTotalPayValue() + arr2[n].getTotalPayValue() +
                            arr3[k].getTotalPayValue()) > totalBet) {
                        LlyArea[] llyAreas = {arr1[m], arr2[n], arr3[k]};
                        if(isExclusion(llyAreas))
                            continue;
                        llyWinAreaLists.add(llyAreas);
                    }
                }
            }
        }
    }

    /**
     * @param
     * @return
     * @description 循环简化
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/9
     */
    private void combine2(List<LlyArea[]> llyWinAreaLists, LlyArea[] arr1, LlyArea[] arr2, long totalBet) {
        for (int m = 0; m < arr1.length; m++) {
            for (int n = 0; n < arr2.length; n++) {
                if ((arr1[m].getTotalPayValue() + arr2[n].getTotalPayValue()) > totalBet) {
                    LlyArea[] llyAreas = {arr1[m], arr2[n]};
                    if(isExclusion(llyAreas))
                        continue;
                    llyWinAreaLists.add(llyAreas);
                }
            }
        }
    }


    /**
     * @param
     * @return
     * @description 获取总的投注额
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/9
     */
    private long getTotalBet(long[] areaBet) {
        long totalBet = 0;
        for (int i = 0; i < areaBet.length; i++) {
            totalBet += areaBet[i];
        }
        return totalBet;
    }
    private boolean isExclusion(LlyArea[] llyAreas){
        Integer[] integers = new Integer[llyAreas.length];
        for(int i=0;i<llyAreas.length;i++){
            integers[i]=llyAreas[i].getArea();
        }
        return ExclusionGroupEnum.isEqual(integers);
    }
    //赔付处理
    private long[] payOperation(int cbCardData, long[] areaBet) {
        long[] areaPay = new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        //    获取赢区域
        int[] judgeArr = getWinArea(cbCardData);

        //    红黑区域赔付计算
        if (judgeArr[0] == LlyConstants.LOGIC_RED) {
            //    红色赔付
            areaPay[LlyConstants.AREA_RED] = getPayScore(LlyConstants.AREA_RED, areaBet);
        } else if (judgeArr[0] == LlyConstants.LOGIC_BLACK) {
            //    黑色赔付
            areaPay[LlyConstants.AREA_BLACK] = getPayScore(LlyConstants.AREA_BLACK, areaBet);
        } else {
            //    无赔付
        }

        //    花色区域赔付计算
        if (judgeArr[1] == LlyConstants.LOGIC_DIAMOND) {
            //    方块赔付
            areaPay[LlyConstants.AREA_DIAMOND] = getPayScore(LlyConstants.AREA_DIAMOND, areaBet);
        } else if (judgeArr[1] == LlyConstants.LOGIC_CLUB) {
            //    梅花赔付
            areaPay[LlyConstants.AREA_CLUB] = getPayScore(LlyConstants.AREA_CLUB, areaBet);
        } else if (judgeArr[1] == LlyConstants.LOGIC_HEART) {
            //    红桃赔付
            areaPay[LlyConstants.AREA_HEART] = getPayScore(LlyConstants.AREA_HEART, areaBet);
        } else if (judgeArr[1] == LlyConstants.LOGIC_SPADE) {
            //    黑桃赔付
            areaPay[LlyConstants.AREA_SPADE] = getPayScore(LlyConstants.AREA_SPADE, areaBet);
        } else {
            //    无赔付
        }

        //    大小区域赔付计算
        if (judgeArr[2] == LlyConstants.LOGIC_BIG) {
            //    大赔付
            areaPay[LlyConstants.AREA_BIG] = getPayScore(LlyConstants.AREA_BIG, areaBet);
        } else if (judgeArr[2] == LlyConstants.LOGIC_SMALL) {
            //    小赔付
            areaPay[LlyConstants.AREA_SMALL] = getPayScore(LlyConstants.AREA_SMALL, areaBet);
        } else {
            //    无赔付
        }

        //    单双区域赔付计算
        if (judgeArr[3] == LlyConstants.LOGIC_SINGLE) {
            //    大赔付
            areaPay[LlyConstants.AREA_SINGLE] = getPayScore(LlyConstants.AREA_SINGLE, areaBet);
        } else if (judgeArr[3] == LlyConstants.LOGIC_DOUBLE) {
            //    小赔付
            areaPay[LlyConstants.AREA_DOUBLE] = getPayScore(LlyConstants.AREA_DOUBLE, areaBet);
        } else {
            //    无赔付
        }

        //    7区域赔付计算
        if (judgeArr[4] == LlyConstants.LOGIC_SEVEN) {
            //    大赔付
            areaPay[LlyConstants.AREA_SEVEN] = getPayScore(LlyConstants.AREA_SEVEN, areaBet);
        } else {
            //    无赔付
        }

        return areaPay;
    }

    //    获取赢区域
    private int[] getWinArea(int cbCardData) {
        int cardColor = GetCardColor(cbCardData);
        int cardValue = GetCardValue(cbCardData);

        //  赢的区域
        int[] judgeArr = new int[5];
        switch (cardColor) {
            case LlyConstants.LOGIC_DIAMOND:
                judgeArr[0] = LlyConstants.LOGIC_RED;
                judgeArr[1] = LlyConstants.LOGIC_DIAMOND;
                break;
            case LlyConstants.LOGIC_CLUB:
                judgeArr[0] = LlyConstants.LOGIC_BLACK;
                judgeArr[1] = LlyConstants.LOGIC_CLUB;
                break;
            case LlyConstants.LOGIC_HEART:
                judgeArr[0] = LlyConstants.LOGIC_RED;
                judgeArr[1] = LlyConstants.LOGIC_HEART;
                break;
            case LlyConstants.LOGIC_SPADE:
                judgeArr[0] = LlyConstants.LOGIC_BLACK;
                judgeArr[1] = LlyConstants.LOGIC_SPADE;
                break;
            default:
                break;
        }


        if (cardValue > 7) {
            judgeArr[2] = LlyConstants.LOGIC_BIG;
        } else {
            judgeArr[2] = LlyConstants.LOGIC_SMALL;
        }
        if (cardValue % 2 != 0) {
            judgeArr[3] = LlyConstants.LOGIC_SINGLE;
        } else {
            judgeArr[3] = LlyConstants.LOGIC_DOUBLE;
        }

        if (cardValue == 7) {
            judgeArr[0] = LlyConstants.LOGIC_NULL;
            judgeArr[1] = LlyConstants.LOGIC_NULL;
            judgeArr[2] = LlyConstants.LOGIC_NULL;
            judgeArr[3] = LlyConstants.LOGIC_NULL;
            judgeArr[4] = LlyConstants.LOGIC_SEVEN;

        }
        return judgeArr;
    }

    //    赔付计算
    private long winScore(long areaBet, int payRate) {
        long areaPay = areaBet * payRate;
        return areaPay;
    }

    //获取数值
    private int GetCardValue(int cbCardData) {
        return cbCardData & LlyConstants.LOGIC_MASK_VALUE;
    }

    //获取花色
    private int GetCardColor(int cbCardData) {
        return cbCardData & LlyConstants.LOGIC_MASK_COLOR;
    }

    //    获取赔付分数
    private long getPayScore(int area, long[] areaBet) {

        return areaBet[area] * LlyConstants.PAY_RATE[area];
    }

    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        long[] areaBet = new long[]{10, 70, 80, 30, 60, 0, 50, 90, 50, 20, 60};
        LlyStartParam llyStartParam=new LlyStartParam();
        llyStartParam.setAreaBet(areaBet);
        gameLogic.execute(llyStartParam,areaBet);
        long[] longs = gameLogic.payOperation(33, areaBet);
        long sum = 0;
        for (int i = 0; i < longs.length; i++) {
            sum += longs[i];
        }
        System.out.println("------------------------------------- " + sum);
    }
}
