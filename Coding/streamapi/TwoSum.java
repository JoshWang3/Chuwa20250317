import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);
        System.out.println("Indexes: [" + result[0] + ", " + result[1] + "]");
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] result = new int[2];

        IntStream.range(0, nums.length)
                .anyMatch(i -> {
                    int need = target - nums[i];
                    if(map.containsKey(need)){
                        result[0] = map.get(need);
                        result[1] = i;
                        return true;
                    }
                    map.put(nums[i], i);
                    return false;
                });

        return result;
    }
}