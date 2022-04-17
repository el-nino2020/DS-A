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
}
