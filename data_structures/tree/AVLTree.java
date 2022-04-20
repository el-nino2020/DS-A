package data_structures.tree;

import data_structures.tree.BinarySortTree;

public class AVLTree extends BinarySortTree {

    public static class AVLNode extends Node {
        public AVLNode() {
        }

        public AVLNode(int val) {
            super(val);
        }

        int height = 1;//设置该属性，而不是在每次需要高度信息时才递归地查找，能提高效率
    }


    public AVLTree() {
    }

    public AVLTree(AVLNode root) {
        super(root);
    }

    /**
     * 返回当前节点的高度，如果节点为空则返回0
     */
    private int height(AVLNode node) {
        //这个函数的功能还是简化代码
        return node == null ? 0 : node.height;
    }


    /**
     * @param node 需要左转的树的根节点
     * @return 左转后新的根节点
     */
    private AVLNode leftRotate(AVLNode node) {
        AVLNode x = node, y = (AVLNode) x.right, t2 = (AVLNode) y.left;

        y.left = x;
        x.right = t2;
        //更新x的高度是更新y高度的前提，因为x是y的子节点
        x.height = Math.max(height((AVLNode) x.left), height((AVLNode) x.right)) + 1;
        y.height = Math.max(height((AVLNode) y.left), height((AVLNode) y.right)) + 1;

        return y;
    }

    /**
     * @param node 需要右转的树的根节点
     * @return 右转后新的根节点
     */
    private AVLNode rightRotate(AVLNode node) {
        AVLNode y = node, x = (AVLNode) y.left, t2 = (AVLNode) x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height((AVLNode) y.left), height((AVLNode) y.right)) + 1;
        x.height = Math.max(height((AVLNode) x.left), height((AVLNode) x.right)) + 1;

        return x;
    }


    /**
     * 如果以node为根节点的AVL树不平衡，则对它进行再平衡
     * 否则无事发生
     */
    private AVLNode balance(AVLNode node) {
        int diff = height((AVLNode) node.left) - height((AVLNode) node.right);
        if (Math.abs(diff) <= 1)//不需要再平衡
            return node;

        if (diff > 1) {//左子树高
            if (node.left != null && //将left right转化为left left
                    height((AVLNode) node.left.right) > height((AVLNode) node.left.left)) {
                node.left = leftRotate((AVLNode) node.left);
            }
            //left left
            node = rightRotate(node);

        } else {//右子树高
            if (node.right != null &&//将right left转化为right right
                    height((AVLNode) node.right.left) > height((AVLNode) node.right.right)) {
                node.right = rightRotate((AVLNode) node.right);
            }
            //right right
            node = leftRotate(node);
        }

        return node;
    }


    //重写BST中的方法add(int, AVLNode)，为此需要将父类的方法的访问权限改为protected
    //重写后，BST中的add(int)方法可以通过动态绑定使用AVL的add(int, AVLNode)方法
    //又复习了一下java基础
    /**
     * 向以node为根节点的AVL中添加值为val的节点,
     * 添加完会进行自平衡操作，并且更新添加节点的所有父节点的高度
     *
     * @return 添加后的AVL的根节点
     */
    @Override
    protected Node add(int val, Node node) {
        if (node == null) {
            return new AVLNode(val);
        } else if (val < node.val) {
            node.left = add(val, node.left);
        } else if (val > node.val) {
            node.right = add(val, node.right);
        }
        //插入节点后，更新其所有父节点的高度
        ((AVLNode) node).height = Math.max(height((AVLNode) node.left), height((AVLNode) node.right)) + 1;

        node = balance((AVLNode) node);

        return node;
    }


    //和本类的add方法一样，会被动态绑定机制调用到
    /**
     * 从以node为根节点的AVL中尝试删除值为val的节点,
     * 删除完会进行自平衡操作
     * 如果节点不存在，则无事发生
     */
    @Override
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

        node = balance((AVLNode) node);

        return node;
    }


    /**
     * @return 这棵树是否具有AVL的性质
     */
    boolean isAVL() {
        return isAVL((AVLNode) getRoot());
    }


    /**
     * 检验以node为根节点的树是否为一棵AVL树
     */
    private boolean isAVL(AVLNode node) {
        if (node == null) return true;
        //node是一棵AVL树
        return (Math.abs(height((AVLNode) node.left) - height((AVLNode) node.right)) <= 1)
                && isAVL((AVLNode) node.left) //它的子树也是AVL树
                && isAVL((AVLNode) node.right);
    }

}
