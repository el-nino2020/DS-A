package algorithms.coding;

import data_structures.tree.HuffmanTree;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCoding {


    /**
     * 判断原始字符串与压缩后再解压的字符串是否一致
     */
    @Test
    public void testZipAndUnzip() {
        String origin = "hello, world! It is 2922.";
        System.out.println("输入的字符串为：");
        System.out.println(origin);
        byte[] compressed = huffmanZip(origin);

        HuffmanTree.Node root = HuffmanTree.createHuffmanTree(countAppearance(origin));

        String decompressed = huffmanUnzip(compressed, root);
        System.out.println("压缩后再解压得到的字符串为：");
        System.out.println(decompressed);

        if (origin.equals(decompressed)) {
            System.out.println("成功");
            double l1 = compressed.length, l2 = origin.getBytes().length;
            System.out.println("压缩率为" + ((l2 - l1) / l2 * 100) + "%");
        } else {
            System.out.println("失败");
        }
    }

    /**
     * 检验每个字符对应的编码是否正确
     */
    @Test
    public void testCodeRule() {

        /*
        使用一棵唯一的赫夫曼树验证编码的正确性
         */
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HashMap<Character, Integer> info = new HashMap<Character, Integer>();
        char c = 'a';
        for (int i = 0; i < arr.length; i++) {
            info.put(c, arr[i]);
            c++;
        }

        HuffmanTree.Node root = HuffmanTree.createHuffmanTree(info);
        HuffmanTree.levelOrder(root);
        System.out.println("===============================");

        Map<Character, String> rule = getCodeRule(root);
        System.out.println("各字符编码如下：");
        for (Map.Entry<Character, String> entry : rule.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }


    }


    /**
     * 输入一个字符串，以字符串的形式返回赫夫曼编码后的二进制数据。
     * 返回的字符串中的字符为0或1。实际并没有压缩完毕，因为ans.length()肯定大于s.length()
     * 设计这个方法也是是方便测试
     *
     * @param s 要进行编码的字符串
     * @return 二进制字符串
     */
    private static String encode(String s) {
        //每个字符出现了多少次
        Map<Character, Integer> info = countAppearance(s);

        //生成赫夫曼树
        HuffmanTree.Node root = HuffmanTree.createHuffmanTree(info);

        //生成各个字符的编码
        Map<Character, String> codeRule = getCodeRule(root);

        StringBuilder ans = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            ans.append(codeRule.get(s.charAt(i)));
        }

        return ans.toString();
    }

    /**
     * 返回各个字符的编码
     *
     * @param root 赫夫曼树根节点
     * @return HashMap对象，键表示字符，值表示该字符的编码
     */
    private static Map<Character, String> getCodeRule(HuffmanTree.Node root) {
        StringBuilder code = new StringBuilder();
        Map<Character, String> ans = new HashMap<>();
        traverse(root, new StringBuilder(), ans);

        return ans;
    }

    /**
     * 对赫夫曼树进行前序遍历，以生成各个字符对应的编码
     *
     * @param node  赫夫曼树的根节点
     * @param code  代表某个字符的编码——会随着遍历不断变化
     * @param rules 存放所有字符编码的集合
     */
    private static void traverse(HuffmanTree.Node node, StringBuilder code, Map<Character, String> rules) {
        if (node == null) return;
        if (node.getLeft() == null) {
            rules.put((Character) node.getData(), code.toString());
            return;
        }
        code.append("0");
        traverse(node.getLeft(), code, rules);
        code.deleteCharAt(code.length() - 1);

        code.append("1");
        traverse(node.getRight(), code, rules);
        code.deleteCharAt(code.length() - 1);
    }


    /**
     * 判断s中每个字符出现了多少次
     *
     * @param s
     * @return HashMap对象，对于一个键值对，键代表字符，值代表该字符出现的次数
     */
    private static Map<Character, Integer> countAppearance(String s) {
        HashMap<Character, Integer> ans = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Integer val = ans.getOrDefault(c, 0);
            ans.put(c, val + 1);
        }

        return ans;
    }

    /**
     * @param s 需要压缩的字符串
     * @return 压缩后的二进制数据，存放在byte[]中
     */
    public static byte[] huffmanZip(String s) {
        String binData = encode(s);
        byte[] ans = new byte[(int) Math.ceil(binData.length() / 8.0)];

        //每8位取出，放入
        int n = binData.length();
        for (int i = 0, j = 0; i < n; i += 8, ++j) {
            ans[j] = (byte) Integer.parseInt(
                    binData.substring(i, Math.min(i + 8, n)), 2);
        }

        return ans;
    }

    /**
     * @param bytes 需要压缩的数据
     * @return 压缩后的二进制数据，存放在byte[]中
     */
    private static byte[] huffmanZip(byte[] bytes) {
        return huffmanZip(new String(bytes));
    }

    /**
     * @param bytes 待解压的数据
     * @param root  赫夫曼树的根节点，用来根据编码检索字符，这样效率比较高
     * @return 解压前的字符串
     */
    public static String huffmanUnzip(byte[] bytes, HuffmanTree.Node root) {
        int n1 = bytes.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n1 - 1; i++) {
            sb.append(byteTobitString(bytes[i], false));
        }
        sb.append(byteTobitString(bytes[n1 - 1], true));
        //binData为压缩后的二进制数据
        String binData = sb.toString();
        int n2 = binData.length();

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n2; ) {
            HuffmanTree.Node node = root;
            //找到编码对应的字符
            while (node.getLeft() != null) {
                if (binData.charAt(i) == '1') {
                    node = node.getRight();
                } else {
                    node = node.getLeft();
                }
                ++i;
            }
            ans.append(node.getData());
        }


        return ans.toString();
    }


    /**
     * 将一个byte转成二进制字符串
     *
     * @param b
     * @param last true：正数前面不补0；false：正数前面补0
     * @return 二进制字符串，last == false时长度为8
     */
    private static String byteTobitString(byte b, boolean last) {
        boolean positive = false;
        int i = b;
        //Integer.toBinaryString传入正数，输出只会按照正数本身的长度来确定，而不是定长的
        //我们只需要8位，故使其第9位等于1，传入Integer.toBinaryString即可保留后8位
        if (i >= 0 && !last) {
            i |= 0b100000000;
            positive = true;
        }

        if (positive) return Integer.toBinaryString(i).substring(1);

        if (last && i > 0) return Integer.toBinaryString(i);
        //负数返回最后8位
        return Integer.toBinaryString(i).substring(32 - 8);

    }

}
