package algorithms.sort;

public class QuickSort {
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
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = arr[right];
        int temp, i = left;

        //循环的目的: 不断地将比pivot小的元素交换到数组的前面
        //j == i时, 如果arr[i] < pivot, 那么pivot应该放在索引为 i + 1 处
        for (int j = right - 1; j >= i; --j) {
            if (arr[j] < pivot) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;

                ++i;//i的改变是被动的，受当前if语句的影响才会改变

                ++j;//由于不知道交换到arr[j]的元素是否比pivot小，所以需要再次比较
            }
        }

        arr[right] = arr[i];
        arr[i] = pivot;

        sort(arr, left, i - 1);
        sort(arr, i + 1, right);
    }


}
