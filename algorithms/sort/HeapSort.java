package algorithms.sort;

public class HeapSort {

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
        int n = arr.length, temp;
        //构建大顶堆
        // arr[n / 2 - 1]是第一个非叶节点
        for (int i = n / 2 - 1; i >= 0; i--) {
            sinkNode(arr, i, n);
        }

        //开始构建有序表
        for (int i = n - 1; i >= 0; i--) {
            //将堆顶元素交换到当前堆的最后——增加有序表的元素
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            sinkNode(arr, 0, i);
        }
    }

    /**
     * 只要index代表的节点的值比它的子节点要小，就一直把它往下交换
     *
     * @param arr      可视作顺序存储的二叉树
     * @param index    该节点在arr中的索引
     * @param heapSize arr[0, heapSize - 1]表示一个大顶堆
     */
    private static void sinkNode(int arr[], int index, int heapSize) {
        int temp;
        while (index * 2 + 1 <= heapSize - 1) {//该节点有子节点
            int left = arr[index * 2 + 1];
            //考虑到index的右子节点可能为null
            int right = index * 2 + 2 >= heapSize ?
                    Integer.MIN_VALUE : arr[index * 2 + 2];
            //该节点比它的子节点都要大，没必要交换了
            if (Math.max(left, right) <= arr[index]) {
                break;
            }

            if (left >= right) {//左节点更大
                temp = arr[index];
                arr[index] = arr[index * 2 + 1];
                arr[index * 2 + 1] = temp;
                index = index * 2 + 1;
            } else {//右节点更大
                temp = arr[index];
                arr[index] = arr[index * 2 + 2];
                arr[index * 2 + 2] = temp;
                index = index * 2 + 2;
            }
        }
    }
}
