package algorithms.backtracking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

public class KnightTourProblem {
    private static int X;
    private static int Y;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int[][] ans = solve(10, 10, new Point(5, 3));

        long end = System.currentTimeMillis();

        if (ans == null) {
            System.out.println("无解");
            return;
        }

        System.out.println("结果为：");
        print2DArray(ans);
        System.out.println("耗时" + ((end - start) / 1000.0) + "秒");
    }

    /**
     * @param row 棋盘的长度
     * @param col 棋盘的宽度
     * @param p   出发点，其中0<= p.x < row, 0<= p.y < col
     * @return 有解则返回一个每一步应该走哪一个点的int[row][col]，否则返回null
     *///使用了可修改的静态变量，因而设置为synchronized，不然会出大问题
    public static synchronized int[][] solve(int row, int col, Point p) {
        if (row <= 0 || col <= 0 || p == null || p.x >= row || p.y >= col)
            return null;
        X = row;
        Y = col;

        boolean[][] visited = new boolean[X][Y];
        int[][] ans = new int[X][Y];

        if (!solve(p, 0, ans, visited))
            return null;

        return ans;
    }

    /**
     * @param cur     当前位于棋盘上的哪一点
     * @param count   当前走了多少步
     * @param ans     存放结果的二维数组
     * @param visited 判断某个点是否已经走过
     * @return 有解则返回true
     */
    private static boolean solve(Point cur, int count, int[][] ans, boolean[][] visited) {
        visited[cur.x][cur.y] = true;

        ans[cur.x][cur.y] = count + 1;
        if (count == X * Y - 1) return true;

        ArrayList<Point> next = next(cur);

        //应该调用ArrayList的sort方法，而不是Collections.sort
        //前者效率更高
        next.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });

        for (Point point : next) {
            if (!visited[point.x][point.y]) {
                if (solve(point, count + 1, ans, visited))
                    return true;
            }
        }

        visited[cur.x][cur.y] = false;
        return false;
    }

    /**
     *
     * @param cur 当前位置，能够保证一定是非空的
     * @return 从当前位置出发能访问(没有超出棋盘)的格子的集合
     */
    private static ArrayList<Point> next(Point cur) {
        int x = cur.x;
        int y = cur.y;
        ArrayList<Point> ans = new ArrayList<>();
        //0
        if (x + 2 < X && y - 1 >= 0) ans.add(new Point(x + 2, y - 1));
        //1
        if (x + 2 < X && y + 1 < Y) ans.add(new Point(x + 2, y + 1));
        //2
        if (x + 1 < X && y + 2 < Y) ans.add(new Point(x + 1, y + 2));
        //3
        if (x - 1 >= 0 && y + 2 < Y) ans.add(new Point(x - 1, y + 2));
        //4
        if (x - 2 >= 0 && y + 1 < Y) ans.add(new Point(x - 2, y + 1));
        //5
        if (x - 2 >= 0 && y - 1 >= 0) ans.add(new Point(x - 2, y - 1));
        //6
        if (x - 1 >= 0 && y - 2 >= 0) ans.add(new Point(x - 1, y - 2));
        //7
        if (x + 1 < X && y - 2 >= 0) ans.add(new Point(x + 1, y - 2));

        return ans;
    }

    //这个方法设为public貌似也无所谓
    private static void print2DArray(int[][] array) {
        for (int[] ints : array) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
