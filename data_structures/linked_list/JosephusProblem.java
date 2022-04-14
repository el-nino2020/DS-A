package data_structures.linked_list;

import java.util.ArrayList;
import java.util.List;

public class JosephusProblem {
    public static void main(String[] args) {
        System.out.println(josephus(5, 1, 2));
    }

    /**
     * @param n 总共有n个人
     * @param k 从第k个人开始数
     * @param m 数到m的人出圈
     * @return 出圈的顺序
     */
    public static List<Integer> josephus(int n, int k, int m) {
        List<Integer> ans = null;
        if (n <= 0) return ans;
        ans = new ArrayList<>(n);

        Node first = new Node(1);
        Node t = first;

        for (int i = 2; i < n; i++) {
            t.next = new Node(i, t, null);
            t = t.next;
        }
        t.next = new Node(n, t, first);
        first.prev = t.next;//环形链表完成

        //从第k个人开始
        while (first.no != k) {
            first = first.next;
        }

        while (n != 1) {
            for (int i = 1; i < m; i++) {//往下数m-1次
                first = first.next;
            }
            Node nextStart = first.next;//下一次从他开始数1
            ans.add(first.no);
            --n;
            //删除这个节点
            first.prev.next = first.next;
            first.next.prev = first.prev;
            first = nextStart;
        }

        ans.add(first.no);
        return ans;
    }

    private static class Node {
        int no;
        Node prev;
        Node next;

        public Node(int no) {
            this.no = no;
        }

        public Node(int no, Node prev, Node next) {
            this.no = no;
            this.prev = prev;
            this.next = next;
        }
    }
}
