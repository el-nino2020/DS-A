package data_structures.linked_list;

import java.util.Scanner;

public class Test {
    private static Scanner scanner = new Scanner(System.in);
    private static String choice;

    public static void main(String[] args) {
        testSingleLinkedList();
    }

    public static void testSingleLinkedList() {
        /*
                1:打印链表
                2 x:添加x到链表末尾
                3 x y:将第一个值为x的节点的值改为y
                4 x:删除第一个值为x的节点

         */
        SingleLinkedList sll = new SingleLinkedList();
        while (true) {
            choice = scanner.next();
            switch (choice) {
                case "1":
                    sll.show();
                    break;
                case "2":
                    System.out.println(sll.add(scanner.nextInt()));
                    break;

                case "3":
                    System.out.println(sll.replace(scanner.nextInt(), scanner.nextInt()));
                    break;

                case "4":
                    System.out.println(sll.remove(scanner.nextInt()));
                    break;
            }
        }
    }
}


