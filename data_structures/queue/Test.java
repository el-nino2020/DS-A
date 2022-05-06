package data_structures.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings({"unused"})
public class Test {
    public static void main(String[] args) {

    }

    @org.junit.Test
    public void testArrayQueue() {
        ArrayQueue queue = new ArrayQueue(3);

        /*
        1:显示队列
        2 x:将x加入队列
        3：显示队列首元素
        4: 删除队列首元素

        */

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    queue.show();
                    break;
                case "2":
                    int i = scanner.nextInt();
                    System.out.println(queue.add(i));
                    break;
                case "3":
                    System.out.println(queue.peek());
                    break;
                case "4":
                    System.out.println(queue.get());
            }
        }
    }


    @org.junit.Test
    public void testCircleArrayQueue() {
        CircleArrayQueue queue = new CircleArrayQueue(3);

        /*
        1:显示队列
        2 x:将x加入队列
        3：显示队列首元素
        4: 删除队列首元素
        */

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    queue.show();
                    break;
                case "2":
                    int i = scanner.nextInt();
                    System.out.println(queue.add(i));
                    break;
                case "3":
                    System.out.println(queue.peek());
                    break;
                case "4":
                    System.out.println(queue.get());
            }
        }
    }

    @org.junit.Test
    public void testGenericQueue() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Queue<Integer> queue = new Queue<Integer>(10);
        ArrayList<String> record = new ArrayList<>();

        int ops = 4;//支持的操作数
        int n = 1000000;
        for (int i = 0; i < n; i++) {
            int val = (int) (Math.random() * n);
            double d = Math.random();
            if (d < 1 / (double) ops) {//addLast
                queue.addLast(val);
                deque.addLast(val);
                record.add(String.format("queue.addLast(%d);", val));
            } else if (d < 2 / (double) ops) {//pollFirst
                Integer i1 = queue.pollFirst();
                Integer i2 = deque.pollFirst();
                record.add("queue.pollFirst();");

                if (i1 == null && i2 == null) {
                    continue;
                } else if (!i2.equals(i1)) {
                    System.out.println("队列实现失败");
                    return;
                }
            } else if (d < 3 / (double) ops) {//peek
                Integer i1 = queue.peek();
                Integer i2 = deque.peek();
                record.add("queue.peek();");

                if (i1 == null && i2 == null) {
                    continue;
                } else if (!i2.equals(i1)) {
                    System.out.println("队列实现失败");
                    return;
                }
            } else if (d < 4 / (double) ops) {//size
                int size1 = queue.size();
                int size2 = deque.size();
                record.add("queue.size();");

                if (size1 != size2) {
                    System.out.println("队列实现失败");
                    return;
                }
            }

            Object[] objects1 = deque.toArray();
            Object[] objects2 = queue.toArray();
            if (!Arrays.equals(objects1, objects2)) {
                System.out.println("队列实现失败");
                return;
            }
        }

        System.out.println("实现成功");
    }
}
