package data_structures.sparse_array;

/**
 * 稀疏数组
 */
@SuppressWarnings({"unused"})
public class SparseArray {
    //默认值，即不被记录的那些点
    private static int defaultValue = 0;

    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        matrix[0][2] = 3;
        matrix[3][2] = 767;
        matrix[4][3] = 9;
        matrix[1][4] = -1;

        print2D(matrix);

        System.out.println("================================");
        print2D(sparseArrayTo2D(twoDimToSparse(matrix)));
    }

    /**
     * 将二维数组转化为稀疏数组
     *
     * @param matrix 二维数组
     * @return 稀疏数组
     */
    public static int[][] twoDimToSparse(int[][] matrix) {
        int[][] ans = null;
        if (matrix == null) return ans;
        int m = matrix.length, n = matrix[0].length;
        //1. 先求有多少个非默认值的点
        int count = 0;
        for (int[] ints : matrix) {
            for (int i : ints) {
                if (i != defaultValue) {
                    ++count;
                }
            }
        }
        //2.生成稀疏数组
        ans = new int[count + 1][3];
        ans[0][0] = m;
        ans[0][1] = n;
        ans[0][2] = count;

        int k = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != defaultValue) {
                    ans[k][0] = i;
                    ans[k][1] = j;
                    ans[k][2] = matrix[i][j];
                    k++;
                }
            }
        }

        return ans;
    }

    /**
     * 稀疏数组转二维数组
     *
     * @param matrix 稀疏数组
     * @return 二维数组
     */
    public static int[][] sparseArrayTo2D(int[][] matrix) {
        int[][] ans = null;
        if (matrix == null) return ans;
        int m = matrix[0][0];
        int n = matrix[0][1];
        int count = matrix[0][2];

        ans = new int[m][n];
        if (defaultValue != 0) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    ans[i][j] = defaultValue;
                }
            }
        }
        if (count == 0) return ans;

        //使用count作为循环的界限是考虑到c语言的数组长度未知
        for (int i = 1; i <= count; i++) {
            ans[matrix[i][0]][matrix[i][1]] = matrix[i][2];
        }


        return ans;
    }

    public static void print2D(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }


    public static int getDefaultValue() {
        return defaultValue;
    }

    public static void setDefaultValue(int defaultValue) {
        SparseArray.defaultValue = defaultValue;
    }
}
