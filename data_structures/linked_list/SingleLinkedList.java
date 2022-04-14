package data_structures.linked_list;

/**
 * 单向链表
 */
@SuppressWarnings({"unused"})
public class SingleLinkedList {

    public static class Node {
        private int val;
        private Node next;

        public Node() {
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }

    }

    Node head;//头结点不存放数据
    Node tail;//尾节点为实际的最后一个节点，初始值等于head

    public SingleLinkedList() {
        head = new Node();
        tail = head;
    }

    /**
     * 打印链表
     */
    public void show() {
        System.out.print("开始打印: ");
        Node t = head.next;
        while (t != null) {
            System.out.print(t.val + "\t");
            t = t.next;
        }
        System.out.println();
    }

    /**
     * 在链表尾部添加节点
     */
    public boolean add(int val) {
        tail.next = new Node(val);
        tail = tail.next;
        return true;
    }

    /**
     * 将链表中第一个值为oldVal的节点的值更新为newVal
     *
     * @return 更新成功则为true, 否则为false
     */
    public boolean replace(int oldVal, int newVal) {
        Node t = head.next;
        while (t != null) {
            if (t.val == oldVal) {
                t.val = newVal;
                return true;
            }
            t = t.next;
        }
        return false;
    }


    public boolean exists(int val) {
        Node t = head.next;
        while (t != null) {
            if (t.val == val) {
                return true;
            }
            t = t.next;
        }
        return false;
    }

    /**
     * 删除第一个值为val的节点
     * @return 删除成功返回true
     */
    public boolean remove(int val) {
        Node t = head;
        while (t.next != null) {
            if (t.next.val == val) {
                if (t.next == tail) {//要删除的是最后一个节点
                    tail = t;
                }

                t.next = t.next.next;

                return true;
            }
            t = t.next;
        }
        return false;//没有找到值为val的节点
    }

}
