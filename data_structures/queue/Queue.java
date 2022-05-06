package data_structures.queue;

@SuppressWarnings({"unchecked"})
public class Queue<T> {
    private int front = 0;//front + 1指向队首数据
    private int rear = 0;//rear指向队尾数据
    private int capacity;
    private Object[] arr;

    public Queue(int capacity) {
        this.capacity = capacity;
        arr = new Object[capacity + 1];//总是空出一个存储空间以实现环形队列
    }

    /**
     * 向队尾添加数据
     *
     * @param val
     * @return 添加成功返回true, 否则为false
     */
    public boolean addLast(T val) {
        if (isFull()) {
            grow();
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
    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }
        front = (front + 1) % capacity;
        return (T) arr[front];
    }

    /**
     * 查看队首数据，但不取出
     *
     * @return 队首数据存在则返回，否则返回null
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) arr[(front + 1) % capacity];
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

    public int getCapacity() {
        return capacity;
    }

    private void grow() {
        int oldCapacity = capacity;
        int oldSize = size();
        int newCapacity = oldCapacity << 1;

        Object[] newArr = new Object[newCapacity + 1];
        for (int i = 0; i < oldSize; i++) {
            newArr[i + 1] = arr[(front + 1 + i) % oldCapacity];
        }

        arr = newArr;
        capacity = newCapacity;
        front = 0;
        rear = oldSize;
    }

    public Object[] toArray() {
        int size = size();
        Object[] ans = new Object[size];
        for (int i = 0; i < size; i++) {
            ans[i] = arr[(front + 1 + i) % capacity];
        }


        return ans;
    }

}

