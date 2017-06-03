package com.jd.study.common.javase_base.dataStructure.DefLink;

/**
 * @author dushuangcheng
 * @description 双向链表
 * @create 2017/3/21
 */
public class DefLink<T> {
    private NodeDef<T> first;
    private NodeDef<T> last;

    /**
     * 判断是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 表头插入数据
     *
     * @param
     */
    public void insertFirst(T key) {
        NodeDef<T> nodeDef = new NodeDef<>();
        nodeDef.setData(key);

        if(first==null){
            first=nodeDef;
            first.setPrevious(null);
            last=nodeDef;
            last.setNext(null);
            return;
        }
        first.setPrevious(nodeDef);
        nodeDef.setNext(first);
        first=nodeDef;
    }
    /**
     * 在给点的key之后进行插入
     */
    public boolean insertAny(T key,T data){
        NodeDef<T> nodeDef = new NodeDef<>();
        nodeDef.setData(data);
        NodeDef<T> current=first;
        while (!current.getData().equals(key)){
            current=current.next;
            if(current==null){
                System.out.println("没有匹配到给定的数值");
                return false;
            }
        }
        //找到之后,但是在链尾
        if(current==last){
            nodeDef.next=null;
            last=nodeDef;
        }else {
            //技巧一定要先连接后面的，再连接前面的
           nodeDef.next=current.next;
            current.next.previous=nodeDef;
        }
        nodeDef.previous=current;
        current.next=nodeDef;
        return true;
    }
    /**
     * 在给定位置之后进行删除
     */
    public NodeDef<T> deleteKey(T key) throws Exception {
        if(isEmpty()){
            throw new Exception("已经没有可以删除的元素了");
        }
        //先查找这个
        NodeDef<T> temp=null;
        NodeDef<T> current=first;
        while (current!=null&&!current.getData().equals(key)){
            current=current.next;
            if(current==null){
                System.out.println("没有找到需要删除的元素！");
                return  null;
            }
        }
        //找到之后
        temp=current;
        temp.previous.next=temp.next;
        temp.next.previous=temp.previous;

        return temp;
    }
    /**
     * 表尾插入数据
     *
     * @param
     */
    public void insertAfter(T key) {
        NodeDef<T> nodeDef = new NodeDef<>();
        nodeDef.setData(key);

        if (isEmpty()) {
            first=nodeDef;
            first.setPrevious(null);
            last=nodeDef;
            last.setNext(null);
            return;
        }
        last.next=nodeDef;
       // last.setNext(nodeDef);
        nodeDef.setPrevious(last);
        last=nodeDef;
    }

    /**
     * 表头删除
     * @param
     */
    public NodeDef<T> deleteFirst() throws Exception {
        if(isEmpty()){
            throw new Exception("没有可以删除的元素");
        }
        NodeDef<T> temp=first;
        first=temp.getNext();
        temp.getNext().setPrevious(null);
        return temp;
    }

    /**
     * 表尾删除
     * @param
     */
    public NodeDef<T> deleteAfter() throws Exception {
        if(isEmpty()){
            throw new Exception("没有可以删除的元素");
        }
        NodeDef<T> temp=last;
        last=last.getPrevious();
        temp.getPrevious().setNext(null);
        return temp;
    }

    /**
     * 显示
     * @param
     */
    public void disPlay(){
        if(isEmpty()){
            System.out.println("null");
        }
        NodeDef<T> current=first;
        while (current!=null){
            System.out.println(current.getData());
            current=current.getNext();
        }
    }

    class NodeDef<T> {
        /**
         * 前向指针
         */
        private NodeDef<T> previous;
        /**
         * 后向指针
         */
        private NodeDef<T> next;

        private T data;

        public NodeDef<T> getPrevious() {
            return previous;
        }

        public void setPrevious(NodeDef<T> previous) {
            this.previous = previous;
        }

        public NodeDef<T> getNext() {
            return next;
        }

        public void setNext(NodeDef<T> next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws Exception {
        DefLink<Integer> integerDefLink = new DefLink<Integer>();
        integerDefLink.insertFirst(10);
        integerDefLink.insertFirst(30);
        integerDefLink.insertFirst(0);
        integerDefLink.insertFirst(20);

        integerDefLink.insertAfter(-20);
        integerDefLink.insertAfter(-30);
        integerDefLink.insertAfter(-50);

        integerDefLink.disPlay();
        System.out.println("-------------------------------------------------");
        integerDefLink.deleteFirst();
        integerDefLink.deleteAfter();

        integerDefLink.disPlay();
        System.out.println("-------------------------------------------------");

        integerDefLink.insertAny(-20, 1000);
        integerDefLink.insertAny(1000,999);
       integerDefLink.deleteKey(-20);

        integerDefLink.disPlay();
        System.out.println("-------------------------------------------------");
    }
}
