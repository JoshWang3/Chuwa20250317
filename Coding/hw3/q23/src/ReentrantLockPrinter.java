import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPrinter {
    private static final Lock lock = new ReentrantLock();
    private static final Condition oddCondition = lock.newCondition();
    private static final Condition evenCondition = lock.newCondition();
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                lock.lock();
                try {
                    while (!isOddTurn) {
                        oddCondition.await();
                    }
                    System.out.printf("Thread-0: %d\n", i);
                    isOddTurn = false;
                    evenCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                lock.lock();
                try {
                    while (isOddTurn) {
                        evenCondition.await();
                    }
                    System.out.printf("Thread-1: %d\n", i);
                    isOddTurn = true;
                    oddCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}