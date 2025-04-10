import java.util.Arrays;
import java.util.List;

public class StreamAverage {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(20, 3, 78, 9, 6, 53, 73, 99, 24, 32);
        double average = list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        System.out.println(average);
    }
}
