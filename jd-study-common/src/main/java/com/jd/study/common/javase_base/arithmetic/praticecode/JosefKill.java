package com.jd.study.common.javase_base.arithmetic.praticecode;
/**
 * @description 约瑟夫环
 * @author dushuangcheng
 * @create 2017/7/11
 */
public class JosefKill {
    public Node josephusKill(Node head,int m){
        if(head ==null|| head.next== head ||m<1){
            return head;
        }
        Node last= head;
        while(last.next!= head){
            last=last.next;
        }
        int count=0;
        while (head !=last){
            if(++count==m){
                last.next= head.next;
                count=0;
            }else {
                last=last.next;
            }
            head =last.next;
        }
        return head;
    }
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
