package algorithms.minimum_spanning_tree;

import data_structures.graph.Graph;
import data_structures.linked_list.DoubleLinkedList;

import java.util.ArrayList;
import java.util.HashSet;

public class Prim {

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

        int weightSum = 0;
        int[][] treeEdges = solve(edges);
        for (int[] treeEdge : treeEdges) {
            System.out.println(chars[treeEdge[0]] + "-" + chars[treeEdge[1]]);
            weightSum += treeEdge[2];
        }

        System.out.println("总权重为 " + weightSum);

    }


    /**
     * Prim算法实现最小生成树
     *
     * @param graph 一张无向连通图
     * @return 最小生成树的所有边，大小为int[|V|-1][3];
     * 对于第i条边，两端的节点为ans[i][0]和ans[i][1]，权重为ans[i][2]
     */
    private static int[][] solve(Graph<?> graph) {
        if (graph == null) return null;
        int n = graph.getSize();
        int[][] ans = new int[n - 1][3];

        boolean[] visited = new boolean[n];
        ArrayList<Integer> nodes = new ArrayList<>();//当前树中的节点的索引

        nodes.add(0);
        visited[0] = true;

        for (int i = 0; i < n - 1; i++) {
            int min = Integer.MAX_VALUE;
            int from = -1, to = -1, k, m;
            for (Integer node : nodes) {//遍历当前树中的所有节点
                DoubleLinkedList neighbour = graph.getOneList(node);
                neighbour.resetNext();

                while (neighbour.hasNext()) {
                    if (!visited[(k = neighbour.next())] &&
                            (m = graph.getWeight(node, k)) < min) {
                        to = k;
                        from = node;
                        min = m;
                    }
                }
            }

            nodes.add(to);
            visited[to] = true;

            ans[i][0] = from;
            ans[i][1] = to;
            ans[i][2] = graph.getWeight(from, to);
        }

        return ans;
    }

    /**
     * Prim算法实现最小生成树
     *
     * @param edges 一张无向连通图的所有边，大小为[|E|][3],
     *              对于第i条边，两端的节点为edges[i][0]和edges[i][1]，权重为edges[i][2]
     * @return 最小生成树的所有边，大小为int[|V|-1][2];
     * 对于第i条边，两端的节点为ans[i][0]和ans[i][1]，权重为ans[i][2]
     */
    public static int[][] solve(int[][] edges) {
        HashSet<Integer> vertices = new HashSet<>();
        for (int[] edge : edges) {
            vertices.add(edge[0]);
            vertices.add(edge[1]);
        }

        Graph<Object> graph = new Graph<Object>(vertices.size());

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1], edge[2]);
        }

        return solve(graph);
    }
}
