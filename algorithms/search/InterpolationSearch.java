package algorithms.search;

public class InterpolationSearch {


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

    public static int search(int[] arr, int val) {
        return search(arr, val, 0, arr.length - 1);
    }

    public static int search(int[] arr, int val, int left, int right) {

        //由于mid受val影响，val不在arr[left, right]内会导致下标越界
        if (left > right || val < arr[left] || val > arr[right]) return -1;

        int mid = left + (val - arr[left]) * (right - left) / (arr[right] - arr[left]);

        if (arr[mid] < val) {
            return search(arr, val, mid + 1, right);
        } else if (arr[mid] > val) {
            return search(arr, val, left, mid - 1);
        } else {
            return mid;
        }
    }
}
