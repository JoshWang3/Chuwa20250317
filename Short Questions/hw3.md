# Multithreading
## 2. Thread-safe Singleton Class
```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
## 3. Create a New Thread
1. Extending the `Thread` class.
2. Implementing the `Runnable` interface.
3. Implementing the `Callable` interface.
4. Using a Thread Pool (ExecutorService).

## 4. Difference between Runnable and Callable


|               | Runnable                                   | Callable                                       |
|---------------------|--------------------------------------------|------------------------------------------------|
| Return Value        | Does not return a result                    | Returns a result (via `Future`)                 |
| Exception Handling  | Cannot throw checked exceptions             | Can throw checked exceptions                    |
| Method Signature    | `public void run()`                         | `public V call() throws Exception`              |
| Functional Interface| Yes (can be used with lambda expressions)   | Yes (can be used with lambda expressions)        |
| Interface Type      | Part of `java.lang` package                 | Part of `java.util.concurrent` package           |

---

## 5. Difference between `t.start()` and `t.run()`
- `t.start()`: Creates a new thread and calls the `run()` method in that new thread.
- `t.run()`: Calls the `run()` method on the current thread without creating a new one.

---

## 6. Which is better: Thread or Runnable?
- Use `Runnable` when you need to share resources between threads.
- Use `Thread` when you want to extend the `Thread` class directly.

---

## 7. Thread Statuses
- **New**: Thread is created but not started.
- **Runnable**: Thread is ready to run.
- **Blocked**: Waiting to acquire a monitor lock.
- **Waiting**: Waiting indefinitely for another thread.
- **Timed Waiting**: Waiting for another thread for a specific time.
- **Terminated**: Thread has completed execution.

---

## 8. Deadlock and Resolution
Deadlock occurs when two or more threads block each other while waiting for resources.

### Deadlock Resolution:
- Avoid nested locks.
- Use `tryLock()` with a timeout.
- Maintain a strict order when acquiring locks.

---

## 9. Thread Communication
Threads communicate using:
- `wait()`: Releases the lock and waits.
- `notify()`: Wakes up a single waiting thread.
- `notifyAll()`: Wakes up all waiting threads.

---

## 10. Class Lock vs Object Lock
- **Class Lock**: Acquired on the class object itself.
- **Object Lock**: Acquired on an instance of the class.

---

## 11. `join()` Method
Allows one thread to wait for the completion of another thread.


---

## 12. `yield()` Method
Pauses the currently executing thread to give a chance to other threads of the same priority.

---

## 13. ThreadPool and TaskQueue
- **ThreadPool**: Manages a group of reusable threads.
- **TaskQueue**: Holds tasks waiting to be executed by the thread pool.

### Types of ThreadPool:
- FixedThreadPool
- CachedThreadPool
- SingleThreadExecutor
- ScheduledThreadPool

---

## 14. Library and Interface in ThreadPool
### Library:
- The **java.util.concurrent** library is used to create and manage thread pools in Java.

### Main Interface:
- The **ExecutorService** interface provides the main functions of a thread pool.

### Key Implementations of ExecutorService:
- **ThreadPoolExecutor**: Provides a flexible and configurable thread pool.
- **ScheduledThreadPoolExecutor**: Supports scheduling tasks at fixed rates or with delays.

### Main Methods in ExecutorService:
- `execute(Runnable task)`: Executes a Runnable task.
- `submit(Callable task)`: Submits a task for execution and returns a Future representing the result.
- `shutdown()`: Initiates an orderly shutdown.
- `shutdownNow()`: Attempts to stop all actively executing tasks.
- `invokeAll(Collection<Callable>)`: Executes a batch of tasks.
- `invokeAny(Collection<Callable>)`: Executes a batch and returns the result of one successful execution.

---

## 15.Submit a Task to ThreadPool
1. **Runnable Task Submission:**
   - Use the `execute()` method to submit a Runnable task.
2. **Callable Task Submission:**
   - Use the `submit()` method to submit a Callable task.

---

## 16.Advantage of ThreadPool
1. **Improved Performance:**
   - Reduces the overhead of creating and destroying threads repeatedly.
   - Uses a pool of reusable threads.

2. **Efficient Resource Management:**
   - Controls the number of concurrent threads.
   - Prevents the application from running out of resources.

3. **Better Responsiveness:**
   - Maintains a pool of pre-created threads ready to be used.
   - Reduces latency for processing incoming tasks.

4. **Flexibility:**
   - Supports different types of thread pools (fixed, cached, single, scheduled).
   - Customizable through the `ThreadPoolExecutor`.

5. **Scalability:**
   - Suitable for applications with high concurrency.
   - Adjusts the number of threads dynamically based on workload.

---

## 17. Shutdown Methods
- **shutdown()**: Initiates an orderly shutdown.
- **shutdownNow()**: Attempts to stop all actively executing tasks.

---

## 18. Atomic Classes
Atomic classes provide a way to perform atomic operations without using synchronized blocks.

### Types of Atomic Classes:
1. **AtomicInteger:** Atomic operations on integer values.
2. **AtomicLong:** Atomic operations on long values.
3. **AtomicBoolean:** Atomic operations on boolean values.
4. **AtomicReference:** Atomic operations on object references.
5. **AtomicIntegerArray:** Atomic operations on arrays of integers.
6. **AtomicLongArray:** Atomic operations on arrays of long values.
7. **AtomicReferenceArray:** Atomic operations on arrays of object references.
8. **AtomicStampedReference:** Supports atomic updates with a version stamp to avoid the ABA problem.
9. **AtomicMarkableReference:** Similar to `AtomicReference` but includes a boolean mark.


### Use Cases:
- **Counters:** AtomicInteger for thread-safe counters.
- **Flags:** AtomicBoolean for status flags.
- **Reference Updates:** AtomicReference for updating object references atomically.
- **Version Control:** AtomicStampedReference to prevent the ABA problem.

#### Example:

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);

        // Increment and get the value
        int newValue = atomicInt.incrementAndGet();
        System.out.println("Incremented value: " + newValue);

        // Get and increment the value
        int oldValue = atomicInt.getAndIncrement();
        System.out.println("Old value before increment: " + oldValue);
        System.out.println("New value after increment: " + atomicInt.get());

        // Compare and set
        boolean success = atomicInt.compareAndSet(2, 5);
        System.out.println("Compare and set successful: " + success);
        System.out.println("Current value: " + atomicInt.get());
    }
}
```
---

## 19. Concurrent Collections
- **ConcurrentHashMap**
- **CopyOnWriteArrayList**
- **ConcurrentLinkedQueue**

### Advantages:
- Thread-safe without explicit synchronization.
- Better performance in multi-threaded environments.

---

## 20. Types of Locks
- **ReentrantLock**: Allows the same thread to acquire the lock multiple times.
- **ReadWriteLock**: Separates read and write locks.
- **StampedLock**: Optimized for read-heavy scenarios.

---

## 21. Future and CompletableFuture
- **Future**: Represents the result of an asynchronous computation.
- **CompletableFuture**: Extends `Future` and allows chaining of tasks.

### Key Methods of CompletableFuture:
- `supplyAsync()`
- `thenApply()`
- `thenAccept()`
- `exceptionally()`

---



