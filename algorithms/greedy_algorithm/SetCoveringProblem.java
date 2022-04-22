package algorithms.greedy_algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SetCoveringProblem {

    public static void main(String[] args) {
        //北京-1 上海-2 天津-3 广州-4 深圳-5 成都-6 杭州-7 大连-8
        int[][] sets = {{1, 2, 3}, {4, 1, 5}, {6, 2, 7}, {2, 3}, {7, 8}};
        System.out.println(solve(sets));
    }

    /**
     * 使用贪心算法找到sets中(近似)最小的子集合数，
     * 使这些集合的并集等于 sets中所有集合的并集
     *
     * @param sets sets[i]表示第i个集合，该数组包含了该集合的元素
     */
    public static Set<Integer> solve(int[][] sets) {
        HashSet<Integer> uncovered = new HashSet<>();
        HashSet<Integer> ans = new HashSet<>();
        HashSet<Integer> curSets = new HashSet<>();//没有选中的集合


        for (int j = 0; j < sets.length; ++j) {
            int[] set = sets[j];
            curSets.add(j);
            for (int i : set) {
                uncovered.add(i);
            }
        }

        while (!uncovered.isEmpty()) {
            int max = -1;
            int index = -1;

            for (Integer i : curSets) {//找到一个覆盖了最多未覆盖元素的集合
                int count = 0;
                for (int e : sets[i]) {
                    if (uncovered.contains(e)) ++count;
                }
                if (count > max) {
                    max = count;
                    index = i;
                }
            }

            ans.add(index);
            for (int e : sets[index]) {
                uncovered.remove(e);
            }

        }

        return ans;
    }
}
