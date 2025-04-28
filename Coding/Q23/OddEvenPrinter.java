package com.chuwa.tutorial.t08_multithreading;

public class OddEvenPrinter {
    private static final Object lock = new Object();
    private static int number = 1;
    private static final int MAX_NUMBER = 10;

    public static void main(String[] args) {
        Runnable printOdd = () -> {
            while (number <= MAX_NUMBER) {
                synchronized (lock) {
                    if (number % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        };

        Runnable printEven = () -> {
            while (number <= MAX_NUMBER) {
                synchronized (lock) {
                    if (number % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        };

        Thread oddThread = new Thread(printOdd, "Thread-0");
        Thread evenThread = new Thread(printEven, "Thread-1");

        oddThread.start();
        evenThread.start();
    }
}
