import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import org.json.JSONArray;
import org.json.JSONObject;

public class StoreDataFetcher {

    // Endpoints for products, reviews, and inventory.
    private static final String PRODUCTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final String REVIEWS_URL = "https://jsonplaceholder.typicode.com/comments";
    // Use users data as inventory for convenience
    private static final String INVENTORY_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        // Fetch products asynchronously.
        CompletableFuture<String> productsFuture = fetchAsync(client, PRODUCTS_URL)
                .exceptionally(ex -> {
                    System.err.println("Error fetching products: " + ex.getMessage());
                    return "[]"; // Return an empty JSON array on error.
                });

        // Fetch reviews asynchronously.
        CompletableFuture<String> reviewsFuture = fetchAsync(client, REVIEWS_URL)
                .exceptionally(ex -> {
                    System.err.println("Error fetching reviews: " + ex.getMessage());
                    return "[]";
                });

        // Fetch inventory asynchronously.
        CompletableFuture<String> inventoryFuture = fetchAsync(client, INVENTORY_URL)
                .exceptionally(ex -> {
                    System.err.println("Error fetching inventory: " + ex.getMessage());
                    return "[]";
                });

        // Wait for all API calls to complete.
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        // Once all are done, parse and print the first five entries from each JSON array.
        allFutures.thenRun(() -> {
            String productsData = productsFuture.join();
            String reviewsData = reviewsFuture.join();
            String inventoryData = inventoryFuture.join();

            System.out.println("=== Products (first 5) ===");
            printFirstFive(productsData);

            System.out.println("\n=== Reviews (first 5) ===");
            printFirstFive(reviewsData);

            System.out.println("\n=== Inventory (first 5) ===");
            printFirstFive(inventoryData);
        }).join(); // Wait for the merge task to complete.
    }

    // Helper method to perform an asynchronous HTTP GET request.
    private static CompletableFuture<String> fetchAsync(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    // Helper method to parse the JSON data and print only the first five objects formatted.
    private static void printFirstFive(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            int limit = Math.min(5, jsonArray.length());
            for (int i = 0; i < limit; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                // Print with indentation for clarity.
                System.out.println(obj.toString(4));
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
    }
}