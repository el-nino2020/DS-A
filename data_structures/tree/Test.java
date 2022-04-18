package data_structures.tree;

public class Test {

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
}
