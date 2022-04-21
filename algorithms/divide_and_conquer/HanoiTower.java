package algorithms.divide_and_conquer;

public class HanoiTower {
    public static void main(String[] args) {
        solve(1);
    }

    public static void solve(int n) {
        if (n <= 0 || n > 62) {
            System.out.println("输入应在1-62之间");
            return;
        }
        movePlate('A', 'C', 'B', n);
    }

    /**
     * 将from上的盘子全都移动到to上，需要借助by
     * @param level 移动前from上面有多少个盘子
     */
    private static void movePlate(char from, char to, char by, int level) {
        if (level == 1) {
            System.out.println(from + "->" + to);
        } else {
            movePlate(from, by, to, level - 1);
            movePlate(from, to, by, 1);
            movePlate(by, to, from, level - 1);
        }
    }


}
