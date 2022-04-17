package data_structures.hash_table;

//写个泛型累死了，下次再也不写多个类的泛型嵌套了。。。


/**
 * 该哈希表的键类型为Integer，值类型为V
 *
 * @param <V>
 */
@SuppressWarnings({"rawtypes"})
public class HashTable<V> {
    private LinkedList[] table;
    private int capacity = 32;

    public HashTable() {
        this(32);
    }

    public HashTable(int capacity) {
        this.capacity = Math.max(capacity, 16);
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList();
        }
    }


    /**
     * 散列函数
     *
     * @return table的索引
     */
    private int hash(int i) {
        return i % capacity;
    }

    public void show() {
        for (LinkedList linkedList : table) {
            if (linkedList.empty()) continue;
            linkedList.show();
        }
    }

    public void put(Integer k, V v) {
        int hash = hash(k);
        Node node = table[hash].exists(k);

        if (node == null) {//该key不存在，创建新节点
            table[hash].add(new Node(k, v));
        } else {//更新val
            node.val = v;
        }
    }

    @SuppressWarnings({"unchecked"})
    public V find(int key) {
        Node node = table[hash(key)].exists(key);
        return node == null ? null : (V) node.val;
    }

    @SuppressWarnings({"unchecked"})
    public V remove(int key) {
        Node node = table[hash(key)].remove(key);
        return node == null ? null : (V) node.val;

    }
}

/**
 * 这个链表是在linked_list.SingleLinkedList的基础上修改而成的
 */
@SuppressWarnings({"rawtypes"})
class LinkedList {
    Node head;//头结点不存放数据
    Node tail;//尾节点为实际的最后一个节点，初始值等于head

    public LinkedList() {
        head = new Node();
        tail = head;
    }


    /**
     * 打印链表
     */
    public void show() {
        Node t = head.next;
        while (t != null) {
            System.out.print(t);
            t = t.next;
        }
        System.out.println();
    }

    public boolean empty() {
        return head.next == null;
    }

    /**
     * 在链表尾部添加节点
     */
    public void add(Node node) {
        tail.next = node;
        tail = tail.next;
    }


    /**
     * 查找是否存在键为k的节点
     *
     * @return 找到则返回该节点，否则返回null
     */
    public <K> Node exists(K k) {
        Node t = head.next;
        while (t != null) {
            if (t.key.equals(k)) {
                return t;
            }
            t = t.next;
        }
        return null;
    }

    /**
     * 删除键为key的节点
     *
     * @return 删除成功返回该节点，否则返回null
     */
    public <K> Node remove(K k) {
        Node t = head;
        while (t.next != null) {
            if (t.next.key.equals(k)) {
                Node ans = t.next;

                if (t.next == tail) {//要删除的是最后一个节点
                    tail = t;
                }

                t.next = t.next.next;
                return ans;
            }
            t = t.next;
        }
        return null;
    }
}

/**
 * 一个Node对象存储一个键值对
 *
 * @param <K> key
 * @param <V> value
 */
@SuppressWarnings({"unused"})
class Node<K, V> {
    K key;
    V val;
    Node<K, V> next;

    public Node(K key, V val, Node<K, V> next) {
        this.key = key;
        this.val = val;
        this.next = next;
    }

    public Node() {
    }

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toString() {
        return "{" + key + ": " + val + "}";
    }
}

@SuppressWarnings({"unused"})
class Emp {
    private int id;
    private String name;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + id + " " + name + "]";
    }
}

