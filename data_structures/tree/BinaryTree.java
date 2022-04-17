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
}
