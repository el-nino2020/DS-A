package data_structures.tree;

import java.util.List;

public class BinaryTree {
    public static class Node {
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
    }

    Node root;

    public BinaryTree() {
    }

    /**
     * 输出前序遍历的结果
     *
     * @param node 以node为根节点的树
     */
    public static void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.val + "\t");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 输出中序遍历的结果
     *
     * @param node 以node为根节点的树
     */
    public static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.val + "\t");
        inOrder(node.right);
    }

    /**
     * 输出后序遍历的结果
     *
     * @param node 以node为根节点的树
     */
    public static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + "\t");

    }

    /**
     * 查找二叉树中是否存在值为val的节点,
     * 如果找到，返回该节点，否则返回null
     */
    public static Node search(Node node, int val) {
        //以中序遍历为例来实现，其实三种遍历方式都差不多
        if (node == null) return null;

        if (node.val == val) return node;

        Node node1 = search(node.left, val);
        if (node1 != null) return node1;

        Node node2 = search(node.right, val);
        if (node2 != null) return node2;

        return null;
    }

    /**
     * 对二叉树进行前序遍历
     *
     * @param arr     以数组形式存储的二叉树
     * @param current 当前节点的索引
     */
    public static void preOrder(int[] arr, int current) {
        if (current >= arr.length || arr == null) return;

        System.out.print(arr[current] + "\t");

        preOrder(arr, 2 * current + 1);
        preOrder(arr, 2 * current + 2);
    }
}
