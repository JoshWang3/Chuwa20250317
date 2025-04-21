# Read: ✅ https://www.interviewbit.com/multithreading-interview-questions#class-level-lock-vs-object-level-lock
# Write a thread-safe singleton class
```java
public class Singleton {

    private Singleton() {
    }

    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```
# How to create a new thread(Please also consider Thread Pool approach)?
## Extending the Thread class:
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running...");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Don't use t1.run() directly
    }
}
```
## Implementing the Runnable interface:
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable thread is running...");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
    }
}
```
## Using Callable with Future (returns a value):
```java
import java.util.concurrent.*;

class MyCallable implements Callable<String> {
    public String call() {
        return "Callable thread completed";
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new MyCallable());
        System.out.println(future.get()); // Waits and gets the result
        executor.shutdown();
    }
}
```
## Using Thread Pool (ExecutorService):
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // Create a thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("Running task in thread: " + Thread.currentThread().getName());
            });
        }

        executor.shutdown(); // Initiates an orderly shutdown
    }
}
```
## Summary
| Method           | Return Value | Scalable | Preferred for Multiple Tasks |
|------------------|--------------|----------|-------------------------------|
| `Thread`         | No           | ❌       | ❌                            |
| `Runnable`       | No           | ✅       | ✅ (with `ExecutorService`)   |
| `Callable`       | Yes          | ✅       | ✅                            |
| `Thread Pool`    | Depends      | ✅✅✅   | ✅✅✅                         |
# Difference between Runnable and Callable?
## Key Differences Between `Runnable` and `Callable`

| Feature                  | `Runnable`                          | `Callable<V>`                          |
|--------------------------|--------------------------------------|----------------------------------------|
| **Return Value**         | ❌ No return value                  | ✅ Returns a result (`V`)              |
| **Exception Handling**   | ❌ Cannot throw checked exceptions  | ✅ Can throw checked exceptions        |
| **Use With Executor**    | Used with `execute()` method        | Used with `submit()` method (`Future`) |
| **Result Retrieval**     | No result                          | Can retrieve result via `Future.get()` |
| **Introduced In**        | Java 1.0                            | Java 5                                 |
## Runnable Example:
```java
public class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable running...");
    }
}
```
## Callable Example:
```java
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    public String call() throws Exception {
        return "Callable result";
    }
}
```
## Summary:
- Use **`Runnable`** when you just want to execute code in a separate thread.
- Use **`Callable`** when you want the task to **return a result** or **throw exceptions**.

# What is the difference between t.start() and t.run()?
## Summary: `t.start()` vs `t.run()`

| Method     | Creates New Thread? | Runs Concurrently? | Uses Thread Scheduler? |
|------------|---------------------|---------------------|-------------------------|
| `t.start()`| ✅ Yes              | ✅ Yes              | ✅ Yes                  |
| `t.run()`  | ❌ No               | ❌ No               | ❌ No                   |

# Which way of creating threads is better: Thread class or Runnable interface?
Use **Runnable** (or Callable if you need a return value) in most situations. It’s cleaner, more flexible, and works better with modern concurrency tools like `ExecutorService`.
## Summary: Runnable vs Thread Class

| Feature                         | `Runnable` (Preferred) | `Thread` Class           |
|----------------------------------|-------------------------|--------------------------|
| Task and thread separation      | ✅ Yes                  | ❌ No                    |
| Allows extending another class  | ✅ Yes                  | ❌ No                    |
| Suitable for thread pools       | ✅ Yes                  | ❌ No                    |
| Code reusability/flexibility    | ✅ Better               | ❌ Limited               |
| Complexity                      | ✅ Simple               | ✅ Simple                |

# What are the thread statuses?
## Java Thread States

| Thread State       | Description |
|--------------------|-------------|
| **NEW**            | Thread is created but not started. |
| **RUNNABLE**       | Thread is ready to run or running. |
| **BLOCKED**        | Thread is waiting to acquire a lock. |
| **WAITING**        | Thread is waiting indefinitely for another thread. |
| **TIMED_WAITING**  | Thread is waiting for a specified amount of time. |
| **TERMINATED**     | Thread has finished execution. |

# Demonstrate deadlock and how to resolve it in Java code.
## Deadlock Example
```java
public class DeadlockExample {

    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (LockA) {
                System.out.println("Thread 1: Holding LockA...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread 1: Waiting for LockB...");
                synchronized (LockB) {
                    System.out.println("Thread 1: Acquired LockB!");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (LockB) {
                System.out.println("Thread 2: Holding LockB...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread 2: Waiting for LockA...");
                synchronized (LockA) {
                    System.out.println("Thread 2: Acquired LockA!");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
```
    •	Thread 1 locks LockA, then tries to get LockB
    •	Thread 2 locks LockB, then tries to get LockA
    •	Neither thread can proceed — deadlock!

## How to Avoid/Resolve Deadlock
### Always Lock Resources in the Same Order
```java
public class DeadlockResolved {

    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {

        Runnable task = () -> {
            synchronized (LockA) {
                System.out.println(Thread.currentThread().getName() + ": Holding LockA...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (LockB) {
                    System.out.println(Thread.currentThread().getName() + ": Acquired LockB!");
                }
            }
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
```
### Why it works:
Both threads lock resources in the same order: `LockA` → `LockB`. So one thread completes before the other starts the second lock.

# How do threads communicate each other?
## Key Methods for Thread Communication in Java

| Method        | Description |
|---------------|-------------|
| `wait()`      | Causes the current thread to wait until another thread calls `notify()` or `notifyAll()` on the same object. Must be called inside a synchronized block. |
| `notify()`    | Wakes up one thread that is waiting on the object's monitor. Must be called inside a synchronized block. |
| `notifyAll()` | Wakes up all threads that are waiting on the object's monitor. Must be called inside a synchronized block. |

# What’s the difference between class lock and object lock?
## Class Lock vs Object Lock in Java

| Type            | Lock Is On           | Applies To                                   |
|------------------|----------------------|----------------------------------------------|
| **Object Lock**   | Instance (object)     | `synchronized` instance methods or blocks     |
| **Class Lock**    | Class (Class object)  | `synchronized static` methods or blocks       |

# What is join() method?
The join() method allows **one thread to wait for the completion of another thread.**
## How `join()` Works

- When you call `t.join()`, the **current thread pauses** and waits for thread `t` to **finish its execution**.
- Once `t` finishes, the current thread **resumes execution**.
- This is useful when you want one thread (e.g., `main`) to wait for other threads to complete before proceeding.

# What is yield() method
The `yield()` method in Java is part of the Thread class and is used to **suggest** that the current thread is **willing to pause** and let other threads of the same or higher priority run.
- It’s a static method in the Thread class.
- It causes the currently executing thread to pause temporarily, giving a chance for other threads to execute.
- It tells the Thread Scheduler: “I’m okay to pause now, maybe let someone else run.”
- But there’s no guarantee the thread will actually yield — it’s up to the scheduler.
- Often used for testing, debugging, or fine-tuning thread behavior.

# What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
A **ThreadPool** is a managed pool of worker threads that efficiently execute multiple tasks concurrently. Instead of creating a new thread for every task (which is costly), threads are reused from the pool.
## How Many Types of ThreadPool?

Java provides several built-in types via the `Executors` class:

| ThreadPool Type            | Method                                | Description |
|----------------------------|----------------------------------------|-------------|
| **Fixed Thread Pool**      | `Executors.newFixedThreadPool(n)`     | A pool with a fixed number of threads. Extra tasks wait in a queue. |
| **Cached Thread Pool**     | `Executors.newCachedThreadPool()`     | A pool with unlimited threads, reuses idle threads, creates new ones as needed. |
| **Single Thread Executor** | `Executors.newSingleThreadExecutor()` | One worker thread; tasks are executed sequentially. |
| **Scheduled Thread Pool**  | `Executors.newScheduledThreadPool(n)` | Can schedule tasks after a delay or periodically. |
| **Work-Stealing Pool**     | `Executors.newWorkStealingPool()`     | Uses multiple queues for efficient CPU utilization in parallel tasks. (Java 8+) |
## What is the TaskQueue in ThreadPool?
When all threads in the pool are busy, new tasks are placed into a **TaskQueue** until a thread becomes available.
### Purpose of TaskQueue:
- Temporarily stores waiting tasks.
- Helps manage load when the pool is at full capacity.
- Avoids creating too many threads at once.

# Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
## Which Library is used to create ThreadPool?
**java.util.concurrent**
## Which Interface provide main functions of thread-pool?
**ExecutorService Interface**

# How to submit a task to ThreadPool?
- Create a ThreadPool
- Submit a Runnable (no return value)
- Submit a Callable (returns a result)
- Shutdown the executor when done
```java
ExecutorService executor = Executors.newFixedThreadPool(3);

executor.execute(() -> {
    System.out.println("Running a Runnable task...");
});

Future<String> future = executor.submit(() -> {
    // Simulate some work
    Thread.sleep(1000);
    return "Callable task result!";
});

try {
    String result = future.get();  // blocks until result is available
    System.out.println("Result: " + result);
} catch (Exception e) {
    e.printStackTrace();
}

executor.shutdown();
```

# What is the advantage of ThreadPool?
## Advantages of Using ThreadPool in Java

| Benefit                      | Explanation |
|-----------------------------|-------------|
| **Thread Reuse**         | Threads are reused for multiple tasks instead of being created and destroyed repeatedly — saving time and memory. |
| **Better Resource Management** | You can control the number of threads (e.g., max 10 threads) to avoid exhausting system resources (CPU/memory). |
| **Improved Performance** | Reduces the overhead of frequent thread creation and garbage collection — especially in apps with many short-lived tasks. |
| **Task Queuing**         | Tasks can be queued when all threads are busy, enabling smooth load handling. |
| **Built-in Management**  | With `ExecutorService`, you get easy-to-use tools for scheduling, shutting down, and tracking task completion. |
| **Scalability**          | ThreadPools support scaling efficiently for both small and large workloads. |
| **Avoids Thread Exhaustion** | Limits the number of concurrent threads to prevent too many threads from crashing the JVM. |

# Difference between shutdown() and shutdownNow() methods of executor
## Difference Between `shutdown()` and `shutdownNow()` in Java
| Method           | Behavior |
|------------------|----------|
| **`shutdown()`** | Initiates an **orderly shutdown**: stops accepting new tasks, allows existing ones to finish. |
| **`shutdownNow()`** | Attempts to **immediately stop** all running tasks and returns a list of tasks that were waiting in the queue. |
## Comparison Table
| Feature                      | `shutdown()`          | `shutdownNow()`         |
|------------------------------|------------------------|--------------------------|
| Accepts new tasks?           | ❌ No                 | ❌ No                   |
| Ongoing tasks complete?      | ✅ Yes                | ❌ Not guaranteed        |
| Pending tasks in queue?      | ✅ Will execute       | ❌ Returned in a list    |
| Interrupts running threads?  | ❌ No                 | ✅ Yes                   |
| Use case                     | Graceful exit         | Forceful exit            |

# What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
## What Are Atomic Classes in Java?
Atomic classes provide a way to safely update variables in concurrent environments using lock-free, thread-safe, low-level operations (like CAS – Compare-And-Swap). They’re part of the java.util.concurrent.atomic package.
## Types of Atomic Classes
| Category              | Classes |
|-----------------------|---------|
| Atomic Primitives     | AtomicInteger, AtomicLong, AtomicBoolean |
| Atomic Arrays         | AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray |
| Atomic References     | AtomicReference<T>, AtomicStampedReference |
| Field Updaters        | AtomicIntegerFieldUpdater, etc. |
## Code Example
```java
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicFlag {
    private static AtomicBoolean isProcessing = new AtomicBoolean(false);

    public static void main(String[] args) {
        if (isProcessing.compareAndSet(false, true)) {
            System.out.println("Task started.");
            // Do task
            isProcessing.set(false);
        } else {
            System.out.println("Task is already running.");
        }
    }
}
```
## When to Use Atomic Classes
- Replacing synchronized blocks for simple variables
- Managing concurrent counters or flags
- Improving performance in concurrent scenarios

# What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Great topic! In Java, concurrent collections are specialized versions of standard collections that are thread-safe and designed for high concurrency with better performance than using synchronized wrappers. They are part of the **java.util.concurrent** package and are lock-free or fine-grained lock-based, depending on the data structure.
## Common Thread-Safe Data Structures
| Type      | Concurrent Class               | Description |
|-----------|--------------------------------|-------------|
| Map       | ConcurrentHashMap              | High-concurrency thread-safe Map |
| Queue     | ConcurrentLinkedQueue          | Non-blocking, unbounded queue |
|           | LinkedBlockingQueue            | Blocking queue with optional capacity |
|           | ArrayBlockingQueue             | Fixed-capacity blocking queue |
|           | PriorityBlockingQueue          | Blocking queue with priority sorting |
|           | SynchronousQueue               | No storage — direct handoff between threads |
|           | DelayQueue                     | Delayed tasks execution |
| Deque     | ConcurrentLinkedDeque          | Thread-safe double-ended queue |
| Set       | ConcurrentSkipListSet          | Sorted set with concurrent access |
| SortedMap | ConcurrentSkipListMap          | Sorted, concurrent map |

# What kind of locks do you know? What is the advantage of each lock?
## Types of Locks in Java and Their Advantages

| Lock Type                  | Description | Advantages |
|----------------------------|-------------|-------------|
| **synchronized**           | Built-in monitor lock | Simple, automatic lock management |
| **ReentrantLock**          | Explicit lock with more features | tryLock(), fairness, interruptibility |
| **ReentrantReadWriteLock** | Separate read/write locks | High read concurrency, useful in read-heavy apps |
| **StampedLock**            | Optimistic and write/read locking | Higher performance for reads (non-reentrant) |
| **Semaphore**              | Counting permits for resources | Controls concurrent access to limited resources |
| **CountDownLatch**         | Countdown-based coordination | Wait until operations complete |
| **CyclicBarrier**          | Barrier for thread groups | Reusable sync point for threads |
| **LockSupport**            | Low-level thread parking/unparking | Used internally by frameworks for fine control |

# What is future and completableFuture? List some main methods of ComplertableFuture.
## Future
A **Future<T>** represents **the result of an asynchronous computation** that may complete in the future.
- Introduced in Java 5 (java.util.concurrent)
- Can get the result using get() (blocking)
- Used with ExecutorService
## CompletableFuture
A **CompletableFuture<T>** is an extension of Future introduced in Java 8 that allows:
- Non-blocking, asynchronous programming
- Functional-style chaining of tasks
- Manual completion (complete())
- Exception handling

It’s part of `java.util.concurrent`.

## Common Methods of CompletableFuture

| Method                     | Description |
|----------------------------|-------------|
| `supplyAsync()`            | Async task with return value |
| `runAsync()`               | Async task without return |
| `thenApply()`              | Transform result |
| `thenAccept()`             | Consume result |
| `thenRun()`                | Run after completion |
| `thenCombine()`            | Combine with another future |
| `exceptionally()`          | Handle errors |
| `whenComplete()`           | Final action on success/failure |
| `allOf()`, `anyOf()`       | Combine multiple futures |
| `complete(value)`          | Complete manually |

# Type the code by your self and try to understand it. 
```java
public class FutureVsCompletableFuture {

    @Test
    public void testFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> futureResult = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return 10 + 20;
        });

        try {
            System.out.println("Performing other tasks...");

            int result = futureResult.get();
            System.out.println("Result from the future: " + result);

            System.out.println("----");
            for (int i = 0; i < 7; i++) {
                System.out.println("step " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    @Test
    public void testCompletableFuture() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10 + 20;
        });

        System.out.println("Performing other tasks...");

        completableFuture.thenAccept(result -> System.out.println("Result from the CompletableFuture: " + result));

        System.out.println("----");
        for (int i = 0; i < 7; i++) {
            System.out.println("step " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
```

# Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10.
```java
public class OddEventPrinter {
    private static final Object monitor = new Object();
    private static int value = 1;

    public static void main(String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    static class PrintRunnable implements Runnable {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        @Override
        public void run() {
            lock.lock();
            try   {
                while (value <= 10) {
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    condition.signalAll();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
```

# Create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run
sequence is random. 
```java
public class PrintNumber1 {
    private static int n = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printNumber());
        Thread t2 = new Thread(() -> printNumber());
        Thread t3 = new Thread(() -> printNumber());

        t1.start();
        t2.start();
        t3.start();
    }

    private static synchronized void printNumber() {
        int count = 10;
        while (count-- > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + n++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        PrintNumber1.class.notifyAll();
    }
}
```

# Completable future:
## Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
```java
import java.util.concurrent.CompletableFuture;

public class AsyncMathOperations {

    public static void main(String[] args) {

        int a = 5;
        int b = 3;

        // Asynchronously compute the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating sum...");
            return a + b;
        });

        // Asynchronously compute the product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating product...");
            return a * b;
        });

        // Print the results when done
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));

        // Wait for both to complete (just to prevent main thread from exiting early)
        CompletableFuture.allOf(sumFuture, productFuture).join();

        System.out.println("Done!");
    }
}
```

## Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing.
```java
import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;

public class OnlineStoreAPI {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static CompletableFuture<JSONArray> fetchProducts() {
        return fetchAsync("https://jsonplaceholder.typicode.com/posts");
    }

    public static CompletableFuture<JSONArray> fetchReviews() {
        return fetchAsync("https://jsonplaceholder.typicode.com/comments");
    }

    public static CompletableFuture<JSONArray> fetchInventory() {
        return fetchAsync("https://jsonplaceholder.typicode.com/users");
    }

    public static CompletableFuture<JSONArray> fetchAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(JSONArray::new);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Fetching data asynchronously...");

        CompletableFuture<JSONArray> productsFuture = fetchProducts();
        CompletableFuture<JSONArray> reviewsFuture = fetchReviews();
        CompletableFuture<JSONArray> inventoryFuture = fetchInventory();

        // Wait for all futures to complete
        CompletableFuture<Void> allDone = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allDone.thenRun(() -> {
            try {
                JSONArray products = productsFuture.get();
                JSONArray reviews = reviewsFuture.get();
                JSONArray inventory = inventoryFuture.get();

                // Simulate merging or processing the data
                System.out.println("Products fetched: " + products.length());
                System.out.println("Reviews fetched: " + reviews.length());
                System.out.println("Inventory fetched: " + inventory.length());

                // You can merge and process them here as needed

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();

        System.out.println("All data fetched and ready for processing.");
    }
}
```

## Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.
```java
import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;

public class OnlineStoreAPIWithExceptionHandling {

    private static final HttpClient client = HttpClient.newHttpClient();

    // Fetch products (simulating API call)
    public static CompletableFuture<JSONArray> fetchProducts() {
        return fetchAsync("https://jsonplaceholder.typicode.com/posts")
                .exceptionally(ex -> {
                    System.err.println("Error fetching products: " + ex.getMessage());
                    return new JSONArray();  // Return an empty JSON array as default
                });
    }

    // Fetch reviews (simulating API call)
    public static CompletableFuture<JSONArray> fetchReviews() {
        return fetchAsync("https://jsonplaceholder.typicode.com/comments")
                .exceptionally(ex -> {
                    System.err.println("Error fetching reviews: " + ex.getMessage());
                    return new JSONArray();  // Return an empty JSON array as default
                });
    }

    // Fetch inventory (simulating API call)
    public static CompletableFuture<JSONArray> fetchInventory() {
        return fetchAsync("https://jsonplaceholder.typicode.com/users")
                .exceptionally(ex -> {
                    System.err.println("Error fetching inventory: " + ex.getMessage());
                    return new JSONArray();  // Return an empty JSON array as default
                });
    }

    // Async fetch method to simulate fetching data from an API
    public static CompletableFuture<JSONArray> fetchAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(response -> new JSONArray(response))
                .exceptionally(ex -> {
                    System.err.println("Error during HTTP request: " + ex.getMessage());
                    return new JSONArray();  // Return an empty JSON array on failure
                });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Fetching data asynchronously...");

        // Fetch the data concurrently
        CompletableFuture<JSONArray> productsFuture = fetchProducts();
        CompletableFuture<JSONArray> reviewsFuture = fetchReviews();
        CompletableFuture<JSONArray> inventoryFuture = fetchInventory();

        // Wait for all futures to complete
        CompletableFuture<Void> allDone = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allDone.thenRun(() -> {
            try {
                // Get results and process
                JSONArray products = productsFuture.get();
                JSONArray reviews = reviewsFuture.get();
                JSONArray inventory = inventoryFuture.get();

                // Output the result sizes
                System.out.println("Products fetched: " + products.length());
                System.out.println("Reviews fetched: " + reviews.length());
                System.out.println("Inventory fetched: " + inventory.length());

                // Further processing can happen here...

            } catch (Exception e) {
                System.err.println("Error processing results: " + e.getMessage());
            }
        }).join();

        System.out.println("All data fetched and ready for processing.");
    }
}
```