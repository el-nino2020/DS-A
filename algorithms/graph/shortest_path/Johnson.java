package algorithms.graph.shortest_path;

import data_structures.graph.Graph;

import java.util.HashSet;

public class Johnson {

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
        int[][][] ans = solve(graph);
        printAns(ans, chars);
    }


    /**
     * 使用Johnson's algorithm算法求出 所有点 * 所有点 的最短路径
     *
     * @param graph 包含n个节点的一张图
     * @return 大小为int[n][2][n]，
     * 其中，ans[i]表示第i个节点,ans[i][0]表示从第i个节点出发到其他节点的最短路径长度
     * ans[i][1]表示其他节点到第i个节点的最短路径上的第一个前驱节点，
     * 递归地使用该数组即可求出最短路径
     */
    public static int[][][] solve(Graph<?> graph) {
        if (graph == null) return null;

        int n = graph.getSize();

        Graph<?> graphS = Graph.enlargeGraph(graph, 1);
        for (int i = 0; i < n; i++) {
            graphS.addEdge(n, i, 0, true);
        }

        int[][] sssp = BellmanFord.solve(graphS, n);

        if (sssp == null) //存在负环路,或图本身有问题
            return null;

        int[] distFromS = sssp[0];

        //不应该更改原图边的权重，故复制一份
        Graph<?> graphModified = Graph.enlargeGraph(graph, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int w;
                if ((w = graphModified.getWeight(i, j)) != Integer.MAX_VALUE) {
                    graphModified.addEdge(i, j,
                            w + distFromS[i] - distFromS[j], true);
                }
            }
        }

        int[][][] ans = new int[n][][];

        for (int i = 0; i < n; i++) {
            int[][] solution = Dijkstra.solve(graphModified, i);
            ans[i] = solution;
        }

        return ans;
    }

    /**
     * @param ans   大小为int[n][2][n]，
     *              其中，ans[i]表示第i个节点,ans[i][0]表示从第i个节点出发到其他节点的最短路径长度
     *              ans[i][1]表示其他节点到第i个节点的最短路径上的第一个前驱节点，
     *              递归地使用该数组即可求出最短路径
     * @param chars chars[i]表示第i个节点的别名，方便打印
     * @see Dijkstra
     */
    public static void printAns(int[][][] ans, char[] chars) {
        for (int start = 0; start < ans.length; start++) {
            Dijkstra.printAns(ans[start], chars, start);
        }
    }


}
