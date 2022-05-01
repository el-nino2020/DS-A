package data_structures.heap;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.PriorityQueue;

public class Test {

    //创建一个空的堆
    //排序规则为Integer::compareTo
    @org.junit.Test
    public void testBinaryHeapWithEmptyArray() {
        int n = 20000;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compareTo);
        BinaryHeap<Integer> heap = new BinaryHeap<>(Integer::compareTo);

        for (int i = 0; i < n; i++) {
            double d1 = Math.random();

            if (d1 <= 1.0 / 3) {//add
                int val = (int) (Math.random() * n * 5);
                pq.add(val);
                heap.add(val);
                if (!heap.verifyHeap()) {
                    System.out.println("添加验证失败");
                    return;
                }
            } else if (d1 > 2.0 / 3) {//peek
                Integer i1 = pq.peek();
                Integer i2 = heap.peek();
                if (i1 == null && i2 == null) continue;

                if (!i1.equals(i2)) {//类之间判断不能使用 =
                    System.out.println("peek失败");
                    return;
                }
            } else {//poll
                Integer i1 = pq.poll();
                Integer i2 = heap.poll();
                if (i1 == null && i2 == null) continue;
                if (!i1.equals(i2)) {
                    System.out.println("poll失败");
                    return;
                }
                if (!heap.verifyHeap()) {
                    System.out.println("poll验证失败");
                    return;
                }

            }
        }
        System.out.println("成功");
    }

    //使用已有数组初始化堆
    //排序规则为String长度
    @org.junit.Test
    public void testBinaryHeapWithHeapBuild() {
        int length = 100;
        int initSize = 500;
        int n = 20000;

        String[] strings = new String[initSize];


        for (int i = 0; i < initSize; i++) {
            strings[i] = RandomStringUtils.randomAlphanumeric(
                    (int) (Math.random() * length + 5));
        }

        BinaryHeap<String> heap = new BinaryHeap<>(strings, (s1, s2) -> s1.length() - s2.length());

        if (!heap.verifyHeap()) {
            System.out.println("初始化数组堆化失败");
            return;
        }

        PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> s1.length() - s2.length());
        for (int i = 0; i < initSize; i++) {
            pq.add(strings[i]);
        }

        for (int i = 0; i < n; i++) {
            double d1 = Math.random();

            if (d1 <= 1.0 / 3) {//add
                String val = RandomStringUtils.randomAlphanumeric(
                        (int) (Math.random() * length + 5));
                pq.add(val);
                heap.add(val);
                if (!heap.verifyHeap()) {
                    System.out.println("添加验证失败");
                    return;
                }
            } else if (d1 > 2.0 / 3) {//peek
                String i1 = pq.peek();
                String i2 = heap.peek();
                if (i1 == null && i2 == null) continue;

                if (i1.length() != i2.length()) {
                    System.out.println("peek失败");
                    return;
                }
            } else {//poll
                String i1 = pq.poll();
                String i2 = heap.poll();
                if (i1 == null && i2 == null) continue;
                if (i1.length() != i2.length()) {
                    System.out.println("poll失败");
                    return;
                }
                if (!heap.verifyHeap()) {
                    System.out.println("poll验证失败");
                    return;
                }

            }
        }
        System.out.println("成功");

    }
}
