package algorithms.graph.shortest_path;

import data_structures.graph.Graph;
import data_structures.linked_list.DoubleLinkedList;

import java.util.HashSet;

public class BellmanFord {

    public static void main(String[] args) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] edges = {
                {6, 0, 2},//A G 2
                {6, 1, 3},//B G 3
                {6, 5, 6},//G F 6
                {6, 4, 4},//G E 4
                {0, 1, 5},//A B 5
                {0, 2, 7},//A C 7
                {1, 3, 9},//B D 9
                {3, 5, 4},//D F 4
                {2, 4, 8},//C E 8
                {4, 5, 5} //E F 5
        };
        HashSet<Integer> vertices = new HashSet<>();
        for (int[] edge : edges) {
            vertices.add(edge[0]);
            vertices.add(edge[1]);
        }

        Graph<Object> graph = new Graph<Object>(vertices.size());

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1], edge[2]);
        }

        //构建Graph对象完毕


        for (int start = 0; start < vertices.size(); start++) {
            int[][] ans = solve(graph, start);
            Dijkstra.printAns(ans, chars, start);
        }

    }


    /**
     * BellmanFord求解最短路径
     *
     * @param graph 包含n个节点的图
     * @param start 起始点
     * @return int[2][n]，其中ans[0][i]表示第i个节点到起始点的最短路径长度，
     * ans[1][i]表示第i个节点到起始点的最短路径上的第一个前驱节点，
     * 递归地使用该数组即可求出最短路径
     */
    public static int[][] solve(Graph<?> graph, int start) {
        if (graph == null || graph.getSize() <= start || start < 0) return null;
        int n = graph.getSize();

        int[] prev = new int[n];
        int[] dist = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        for (int i = 0; i < n - 1; i++) {
            //遍历所有边
            for (int j = 0; j < n; j++) {
                DoubleLinkedList neighbors = graph.getOneList(j);
                neighbors.resetNext();

                while (neighbors.hasNext()) {
                    int k = neighbors.next();

                    int weight = graph.getWeight(j, k);

                    if (DAGRelaxation.mathAdd(weight, dist[j]) < dist[k]) {
                        dist[k] = DAGRelaxation.mathAdd(weight, dist[j]);
                        prev[k] = j;
                    }
                }
            }
        }

        boolean hasNegativeCycle = false;
        for (int j = 0; j < n && !hasNegativeCycle; j++) {
            DoubleLinkedList neighbors = graph.getOneList(j);
            neighbors.resetNext();

            while (neighbors.hasNext()) {
                int k = neighbors.next();

                int weight = graph.getWeight(j, k);

                if (DAGRelaxation.mathAdd(weight, dist[j]) < dist[k]) {
                    hasNegativeCycle = true;
                    break;
                }
            }
        }

        //如果存在权重为负的环，那么最短距离为负无穷，最短路径的边的数量是无穷，没啥实际意义
        if (hasNegativeCycle) return null;

        int[][] ans = new int[2][];
        ans[0] = dist;
        ans[1] = prev;
        return ans;


    }
}
