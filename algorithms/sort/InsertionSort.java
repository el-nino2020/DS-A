package algorithms.sort;

public class InsertionSort {
    public static void main(String[] args) {
        int n = 5;
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
        for (int i = 1; i < n; i++) {
            int j = i - 1;//从后往前遍历有序表的索引
            int val = arr[i];//待插入的元素
            while (j >= 0 && val < arr[j]) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = val;
        }
    }
}
