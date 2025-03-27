import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class StreamLeetCode {

    // 1. Two Sum
    // https://leetcode.com/problems/two-sum/submissions/1587528167
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] res = new int[2];

        IntStream.range(0, nums.length)
                .anyMatch(i -> {
                    if(map.containsKey(target - nums[i])){
                        res[0] = map.get(target - nums[i]);
                        res[1] = i;
                        return true;
                    }
                    map.put(nums[i], i);
                    return false;
                });
        return res;
    }

    // 49. Group Anagrams
    // https://leetcode.com/problems/group-anagrams/submissions/1588540747
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    char[] arr = str.toCharArray();
                    Arrays.sort(arr);
                    return new String(arr);
                }))
                .values());
    }

    // 347. Top K Frequent Elements
    // https://leetcode.com/problems/top-k-frequent-elements/submissions/1588561096
    public int[] topKFrequent(int[] nums, int k) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, Collectors.summingInt(n -> 1)))
                .entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    // 720. Longest Word in Dictionary
    // https://leetcode.com/problems/longest-word-in-dictionary/submissions/1588593571
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>();
        set.add("");
        return Arrays.stream(words)
                .sorted()
                .filter(w -> set.contains(w.substring(0, w.length() - 1)))
                .peek(set::add)
                .min((a, b) -> {
                    if (a.length() == b.length()) return a.compareTo(b);
                    else return b.length() - a.length();
                }).orElse("");
    }

    // 268. Missing Number
    // https://leetcode.com/problems/missing-number/submissions/1588601357
    public int missingNumber(int[] nums) {
        int n = nums.length;
        return IntStream.rangeClosed(0, n).reduce(0, (res, i) -> res ^ i ^ (i < n ? nums[i] : 0));
    }
}