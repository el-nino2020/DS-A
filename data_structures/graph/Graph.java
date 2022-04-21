package data_structures.graph;

import java.util.ArrayList;
import java.util.Arrays;

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
    private int[][] edges;//邻接矩阵

    public Graph(int size) {
        this.size = size;
        vertices = new ArrayList<>(size);
        edges = new int[size][size];

        for (int i = 0; i < size; i++) {
            vertices.add(new Vertex<D>(i));

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

        edges[from][to] = weight;
        if (!directed)
            edges[to][from] = weight;
        return true;
    }

    /**
     * 删除从from到to的有向边
     *
     * @return 删除成功返回true
     */
    public boolean removeEdge(int from, int to) {
        if (from < 0 || from >= size) return false;
        if (to < 0 || to >= size) return false;
        edges[from][to] = 0;
        return true;
    }


    /**
     * 添加一条从from到to的无向边，权重为1
     */
    public boolean addEdge(int from, int to) {
        return addEdge(from, to, 1, false);
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
        return edges[from][to];
    }

    public void showGraph() {
        System.out.print("\t");
        System.out.println(vertices);
        for (int j = 0; j < size; ++j) {
            System.out.print(vertices.get(j)+"\t\t");
            int[] line = edges[j];

            for (int i : line) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }


    }

}
