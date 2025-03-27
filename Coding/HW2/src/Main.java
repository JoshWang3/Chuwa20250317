package src;
import src.leetcodeStream.*;

import java.util.Arrays;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //leetcode
         Solution s = new Solution();
         System.out.println(s.missingNumber(new int[] {3, 0, 1}));
         System.out.println(s.longestWord(new String[] {"w","wo","wor","worl","world"}));
         System.out.println(Arrays.toString(s.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
         System.out.println(s.groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}));
         System.out.println(Arrays.toString(s.twoSum(new int[]{2, 7, 11, 15}, 9)));

         //Optional interface examples:
         String name1 = "Alice";
         Optional<String> optionalName1 = Optional.of(name1);
         System.out.println(optionalName1);
         Optional<String> optionalName2 = Optional.ofNullable(null);
         Optional<String> optionalName3 = Optional.ofNullable("Bob");
         System.out.println(optionalName2);
         System.out.println(optionalName3);
         System.out.println(optionalName2.isPresent());
         System.out.println(optionalName3.isPresent());
         optionalName3.ifPresent(System.out::println);
    }
}
