package data_structures.hash_table;


import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        testHashTable();
    }

    public static void testHashTable() {
        HashTable<Emp> ht = new HashTable<Emp>();


        /*
        1 k v:添加/修改雇员
        2:显示所有雇员
        3 k：查找雇员
        4 k：删除雇员
        */

        Scanner scanner = new Scanner(System.in);
        int k;
        while (true) {
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    ht.put(k = scanner.nextInt(), new Emp(k, scanner.next()));
                    break;
                case "2":
                    ht.show();
                    System.out.println("============显示完毕===============");
                    break;
                case "3":
                    System.out.println(ht.find(scanner.nextInt()));
                    break;
                case "4":
                    System.out.println(ht.remove(scanner.nextInt()));
            }
        }
    }
}
