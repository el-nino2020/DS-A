package algorithms.graph.shortest_path;

import algorithms.graph.TopologicalSort;
import data_structures.graph.Graph;
import data_structures.linked_list.DoubleLinkedList;
import org.junit.Test;

public class DAGRelaxation {

    @Test
    public void test() {
        Graph<Object> graph = new Graph<>(6);
        graph.addEdge(0, 1, 4, true);
        graph.addEdge(0, 2, 2, true);
        graph.addEdge(1, 2, 5, true);
        graph.addEdge(1, 3, 10, true);
        graph.addEdge(2, 4, 3, true);
        graph.addEdge(4, 3, 4, true);
        graph.addEdge(3, 5, 11, true);

        int[] ans = solve(graph, 1);
        for (int dist : ans) {
            System.out.print(dist + "\t");
        }
    }

    /**
     * 给定一个DAG和一个起点，返回其他顶点到该顶点的最短距离
     *
     * @param graph 一个DAG
     * @param start 起始顶点
     * @return 其他顶点到该顶点的最短距离，如果graph不是DAG，返回null
     * @see algorithms.graph.TopologicalSort
     */
    public static int[] solve(Graph<?> graph, int start) {
        if (graph == null || start >= graph.getSize() || start < 0)
            return null;

        int n = graph.getSize();

        int[] seq = TopologicalSort.solve(graph);
        if (seq == null)//graph不是DAG
            return null;

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = Integer.MAX_VALUE;
        }
        ans[start] = 0;

        for (int index : seq) {
            DoubleLinkedList neighbors = graph.getOneList(index);
            neighbors.resetNext();
            while (neighbors.hasNext()) {
                int next = neighbors.next();
                int weight = graph.getWeight(index, next);
                if (ans[next] > mathAdd(ans[index], weight)) {
                    ans[next] = mathAdd(ans[index], weight);
                }
            }
        }

        return ans;
    }

    /**
     * 视Integer.MAX_VALUE为正无穷，根据数学中的定义，其中一个加法数为无穷，结果为无穷
     * 这里主要是防止整数溢出，且假设 a, b都不为Integer.MAX_VALUE的情况下，
     * a + b <= Integer.MAX_VALUE
     */
     static int mathAdd(int a, int b) {
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        return a + b;
    }
}
