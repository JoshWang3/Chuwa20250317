import java.util.*;

public class LongestWordInDictionary {

    public static void main(String[] args) {
        String[] words = {"w", "wo", "wor", "worl", "world", "banana", "ban"};
        String result = longestWord(words);
        System.out.println("Longest word: " + result);
    }

    public static String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        set.add("");

        return Arrays.stream(words)
                .filter(word -> set.contains(word.substring(0, word.length() - 1)))
                .peek(set::add)
                .reduce("", (longest, word) ->
                        word.length() > longest.length() ? word : longest);
    }
}
