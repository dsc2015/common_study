package com.jd.study.common.javase_base.dataStructure.graph;

/**
 * @author dushuangcheng
 * @description 图
 * @create 2017/7/12
 */
public class Graph {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);


        graph.deepFirstSearch();

    }

    private StackX theStack = new StackX();
    private final int MAX_VER = 20;
    //邻接表表示方法
    private Vertex[] vertexLists;
    //邻接矩阵表示法
    private int[][] adjMat;
    private int nVer;

    public Graph() {
        //初始化邻接表
        this.vertexLists = new Vertex[MAX_VER];
        this.adjMat = new int[MAX_VER][MAX_VER];
        this.nVer = 0;
        //初始化邻接矩阵
        for (int i = 0; i < MAX_VER; i++) {
            for (int j = 0; j < MAX_VER; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    //深度优先遍历算法
    public void deepFirstSearch() {
        vertexLists[0].visited = true;
        display(0);
        theStack.push(0);

        while (!theStack.isEmpty()) {
            int v = getAdjUnvisitedVertex(theStack.peek());
            //到头了
            if (v == -1) {
                theStack.pop();
            } else {
                vertexLists[v].visited = true;
                display(v);
                theStack.push(v);
            }
        }
        //结束后重置
        for (int i = 0; i < nVer; i++) {
            vertexLists[i].visited = false;
        }
    }

    //在邻接矩阵中检查节点是否被访问过的列号
    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVer; j++) {
            if (adjMat[v][j] == 1 && vertexLists[j].visited == false) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 添加节点
     */
    public void addVertex(String str) {
        vertexLists[nVer++] = new Vertex(str);
    }

    /**
     * 添加边界
     */
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    /**
     * 打印顶点
     */
    public void display(int v) {
        System.out.println(v + "================" + vertexLists[v].location);
    }

    //自定义的栈数据结构
    static class StackX {
        private final int SIZE = 20;
        private int[] st;
        private int top;

        public StackX() {
            this.st = new int[SIZE];
            this.top = -1;
        }

        /**
         * 入栈
         *
         * @param data
         */
        public void push(int data) {
            st[++top] = data;
        }

        /**
         * 出栈
         *
         * @return
         */
        public int pop() {
            return st[top--];
        }

        /**
         * 获取栈顶元素
         *
         * @return
         */
        public int peek() {
            return st[top];
        }

        /**
         * 判断非空
         */
        public boolean isEmpty() {
            return !(top > -1);
        }
    }

    static class Vertex {
        private String location;
        private boolean visited;

        public Vertex() {
        }

        public Vertex(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "location='" + location + '\'' +
                    ", visited=" + visited +
                    '}';
        }
    }
}
