package com.jd.study.common.javase_base.dataStructure.link;


/**
 * @description 链表测试
 * @author dushuangcheng
 * @create 2017/3/16
 */
public class LinkTest {
    public static void main(String[] args) throws Exception {


        //单链表测试
        Link link1 = new Link(100);
        Link link2 = new Link(200);
        Link link3 = new Link(10);
        Link link4 = new Link(20);

        //测试引用链
        link1=link2;
        link2=link3;
        System.out.println("link是否相等。。。。"+link1.equals(link2));

        /*LinkList linkList = new LinkList(link1);
        linkList.insert(link1);
        linkList.insert(link2);
        linkList.insert(link3);
        linkList.insert(link4);
        linkList.display(linkList);
        Link link = linkList.find(linkList, 400);
        boolean b = linkList.deleteNode(linkList, 20);
        System.out.println("==================="+b);
        System.out.println(link);
        //
        linkList.delete();
        System.out.println("-----------------------");
        System.out.println(linkList.delete());*/


    }
}
