package data_structures.stack;

import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {
    public static void main(String[] args) {
        System.out.println(view(null));
    }

    /**
     * 用来和用户进行交互
     *
     * @param exp 为null则接收用户输入，否则使用该表达式
     * @return 计算的结果
     */
    public static Integer view(String exp) {
        String expression = null;
        if (exp == null) {
            System.out.print("输入逆波兰表达式：");
            expression = new Scanner(System.in).nextLine();
        } else {
            expression = exp;
        }

        if (!expression.matches("^[\\d\\+\\-*/\\s]*$")) {
            System.out.println("表达式有误");
            return null;
        }
        return calculate(expression);
    }

    /**
     * 计算逆波兰表达式
     *
     * @param expression 逆波兰表达式
     * @return 计算的结果
     */
    private static Integer calculate(String expression) {
        Stack<Integer> stk = new Stack<>();
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
                stk.push(num);
                i = j - 1;//保证下一次遍历的正确性

            } else {//操作符
                Integer v2 = stk.pop();
                Integer v1 = stk.pop();
                Integer ans = null;
                switch (c) {
                    case '+':
                        ans = v1 + v2;
                        break;
                    case '-':
                        ans = v1 - v2;
                        break;
                    case '*':
                        ans = v1 * v2;
                        break;
                    case '/':
                        ans = v1 / v2;
                        break;
                    default:
                        throw new RuntimeException("未知操作符");
                }
                stk.push(ans);
            }
        }

        if (stk.size() != 1) {
            System.out.println("表达式有误");
            return null;
        }

        return stk.pop();
    }
}
