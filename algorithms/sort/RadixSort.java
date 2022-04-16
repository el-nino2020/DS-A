package algorithms.sort;

public class RadixSort {

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
        int maxBit = maxBits(arr);
        int[] buckets = new int[10];
        int t = 1;//用来辅助取出某一位的值
        int[] temp = new int[n];

        for (int i = 0; i < maxBit; i++, t *= 10) {
            for (int j = 0; j < n; j++) {
                buckets[arr[j] / t % 10]++;
            }

            //将buckets变为前缀和数组
            for (int j = 1; j < 10; j++) {
                buckets[j] += buckets[j - 1];
            }
            int x = 0;
            //重新排序
            for (int j = n - 1; j >= 0; j--) {
                int bit = arr[j] / t % 10;
                buckets[bit]--;
                temp[buckets[bit]] = arr[j];
            }

            //拷贝到原数组
            for (int j = 0; j < n; j++) {
                arr[j] = temp[j];
            }

            //为下一次循环做准备
            for (int j = 0; j < 10; j++) {
                buckets[j] = 0;
            }

        }

    }

    /**
     * @return arr数组中最大元素的(Dec)位数
     */
    public static int maxBits(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;

        for (int i : arr) {
            if (i > max) max = i;
        }

        return (max + "").length();
    }
}
