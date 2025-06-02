public class OddEvenPrinter1 {
    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);
        oddThread.start();
        evenThread.start();
    }
}

class OddEvenPrinter {
    private int count = 1;
    private final int MAX = 10;

    public synchronized void printOdd() {
        while (count < MAX) {
            while (count % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + count);
            count++;
            notify();
        }
    }

    public synchronized void printEven() {
        while (count <= MAX) {
            while (count % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + count);
            count++;
            notify();
        }
    }
}
