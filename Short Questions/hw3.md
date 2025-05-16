1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock
   
2. Write a thread-safe singleton class
```Java
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
```


3. How to create a new thread(Please also consider Thread Pool approach)?
```Java
// using Thread class
Thread thread = new Thread(() -> {
    System.out.println("Running in a separate thread");
});
thread.start();


// using ExecutorService(thread pool)
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.submit(() -> System.out.println("Running via thread pool"));
        executor.shutdown();
```


4. Difference between Runnable and Callable?

| Feature      | `Runnable`                      | `Callable<V>`                |
| ------------ | ------------------------------- | ---------------------------- |
| Return Value | No return (`void`)              | Returns a value (`V`)        |
| Exception    | Cannot throw checked exceptions | Can throw checked exceptions |
| Method       | `run()`                         | `call()`                     |
| Used in      | `Thread`, `ExecutorService`     | `ExecutorService`, `Future`  |



5. What is the difference between t.start() and t.run()?

- t.start(): Starts a new thread; invokes run() in that new thread.

- t.run(): Executes the run() method in the current thread; no new thread is started.


6. Which way of creating threads is better: Thread class or Runnable interface?

Runnable is better:
- Supports multiple inheritance (your class can extend another class too).
- Encourages separation of task (Runnable) and execution (Thread).


7. What are the thread statuses?
```Java
Java thread states (from Thread.State enum):
NEW: Thread created but not started.
RUNNABLE: Ready or running.
BLOCKED: Waiting to enter a synchronized block.
WAITING: Waiting indefinitely (wait(), join()).
TIMED_WAITING: Waiting for a specified time (sleep(), join(timeout)).
TERMINATED: Thread has finished execution.
```


8. Demonstrate deadlock and how to resolve it in Java code.
```Java
public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("Method 1");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("Method 2");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockExample d = new DeadlockExample();
        new Thread(d::method1).start();
        new Thread(d::method2).start();
    }
}

```
To resolve: always lock in the same order
```Java
// Acquire lock1 before lock2 in both methods
```


9. How do threads communicate with each other?
- wait(): Releases the lock and waits.
- notify(): Wakes up one waiting thread.
- notifyAll(): Wakes up all waiting threads.

10. What’s the difference between class lock and object lock?

| Lock Type       | Description                                                                                         |
| --------------- | --------------------------------------------------------------------------------------------------- |
| **Object Lock** | Acquired on a specific instance using `synchronized(instance)` or `synchronized non-static methods` |
| **Class Lock**  | Acquired on the class object using `synchronized(ClassName.class)` or `synchronized static methods` |

```Java
synchronized (this) { } // Object lock
synchronized (MyClass.class) { } // Class lock
```

11. What is join() method?

* The `join()` method **waits** for a thread to die.
* It allows one thread to wait for the completion of another.

```java
Thread t1 = new Thread(() -> {
    System.out.println("Thread1 running...");
});

t1.start();
t1.join();  // Main thread waits for t1 to finish
System.out.println("Main continues after t1");
```


12. what is yield() method

* It causes the **currently executing thread to pause** and give a chance to other threads of equal priority.
* It doesn't guarantee another thread will run.

```java
Thread.yield();
```

13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

* ThreadPool is a pool of worker threads that execute submitted tasks efficiently.

### Types of ThreadPools:

* `newFixedThreadPool(int nThreads)`
* `newCachedThreadPool()`
* `newSingleThreadExecutor()`
* `newScheduledThreadPool(int corePoolSize)`

### TaskQueue:

* Internally used by thread pool to hold tasks before they are executed (usually a **BlockingQueue** like `LinkedBlockingQueue`).


14. Which library and interface for ThreadPool?

* **Library**: `java.util.concurrent`
* **Interface**: `ExecutorService`


15. How to submit a task to ThreadPool?

```java
ExecutorService executor = Executors.newFixedThreadPool(3);

// Using Runnable
executor.execute(() -> System.out.println("Task using execute"));

// Using Callable
Future<String> future = executor.submit(() -> "Task using submit");
System.out.println(future.get());

executor.shutdown();
```

16. What is the advantage of ThreadPool?

* Reuses threads → reduces overhead.
* Limits concurrent threads.
* Separates task submission and execution.
* Improves performance in resource-intensive apps.


17. Difference between `shutdown()` and `shutdownNow()`

| Method          | Description                                                             |
| --------------- | ----------------------------------------------------------------------- |
| `shutdown()`    | Gracefully shuts down after completing current tasks.                   |
| `shutdownNow()` | Immediately stops ongoing tasks and returns unexecuted tasks as a list. |


18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic
    classes and its main methods. when to use it?

Atomic classes ensure **lock-free** thread-safe operations on single variables.

Common Atomic Classes:

* `AtomicInteger`
* `AtomicLong`
* `AtomicBoolean`
* `AtomicReference`

Example:

```java
import java.util.concurrent.atomic.AtomicInteger;

AtomicInteger count = new AtomicInteger(0);

count.incrementAndGet(); // ++
count.decrementAndGet(); // --
count.get();             // Read value
```
**Use when** you need thread-safe counters or flags without using synchronization.


19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

Thread-safe collections designed for concurrent access.

Examples:

* `ConcurrentHashMap`
* `CopyOnWriteArrayList`
* `ConcurrentLinkedQueue`
* `BlockingQueue` (e.g., `ArrayBlockingQueue`, `LinkedBlockingQueue`)


20. What kind of locks do you know?

| Lock Type       | Description                                               |
| --------------- | --------------------------------------------------------- |
| `synchronized`  | Intrinsic lock on object/class                            |
| `ReentrantLock` | Explicit lock with more control (e.g., tryLock, fairness) |
| `ReadWriteLock` | Allows multiple readers or one writer                     |
| `StampedLock`   | More efficient read/write locking                         |
**ReentrantLock** allows timed, interruptible lock attempts.


21. What is future and completableFuture? List some main methods of ComplertableFuture.

`Future`:

* Represents the result of an async computation.
* Requires blocking `get()` to retrieve result.

`CompletableFuture`:

* Non-blocking, functional way to handle async computations.
* Supports chaining, exception handling, etc.

Common Methods:

* `supplyAsync()`
* `thenApply()`
* `thenAccept()`
* `thenCombine()`
* `exceptionally()`

Example:

```java
CompletableFuture.supplyAsync(() -> 5)
    .thenApply(x -> x * 2)
    .thenAccept(System.out::println);
```


22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)


23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in
    com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
    [1]One solution use synchronized and wait notify
    [2]One solution use ReentrantLock and await, signal
```Java
Thread-0: 1
Thread-1: 2
Thread-0: 3
Thread-1: 4
Thread-0: 5
Thread-1: 6
Thread-0: 7
Thread-1: 8
Thread-0: 9
Thread-1: 10
Process finished with exit code 0
```
```Java
public class OddEvenPrinterWithWaitNotify {
    private final int MAX = 10;
    private int num = 1;
    private final Object lock = new Object();

    public static void main(String[] args) {
        OddEvenPrinterWithWaitNotify printer = new OddEvenPrinterWithWaitNotify();

        Thread t1 = new Thread(() -> printer.printOdd(), "Thread-0");
        Thread t2 = new Thread(() -> printer.printEven(), "Thread-1");

        t1.start();
        t2.start();
    }

    public void printOdd() {
        synchronized (lock) {
            while (num <= MAX) {
                if (num % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + ": " + num++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            lock.notify(); // 防止线程永久挂起
        }
    }

    public void printEven() {
        synchronized (lock) {
            while (num <= MAX) {
                if (num % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + num++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            lock.notify(); // 防止线程永久挂起
        }
    }
}
```
```Java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterWithLock {
    private final int MAX = 10;
    private int num = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        OddEvenPrinterWithLock printer = new OddEvenPrinterWithLock();

        Thread t1 = new Thread(() -> printer.printOdd(), "Thread-0");
        Thread t2 = new Thread(() -> printer.printEven(), "Thread-1");

        t1.start();
        t2.start();
    }

    public void printOdd() {
        lock.lock();
        try {
            while (num <= MAX) {
                if (num % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + ": " + num++);
                    condition.signal();
                } else {
                    condition.await();
                }
            }
            condition.signal(); // 防止另一线程阻塞
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void printEven() {
        lock.lock();
        try {
            while (num <= MAX) {
                if (num % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + num++);
                    condition.signal();
                } else {
                    condition.await();
                }
            }
            condition.signal(); // 防止另一线程阻塞
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}

```



24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run
    sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
```Java
Thread-0: 1
Thread-0: 2
Thread-0: 3
Thread-0: 4
Thread-0: 5
Thread-0: 6
Thread-0: 7
Thread-0: 8
Thread-0: 9
Thread-0: 10
Thread-2: 11
Thread-2: 12
Thread-2: 13
Thread-2: 14
Thread-2: 15
Thread-2: 16
Thread-2: 17
Thread-2: 18
Thread-2: 19
Thread-2: 20
Thread-1: 21
Thread-1: 22
Thread-1: 23
Thread-1: 24
Thread-1: 25
Thread-1: 26
Thread-1: 27
Thread-1: 28
Thread-1: 29
Thread-1: 30
```
```Java
public class PrintNumber1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new NumberPrinter(1, 10), "Thread-0");
        Thread t2 = new Thread(new NumberPrinter(11, 20), "Thread-2");
        Thread t3 = new Thread(new NumberPrinter(21, 30), "Thread-1");

        t1.start();
        t2.start();
        t3.start();
    }

    static class NumberPrinter implements Runnable {
        private final int start;
        private final int end;

        public NumberPrinter(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(50); // Optional: makes output more readable & realistic
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}


```
25. completable future:
- Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum
   and product of two integers, and print the results.
- Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,
   reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
   data for further processing. (需要找public api去模拟)

[1]Sign In to Developer.BestBuy.com 

[2]Best Buy Developer API Documentation (bestbuyapis.github.io)

[3]可以⽤fake api https://jsonplaceholder.typicode.com/

[4]Github public api: https://api.github.com/users/your-user-name/repos

- Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API
   call, return a default value and log the exception information.