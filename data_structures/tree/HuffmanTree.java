package data_structures.tree;


import java.util.ArrayDeque;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @version 1.1 更新Node内部类的定义，将数据与权重区分开来，方便生成赫夫曼编码
 */
public class HuffmanTree {
    //实现Comparable接口是为了方便排序
    public static class Node implements Comparable<Node> {
        int weight;//节点的权重
        Object data;//节点的数据信息
        Node left;
        Node right;

        public Node() {
        }

        public Node(int weight) {
            this.weight = weight;
        }

        public Node(int weight, Node left, Node right) {
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        public Node(int weight, Object data, Node left, Node right) {
            this.weight = weight;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(int weight, Object data) {
            this.weight = weight;
            this.data = data;
        }

        public int getWeight() {
            return weight;
        }

        public Object getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
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
            Node node = new Node(left.weight + right.weight, left, right);
            pq.add(node);
        }

        return pq.poll();
    }

    /**
     * @param info 每一个键值对可视作一个节点，键代表data，值代表weight
     * @return 赫夫曼树的根节点
     * 该方法为@version 1.1 中新增添的
     */
    public static Node createHuffmanTree(Map<?, Integer> info) {
        if (info == null || info.isEmpty()) return null;

        Set<?> set = info.keySet();

        //也可以自己实现一个堆，来代替java.util.PriorityQueue
        PriorityQueue<Node> pq = new PriorityQueue<>();


        for (Object key : set) {
            pq.add(new Node(info.get(key), key));

        }

        while (pq.size() != 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node node = new Node(left.weight + right.weight, left, right);
            pq.add(node);
        }

        return pq.poll();
    }

    /**
     * 对赫夫曼树进行层序遍历：
     * 打印叶节点的值（权值），但用 * 代替非叶节点的值。
     * 对于赫夫曼树来说，重点在于权值在树的哪一层，
     * 而权值和 * 的相对关系是无所谓的
     *
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
                    System.out.print(cur.weight + "\t");
                }
            }
            System.out.println();
        }

    }

}
