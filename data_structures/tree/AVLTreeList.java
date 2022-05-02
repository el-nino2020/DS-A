package data_structures.tree;

import javax.xml.bind.annotation.XmlIDREF;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 使用AVL实现List接口
 *
 * @param <T> 序列中存放的元素
 */
public class AVLTreeList<T> {
    /**
     * AVL树实现List接口的节点，与实现Map接口的节点不同
     *
     * @param <E> 节点中的元素类型
     */
    public static class Node<E> {
        E val;
        Node<E> left;
        Node<E> right;

        //以下的属性为Subtree Property，即这些属性与以该节点为根节点的子树相关
        //如果当前节点只是一个单独的节点，那么以下的属性从定义上来看也是没有意义的
        int height = 1;//高度为了方便实现自平衡
        int leftTreeSize;
        int rightTreeSize;

        public Node() {
        }

        public Node(E val) {
            this.val = val;
        }

        public Node(E val, Node<E> left, Node<E> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        /**
         * 返回当前节点的高度，如果节点为空则返回0
         */
        private static <G> int height(Node<G> node) {
            return node == null ? 0 : node.height;
        }

        /**
         * 返回当前节点的左子树中的节点总数，如果左子节点为空则返回0
         */
        private static <G> int leftTreeSize(Node<G> node) {
            return node == null ? 0 : node.leftTreeSize;
        }

        /**
         * 返回当前节点的右子树中的节点总数，如果右子节点为空则返回0
         */
        private static <G> int rightTreeSize(Node<G> node) {
            return node == null ? 0 : node.rightTreeSize;
        }

        /**
         * 返回以当前节点为根节点的树的节点总数，如果节点为空则返回0
         */
        private static <G> int size(Node<G> node) {
            return node == null ? 0 : 1 + leftTreeSize(node) + rightTreeSize(node);
        }
    }

    private Node<T> root;
    private int size;

    public AVLTreeList() {
    }


    /**
     * @param node 需要左转的树的根节点
     * @return 左转后新的根节点
     */
    private Node<T> leftRotate(Node<T> node) {
        Node<T> x = node, y = x.right, t2 = y.left;

        y.left = x;
        x.right = t2;

        //x是y的子节点,所以先更新x的subtree property
        updateSubtreeProperty(x);
        updateSubtreeProperty(y);

        return y;
    }


    /**
     * @param node 需要右转的树的根节点
     * @return 右转后新的根节点
     */
    private Node<T> rightRotate(Node<T> node) {
        Node<T> y = node, x = y.left, t2 = x.right;

        x.right = y;
        y.left = t2;

        //y是x的子节点,所以先更新y的subtree property
        updateSubtreeProperty(y);
        updateSubtreeProperty(x);

        return x;
    }

    /**
     * 如果以node为根节点的AVL树不平衡，则对它进行再平衡
     * 否则无事发生
     *
     * @param node 需要再平衡的根节点
     * @return 再平衡后新的根节点
     */
    private Node<T> balance(Node<T> node) {
        int diff = Node.height(node.left) - Node.height(node.right);
        if (Math.abs(diff) <= 1)//不需要再平衡
            return node;

        if (diff > 1) {//左子树高
            if (node.left != null && //将left right转化为left left
                    Node.height(node.left.right) > Node.height(node.left.left)) {
                node.left = leftRotate(node.left);
            }
            //left left
            node = rightRotate(node);

        } else {//右子树高
            if (node.right != null &&//将right left转化为right right
                    Node.height(node.right.left) > Node.height(node.right.right)) {
                node.right = rightRotate(node.right);
            }
            //right right
            node = leftRotate(node);
        }

        return node;
    }

    /**
     * 更新 node节点的subtree property
     */
    private void updateSubtreeProperty(Node<T> node) {
        if (node == null) return;

        node.height = Math.max(Node.height(node.left), Node.height(node.right)) + 1;
        node.leftTreeSize = Node.size(node.left);
        node.rightTreeSize = Node.size(node.right);
    }


    /**
     * 添加元素到序列最后
     * 时间复杂度为O(log(n))
     *
     * @param t 要添加的元素
     */
    public void add(T t) {
        add(size, t);
    }


    /**
     * 添加元素到指定索引，当前索引及其往后的元素会被后移
     *
     * @param index 要插入的索引
     * @param t     要插入的元素
     * @throws IndexOutOfBoundsException index的范围为 0 <= index && index <= size
     */
    public void add(int index, T t) throws IndexOutOfBoundsException {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        root = add(root, t, index);
        ++size;
    }


    /**
     * 在以node为根节点的二叉树中插入t，位置为中序遍历的第index个
     * 这个方法的实现参考了
     * https://www.nayuki.io/res/avl-tree-list/AvlTreeList.java
     * 中的insertAt方法
     *
     * @param node  待插入元素的二叉树的根节点
     * @param t     待插入的元素
     * @param index 对应新的元素在二叉树中序遍历的位置
     * @return 插入元素后二叉树的新的根节点
     */
    private Node<T> add(Node<T> node, T t, int index) {
        if (node == null) {
            return new Node<>(t);
        } else if (index <= Node.leftTreeSize(node)) {
            node.left = add(node.left, t, index);
        } else {
            node.right = add(node.right, t, index - Node.leftTreeSize(node) - 1);
        }
        updateSubtreeProperty(node);
        node = balance(node);
        return node;
    }

    /**
     * 递归实现添加元素到序列最后的操作
     *
     * @param node 插入到以node为根节点的子树的最右端
     * @param t    要插入的元素
     */
    @Deprecated
    private Node<T> add(Node<T> node, T t) {
        if (node == null) {
            return new Node<>(t);
        }
        node.right = add(node.right, t);
        updateSubtreeProperty(node);
        node = balance(node);

        return node;
    }

    /**
     * 根据索引删除元素，并返回该元素
     *
     * @param index 要删除的元素所在的索引
     * @return 删除的索引处的元素
     * @throws IndexOutOfBoundsException index的范围为 0 <= index && index < size
     */
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        T oldVal = get(index);
        root = remove(root, index);
        --size;

        return oldVal;
    }

    /**
     * 删除以node为根节点的树中中序遍历第index个的节点
     *
     * @param node  树的根节点
     * @param index 要删除的节点在中序遍历中的位置
     * @return 删除后新的根节点
     */
    private Node<T> remove(Node<T> node, int index) {
        if (index == Node.leftTreeSize(node)) {
            if (node.left != null && node.right != null) {//有两个子节点
                node.val = get(node.right, 0).val;//(右子树中的第一个节点，即第index + 1个元素)
                node.right = remove(node.right, 0);
            } else if (node.left != null) {//只有左子节点
                return node.left;
            } else if (node.right != null) {//只有右子节点
                return node.right;
            } else {//没有子节点——当前节点为叶子结点
                return null;
            }
        } else if (index < Node.leftTreeSize(node)) {
            node.left = remove(node.left, index);
        } else {
            node.right = remove(node.right, index - Node.leftTreeSize(node) - 1);
        }

        updateSubtreeProperty(node);
        node = balance(node);
        return node;
    }

    /**
     * 返回索引 index 处的元素
     * 时间复杂度为O(log(n))
     *
     * @param index 元素在List中的索引
     * @throws IndexOutOfBoundsException index越界
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return get(root, index).val;
    }

    /**
     * 将索引 index 处的元素设置为newVal
     * 时间复杂度为O(log(n))
     *
     * @param index 元素在List中的索引
     * @return index处原先的元素
     * @throws IndexOutOfBoundsException index越界
     */
    public T set(int index, T newVal) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> node = get(root, index);
        T ans = node.val;
        node.val = newVal;
        return ans;
    }

    /**
     * 返回在以node为根节点的二叉树中，中序遍历第index个的节点,
     * 时间复杂度为O(log(n))
     */
    private Node<T> get(Node<T> node, int index) {
        if (index == Node.leftTreeSize(node)) {
            return node;
        } else if (index < Node.leftTreeSize(node)) {
            return get(node.left, index);
        } else {
            return get(node.right, index - Node.leftTreeSize(node) - 1);
        }
    }


    /**
     * 顺序遍历每个元素
     *
     * @param consumer 每个节点传入consumer的accept(T)方法
     */
    public void forEach(Consumer<T> consumer) {
        inOrder(root, consumer, null);
    }


    /**
     * 从node开始进行中序遍历，每个节点作为参数传入consumer的accept(T)方法
     *
     * @param node     中序遍历的起始节点
     * @param consumer 每个节点传入consumer的accept(T)方法
     * @param list     为了方便getList()方法而设置的参数
     */
    private void inOrder(Node<T> node, Consumer<T> consumer, List<T> list) {
        if (node == null) return;
        inOrder(node.left, consumer, null);
        consumer.accept(node.val);
        inOrder(node.right, consumer, null);
    }

    /**
     * 以List形式按顺序返回所有元素
     *
     * @return ArrayList<T>对象
     */
    public List<T> getList() {
        ArrayList<T> ans = new ArrayList<>();
        inOrder(root, ans::add, ans);//方法引用, Java 8的内容
        return ans;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * @return 这棵树是否具有AVL的性质
     */
    boolean isAVL() {
        return isAVL(root);
    }


    /**
     * 检验以node为根节点的树是否为一棵AVL树
     */
    private boolean isAVL(Node node) {
        if (node == null) return true;
        //node是一棵AVL树
        return (Math.abs(Node.height(node.left) - Node.height(node.right)) <= 1)
                && isAVL(node.left) //它的子树也是AVL树
                && isAVL(node.right);
    }
}
