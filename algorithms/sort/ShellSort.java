package algorithms.sort;

import java.util.Arrays;

public class ShellSort {
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
        int gap = n / 2;

        while (gap != 0) {
            for (int i = 0; i < gap; i++) {
                //对第i分组进行简单插入排序，第i个分组中每个元素索引间隔gap
                for (int j = i + gap; j < n; j += gap) {
                    //arr[j]为第i组无序表中第一个元素，要插入到第i组的有序表中
                    int val = arr[j];
                    int index = j - gap;
                    while (index >= 0 && val < arr[index]) {
                        arr[index + gap] = arr[index];
                        index -= gap;
                    }
                    arr[index + gap] = val;
                }
            }

            gap /= 2;
        }
    }
}
