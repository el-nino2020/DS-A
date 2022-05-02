package data_structures.tree;


import java.util.*;

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
        long start = System.currentTimeMillis();

        BinarySortTree bst = new BinarySortTree();
        HashSet<Integer> set = new HashSet<>();
        int n = 2000000;

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

        long end = System.currentTimeMillis();
        System.out.println("对容量上限为" + n + "的BST进行处理和检验花费" + (end - start) + "毫秒");
    }


    /**
     * 如果加入的数据是随机分布的，int t = (int) (Math.random() * n * 5);
     * 那么效率与BST类似
     * 尝试加入单调的数据，再与BST比较，如 int t =i；
     * 那么AVL运行效率很高，而BST会产生java.lang.StackOverflowError
     */
    @org.junit.Test
    public void testAVLMap() {
        long start = System.currentTimeMillis();


        AVLTreeMap avl = new AVLTreeMap();
        HashSet<Integer> set = new HashSet<>();
        int n = 20000;

        for (int i = 0; i < n; i++) {
            int t = (int) (Math.random() * n * 5);
            avl.add(t);
            set.add(t);
            if (!avl.isAVL()) {//与BST比较运行速度时不应该加入该语句
                System.out.println("没有自平衡，这不是一棵AVL");
                return;
            }
        }
        List<Integer> list = avl.inOrder();
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
            if (!avl.exists(integer)) {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "添加成功" : "添加失败");


        for (int i = 0; i < n / 2; i++) {
            int t = (int) (Math.random() * n * 5);
            set.remove(t);
            avl.remove(t);
            if (!avl.isAVL()) {//与BST比较运行速度时不应该加入该语句
                System.out.println("没有自平衡，这不是一棵AVL");
                return;
            }
        }

        list = avl.inOrder();
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
            if (!avl.exists(integer)) {
                flag = false;
                break;
            }
        }
        //将所有节点删光
        for (Integer integer : set) {
            avl.remove(integer);
            if (!avl.isAVL()) {//与BST比较运行速度时不应该加入该语句
                System.out.println("没有自平衡，这不是一棵AVL");
                return;
            }
        }

        if (avl.inOrder().size() != 0) flag = false;

        System.out.println(flag ? "删除成功" : "删除失败");

        long end = System.currentTimeMillis();
        //可以同时运行testBST方法进行比较
        System.out.println("对容量上限为" + n + "的AVL进行处理和检验花费" + (end - start) + "毫秒");
    }


    @org.junit.Test
    public void testAVLList() {
        int opCount = 5;//  AVLTreeList支持的List方法数量
        int n = 30000;

        ArrayList<Integer> arrayList = new ArrayList<>();
        AVLTreeList<Integer> AVLList = new AVLTreeList<>();
        ArrayList<String> record = new ArrayList<>();//记录所有行为

        boolean control = true;
        for (int i = 0; i < n; i++) {
            double d = Math.random();

            if (control && d < 1 / (double) opCount) {//add(t)
                int val = (int) (Math.random() * n);
                arrayList.add(val);
                AVLList.add(val);
                record.add(String.format("list.add(%d);", val));
            } else if (control && d < 2 / (double) opCount) {//get
                int size = arrayList.size();
                if (size == 0) continue;

                int index = (int) (Math.random() * size);

                Integer i1 = arrayList.get(index);
                Integer i2 = AVLList.get(index);
                record.add(String.format("list.get(%d);", index));


                if (!i1.equals(i2)) {
                    System.out.println("AVL 实现 List失败");
                    return;
                }
            } else if (control && d < 3 / (double) opCount) {//set
                int size = arrayList.size();
                if (size == 0) continue;

                int index = (int) (Math.random() * size);
                int val = (int) (Math.random() * n);
                arrayList.set(index, val);
                AVLList.set(index, val);
                record.add(String.format("list.set(%d,  %d);", index, val));

            } else if (control && d < 4 / (double) opCount) {//add(index, t)
                int size = arrayList.size();
                int index = (int) (Math.random() * size);
                int val = (int) (Math.random() * n);
                arrayList.add(index, val);
                AVLList.add(index, val);
                record.add(String.format("list.add( %d,  %d);", index, val));
            } else if (control && d < 5 / (double) opCount) {//remove(index, t)
                int size = arrayList.size();
                if (size == 0) continue;
                int index = (int) (Math.random() * size);

                Integer i1 = arrayList.remove(index);
                Integer i2 = AVLList.remove(index);

                record.add(String.format("list.remove(%d);", index));

                if (!i1.equals(i2)) {
                    System.out.println("AVL 实现 List失败");
                    return;
                }

            }

            List<Integer> tempList = AVLList.getList();

            //校验方法是否成功实现
            if (!arrayList.equals(tempList)) {
                System.out.println("AVL 实现 List失败");
                for (String s : record) {
                    System.out.println(s);
                }
                return;
            }

            if (!AVLList.isAVL()) {
                System.out.println("AVL 自平衡失败");
                for (String s : record) {
                    System.out.println(s);
                }
                return;
            }


        }

        System.out.println("AVL 实现 List成功");

    }

    //人工测试某个数据结构
    @org.junit.Test
    public void manualTest() {
        AVLTreeList<Integer> list = new AVLTreeList<>();

        list.add(0, 9372);
        System.out.println(list.getList());

        list.add(0, 7428);
        System.out.println(list.getList());

        list.add(1, 1770);
        System.out.println(list.getList());

        list.add(1, 15899);
        System.out.println(list.getList());

//        ArrayList<Integer> integers = new ArrayList<>();
//        integers.remove(1)
    }

}
