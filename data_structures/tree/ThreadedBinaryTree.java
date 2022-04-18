package data_structures.tree;

public class ThreadedBinaryTree {

    public static class Node {
        int val;
        Node left;
        Node right;

        /**
         * 0:左节点是一棵树，1:左节点指向前驱节点
         */
        int leftType;

        /**
         * 0:右节点是一棵树，1:右节点指向后继节点
         */
        int rightType;

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

    /**
     * 指向线索化当前节点时的前驱节点
     */
    private Node prev;

    private Node root;

    public ThreadedBinaryTree() {
    }

    public ThreadedBinaryTree(Node root) {
        this.root = root;
    }

    /**
     * 中序线索化
     *
     * @param node 当前进行线索化的节点
     */
    private void inOrderThreading(Node node) {
        if (node == null) return;
        inOrderThreading(node.left);

        //设置上一个线索化节点的后继节点
        if (prev != null && (prev.right == null || prev.rightType == 1)) {
            prev.right = node;
            prev.rightType = 1;
        }
        //设置当前线索化节点的前驱节点
        if (node.left == null || node.leftType == 1) {//判断leftType == 1是考虑到该二叉树之前已经被线索化了
            node.left = prev;
            node.leftType = 1;
        }
        prev = node;//对下一个线索化的节点来说，当前节点为上一个线索化的节点

        inOrderThreading(node.right);
    }

    /**
     * 对二叉树进行线索化
     *
     * @param type 1: 前序; 2: 中序; 3: 后序
     */
    public void threading(int type) {
        prev = null;//prev在每次线索化时都要置空，这是写这个方法的主要目的
        switch (type) {
            case 1:
                break;
            case 2:
                inOrderThreading(root);
                break;
            case 3:
                break;
            default:
                throw new RuntimeException("线索化类型输入错误，无事发生~~~");
        }
    }


}
