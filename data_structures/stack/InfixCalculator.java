package data_structures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 功能与Calculator无异，但是可以输入括号
 * 重点是将中缀表达式转后缀表达式
 * 只能计算自然数的加减乘除!!!
 */
public class InfixCalculator {
    public static void main(String[] args) {
        System.out.print("输入(中缀)表达式: ");
        String infix = new Scanner(System.in).nextLine();
        if (!infix.matches("^[\\d\\+\\-*/\\s()]*$")) {
            System.out.println("输入有误");
        }
        System.out.println("对应的后缀表达式为:");
        String suffix = infixToSuffix(infixExpToElements(infix));
        System.out.println(suffix);

        System.out.print("计算结果为: ");
        System.out.println(RPNCalculator.view(suffix));
    }


    /**
     * @param infix 中缀表达式各个元素组成的集合
     * @return 后缀表达式
     */
    private static String infixToSuffix(List<String> infix) {
        StringBuilder ans = new StringBuilder();
        Stack<String> stk = new Stack<>();

        int n = infix.size();

        for (int i = 0; i < n; i++) {
            String ele = infix.get(i);
            //数字，直接存入ans
            if (Character.isDigit(ele.charAt(0))) {
                ans.append(ele);
                ans.append(" ");
            } else if (!ele.equals("(") && !ele.equals(")")) {
                //操作符
                if (stk.empty() || stk.peek().equals("(")) {
                    stk.push(ele);
                } else if (!priority(ele, stk.peek())) {
                    stk.push(ele);
                } else {
                    ans.append(stk.pop());
                    ans.append(" ");
                    i--;//再次比较
                    continue;
                }
            } else {//括号
                if (ele.equals("(")) {
                    stk.push(ele);
                } else {
                    String s;
                    while (!(s = stk.pop()).equals("(")) {
                        ans.append(s);
                        ans.append(" ");
                    }
                }
            }
        }

        while (stk.size() != 0) {
            ans.append(stk.pop());
            ans.append(" ");
        }

        return ans.toString();
    }

    /**
     * 将表达式中的各个元素取出，放入List中，方便转后缀时使用
     *
     * @param infix 中缀表达式
     */
    private static List<String> infixExpToElements(String infix) {
        ArrayList<String> ans = new ArrayList<>();

        int n = infix.length();

        for (int i = 0; i < n; i++) {
            char c = infix.charAt(i);

            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                int j = i + 1;
                while (j < n && Character.isDigit(infix.charAt(j))) {
                    ++j;
                }
                //当前数字
                ans.add(infix.substring(i, j));

                i = j - 1;//保证下一次遍历的正确性

            } else {//操作符和括号
                ans.add(c + "");
            }
        }

        return ans;
    }

    /**
     * @return a的优先级小于等于b，则返回true，否则为false
     */
    private static boolean priority(String a, String b) {
        if (a.equals("+") || a.equals("-")) return true;
        if (b.equals("*") || b.equals("/")) return true;
        return false;
    }
}
