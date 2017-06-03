package com.jd.study.common.commontest;
/**
 * @author dushuangcheng
 * @create 2017-02-07 14:42
 */
public class LlzMainTest {
    public static int count=0;

    public static int redCount=0;
    public static int blueCount=0;
    public static int yellowCount=0;

    public static int countInner=0;


    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        LlzMainTest.count = count;
    }

    public static void main(String[] args) {
     LlzMainTest llzMainTest=new LlzMainTest();

        for(int i=0;i<10000;i++){
            llzMainTest.getResult(llzMainTest.getCount(llzMainTest.getRandomIndex()));
        }
        int total=llzMainTest.redCount*17+llzMainTest.yellowCount*7+llzMainTest.blueCount*5;

        System.out.println("=======================>总的中奖次数为"+llzMainTest.getCount()+"红色，黄色，蓝色区域中的次数为：" +
                llzMainTest.redCount+" : "+llzMainTest.yellowCount+" : "+llzMainTest.blueCount);

        System.out.println("===========================>"+ total+"赚了====>"+(total-80000));


        System.out.println("*********************====>"+llzMainTest.countInner);
    }

    //获取随机的下标
    public int[] getRandomIndex(){
        int[] indexAry=new int[3];
        for(int i=0;i<3;i++){
            indexAry[i]=(int)Math.floor(Math.random()*7);
        }
        return indexAry;
    }

    //模拟转盘的效果
    public  int[][] getCount(int[] indexAry){
        int[] a=new int[]{1,3,2,3,1,3,2,2};
        int[] b=new int[]{3,2,2,3,1,1,3,2};
        int[] c=new int[]{2,1,2,3,1,3,2,3};
        int[][] abc=new int[][]{a,b,c};
        int[][] newAbc=new int[3][8];

        for(int i=0; i<3; i++) {
            for (int j = indexAry[i], k=0; k < 8; j++,k++) {
                if(j>=8){
                    j = 0;
                }
                newAbc[i][j]=abc[i][k];
            }
        }
        return newAbc;
    }

    //算奖
    public void getResult(int[][] newAbc ){
        boolean flag=false;


        for(int i=0;i<8;i++){
                if((newAbc[0][i]==newAbc[1][i])&&(newAbc[0][i]==newAbc[2][i])){
                    System.out.println("这个位置中奖了。。。===========");
                   countInner++;
                    flag=true;
                    if(newAbc[0][i]==1){
                        redCount++;
                    }
                    if(newAbc[0][i]==2){
                        yellowCount++;
                    }
                    if(newAbc[0][i]==3){
                        blueCount++;
                    }
                }
        }

        if(flag){
            count++;
            System.out.println("本次游戏中奖了。。============="+count);
        }
        System.out.println("-------------------------------------------------------------");

   }

}
