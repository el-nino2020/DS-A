package data_structures.tree;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Test {

    /**
     * 二叉树的前序、中序、后序遍历
     */
    @org.junit.Test
    public void testTraverse() {
        BinaryTree.Node root = new BinaryTree.Node(1);
        root.left = new BinaryTree.Node(2,
                new BinaryTree.Node(4), new BinaryTree.Node(5));
        root.right = new BinaryTree.Node(3,
                new BinaryTree.Node(6), new BinaryTree.Node(7));

        /*
                1
               / \
              2   3
             / \ / \
            4  5 6  7        */

        System.out.println("\npre order");
        BinaryTree.preOrder(root);

        System.out.println("\nin order");
        BinaryTree.inOrder(root);

        System.out.println("\npost order");
        BinaryTree.postOrder(root);

    }

    /**
     * 查找二叉树中的节点
     */
    @org.junit.Test
    public void testSearch() {
        BinaryTree.Node root = new BinaryTree.Node(1);
        root.left = new BinaryTree.Node(2,
                new BinaryTree.Node(4), new BinaryTree.Node(5));
        root.right = new BinaryTree.Node(3,
                new BinaryTree.Node(6), new BinaryTree.Node(7));

        boolean flag = true;
        for (int i = -10; i <= 10; i++) {
            if (1 <= i && i <= 7) {//应该找到
                if (BinaryTree.search(root, i) == null) {
                    flag = false;
                    break;
                }
            } else {//不应该找到
                if (BinaryTree.search(root, i) != null) {
                    flag = false;
                    break;
                }
            }
        }
        System.out.println(flag ? "查找成功" : "查找失败");

    }

    /**
     * 对顺序存储二叉树进行遍历
     */
    @org.junit.Test
    public void testArrayBinaryTreePreOrder() {
        BinaryTree.Node root = new BinaryTree.Node(1);
        root.left = new BinaryTree.Node(2,
                new BinaryTree.Node(4), new BinaryTree.Node(5));
        root.right = new BinaryTree.Node(3,
                new BinaryTree.Node(6), new BinaryTree.Node(7));
        System.out.println("答案：");
        BinaryTree.preOrder(root);
        System.out.println();

        System.out.println("待测试方法的回答：");
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        BinaryTree.preOrder(arr, 0);
    }

    @org.junit.Test
    public void testThreading() {
        ThreadedBinaryTree.Node root = new ThreadedBinaryTree.Node(1);
        root.left = new ThreadedBinaryTree.Node(2,
                new ThreadedBinaryTree.Node(4), new ThreadedBinaryTree.Node(5));
        root.right = new ThreadedBinaryTree.Node(3,
                new ThreadedBinaryTree.Node(6), new ThreadedBinaryTree.Node(7));

        /*
        该二叉树中序遍历的结果为：
        4	2	5	1	6	3	7
        用debug功能查看是否线索化成功
         */
        ThreadedBinaryTree tree = new ThreadedBinaryTree(root);
        tree.threading(2);

        System.out.println("线索化完成");
        System.out.println("遍历的结果为：");
        tree.threadedInOrder();

    }

    @org.junit.Test
    public void testCreateHuffmanTree() {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree.Node tree = HuffmanTree.createHuffmanTree(arr);
        /*
        权值在树中各层的相对位置应该如下：（忽略非叶节点）
        29
        7 8  13
        6
        1 3
         */

        HuffmanTree.levelOrder(tree);
    }

    @org.junit.Test
    public void testBST() {
        BinarySortTree bst = new BinarySortTree();
        HashSet<Integer> set = new HashSet<>();
        int n = 2000;

        for (int i = 0; i < n; i++) {
            int t = (int) (Math.random() * n * 5);
            bst.add(t);
            set.add(t);
        }
        List<Integer> list = bst.inOrder();
        boolean flag = true;

        //利用中序遍历BST的结果是升序的这一性质验证BST构建的正确性
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                flag = false;
                break;
            }
        }
        //查看是否添加成功
        for (Integer integer : set) {
            if (!bst.exists(integer)) {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "添加成功" : "添加失败");


        for (int i = 0; i < n / 2; i++) {
            int t = (int) (Math.random() * n * 5);
            set.remove(t);
            bst.remove(t);
        }

        list = bst.inOrder();
        flag = true;

        //删除后BST的性质是否依旧？
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                flag = false;
                break;
            }
        }
        //查看删除是否成功
        for (Integer integer : set) {
            if (!bst.exists(integer)) {
                flag = false;
                break;
            }
        }
        //将所有节点删光
        for (Integer integer : set) {
            bst.remove(integer);
        }

        if (bst.inOrder().size() != 0) flag = false;

        System.out.println(flag ? "删除成功" : "删除失败");

    }

}
