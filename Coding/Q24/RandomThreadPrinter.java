package com.chuwa.tutorial.t08_multithreading;

public class RandomThreadPrinter {
    public static void main(String[] args) {
        Runnable print1To10 = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        };

        Runnable print11To20 = () -> {
            for (int i = 11; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        };

        Runnable print21To30 = () -> {
            for (int i = 21; i <= 30; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        };

        Thread thread1 = new Thread(print1To10, "Thread-0");
        Thread thread2 = new Thread(print11To20, "Thread-2");
        Thread thread3 = new Thread(print21To30, "Thread-1");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

