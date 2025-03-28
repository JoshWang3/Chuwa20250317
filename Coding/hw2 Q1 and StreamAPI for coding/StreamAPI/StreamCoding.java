package com.chuwa.exercise.collection;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamCoding {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map;
        map = IntStream.range(0, nums.length)
                .boxed()
                .collect(Collectors.toMap(i -> nums[i], i -> i, (a, b) -> b));

        return IntStream.range(0, nums.length)
                .filter(i -> map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i)
                .mapToObj(i -> new int[]{i, map.get(target - nums[i])})
                .findFirst()
                .orElse(new int[0]);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        return Arrays.stream(strs)
                .collect(Collectors.groupingBy(
                        s -> s.chars().sorted()
                                .collect(StringBuilder::new,
                                        StringBuilder::appendCodePoint,
                                        StringBuilder::append)
                                .toString()
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Long> freqMap = Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return freqMap.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public String longestWord(String[] words) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));

        return Arrays.stream(words)
                .filter(word -> IntStream.range(1, word.length())
                        .allMatch(i -> wordSet.contains(word.substring(0, i))))
                .sorted((a, b) -> b.length() == a.length() ? a.compareTo(b) : Integer.compare(b.length(), a.length()))
                .findFirst()
                .orElse("");
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = IntStream.rangeClosed(0, n).sum();
        int actualSum = Arrays.stream(nums).sum();
        return expectedSum - actualSum;
    }

}
