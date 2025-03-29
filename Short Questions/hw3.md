# hw3
### 1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock
### 2. Write a thread-safe singleton class
- The `volatile` keyword ensures visibility and atomicity.
- The double-check locking prevents unnecessary synchronization after the instance is created.
- Using `synchronized` only when necessary ensures performance efficiency.
```java
public class Main {
    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();
    }
}

class Singleton {
    private static volatile Singleton instance; // Ensures visibility across threads

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (Singleton.class) { // Locking only when necessary
                if (instance == null) { // Second check (thread-safe)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Singleton Instance: " + this);
    }
}
```
### 3. How to create a new thread(Please also consider Thread Pool approach)?
```java
public class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
```
Thread Pool approach:
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}
```
### 4. Difference between Runnable and Callable?
| Feature | Runnable | Callable\<T> |
| --- | --- | --- |
| Return Type | void | Returns a value (T) |
| Exception Handling | Cannot throw checked exceptions | Can throw checked exceptions |
| Execution with ExecutorService | execute(Runnable task) | submit(Callable<T> task) -> Future<T> |
| Blocking on Result | No result, so no need to block | Uses Future.get() to retrieve result (blocking) |

Runnable:
```java
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable running: " + Thread.currentThread().getName());
    }
}
```
Callable:
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future = executor.submit(new MyCallable());
        System.out.println("Task submitted, waiting for result...");
        System.out.println("Result: " + future.get());
        executor.shutdown();
    }
}

class MyCallable implements Callable<Integer> {
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 1;
    }
}
```
### 5. What is the difference between t.start() and t.run()?
- **t.start()**: Calls the run() method in a new thread, so it runs asynchronously.
- **t.run()**: Calls the run() method directly in the current method, so it runs sequentially within the same thread.
### 6. Which way of creating threads is better: Thread class or Runnable interface?
Implementing Runnable is the better approach:
- A class can **extend another class** while implementing Runnable
- Better for **thread pooling** (works well with `ExecutorService`)
- The Runnable task is separate from the Thread execution
- **Reusability**: Support multiple threads to share the same task
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++)  executor.execute(new MyTask(i));
        executor.shutdown();
    }
}

class BaseClass {
    void printBaseMessage() {
        System.out.println("Base class method executed by: " + Thread.currentThread().getName());
    }
}

class MyTask extends BaseClass implements Runnable {
    private final int taskId;

    public MyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        printBaseMessage(); // Calling the method from the extended class
        System.out.println("Executing Task ID: " + taskId + " on Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
### 7. What are the thread statuses?
Java threads go through six different states during their lifecycle. These states are defined in the `Thread.State` enum.
1. **NEW**: Thread is created but not started yet.
2. **RUNNABLE**: Thread is ready to run but waiting for CPU time.
3. **BLOCKED**: Thread is waiting to enter a synchronized block.
4. **WAITING**: Thread is waiting indefinitely for another thread’s signal.
5. **TIMED_WAITING**: Thread is waiting for a fixed time.
6. **TERMINATED**: Thread has finished execution.

Transitions:
- `NEW` -> `RUNNABLE` (start())
- `RUNNABLE` -> `BLOCKED` (Trying to enter a synchronized block)
- `BLOCKED` -> `RUNNABLE` (Lock released)
- `RUNNABLE` -> `WAITING/TIMED_WAITING` (wait(), join(), sleep())
- `WAITING/TIMED_WAITING` -> RUNNABLE (notify(), timeout ends)
- Any State -> `TERMINATED` (When execution ends)
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000); // Moves to TIMED_WAITING
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("State after creation: " + t1.getState()); // NEW
        t1.start();
        System.out.println("State after start(): " + t1.getState()); // RUNNABLE

        Thread.sleep(500);
        System.out.println("State while sleeping: " + t1.getState()); // TIMED_WAITING

        t1.join(); // Waits for t1 to finish
        System.out.println("State after completion: " + t1.getState()); // TERMINATED
    }
}
```
### 8. Demonstrate deadlock and how to resolve it in Java code.
- A deadlock occurs when two or more threads are blocked forever, waiting for each other to release a lock. This creates a circular dependency, and neither thread can proceed, resulting in a deadlock.
- One way to resolve deadlock is to enforce a consistent lock ordering. Threads will always acquire the locks in the same order, avoiding circular dependencies.
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();
        Thread t1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " locked ResourceA");
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + " locked ResourceB");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " locked ResourceA");
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + " locked ResourceB");
                }
            }
        });
        t1.start();
        t2.start();
    }
}

class ResourceA {
}

class ResourceB {
}
```
### 9. How do threads communicate each other?
1. Shared Memory
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data.increment();
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " reads count: " + data.getCount());
                try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

class SharedData {
    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " incremented count to: " + count);
    }

    public synchronized int getCount() {
        return count;
    }
}
```
2. Inter-thread Signaling (Using `wait()`, `notify()` or `notifyAll()`)
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer();
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    buffer.consume();
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}

class SharedBuffer {
    private int item = -1;
    private boolean isEmpty = true;

    public synchronized void produce(int value) throws InterruptedException {
        while (!isEmpty) {
            wait();
        }
        item = value;
        isEmpty = false;
        System.out.println("Produced: " + item);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (isEmpty) {
            wait();
        }
        System.out.println("Consumed: " + item);
        item = -1;
        isEmpty = true;
        notify();
    }
}
```
### 10. What’s the difference between class lock and object lock?
| Feature | Runnable | Callable\<T> |
| --- | --- | --- |
| Scope | Locks a specific instance of the class | Locks the class itself (shared among all instances) |
| Lock Target | The instance object (this) | The Class object (ClassName.class) |
| Synchronization | Synchronizes on instance methods | Synchronizes on static methods |

Object lock:
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        Thread t1 = new Thread(() -> counter1.increment()); // Count: 1
        Thread t2 = new Thread(() -> counter2.increment()); // Count: 1
        t1.start();
        t2.start();
    }
}

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println("Count: " + count);
    }
}
```
Class lock:
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> Counter.increment());
        Thread t2 = new Thread(() -> Counter.increment());
        t1.start(); // Count: 1
        t2.start(); // Count: 2
    }
}

class Counter {
    private static int count = 0;

    public static synchronized void increment() {
        count++;
        System.out.println("Count: " + count);
    }
}
```
### 11. What is join() method?
The join() method is used to make one thread wait for another thread to complete its execution.
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
        thread1.join();
        System.out.println("Main thread: thread1 has finished.");
        thread2.join();
        System.out.println("Main thread: thread2 has finished.");
    }
}

class MyThread extends Thread {
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " has finished execution.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
### 12. What is yield() method?
The yield() method is a static method of the Thread class that is used to pause the currently executing thread and allow other threads to execute. However, calling yield() does not guarantee that the current thread will be suspended.
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
    }
}

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is executing " + i);
            Thread.yield();
        }
    }
}
```
### 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
- A ThreadPool is a collection of worker threads that efficiently manage and reuse threads for executing tasks.  
- The common types of thread pools:
    1. FixedThreadPool
    2. CachedThreadPool
    3. SingleThreadExecutor
    4. ScheduledThreadPool
    5. WorkStealingPool
- A TaskQueue is an internal queue used by the ThreadPool to hold tasks that are waiting to be executed.
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();
    }
}
```
### 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
- The `java.util.concurrent` library provides classes and interfaces to create and manage thread pools.  
- The most commonly used class to create a thread pool is `Executors`, which provides static factory methods to create different types of thread pools.  
- `ExecutorService` is the main interface that provides the core functions for thread pooling in Java. It extends the `Executor` interface and provides methods (`submit()`, `invokeAll()`, `shutdown()`, `shutdownNow()`) for managing lifecycle and scheduling tasks.
### 15. How to submit a task to ThreadPool?
Use the `submit()` method provided by the ExecutorService interface. It returns a Future object, which can be used to monitor the status or retrieve the result of the task.
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        executor.shutdown();
    }
}
```
### 16. What is the advantage of ThreadPool?
1. **Thread Reusability**: Threads in the pool are reused, reducing the overhead of creating and destroying threads.
2. **Improved Performance**: Avoids the performance cost of frequently creating threads, improving task execution efficiency.
3. **Resource Management**: Limits the number of threads to prevent resource overload and ensures efficient system resource usage.
4. **Task Queuing**: Tasks are queued and executed in order, ensuring orderly processing.
5. **Concurrency Control**: Allows you to control the number of concurrent threads, preventing excessive threads from degrading performance.
6. **Simplified Code**: Automatically manages threads, simplifying multithreading and reducing errors.
### 17. Difference between shutdown() and shutdownNow() methods of executor
| Feature | shutdown() | shutdownNow() |
| --- | --- | --- |
| New Task Submission | No new tasks are accepted. | No new tasks are accepted. |
| Executing Tasks | Executes all tasks in progress. | Attempts to stop tasks in progress by interrupting them. |
| Pending Tasks | All pending tasks are executed. | Returns a list of tasks that were waiting to be executed. |
| Interrupting Tasks | Does not interrupt running tasks. | Attempts to interrupt running tasks. |
| Completion | Waits for tasks to finish. | Forces shutdown immediately, might not wait for completion. |

shutdown():
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Task 1"));
        executor.submit(() -> System.out.println("Task 2"));
        executor.shutdown();
    }
}
```
shutdownNow():
```java
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Task 1");
            } catch (InterruptedException e) {
                System.out.println("Task 1 interrupted");
            }
        });
        executor.submit(() -> System.out.println("Task 2"));
        executor.submit(() -> System.out.println("Task 3"));
        List<Runnable> pendingTasks = executor.shutdownNow();
        System.out.println("Pending tasks: " + pendingTasks);
    }
}
```
### 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
- Atomic classes in Java provide a way to perform thread-safe operations on variables without using synchronization mechanisms like synchronized blocks or Locks.
- Types of Atomic classes:
    1. `AtomicInteger`: An atomic variable of type `int`.
    2. `AtomicLong`: An atomic variable of type `long`.
    3. `AtomicBoolean`: An atomic variable of type `boolean`.
    4. `AtomicReference\<T>`: An atomic variable that can hold a reference to any object of type `T`.
    5. `AtomicIntegerArray`: An atomic array of `int`s.
    6. `AtomicLongArray`: An atomic array of `long`s.
    7. `AtomicReferenceArray\<E>`: An atomic array of references.
- Use atomic classes when you need to ensure atomicity in multi-threaded environments.
```java
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);
        // Increment and get the new value
        System.out.println("Increment and get: " + atomicInt.incrementAndGet());    // Increment and get: 1
        // Decrement and get the new value
        System.out.println("Decrement and get: " + atomicInt.decrementAndGet());    // Decrement and get: 0
        // Add and get the result
        System.out.println("Add 10 and get: " + atomicInt.addAndGet(10));     // Add 10 and get: 10
        // Compare and set
        boolean success = atomicInt.compareAndSet(10, 20);
        System.out.println("Compare and set result: " + success);                   // Compare and set result: true
        System.out.println("Current value: " + atomicInt.get());                    // Current value: 20
        // Get and set
        System.out.println("Old value: " + atomicInt.getAndSet(50));       // Old value: 20
        System.out.println("New value: " + atomicInt.get());                        // New value: 50
    }
}
```
### 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
- Concurrent collections are designed to handle multi-threaded access and provide thread-safe operations for data structures in concurrent programming.
- Common concurrent data structure:
    1. CopyOnWriteArrayList
    2. CopyOnWriteArraySet
    3. ConcurrentHashMap
    4. BlockingQueue
    5. ConcurrentSkipListMap
    6. ConcurrentLinkedQueue
    7. SynchronousQueue
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.get(0));    // 1
    }
}
```
### 20. What kind of locks do you know? What is the advantage of each lock?
1. Reentrant Lock
    - Fairness: Can implement fair locks (first-come, first-served).
    - Interruptible Locking: Allows interrupting threads waiting for the lock (`lockInterruptibly()`).
    - Try Lock: Provides `tryLock()`, which allows attempting to acquire the lock without blocking indefinitely.
2. Read/Write Lock
    - Higher Concurrency: Allows multiple threads to read at the same time, improving performance for read-heavy workloads.
    - Granular Control: Separates read and write operations, allowing multiple readers but exclusive writers.
3. Stamped Lock
    - Optimistic Locking: Supports optimistic reading, where threads can read without acquiring the lock if there are no writes.
    - Better Concurrency: Suitable for systems with a high read-to-write ratio.
    - Improved Performance: Avoids the overhead of acquiring a lock when no updates are happening.
4. Spin Lock
    - Low Latency: No context switching overhead, as the thread simply keeps checking the lock.
    - Efficient for Short Critical Sections: Effective for very short sections of code where the lock hold time is small.
### 21. What is future and completableFuture? List some main methods of ComplertableFuture.
- **Future (`java.util.concurrent.Future`)**: Allows retrieving the result once the asynchronous computation is complete. It provides blocking methods (`get()`) to retrieve the result, but doesn't provide chaining, combining, or exception handling.
- **CompletableFuture (`java.util.concurrent.CompletableFuture`)**: Extends `Future` and supports chaining, combining multiple async tasks, and exception handling.
- Main methods of ComplertableFuture:
    - `supplyAsync(Supplier<T>)`
    - `runAsync(Runnable)`
    - `thenApply(Function<T, R>)`
    - `thenAccept(Consumer<T>)`
    - `thenRun(Runnable)`
    - `thenCombine(CompletableFuture, BiFunction)`
    - `exceptionally(Function<Throwable, ? extends T>)`
    - `whenComplete(BiConsumer<T, Throwable>)`
    - `join()`
### 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
### 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
1. One solution use synchronized and wait notify
```java
public class OddEvenPrinter1 {
    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);
        oddThread.start();
        evenThread.start();
    }
}

class OddEvenPrinter {
    private int count = 1;
    private final int MAX = 10;

    public synchronized void printOdd() {
        while (count < MAX) {
            while (count % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + count);
            count++;
            notify();
        }
    }

    public synchronized void printEven() {
        while (count <= MAX) {
            while (count % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + count);
            count++;
            notify();
        }
    }
}
```
2. One solution use ReentrantLock and await, signal
```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter2 {
    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);
        oddThread.start();
        evenThread.start();
    }
}

class OddEvenPrinter {
    private static final int MAX = 10;
    private int count = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printOdd() {
        try {
            lock.lock();
            while (count <= MAX) {
                while (count % 2 == 0) {
                    condition.await();
                }
                if (count <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    condition.signal();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void printEven() {
        try {
            lock.lock();
            while (count <= MAX) {
                while (count % 2 != 0) {
                    condition.await();
                }
                if (count <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    condition.signal();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
```
### 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
```java
public class PrintNumberTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> PrintNumber.printNumber());
        Thread t2 = new Thread(() -> PrintNumber.printNumber());
        Thread t3 = new Thread(() -> PrintNumber.printNumber());

        t1.start();
        t2.start();
        t3.start();
    }
}

class PrintNumber {
    private static int n = 1;

    public static synchronized void printNumber() {
        int count = 10;
        while (count-- > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + n++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        PrintNumber.class.notifyAll();
    }
}
```