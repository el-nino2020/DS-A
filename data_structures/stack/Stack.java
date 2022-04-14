package data_structures.stack;

public class Stack {
    private int capacity;
    private int top = -1;//记录栈顶位置
    private int[] arr;//存放数据

    public Stack(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
    }

    public boolean push(int val) {
        if (isFull()) {//自动扩容
            int[] newArr = new int[capacity * 2];
            System.arraycopy(arr, 0, newArr, 0, capacity);
            arr = newArr;
            capacity *= 2;
        }
        ++top;
        arr[top] = val;
        return true;
    }

    public Integer pop() {
        if (isEmpty()) return null;
        Integer ans = arr[top];
        --top;
        return ans;
    }

    public Integer top() {
        if (isEmpty()) return null;
        return arr[top];
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void show() {
        System.out.print(size() + ": ");
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
