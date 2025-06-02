import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter2 {
    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);
        oddThread.start();
        evenThread.start();
    }
}

class OddEvenPrinter {
    private static final int MAX = 10;
    private int count = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printOdd() {
        try {
            lock.lock();
            while (count <= MAX) {
                while (count % 2 == 0) {
                    condition.await();
                }
                if (count <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    condition.signal();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void printEven() {
        try {
            lock.lock();
            while (count <= MAX) {
                while (count % 2 != 0) {
                    condition.await();
                }
                if (count <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    condition.signal();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}