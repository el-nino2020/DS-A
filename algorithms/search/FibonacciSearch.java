package algorithms.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {-34, -2, -1, 0, 1, 2, 4, 6, 34, 64, 232, 5454};
        boolean flag = true;

        //检验能否查到已有的
        for (int i = 0; i < arr.length; i++) {
            if (search(arr, arr[i]) != i) {
                flag = false;
                break;
            }
        }

        //检验是否会查到不存在的
        int[] arr2 = {-5555, 3, 5, -4, 7, 44, 25666};
        for (int i = 0; i < arr2.length; i++) {
            if (search(arr, arr2[i]) != -1) {
                flag = false;
                break;
            }
        }

        System.out.println(flag ? "查找成功" : "查找失败");
    }

    /**
     * 使用非递归的方式完成斐波那契查找
     *
     * @param arr 目标数组
     * @param val 要查找的值
     * @return val在arr中的索引，如果不存在，返回-1
     */
    public static int search(int[] arr, int val) {
        int n = arr.length;
        int[] F = getFib(20);
        int k = 0;

        while (n > F[k]) {
            k++;
        }

        int[] temp = Arrays.copyOf(arr, F[k]);//多余的部分会用0补充

        for (int i = n; i < F[k]; i++) {
            temp[i] = arr[n - 1];
        }
        //复制了多个arr[n - 1]在temp中，为了不搞错，提前判断
        if (val == arr[n - 1]) return n - 1;

        int left = 0, right = F[k] - 1, mid;

        while (left < right) {
            mid = left + F[k - 1] - 1;
            if (temp[mid] == val) {
                return mid;
            } else if (val < temp[mid]) {
                right = mid;
                k--;
            } else {
                left = mid + 1;
                k -= 2;
            }
        }
        return -1;
    }

    /**
     * @param n 斐波那契数列的长度为Math.max(20,n)
     * @return 一个斐波那契数列
     */
    public static int[] getFib(int n) {
        if (n < 20) n = 20;
        int[] ans = new int[n];

        ans[0] = ans[1] = 1;
        for (int i = 2; i < n; i++) {
            ans[i] = ans[i - 1] + ans[i - 2];
        }

        return ans;
    }


}
