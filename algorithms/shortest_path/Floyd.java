package algorithms.shortest_path;

import data_structures.graph.Graph;

import java.util.HashSet;
import java.util.function.DoubleToIntFunction;

public class Floyd {


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


        for (int start = 0; start < vertices.size(); start++) {
            printAns(ans, start, chars);
        }

    }


    /**
     * 使用Floyd算法求出 所有点 x 所有点 的最短路径
     *
     * @param graph 包含n个节点的一张图
     * @return 大小为int[2][n][n]，其中ans[0][i][j]表示节点i到j的最短路径长度，
     * ans[1][i][j]表示节点i到j的最短路径上i的第一个后继节点
     * 递归地使用ans[1]即可求出最短路径
     */
    public static int[][][] solve(Graph<?> graph) {
        if (graph == null) return null;

        int n = graph.getSize();
        int[][] dist = graph.getCopyOfAdjMatrix();

        int[][] next = new int[n][n];//next[i][j]表示节点i到j的最短路径中i的第一个后继节点

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = j;//假设节点i到j的距离已经是最短的了
            }
        }

        for (int k = 0; k < n; k++) {//中间节点
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //防止出现整数溢出
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE)
                        continue;
                    if (dist[i][j] >= dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = k;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }


        int[][][] ans = new int[2][][];
        ans[0] = dist;
        ans[1] = next;
        return ans;
    }

    /**
     * @param ans   大小为int[2][n][n]，其中ans[0][i][j]表示节点i到j的最短路径长度，
     *              ans[1][i][j]表示节点i到j的最短路径上i的第一个后继节点
     *              递归地使用ans[1]即可求出最短路径
     * @param start 从节点start出发的所有最短路径
     * @param chars chars[i]表示第i个节点的别名，方便打印
     */
    public static void printAns(int[][][] ans, int start, char[] chars) {
        int[][] dist = ans[0];
        int[][] next = ans[1];

        System.out.println("===================================");
        System.out.println("出发点为 " + chars[start] + ":");
        for (int i = 0; i < dist.length; i++) {
            System.out.print(chars[start] + "->" + chars[i] +
                    "最短距离为" + dist[start][i] + "\t : ");
            int index = start;
            while (index != i) {
                System.out.print(chars[index] + " - ");
                index = next[index][i];
            }
            System.out.println(chars[index]);

        }

        System.out.println("打印完毕");
        System.out.println("===================================");

    }
}
