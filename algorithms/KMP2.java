package algorithms;

/**
 * 更简洁的KMP算法的代码
 */
public class KMP2 {
    public int strStr(String haystack, String needle) {
        int n1 = haystack.length(), n2 = needle.length();
        if (n1 < n2) return -1;
        if (n2 == 0) return 0;

        int[] lps = longestPrefixSuffix(needle);
        for (int i = 0, j = 0; i < n1; ) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                i++;
                if (j == n2) return i - j;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else i++;
            }
        }

        return -1;
    }

    int[] longestPrefixSuffix(String pattern) {
        int n = pattern.length();
        int[] ans = new int[n];

        for (int i = 1, j = 0; i < n; ++i) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = ans[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                ++j;
            }
            ans[i] = j;
        }
        return ans;
    }
}
