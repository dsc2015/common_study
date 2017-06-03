package com.jd.study.common.javase_base.dataStructure.hash;
/**
 * @description rehash demo
 * @author dushuangcheng
 * @create 2017/5/10
 */
public class ReHashTest {
    public static void main(String[] args) {
        //容量为23的hashTable
        HashTable1 hashTable1 = new HashTable1(23);
        for(int i=0;i<12;i++){
            DataItem dataItem = new DataItem((int) Math.abs(1000 * Math.random()));
            hashTable1.insertData((int) Math.abs(1000 * Math.random()),dataItem);
        }
        System.out.println(hashTable1);
    }
   static class DataItem{
        private int iData;

        public DataItem(int iData) {
            this.iData = iData;
        }
        public int getKey(){
            return iData;
        }

        @Override
        public String toString() {
            return "DataItem{" +
                    "iData=" + iData +
                    '}';
        }
    }

  static class HashTable1{
        private DataItem[] dataItems;
        private int arraySize;
        private DataItem NoneDataItem;

        public HashTable1(int size) {
            this.dataItems = new DataItem[size];
            this.arraySize = size;
            NoneDataItem = new DataItem(-1);
        }
        //输出所有的数据
        public void display(){
            for (DataItem d:dataItems){
                System.out.println("数据为："+d.getKey());
            }
        }
        //hash函数1,取余从而回到起始的位置，计算在数组中对应的位置
        public int hashFunc1(int key){
            return key%arraySize;
        }
        //hash函数2，主要用于生成变长的步长，避免了二次探测的问题
        public int hashFunc2(int key){
            return 5-key%5;
        }
        //插入数据
        public void insertData(int key,DataItem item){
            //
            int index=hashFunc1(key);
            int stepSize=hashFunc2(key);

            while((dataItems[index]!=null)&&(dataItems[index].getKey()!=-1)){
                index+=stepSize;
                index%=arraySize;
                System.out.println("这个key进行了查找，key="+key);
            }
            //找到空的位置的话,插入
            dataItems[index]=item;
        }
        //删除数据
        public DataItem delete(int key){
            int index=hashFunc1(key);
            int stepSize=hashFunc2(key);

            while (dataItems[index]!=null){
                if(dataItems[index].getKey()==key){
                    DataItem temp=dataItems[index];
                    //替换成-1的值
                    dataItems[index]=NoneDataItem;
                    return temp;
                }
                //
                index+=stepSize;
                index%=arraySize;
            }
            //仍然没有找到，就返回null
            return null;
        }
        //查找
        public DataItem find(int key){
            int index=hashFunc1(key);
            int stepSize=hashFunc2(key);

            while (dataItems[key]!=null){
                //找到了
                if(dataItems[key].getKey()==key){
                    //
                    return dataItems[key];
                }
                //没有找到
                index+=stepSize;
                index%=arraySize;
            }
            //还是没有找到
            return null;
        }
    }
}
