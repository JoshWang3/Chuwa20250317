package com.chuwa.tutorial.t08_multithreading;

import java.util.concurrent.CompletableFuture;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Homework2 {

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
            return "Error fetching data";
        }
        return result.toString();
    }

    public static void main(String[] args) {
        CompletableFuture<String> products = CompletableFuture.supplyAsync(() ->
                fetchData("https://jsonplaceholder.typicode.com/posts")
        );
        CompletableFuture<String> reviews = CompletableFuture.supplyAsync(() ->
                fetchData("https://jsonplaceholder.typicode.com/comments")
        );
        CompletableFuture<String> inventory = CompletableFuture.supplyAsync(() ->
                fetchData("https://jsonplaceholder.typicode.com/todos")
        );

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(products, reviews, inventory);

        allFutures.thenRun(() -> {
            try {
                String combinedData = "Products: " + products.get() +
                        "\nReviews: " + reviews.get() +
                        "\nInventory: " + inventory.get();
                System.out.println(combinedData);
            } catch (Exception e) {
                System.err.println("Error merging results");
            }
        }).join();
    }
}

