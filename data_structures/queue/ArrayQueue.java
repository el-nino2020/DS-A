package data_structures.queue;

/**
 * 数组模拟队列
 * 该队列容量固定，且操作次数有限
 */
@SuppressWarnings({"unused"})
public class ArrayQueue {
    private int front = -1;//front + 1指向第一个数据
    private int rear = -1;//rear指向最后一个数据
    private int capacity;
    private int[] arr;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
    }

    /**
     * 向队尾添加数据
     *
     * @param val
     * @return 添加成功返回true, 否则为false
     */
    public boolean add(int val) {
        if (addable()) {
            return false;
        }
        rear++;
        arr[rear] = val;
        return true;
    }

    /**
     * 取出队首数据
     *
     * @return 队首数据存在则返回，否则返回null
     */
    public Integer get() {
        if (isEmpty()) {
            return null;
        }
        ++front;
        return arr[front];
    }

    /**
     * 查看队首数据，但不取出
     *
     * @return 队首数据存在则返回，否则返回null
     */
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return arr[front + 1];
    }

    //这个方法的名字不应该叫isFull，因为它并不判断size() == capacity
    public boolean addable() {
        return rear == capacity - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public int size() {
        return rear - front;
    }

    public void show() {
        System.out.print(size() + ": ");
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

}
