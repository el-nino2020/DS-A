package algorithms.graph;

import data_structures.graph.Graph;
import data_structures.linked_list.DoubleLinkedList;
import data_structures.queue.CircleArrayQueue;
import org.junit.Test;

import java.util.Arrays;

public class TopologicalSort {
    @Test
    public void test() {
        Graph<Object> graph = new Graph<Object>(6);
        graph.addEdge(5, 0, 1, true);
        graph.addEdge(4, 0, 1, true);
        graph.addEdge(5, 2, 1, true);
        graph.addEdge(4, 1, 1, true);
        graph.addEdge(2, 3, 1, true);
        graph.addEdge(3, 1, 1, true);

        int[] ans = solve(graph);
        Arrays.stream(ans).forEach(i -> System.out.print(i + "\t"));
    }

    /**
     * 返回DAG中节点的拓扑排序
     *
     * @param graph 一个DAG
     * @return int[graph.size]，存放了节点拓扑排序后的顺序;如果graph不为DAG，则返回null
     */
    public static int[] solve(Graph<?> graph) {
        if (graph == null)
            return null;
        int n = graph.getSize();

        int[] inDegree = new int[n];
        int[] ans = new int[n];
        CircleArrayQueue queue = new CircleArrayQueue(n);//当前入度为0的节点的索引
        //统计入度
        for (int i = 0; i < n; i++) {
            DoubleLinkedList neighbors = graph.getOneList(i);
            neighbors.resetNext();
            while (neighbors.hasNext()) {
                inDegree[neighbors.next()]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        int count = 0;
        while (queue.size() != 0) {
            int i = queue.get();
            ans[count++] = i;

            DoubleLinkedList neighbors = graph.getOneList(i);
            neighbors.resetNext();

            while (neighbors.hasNext()) {
                int k = neighbors.next();
                if ((--inDegree[k]) == 0) {
                    queue.add(k);
                }
            }
        }

        if (count != n) return null;

        return ans;
    }
}
