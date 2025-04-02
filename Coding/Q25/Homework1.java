package com.chuwa.tutorial.t08_multithreading;

import java.util.concurrent.CompletableFuture;

public class Homework1 {
    public static void main(String[] args) {
        CompletableFuture<Integer> sum = CompletableFuture.supplyAsync(() -> 5 + 3);
        CompletableFuture<Integer> product = CompletableFuture.supplyAsync(() -> 5 * 3);

        sum.thenAccept(result -> System.out.println("Sum: " + result));
        product.thenAccept(result -> System.out.println("Product: " + result));

        CompletableFuture<Void> combined = CompletableFuture.allOf(sum, product);
        combined.join();
    }
}

