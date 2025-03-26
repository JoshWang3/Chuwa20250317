import java.util.Arrays;
import java.util.stream.IntStream;

public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        int result = missingNumber(nums);
        System.out.println("Missing number is: " + result);
    }

    public static int missingNumber(int[] nums) {
        int n = nums.length;

        int expectedSum = IntStream.rangeClosed(0, n).sum();
        int actualSum = Arrays.stream(nums).sum();

        return expectedSum - actualSum;
    }
}
