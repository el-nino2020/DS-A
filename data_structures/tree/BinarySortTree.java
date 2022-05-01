package data_structures.tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySortTree {
    public static class Node {
        int val;
        Node left;
        Node right;
//        Object item;//BST本质上实现了Map接口

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }
    }

    private Node root;

    protected Node getRoot() {
        return root;
    }

    public BinarySortTree() {
    }

    public BinarySortTree(Node root) {
        this.root = root;
    }

    /**
     * @return BST节点中最小值
     */
    public Integer min() {
        return min(root);
    }

    /**
     * @return 以node为根节点的BST中，节点中最小值
     */
    protected Integer min(Node node) {
        if (node == null) return null;
        //BST最左边的节点一定是值最小的
        while (node.left != null) node = node.left;
        return node.val;
    }


    /**
     * 将一个新节点加入BST中，其值为val
     */
    public void add(int val) {
        root = add(val, root);
    }

    /**
     * 向以node为根节点的BST中添加值为val的节点
     *
     * @return 添加后的BST的根节点
     */
    protected Node add(int val, Node node) {
        if (node == null) {
            return new Node(val);
        } else if (val < node.val) {
            node.left = add(val, node.left);
        } else if (val > node.val) {
            node.right = add(val, node.right);
        }
        //该BST不允许有重复的值
        return node;
    }

    /**
     * @return 值为val的节点是否存在
     */
    public boolean exists(int val) {
        return search(root, val) != null;
    }

    private Node search(Node node, int val) {
        if (node == null) {
            return null;
        } else if (node.val == val) {
            return node;
        } else if (val < node.val) {
            return search(node.left, val);
        } else {
            return search(node.right, val);
        }
    }

    /**
     * 删除值为val的节点，
     * 如果该节点不存在，则无事发生
     */
    public void remove(int val) {
        root = remove(root, val);
    }

    protected Node remove(Node node, int val) {
        if (node == null) {//删除的节点不存在
            return null;
        } else if (val < node.val) {
            node.left = remove(node.left, val);
        } else if (val > node.val) {
            node.right = remove(node.right, val);
        } else {//找到要删除的节点
            if (node.left == null && node.right == null) {//叶节点
                return null;
            } else if (node.left != null && node.right != null) {//有两个子节点
                Integer min = min(node.right);//找到右子树最小的值
                node.val = min;//该节点变为右子树中最小值的节点
                node.right = remove(node.right, min);//删除右子树中最小值的节点——它是多余的
            } else if (node.left != null) {//只有左节点非空
                return node.left;
            } else {//只有右节点非空
                return node.right;
            }
        }

        return node;
    }

    /**
     * @return 集合中存储中序遍历的结果
     */
    public List<Integer> inOrder() {
        ArrayList<Integer> ans = new ArrayList<>();
        inOrder(root, ans);
        return ans;
    }

    private void inOrder(Node node, List<Integer> record) {
        if (node == null) return;
        inOrder(node.left, record);
        record.add(node.val);
        inOrder(node.right, record);
    }
}
