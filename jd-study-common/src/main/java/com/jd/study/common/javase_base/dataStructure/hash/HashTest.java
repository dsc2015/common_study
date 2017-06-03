package com.jd.study.common.javase_base.dataStructure.hash;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/18
 */
public class HashTest {
    public static void main(String[] args) {
        MyHashTable myHashTable = new MyHashTable(12);
        for(int k=0;k<myHashTable.dataItemSize;k++)
        myHashTable.insertValue(new DataItem(k+8));
        myHashTable.displayTable();
    }

    static class DataItem {
        private int iData;

        public DataItem(int iData) {
            this.iData = iData;
        }

        public int getKey() {
            return iData;
        }
    }

     static class MyHashTable {
        //定义一个数组
        private DataItem[] dataItems;
        //数组项的长度
        private int dataItemSize;
        //用于覆盖的数据项
        private DataItem dataNo;
        //
        public void displayTable(){
            System.out.println("start display table+----------------");
            for(int j=0;j<dataItemSize;j++){
                System.out.println("item is:"+dataItems[j].getKey());
            }
            System.out.println("end of display:----------------------");
        }
        //定义哈希函数
        public int hashFunc(int key){
            return key%dataItemSize;
        }
        //插入数据项
        public void insertValue(DataItem dataItem){
            int key = dataItem.getKey();
            //hash之后得到下标索引值
            int hashIndex = hashFunc(key);
            //循环遍历
            while (dataItems[hashIndex]!=null&& dataItems[hashIndex].getKey()!=-1){
                ++hashIndex;
                hashIndex%=dataItemSize;
            }
            dataItems[hashIndex]=dataItem;
        }
        //根据key删除数据项
        public DataItem delete(int key){
            //计算key得到的hash值
            int hashIndex = hashFunc(key);
            while (dataItems[hashIndex]!=null){
                if(dataItems[hashIndex].getKey()==key){
                    DataItem tempData=dataItems[hashIndex];
                    //替换这里的值
                    dataItems[hashIndex]=dataNo;
                    return tempData;
                }
                //继续遍历
                ++hashIndex;
                hashIndex%=dataItemSize;
            }
            return null;
        }

        //查找
        public DataItem find(int key){
            //计算key得到的hash值
            int hashIndex = hashFunc(key);
            //
            while (dataItems[hashIndex]!=null){
                if(dataItems[hashIndex].getKey()==key){
                    return dataItems[hashIndex];
                }
                ++hashIndex;
                hashIndex%=dataItemSize;
            }
            return null;
        }
        public MyHashTable() {
        }

        public MyHashTable(int dataItemSize) {
            this.dataItemSize = dataItemSize;
            this.dataItems = new DataItem[dataItemSize];
            this.dataNo = new DataItem(-1);
        }

        public DataItem[] getDataItems() {
            return dataItems;
        }

        public void setDataItems(DataItem[] dataItems) {
            this.dataItems = dataItems;
        }

        public int getDataItemSize() {
            return dataItemSize;
        }

        public void setDataItemSize(int dataItemSize) {
            this.dataItemSize = dataItemSize;
        }

        public DataItem getDataNo() {
            return dataNo;
        }

        public void setDataNo(DataItem dataNo) {
            this.dataNo = dataNo;
        }
    }
}
