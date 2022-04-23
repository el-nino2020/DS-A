package data_structures.graph;

import data_structures.linked_list.DoubleLinkedList;
import data_structures.queue.CircleArrayQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param <D> 节点中数据的类型
 */
@SuppressWarnings({"unused"})
public class Graph<D> {

    public static class Vertex<T> {
        private int index;
        private T item;//节点存放的数据

        public Vertex(int index) {
            this.index = index;
        }

        public Vertex(int index, T item) {
            this.index = index;
            this.item = item;
        }

        @Override
        public String toString() {
            return index + ":" + item;
        }
    }


    private ArrayList<Vertex<D>> vertices;
    private final int size;
    private int[][] adjMatrix;//邻接矩阵
    private DoubleLinkedList[] adjList;//邻接表

    public Graph(int size) {
        if (size <= 0) size = 5;

        this.size = size;
        vertices = new ArrayList<>(size);
        adjMatrix = new int[size][size];
        adjList = new DoubleLinkedList[size];

        for (int i = 0; i < size; i++) {
            vertices.add(new Vertex<D>(i));
            adjList[i] = new DoubleLinkedList();

        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public boolean setVertexData(int index, D item) {
        if (index < 0 || index >= size) return false;

        Vertex<D> vertex = vertices.get(index);
        vertex.item = item;
        return true;
    }

    /**
     * 往图里添加边
     *
     * @param from     边的起始点的索引
     * @param to       边的终点的索引
     * @param weight   边的权重
     * @param directed true则添加一条有向边，false则添加一条无向边
     * @return 添加成功则返回true
     */
    public boolean addEdge(int from, int to, int weight, boolean directed) {
        if (from < 0 || from >= size) return false;
        if (to < 0 || to >= size) return false;

        adjMatrix[from][to] = weight;
        adjList[from].add(to);
        if (!directed) {
            adjList[to].add(from);
            adjMatrix[to][from] = weight;
        }
        return true;
    }

    /**
     * 添加一条从from到to的无向边，权重为1
     */
    public boolean addEdge(int from, int to) {
        return addEdge(from, to, 1, false);
    }

    /**
     * 添加一条从from到to的无向边，权重为weight
     */
    public boolean addEdge(int from, int to, int weight) {
        return addEdge(from, to, weight, false);
    }

    /**
     * 删除从from到to的有向边
     *
     * @return 删除成功返回true
     */
    public boolean removeEdge(int from, int to) {
        if (from < 0 || from >= size) return false;
        if (to < 0 || to >= size) return false;
        adjMatrix[from][to] = 0;
        adjList[from].remove(to);
        return true;
    }

    /**
     * @param index
     * @return 索引为index的所有邻居
     */
    public DoubleLinkedList getOneList(int index) {
        if (index < 0 || index >= size) return null;
        return adjList[index];
    }


    /**
     * @return 图的节点数
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回索引为index的节点的数据
     */
    public D getVertexItem(int index) {
        if (index < 0 || index >= size) return null;
        return vertices.get(index).item;
    }

    /**
     * 返回从from 到to 的边的权重
     */
    public int getWeight(int from, int to) {
        return adjMatrix[from][to];
    }

    public void showGraph() {
        System.out.print("\t");
        System.out.println(vertices);
        for (int j = 0; j < size; ++j) {
            System.out.print(vertices.get(j) + "\t\t");
            int[] line = adjMatrix[j];

            for (int i : line) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

    }

    public static List<Vertex<?>> DFS(Graph<?> graph, int index) {
        if (graph == null || index < 0 || index >= graph.size) return null;

        ArrayList<Vertex<?>> ans = new ArrayList<>();
        boolean[] visited = new boolean[graph.size];

        DFS(graph, index, ans, visited);

        return ans;
    }

    private static void DFS(Graph<?> graph, int index,
                            List<Vertex<?>> record, boolean[] visited) {
        visited[index] = true;
        record.add(graph.vertices.get(index));

        int[] line = graph.adjMatrix[index];//索引为index的节点的所有邻居

        for (int i = 0; i < graph.size; i++) {
            int connected = line[i];
            if (connected != 0 && !visited[i]) {
                DFS(graph, i, record, visited);
            }
        }

    }

    public static List<Vertex<?>> BFS(Graph<?> graph, int index) {
        if (graph == null || index < 0 || index >= graph.size) return null;

        ArrayList<Vertex<?>> ans = new ArrayList<>();
        boolean[] visited = new boolean[graph.size];

        //不想自己写一个Queue就用java.util.ArrayDeque
        CircleArrayQueue queue = new CircleArrayQueue(graph.size);
        queue.add(index);

        while (!queue.isEmpty()) {
            int cur = queue.get();
            visited[cur] = true;
            ans.add(graph.vertices.get(cur));

            int[] line = graph.adjMatrix[cur];
            for (int i = 0; i < graph.size; i++) {
                if (line[i] != 0 && !visited[i]) {
                    queue.add(i);
                }
            }
        }

        return ans;
    }

    /**
     * @return copy of the adjacent matrix
     */
    public int[][] getCopyOfAdjMatrix() {
        int[][] ans = new int[size][];
        for (int i = 0; i < size; i++) {
            ans[i] = Arrays.copyOf(adjMatrix[i], size);
        }
        return ans;
    }

}
