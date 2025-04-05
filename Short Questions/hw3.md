2. public class DclSingleton {
    private static volatile DclSingleton instance;
    public static DclSingleton getInstance() {
        if (instance == null) {
            synchronized (DclSingleton .class) {
                if (instance == null) {
                    instance = new DclSingleton();
                }
            }
        }
        return instance;
    }

    // private constructor and other methods...
}
3. new Thread(new MyRunnable()).start();
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.execute(new MyRunnable());
4. Runnable doesn't return a result and can't throw checked exceptions.  
Callable returns a result and can throw checked exceptions.  
5. t.start() starts a new thread, t.run() runs in the current thread, no new thread created.  
6. Runnable is better – supports multiple inheritance and better object-oriented design
7. new, runnable, running, blocked, terminated
8. 
class A {
  synchronized void methodA(B b) {
    b.last();
  }
  synchronized void last() {}
}

class B {
  synchronized void methodB(A a) {
    a.last();
  }
  synchronized void last() {}
}  
solve: use .join(), avoid nested lock
9. Using wait(), notify(), and notifyAll() on shared objects  
10. Object lock: On instance methods (synchronized). Class lock: On static methods or synchronized(ClassName.class)
11. Makes one thread wait until another thread finishes execution  
12. Hints the scheduler to pause current thread and give chance to others.  
13. Manages a pool of reusable threads.  
types: Fixed, Cached, Single, Scheduled.  
TaskQueue: Queue holding tasks before execution.  
14. Library: java.util.concurrent. Interface: ExecutorService  
15. executor.submit(new MyCallable());
executor.execute(new MyRunnable());  
16. Reuses threads, improves performance, avoids thread creation overhead.  
17. shutdown(): Graceful, waits for tasks to complete.  
shutdownNow(): Immediate, tries to stop all running tasks.  
18. Thread-safe classes for atomic operations.  
Types: AtomicInteger, AtomicBoolean, AtomicReference  
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
19. Thread-safe collections in java.util.concurrent. Examples: ConcurrentHashMap, CopyOnWriteArrayList, ConcurrentLinkedQueue  
20. Synchronized: Easy to use. ReentrantLock: More control (tryLock, interruptible). ReadWriteLock: Better for read-heavy scenarios. StampedLock: More advanced with optimistic locking  
21. Future: Represents result of async computation, retrieved using get().  
CompletableFuture: Supports async chaining, callbacks, combining futures.
Main methods: thenApply(), thenAccept(), thenCombine(), supplyAsync(), complete(), exceptionally()  
23. 1.
public class OddEvenPrinter {
    private int count = 1;
    private final int MAX = 10;

    public synchronized void printOdd() throws InterruptedException {
        while (count <= MAX) {
            if (count % 2 == 0) wait();
            else {
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                notify();
            }
        }
    }

    public synchronized void printEven() throws InterruptedException {
        while (count <= MAX) {
            if (count % 2 == 1) wait();
            else {
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();

        Thread t1 = new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException ignored) {}
        });

        Thread t2 = new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException ignored) {}
        });

        t1.start();
        t2.start();
    }
}  
   2. 
package com.chuwa.tutorial.t08_multithreading.c05_waitNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterWithLock {
    private int count = 1;
    private final int MAX = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printOdd() {
        while (count <= MAX) {
            lock.lock();
            try {
                while (count % 2 == 0) condition.await();
                if (count <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    condition.signal();
                }
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (count <= MAX) {
            lock.lock();
            try {
                while (count % 2 == 1) condition.await();
                if (count <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    condition.signal();
                }
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinterWithLock printer = new OddEvenPrinterWithLock();

        Thread t1 = new Thread(printer::printOdd);
        Thread t2 = new Thread(printer::printEven);

        t1.start();
        t2.start();
    }
}
24. 
package com.chuwa.exercise.t08_multithreading;

public class PrintNumber1 {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        };

        Runnable task2 = () -> {
            for (int i = 11; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        };

        Runnable task3 = () -> {
            for (int i = 21; i <= 30; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);

        t1.start();
        t2.start();
        t3.start();
    }
}
25. 
hw1  
import java.util.concurrent.CompletableFuture;

public class Homework1 {
    public static void main(String[] args) {
        int a = 5, b = 3;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        sumFuture.thenAccept(result -> System.out.println("Sum: " + result));
        productFuture.thenAccept(result -> System.out.println("Product: " + result));
    }
}  
hw2  
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.net.http.*;
import java.net.URI;

public class Homework2 {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static CompletableFuture<String> fetchProducts() {
        return fetchData("https://jsonplaceholder.typicode.com/posts/1");
    }

    public static CompletableFuture<String> fetchReviews() {
        return fetchData("https://jsonplaceholder.typicode.com/comments/1");
    }

    public static CompletableFuture<String> fetchInventory() {
        return fetchData("https://jsonplaceholder.typicode.com/todos/1");
    }

    private static CompletableFuture<String> fetchData(String url) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> products = fetchProducts();
        CompletableFuture<String> reviews = fetchReviews();
        CompletableFuture<String> inventory = fetchInventory();

        CompletableFuture<Void> all = CompletableFuture.allOf(products, reviews, inventory);

        all.thenRun(() -> {
            try {
                System.out.println("Products: " + products.get());
                System.out.println("Reviews: " + reviews.get());
                System.out.println("Inventory: " + inventory.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}
hw3  
public class Homework3 {
    private static final HttpClient client = HttpClient.newHttpClient();

    private static CompletableFuture<String> safeFetch(String url, String defaultValue) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body)
                     .exceptionally(ex -> {
                         System.out.println("Error fetching from " + url + ": " + ex.getMessage());
                         return defaultValue;
                     });
    }

    public static void main(String[] args) {
        CompletableFuture<String> products = safeFetch("https://jsonplaceholder.typicode.com/posts/1", "Default Product");
        CompletableFuture<String> reviews = safeFetch("https://jsonplaceholder.typicode.com/comments/1", "Default Review");
        CompletableFuture<String> inventory = safeFetch("https://jsonplaceholder.typicode.com/todos/1", "Default Inventory");

        CompletableFuture<Void> all = CompletableFuture.allOf(products, reviews, inventory);

        all.thenRun(() -> {
            try {
                System.out.println("Products: " + products.get());
                System.out.println("Reviews: " + reviews.get());
                System.out.println("Inventory: " + inventory.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}







