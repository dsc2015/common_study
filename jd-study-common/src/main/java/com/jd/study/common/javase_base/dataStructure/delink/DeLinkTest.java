package com.jd.study.common.javase_base.dataStructure.delink;

import com.jd.study.common.javase_base.dataStructure.link.Link;

/**
 * @description 测试双端链表
 * @author dushuangcheng
 * @create 2017/3/17
 */
public class DeLinkTest {
    public static void main(String[] args) {
        DeLink deLink = new DeLink();
        Link link1=new Link(100);
        Link link2=new Link(200);
        Link link3=new Link(300);
        Link link4=new Link(400);

        deLink.insertNodeFirst(link1);
        deLink.insertNodeFirst(link4);
        deLink.insertNodeLast(link2);
    }
}
