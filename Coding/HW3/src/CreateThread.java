import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThread extends Thread implements Runnable{
    ExecutorService executor1 = Executors.newFixedThreadPool(3);

    ExecutorService executor2 = Executors.newCachedThreadPool();
}
