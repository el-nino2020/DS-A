package data_structures.tree;


import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class HuffmanTree {
    //实现Comparable接口是为了方便排序
    public static class Node implements Comparable<Node> {
        int val;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    /**
     * @param arr arr中的每个元素代表一个权值
     * @return 赫夫曼树的根节点
     */
    public static Node createHuffmanTree(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        int n = arr.length;

        //也可以自己实现一个堆，来代替java.util.PriorityQueue
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(new Node(arr[i]));
        }

        while (pq.size() != 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node node = new Node(left.val + right.val, left, right);
            pq.add(node);
        }

        return pq.poll();
    }

    /**
     * 对赫夫曼树进行层序遍历：
     * 打印叶节点的值（权值），但用 * 代替非叶节点的值。
     * 对于赫夫曼树来说，重点在于权值在树的哪一层，
     *  而权值和 * 的相对关系是无所谓的
     * @param node 赫夫曼树的根节点
     */
    public static void levelOrder(Node node) {
        if (node == null) return;
        ArrayDeque<Node> deque = new ArrayDeque<>();

        deque.addLast(node);
        while (deque.size() != 0) {
            int l = deque.size();
            for (int i = 0; i < l; i++) {
                Node cur = deque.pollFirst();
                if (cur.left != null) {
                    System.out.print("*\t");
                    deque.addLast(cur.left);
                    deque.addLast(cur.right);
                } else {
                    System.out.print(cur.val + "\t");
                }
            }
            System.out.println();
        }

    }

}
