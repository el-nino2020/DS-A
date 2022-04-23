package algorithms.minimum_spanning_tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Kruskal {

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
     * Kruskal算法实现最小生成树
     *
     * @param edges 一张无向连通图的所有边，大小为[|E|][3],
     *              对于第i条边，两端的节点为edges[i][0]和edges[i][1]，权重为edges[i][2]
     * @return 最小生成树的所有边，大小为int[|V|-1][2];
     * 对于第i条边，两端的节点为ans[i][0]和ans[i][1]，权重为ans[i][2]
     */
    public static int[][] solve(int[][] edges) {
        HashSet<Integer> vertices = new HashSet<>();
        int temp;
        for (int[] edge : edges) {
            //这一步是为了方便roots的修改，使边的起始点的索引小于终点的索引
            if (edge[0] > edge[1]) {
                temp = edge[0];
                edge[0] = edge[1];
                edge[1] = temp;
            }
            vertices.add(edge[0]);
            vertices.add(edge[1]);
        }
        int n = vertices.size();//统计有多少个节点

        int[][] ans = new int[n - 1][3];

        //这里没有用自己实现的排序算法，因为它们都是基于int[]来排序的
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];//按权重大小排序
            }
        });

        //标记每个节点所在的树的终点
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }

        int count = 0;
        for (int j = 0; count < n - 1 && j < edges.length; j++) {
            int start = edges[j][0], end = edges[j][1];
            //会构成回路的一一条边
            if (roots[start] == roots[end]) continue;

            ans[count][0] = start;
            ans[count][1] = end;
            ans[count][2] = edges[j][2];

            //保证每棵树的终点只可能小于等于它在roots中的索引
            if (end == roots[end]) {//索引为start的节点属于一棵树，而end索引只是一个单纯的节点
                roots[end] = roots[start];
            } else {//两个索引的节点都代表一棵树，需要将end索引的一棵树中的终点变为start索引的树的终点
                for (int i = 0; i < n; i++) {
                    if (roots[i] == roots[end]) {
                        roots[i] = roots[start];
                    }
                }
            }

            count++;
        }

        //MST不可能生成
        if (count != n - 1) return null;

        return ans;
    }
}
