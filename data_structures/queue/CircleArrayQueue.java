package data_structures.queue;


/**
 * 数环形组模拟队列
 * 该队列容量固定
 */
@SuppressWarnings({"unused"})
public class CircleArrayQueue {
    private int front = 0;//front + 1指向第一个数据
    private int rear = 0;//rear指向最后一个数据
    private int capacity;
    private int[] arr;

    public CircleArrayQueue(int capacity) {
        this.capacity = capacity ;
        arr = new int[capacity + 1];//总是空出一个存储空间以实现环形队列

    }

    /**
     * 向队尾添加数据
     *
     * @param val
     * @return 添加成功返回true, 否则为false
     */
    public boolean add(int val) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % capacity;
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
        front = (front + 1) % capacity;
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
        return arr[(front + 1) % capacity];
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public int size() {
        return (rear + capacity - front) % capacity;
    }

    public void show() {
        System.out.print(size() + ": ");
        for (int i = 1; i <= size(); i++) {
            System.out.print(arr[(front + i) % capacity] + "\t");
        }
        System.out.println();
    }


    public int capacity() {
        return capacity ;//返回的为初始化时输入的容量
    }

}

