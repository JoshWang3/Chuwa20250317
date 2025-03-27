package src.leetcodeStream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public int missingNumber(int[] nums) {
        int rangeTotal = IntStream.rangeClosed(0, nums.length).sum();
        int numsTotal = Arrays.stream(nums).sum();
        return rangeTotal - numsTotal;
    }

    public String longestWord(String[] words) {
        HashSet<String> set = new HashSet<>(Arrays.asList(words));
        return Arrays.stream(words)
                .filter(word -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!set.contains(word.substring(0, i))) {
                            return false;
                        }
                    }
                    return true;
                })
                .min((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length())
                .orElse("");
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> freq =  Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()));
        return freq.keySet().stream()
                .sorted((a, b) -> Long.compare(freq.get(b), freq.get(a)))
                .limit(k)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    char[] chars = str.toCharArray();
                    Arrays.sort(chars);
                    return new String(chars);
                }))
                .values());
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        return IntStream.range(0, nums.length)
                .filter(i -> map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i)
                .mapToObj(i -> new int[]{i, map.get(target - nums[i])})
                .findFirst()
                .orElse(new int[0]);
    }
}
