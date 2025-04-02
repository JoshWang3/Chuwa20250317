package com.chuwa.tutorial.t08_multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterWithLock {
    private static int number = 1;
    private static final int MAX_NUMBER = 10;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Runnable printOdd = () -> {
            while (true) {
                lock.lock();
                try {
                    if (number > MAX_NUMBER) break;
                    while (number % 2 == 0) {
                        condition.await();
                    }
                    if (number <= MAX_NUMBER) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        condition.signal();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        };

        Runnable printEven = () -> {
            while (true) {
                lock.lock();
                try {
                    if (number > MAX_NUMBER) break;
                    while (number % 2 == 1) {
                        condition.await();
                    }
                    if (number <= MAX_NUMBER) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        condition.signal();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread oddThread = new Thread(printOdd, "Thread-0");
        Thread evenThread = new Thread(printEven, "Thread-1");

        oddThread.start();
        evenThread.start();
    }
}
