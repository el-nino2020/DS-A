package data_structures.stack;

import java.util.Scanner;

/**
 * 能够计算自然数的加减乘除
 * 输入一个表达式，该类的main方法会输出结果
 */
public class Calculator {
    public static void main(String[] args) {
        Stack numStk = new Stack(20);
        Stack opStk = new Stack(20);

        System.out.print("输入表达式：");
        String expression = new Scanner(System.in).nextLine();

        if (!expression.matches("^[\\d\\+\\-*/\\s]*$")) {
            System.out.println("输入有误");
            return;
        }

        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);

            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                int j = i + 1;
                while (j < n && Character.isDigit(expression.charAt(j))) {
                    ++j;
                }
                //当前数字
                int num = Integer.parseInt(expression.substring(i, j));
                numStk.push(num);
                i = j - 1;//保证下一次遍历的正确性

            } else {//操作符
                if (opStk.isEmpty()) {
                    opStk.push(c);
                } else if (priority(c, (char) ((int) opStk.top()))) {
                    //c的优先级小于等于栈顶的运算符
                    int num = operate(numStk.pop(), numStk.pop(), opStk.pop());
                    numStk.push(num);
                    opStk.push(c);
                } else {
                    opStk.push(c);
                }
            }
        }

        while (opStk.size() != 0) {
            int num = operate(numStk.pop(), numStk.pop(), opStk.pop());
            numStk.push(num);
        }

        if (numStk.size() != 1) {
            System.out.println("表达式有误，无法算出结果");
            return;
        }

        System.out.println("结果为: " + numStk.pop());
    }

    /**
     * @return a的优先级小于等于b，则返回true，否则为false
     */
    private static boolean priority(char a, char b) {
        if (a == '+' || a == '-') return true;
        if (b == '*' || b == '/') return true;
        return false;
    }

    private static int operate(Integer v2, Integer v1, Integer op) {
        if (v1 == null || v2 == null || op == null) {
            throw new RuntimeException("表达式有误，无法算出结果");
        }
        char c = (char) (int) op;
        switch (c) {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            case '*':
                return v1 * v2;
            case '/':
                return v1 / v2;
            default:
                throw new RuntimeException("未知操作符");
        }
    }
}
