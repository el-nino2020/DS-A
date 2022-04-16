package algorithms.search;

public class BinarySearch {
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
     * @param arr 目标数组
     * @param val 要查找的值
     * @return val在arr中的索引，如果不存在，返回-1
     */
    public static int search(int[] arr, int val) {
        return search(arr, val, 0, arr.length - 1);
    }


    /**
     * 递归实现二分查找
     *
     * @param arr 目标数组
     * @param val 要查找的值
     * @return val在arr中的索引，如果不存在，返回-1
     */
    public static int search(int[] arr, int val, int left, int right) {
        if (left > right) return -1;

        int mid = (left + right) / 2;

        if (arr[mid] < val) {
            return search(arr, val, mid + 1, right);
        } else if (arr[mid] > val) {
            return search(arr, val, left, mid - 1);
        } else {
            return mid;
        }
    }


    /**
     * 非递归实现二分查找
     *
     * @param arr 目标数组
     * @param val 要查找的值
     * @return val在arr中的索引，如果不存在，返回-1
     */
    public static int search(int val, int[] arr) {
        //这个方法留待以后实现
        return 0;
    }
}
