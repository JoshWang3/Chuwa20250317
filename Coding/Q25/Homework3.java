package com.chuwa.tutorial.t08_multithreading;

import java.util.concurrent.CompletableFuture;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Homework3 {

    public static String fetchData(String apiUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Exception during API call: " + e.getMessage());
            return "Default data due to exception";
        }
        return result.toString();
    }

    public static void main(String[] args) {
        CompletableFuture<String> products = CompletableFuture.supplyAsync(() ->
                fetchData("https://jsonplaceholder.typicode.com/posts")
        ).exceptionally(e -> "Products: Default data");

        CompletableFuture<String> reviews = CompletableFuture.supplyAsync(() ->
                fetchData("https://jsonplaceholder.typicode.com/comments")
        ).exceptionally(e -> "Reviews: Default data");

        CompletableFuture<String> inventory = CompletableFuture.supplyAsync(() ->
                fetchData("https://jsonplaceholder.typicode.com/todos")
        ).exceptionally(e -> "Inventory: Default data");

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(products, reviews, inventory);

        allFutures.thenRun(() -> {
            try {
                String combinedData = "Products: " + products.get() +
                        "\nReviews: " + reviews.get() +
                        "\nInventory: " + inventory.get();
                System.out.println(combinedData);
            } catch (Exception e) {
                System.err.println("Error merging results: " + e.getMessage());
            }
        }).join();
    }
}
