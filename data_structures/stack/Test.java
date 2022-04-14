package data_structures.stack;

import java.util.Scanner;

@SuppressWarnings({"unused"})
public class Test {
    public static void main(String[] args) {
        testStack();
    }

    public static void testStack() {
        Stack stack = new Stack(3);

        /*
        1:显示栈
        2 x:将x加入栈
        3：pop
        */

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    stack.show();
                    break;
                case "2":
                    System.out.println(stack.push(scanner.nextInt()));
                    break;
                case "3":
                    System.out.println(stack.pop());
            }
        }
    }


}
