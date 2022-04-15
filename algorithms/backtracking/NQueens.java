package algorithms.backtracking;

public class NQueens {
    private static int n;
    private static int judgeCount;
    private static int[] arr;
    private static int ans;

    public static void main(String[] args) {
        solve(8);
    }

    /**
     * 求解n皇后问题
     */
    public static synchronized void solve(int boardSize) {
        if (boardSize < 1) {
            System.out.println("请输入正整数作为棋盘大小");
            return;
        }
        n = boardSize;
        arr = new int[n + 1];
        ans = 0;
        judgeCount = 0;
        recur(1);
        System.out.println(n + "皇后问题有" + ans + "种解法");
        System.out.println("总共判断了" + judgeCount + "次");
    }

    /**
     * @param index 第index个皇后开始判断 , 1 <= index <= n
     */
    private static void recur(int index) {
        //棋盘的第index行，第i列
        for (int i = 1; i <= n; i++) {
            arr[index] = i;

            //判断和其它的皇后是否会互相攻击
            boolean flag = true;
            ++judgeCount;
            //棋盘的第j行
            for (int j = 1; j < index; j++) {
                //      在同一列  or  在同一斜线上,即列差等于行差
                if (i == arr[j] || Math.abs(i - arr[j]) == index - j) {
                    flag = false;
                    break;
                }
            }
            //放在该位置不会和前面的皇后冲突
            if (flag) {
                if (index == n) {
                    ++ans;//找到一种解法
                    return;//第8个皇后每一次只可能在第8行的某一列成立，没必要判断剩下的了
                } else {
                    recur(index + 1);
                }
            }


        }
    }
}
