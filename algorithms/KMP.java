package algorithms;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class KMP {

    @Test
    public void manualTest() {
        String text = "abxabcabcaby";
        String pattern = "abcaby";
        System.out.println(find(text, pattern, 0));
    }

    @Test
    public void largeDataTest() {
        int length = 10000;
        int innerLoop = 1000;
        int outerLoop = 20;
        int k, l;

        for (int j = 0; j < outerLoop; ++j) {
            String s = RandomStringUtils.randomAlphanumeric(length);

            //查得到的情况
            for (int i = 0; i < innerLoop; i++) {
                k = (int) (Math.random() * (length - 50));//子串在s中的索引
                l = (int) (Math.random() * (length - k));//子串的长度

                String substring = s.substring(k, k + Math.max(l, 1));

                if (find(s, substring, 0) != s.indexOf(substring)) {
                    System.out.println("查找失败");
                    return;
                }
            }

            //查不到的情况
            for (int i = 0; i < innerLoop; i++) {
                //随机字符串s2的长度是s1长度的三分之一，大概率是匹配不到的
                String s2 = RandomStringUtils.randomAlphanumeric(length / 3);
                if (find(s, s2) != s.indexOf(s2)) {
                    System.out.println("查找失败");
                    return;
                }
            }

        }
        System.out.println("查找成功");

    }

    /**
     * 等效于s.indexOf(target)
     */
    public static int find(String s, String target) {
        return find(s, target, 0);
    }

    /**
     * 等效于s.indexOf(target, index)
     */
    public static int find(String s, String target, int index) {
        if (s == null || target == null || index < 0 ||
                index + target.length() > s.length())
            return -1;

        char[] text = s.toCharArray();
        char[] pattern = target.toCharArray();

        int tl = s.length(), pl = target.length();
        int[] lps = longestPrefixSuffix(target);

        for (int i = 0, j = 0; i < tl; ++i) {
            if (text[i] == pattern[j]) {
                j++;
            } else {
                if (j != 0) {
                    i--;
                    j = lps[j - 1];
                }
            }
            if (j == pl) {
                return i - j + 1;
            }
        }

        return -1;
    }

    /**
     * 令n = s.length()，该方法返回int[n]
     * 其中，ans[i]为s.substring(0,i)中最长公共前后缀长度
     * 例如，对于 "abcaby"
     * 返回值为[0, 0, 0, 1, 2, 0]
     * 对于 "aabaabaaa"
     * 返回值为[0, 1, 0, 1, 2, 3, 4, 5, 2]
     */
    public static int[] longestPrefixSuffix(String s) {
        if (s == null) return null;

        char[] chars = s.toCharArray();
        int n = s.length();
        int[] ans = new int[n];

        for (int i = 1, j = 0; i < n; ) {
            if (chars[i] == chars[j]) {
                ans[i] = j + 1;//j表示最大公共前缀的长度
                ++j;
                ++i;
            } else {
                if (j != 0) {//j会不断减小，进行循环比较，直到s[i]与s[j]匹配，或者j == 0
                    j = ans[j - 1];
                } else {//j == 0,不存在公共前缀
                    ans[i] = 0;
                    i++;
                }
            }
        }

        return ans;
    }


}
