package data_structures.heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @param <E> 堆中存储的元素类型
 */
@SuppressWarnings({"unchecked"})
public class BinaryHeap<E> {
    /**
     * 数组形式存放堆中元素
     */
    private Object[] arr;

    /**
     * 决定堆中元素排序规则
     * 如果x是y的父节点，则comp.compare(x, y) <= 0
     */
    private Comparator<E> comp;

    private int size;

    private int capacity;

    /**
     * 创建一个空的容量为10的堆
     *
     * @param comp 节点比较规则
     */
    public BinaryHeap(Comparator<E> comp) {
        if (comp == null)
            throw new RuntimeException("参数不能为null");

        this.comp = comp;
        capacity = 10;
        arr = new Object[10];
    }

    public BinaryHeap(Object[] src, Comparator<E> comp) {
        if (src == null || comp == null)
            throw new RuntimeException("参数不能为null");

        //检查src中的元素的类型是否为E
        try {
            E temp;
            for (Object o : src) {
                temp = (E) o;
            }
        } catch (Exception e) {
            throw new RuntimeException("src中的元素的类型与泛型不一致");
        }

        arr = Arrays.copyOf(src, src.length * 2);
        this.comp = comp;
        size = src.length;
        capacity = src.length * 2;

        // 数组堆化
        // arr[size / 2 - 1]是第一个非叶节点
        for (int i = size / 2 - 1; i >= 0; i--) {
            sinkNode(i);
        }
    }

    public void add(E e) {
        grow();
        arr[size] = e;
        floatNode(size);
        ++size;
    }

    /**
     * 堆不为空则返回堆顶元素，否则返回null
     *
     * @return 堆不为空则返回堆顶元素，否则返回null
     */
    public E peek() {
        return isEmpty() ? null : (E) arr[0];
    }

    /**
     * 堆不为空则取出堆顶元素，否则返回null
     *
     * @return 堆不为空则取出堆顶元素并返回，否则返回null
     */
    public E poll() {
        if (isEmpty()) return null;
        Object ans = arr[0];

        arr[0] = arr[size - 1];
        sinkNode(0);
        size--;

        return (E) ans;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == capacity;
    }

    private boolean grow() {
        if (!isFull() || capacity == Integer.MAX_VALUE) return false;

        //防止整数溢出
        capacity = capacity > (Integer.MAX_VALUE >> 1) ?
                Integer.MAX_VALUE : capacity << 1;

        arr = Arrays.copyOf(arr, capacity);

        return true;
    }


    /**
     * 令index的子节点为x,y,如果两者都存在,则x = 2 * index +1, y = 2 * index + 2
     * 只要comp.compare(index, {x, y}) > 0,index就要下沉
     *
     * @param index 该节点在arr中的索引
     */
    private void sinkNode(int index) {
        Object temp;
        while (index * 2 + 1 <= size - 1) {//该节点有至少一个子节点
            E left = (E) arr[index * 2 + 1];
            //考虑到index的右子节点可能为null
            E right = index * 2 + 2 >= size ?
                    null : (E) arr[index * 2 + 2];

            int leftCmp = comp.compare((E) arr[index], left);
            int rightCmp = right == null ? -1 : comp.compare((E) arr[index], right);

            //该节点比它的子节点都要大，没必要交换了
            if (leftCmp <= 0 && rightCmp <= 0) {
                break;
            }

            if (right == null || comp.compare(left, right) < 0) {//左节点更大
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


    /**
     * 令index的父节点为x, 则 x = (index - 1) / 2
     * 只要comp.compare(index, x) < 0,index就要上浮
     *
     * @param index 该节点在arr中的索引
     */
    private void floatNode(int index) {
        Object temp;
        while (index != 0) {
            if (comp.compare((E) arr[index], (E) arr[(index - 1) / 2]) >= 0)//不用上浮了
                break;

            temp = arr[index];
            arr[index] = arr[(index - 1) / 2];
            arr[(index - 1) / 2] = temp;

            //index的父节点索引为 (index - 1) / 2
            index -= 1;
            index /= 2;
        }
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    boolean verifyHeap() {
        for (int i = 0; i <= size / 2 - 1; i++) {
            int left = comp.compare((E) arr[i], (E) arr[2 * i + 1]);
            int right = 2 * i + 2 >= size ? -1 : comp.compare((E) arr[i], (E) arr[2 * i + 2]);
            if (!(left <= 0 && right <= 0)) return false;
        }
        return true;
    }

}
