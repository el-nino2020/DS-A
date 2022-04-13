package data_structures.queue;

import java.util.Scanner;

@SuppressWarnings({"unused"})
public class Test {
    public static void main(String[] args) {
        testArrayQueue();
    }

    public static void testArrayQueue() {
        ArrayQueue queue = new ArrayQueue(5);

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
}
