package data_structures.linked_list;

public class DoubleLinkedList {
    public static class Node {
        int val;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    Node head;//head->next为双向链表的第一个节点
    Node tail;//tail->prev为双向链表的最后一个节点
    Node cur;//用于迭代

    public DoubleLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        cur = head;
    }

    /**
     * 添加到链表最后
     *
     * @return 没有异常则为true
     */
    public boolean add(int val) {
        Node node = new Node(val, tail.prev, tail);
        tail.prev.next = node;
        tail.prev = node;
        return true;
    }

    /**
     * 删除第一个值为val的节点
     *
     * @return 删除成功返回true
     */
    public boolean remove(int val) {
        Node t = head.next;
        while (t != tail) {//tail相当于超尾
            if (t.val == val) {
                //自我删除——双向链表的优点
                t.prev.next = t.next;
                t.next.prev = t.prev;
                return true;
            }
            t = t.next;
        }

        return false;
    }


    /**
     * 将链表中第一个值为oldVal的节点的值更新为newVal
     *
     * @return 更新成功则为true, 否则为false
     */
    public boolean replace(int oldVal, int newVal) {
        Node t = head.next;
        while (t != tail) {
            if (t.val == oldVal) {
                t.val = newVal;
                return true;
            }
            t = t.next;
        }
        return false;
    }

    //以下三个方法实现了一个简易的迭代器功能
    public boolean hasNext() {
        return cur.next != tail;
    }

    public int next() {
        cur = cur.next;
        return cur.val;
    }

    public void resetNext() {
        cur = head;
    }


    /**
     * 打印链表
     */
    public void show() {
        System.out.print("开始打印: ");
        Node t = head.next;
        while (t != tail) {
            System.out.print(t.val + "\t");
            t = t.next;
        }
        System.out.println();
    }
}
