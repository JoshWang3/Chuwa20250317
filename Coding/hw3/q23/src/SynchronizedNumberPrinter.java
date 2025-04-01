public class SynchronizedNumberPrinter {
    private static final Object lock = new Object();
    private static boolean oddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                synchronized (lock) {
                    while (!oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.printf("Thread-0: %d\n", i);
                    oddTurn = false;
                    lock.notifyAll();
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (lock) {
                    while (oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.printf("Thread-1: %d\n", i);
                    oddTurn = true;
                    lock.notifyAll();
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}