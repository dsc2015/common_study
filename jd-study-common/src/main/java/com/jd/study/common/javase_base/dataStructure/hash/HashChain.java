package com.jd.study.common.javase_base.dataStructure.hash;

import com.jd.study.common.javase_base.dataStructure.link.Link;
import org.apache.lucene.queryparser.xml.builders.LikeThisQueryBuilder;

/**
 * @author dushuangcheng
 * @description 链地址法中的链表,数组中存储的是链表
 * @create 2017/5/10
 */
public class HashChain {
    public static void main(String[] args) {
        //创建一个hashTable
        HashTable hashTable = new HashTable(23);
        //进行插入的操作
        for(int i=0;i<40;i++){
            hashTable.insert(new Link((int) Math.abs(1000*Math.random())));
        }

        System.out.println(hashTable);
    }
    //链表节点类
    static class Link {
        private int iData;
        public Link next;

        public Link(int iData) {
            this.iData = iData;
        }

        public int getKey() {
            return iData;
        }
    }

    //链表类
    static class SortedList {
        //链表的起始节点
        private Link first;

        /*public SortedList(Link first) {
            this.first = first;
        }*/

        //插入
        public void insert(Link link) {
            int key = link.getKey();
            Link previous = null;
            Link current = first;
            //遍历找到合适的位置进行插入操作,注意这是个有序的链表
            while (current != null && current.getKey() < key) {
                previous = current;
                current = current.next;
            }
            //假如链表是空的
            if (previous == null) {
                first = link;
            } else {
                previous.next = link;
            }
            //连接起来这个节点
            link.next = current;
        }

        //链节点的删除
        public void delete(int key) {
            Link previous = null;
            Link current=first;

            //找到要删除的节点
            while(current!=null&&current.getKey()!=key){
                previous=current;
                current=current.next;
            }
            //找到的话，去掉这个链节点
            if(previous==null){
                first=first.next;
            }else {
                previous.next=current.next;
            }

        }

        //查找链节点
        public Link find(int key){
            Link current=first;
            while (current!=null&&current.getKey()<=key){
                if(current.getKey()==key){
                    return current;
                }
                current=current.next;
            }
            //没有找到则返回null
            return null;
        }
    }

    static class HashTable{
        private SortedList[] sortedLists;
        private int arraySize;

        public HashTable(int arraySize) {
            this.sortedLists = new SortedList[arraySize];
            this.arraySize = arraySize;
            //填充这个数组
            for(int i=0;i<sortedLists.length;i++){
                sortedLists[i]=new SortedList();
            }
        }
        public int hashFunc(int key){
            return key%arraySize;
        }
        //插入操作
        public void insert(Link link){
            int index=hashFunc(link.getKey());
            //根据key找到对应的数组的索引的位置
            sortedLists[index].insert(link);
        }
        //删除操作
        public void delete(int key){
            int index=hashFunc(key);
            //根据index去找到要删除的位置
            sortedLists[index].delete(key);
        }
        //查找操作
        public Link find(int key){
            int index=hashFunc(key);
            return sortedLists[index].find(key);
        }

    }


}
