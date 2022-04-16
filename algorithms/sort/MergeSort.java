package algorithms.sort;

public class MergeSort {
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
        sort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    /**
     * 将arr的[left, right]部分排序
     *
     * @param temp merge时使用的临时数组，为了节省空间，使用同一个数组
     */
    public static void sort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        sort(arr, left, mid, temp);
        sort(arr, mid + 1, right, temp);
        //现在arr[left, mid]和arr[mid + 1, right]是两个排序好的子序列

        //i是arr[left, mid]的索引, j是arr[mid + 1, right]的索引
        //t是temp的索引
        int i = left, j = mid + 1, t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            ++t;
        }

        //某一子序列可能剩下一部分，这时直接放到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            ++t;
            ++i;
        }
        while (j <= right) {
            temp[t] = arr[j];
            ++t;
            ++j;
        }

        t = 0;
        for (int k = left; k <= right; k++, t++) {
            arr[k] = temp[t];
        }
    }
}
