package algorithms.sort;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int n = 20000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * n * 5);
        }
        long start = System.currentTimeMillis();
        sort(arr);
        long end = System.currentTimeMillis();

        //System.out.println(Arrays.toString(arr));
        System.out.println("花费了" + (end - start) + "毫秒");

        boolean correct = true;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                correct = false;
                break;
            }
        }
        System.out.println(correct ? "排序成功" : "排序失败");
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int index = i;//一开始假定第i个元素为最小的
            int val = arr[i];

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < val) {
                    val = arr[j];
                    index = j;
                }
            }
            //将第i小的元素交换到索引为i处
            arr[index] = arr[i];
            arr[i] = val;
        }
    }
}
