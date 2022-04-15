package algorithms.sort;

import java.util.Arrays;

public class BubbleSorting {

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
        int n = arr.length, d;
        for (int i = 0; i < n - 1; i++) {
            boolean changed = false;

            //最后i个元素已经是排序好的，不需要比较
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    changed = true;
                    d = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = d;
                }

            }
            if (!changed) break;//优化：未排序部分已经是排序好的了，不需要再比较
        }
    }

}
