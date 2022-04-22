package algorithms.dynamic_programming;

public class KnapsackProblem {
    public static void main(String[] args) {
        int capacity = 4;
        int[] weights = {1, 4, 3};
        int[] values = {1500, 3000, 2000};
        System.out.println(solve(weights,values,capacity));
    }

    public static int solve(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null
                || capacity <= 0 || weights.length != values.length
                || weights.length == 0)
            return 0;

        int n = weights.length;
        int[][] ans = new int[n + 1][capacity + 1];

        //java中数组初始化默认为0，故不需要特别指出边界条件

        int curWeight, curVal;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                curWeight = weights[i - 1];//第i个物品的重量
                curVal = values[i - 1];//第i个物品的价值

                if (curWeight > j) {
                    ans[i][j] = ans[i - 1][j];
                } else {
                    ans[i][j] = Math.max(ans[i - 1][j], curVal + ans[i - 1][j - curWeight]);
                }
            }
        }


        return ans[n][capacity];
    }
}
