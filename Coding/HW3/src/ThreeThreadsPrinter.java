import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreeThreadsPrinter {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable task1 = () -> {
            for(int i=1; i<=10; i++) {
                System.out.println("Thread-0: "+i);
            }
        };
        Runnable task2 = () -> {
            for(int i=1; i<=10; i++) {
                System.out.println("Thread-1: "+i);
            }
        };
        Runnable task3 = () -> {
            for(int i=1; i<=10; i++) {
                System.out.println("Thread-2: "+i);
            }
        };
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);

        executor.shutdown();
    }

}
