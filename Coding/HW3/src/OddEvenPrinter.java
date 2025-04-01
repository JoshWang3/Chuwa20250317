import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter {
    //synchronized
//    private int number = 1;
//    private final int limit = 10;
//    private final Object lock = new Object();
//
//    public void printOdd() {
//        try{
//            synchronized(lock) {
//                while(number<=limit) {
//                    if(number%2 == 1) {
//                        System.out.println("Thread-0: " + number);
//                        number+=1;
//                        lock.notify();
//                    } else {
//                        lock.wait();
//                    }
//                }
//            }
//        } catch(InterruptedException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void printEven() {
//        try {
//            synchronized(lock) {
//                while(number<=limit) {
//                    if(number%2==0) {
//                        System.out.println("Thread-1: "+number);
//                        number++;
//                        lock.notify();
//                    } else {
//                        lock.wait();
//                    }
//                }
//            }
//        } catch(InterruptedException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    private int number = 1;
    private final int limit = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition oddCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();

    public void printOdd() {
        lock.lock();
        try {
            while(number<=limit) {
                if(number%2==1) {
                    System.out.println("Thread-0: "+number);
                    number++;
                    evenCondition.signal();
                } else {
                    oddCondition.await();
                }
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void printEven() {
        lock.lock();
        try {
            while(number<=limit) {
                if(number%2==0) {
                    System.out.println("Thread-1: "+number);
                    number++;
                    oddCondition.signal();
                } else {
                    evenCondition.await();
                }
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    static class Printer {
        public static void main(String[] args) {
            OddEvenPrinter oep = new OddEvenPrinter();
            Thread thread1 = new Thread(oep::printOdd);
            Thread thread2 = new Thread(oep::printEven);
            thread1.start();
            thread2.start();
        }
    }
}

