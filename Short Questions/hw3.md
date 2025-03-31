# HW3
1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock
2. Write a thread-safe singleton class
```java
package com.chuwa.practice;
public class MySingleton {
    private static volatile MySingleton instance;
    private MySingleton() {
        System.out.println("Init MySingleton");
    }
    public static MySingleton getInstance() {
        if (instance == null) {
            synchronized (MySingleton.class) {
                if (instance == null) {
                    instance = new MySingleton();
                }
            }
        }
        return instance;
    }
}
```
3. How to create a new thread(Please also consider Thread Pool approach)?
- Extend `Thread`
     ```java
     class MyThread extends Thread {
         public void run() {
             System.out.println("Mythread is running!");
         }
     }

     MyThread t1 = new MyThread();
     t1.start(); 
     ```
- Implements `Runnable`
    ```java
    class MyRunnable implements Runnable {
    public void run() {
        System.out.println("MyRunnable in running!");
    }
    }
    Thread t2 = new Thread(new MyRunnable());
    t2.start();
    ```
- Using lambda
  ```java
  Thread t3 = new Thread(() -> {
    System.out.println("Mythread is running!");
  })
  t3.start();
  ```
- Thread Pool
  ```java
  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 5; i++) {
        int taskId = i;
        pool.execute(() -> {
            System.out.println("Task " + taskId + " run by " + Thread.currentThread().getName());
        });
    }
    pool.shutdown();
  }
  ```
4. Difference between Runnable and Callable?
   - Both interfaces
   - Runnable: do something but not return a result
   - Callable: do something and return a result, can throw checked exceptions.
5. What is the difference between t.start() and t.run()?
   - start: create a new thread in JVM, calls run automatically on that thread. then runs parallel with current thread.
   - run: runs in the current thread, block the current thread.
6. Which way of creating threads is better: Thread class or Runnable interface?
   - Runnable. More flexible, since Java do not allow extend mulitple class, but allows implements mulitple interface.
7. What are the thread statuses?
   - six states: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
8. Demonstrate deadlock and how to resolve it in Java code.
   ```java
      public static void main(String[] args) {
        // Thread 1: locks A, then B
        Thread thread1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 1 locked lockA");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (lockB) {
                    System.out.println("Thread 1 locked lockB");
                }
            }
        });

        // Thread 2: locks B, then A (opposite order)
        Thread thread2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread 2 locked lockB");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (lockA) {
                    System.out.println("Thread 2 locked lockA");
                }
            }
        });

        thread1.start();
        thread2.start();
      }
   ```
   Resolution, make both threads lock objects in the same order.
   ```java
   package com.chuwa.practice;

   public class DeadLockResolution {
       static final Object lockA = new Object();
       static final Object lockB = new Object();

       public static void main(String[] args) {
           Runnable task = () -> {
               synchronized (lockA) {
                   System.out.println(Thread.currentThread().getName() + " locked lockA");
                   try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                   synchronized (lockB) {
                       System.out.println(Thread.currentThread().getName() + " locked lockB");
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
9.  How do threads communicate each other?
    - Share data safely by `synchronized` where only allow one thread access or change the data at a time
    - Signal each other by `wait()` and `notify()`
    - Share latest data updates by `volatile` which ensures that all threads see the latest value when they check it
    
10. What’s the difference between class lock and object lock?
    - Object lock: Each object has its own lock, multiple threads can work on multiple objects at the same time
    - Class lock: An entire lock on a sychronized static method of a class, only one thread can work on this method across all instances belong to the class
    
11. What is join() method?
    - One thread waits for another thread finish to continue
12. what is yield() method
    - Suggest CPU to pauze current thread to let other threads run first, but if no other threads are ready, will continue run current thread until when has other thread ready
13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
    - Thread Pool manages a group of worker threads, when execute task submitted, it reuses existing threads.
    - Four types of ThreadPool and their task queue
      - Fixed thread pool, task queue used
      - Catched thread pool, no task queue, create new thread for new submitted task
      - Single thread pool executor, use task queue
      - Scheduled thrad pool. use delayed work queue
    - TaskQueue is a `BlockingQueue<Runnable>`  that temporarily holds tasks before a thread executes them. 
14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
    - Library `java.util.concurrent`
    - `Executors` Factory class that provides ready-made thread pools via static methods like newFixedThreadPool()
    - `ExecutorService` Interface that defines the contract (submit, shutdown, etc.) for managing thread pools
    ```java
    public class ThreadPool {
        public static void main(String[] args) {
            ExecutorService fixedPool = Executors.newFixedThreadPool(2);

            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + " is working");
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            };

            fixedPool.execute(task); // Task 1
            fixedPool.execute(task); // Task 2
            fixedPool.execute(task); // Task 3 (queued): waits for either thread to become available
            fixedPool.shutdown();
        }
    }
    ```
15. How to submit a task to ThreadPool?
    By `.submit(task)` or `.execute(task)`
    ```java
    ExecutorService executor = Executors.newFixedThreadPool(2);

    Runnable task = () -> System.out.println("Running task in " + Thread.currentThread().getName());

    executor.execute(task); // No result
    executor.submit(task); // 
    executor.shutdown();
    ```
    ```java
    ExecutorService executor = Executors.newFixedThreadPool(2);

    Callable<String> task = () -> {
        Thread.sleep(1000);
        return "Result from " + Thread.currentThread().getName();
    };

    Future<String> future = executor.submit(task); // Returns a Future

    try {
        String result = future.get(); // Blocks until result is ready
        System.out.println(result);
    } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
    }

    executor.shutdown();
    ```
    
16. What is the advantage of ThreadPool?
    - Thread reuse: faster task execution
    - Resource control: limited number of thread prevent thread explosion
    - TaskQueue: smoothly manage tasks
  
17. Difference between shutdown() and shutdownNow() methods of executor
    - `shutdown()`: Let current running tasks finish, not accept new task
    - `shutdownNow()`: Attempts to stop all running tasks immediately
    ```java
          ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " started");
                Thread.sleep(5000); // Simulate long-running task
                System.out.println(Thread.currentThread().getName() + " finished");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted");
            }
        };

        executor.submit(task);
        executor.submit(task);

        // executor.shutdown();      // Let tasks finish
        // executor.shutdownNow();   // Interrupt tasks immediately
    ```
    
18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic
classes and its main methods. when to use it?
    - Part of `java.until.concurrent.atomic` package, 
    - Totally 9 types of atomic classes
      - Primitive types: AtomicInteger, AtomicLong, AtomicBoolean
      - Array types: AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray
      - Reference types: AtomicReference<T>, AtomicMarkableReference, AtomicStampedReference 
  ```java
  public class Atomic {
      public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        count.incrementAndGet();    // ++count
        count.decrementAndGet();    // --count
        count.getAndAdd(5);         // returns current, then adds 5
        count.compareAndSet(5, 10); // sets to 10 if current value is 5

        AtomicBoolean flag = new AtomicBoolean(false);
        flag.compareAndSet(false, true); // Atomically sets to true if it was false
        flag.get();

        AtomicReference<String> ref = new AtomicReference<>("Hello");
        ref.set("Hi");
        ref.compareAndSet("Hi", "Hey"); // Sets to "Hey" only if current is "Hi"
        System.out.println(ref.get());  // "Hey"
      }
  }
```

19.   What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
    - Concurrent collections are thread-safe implementations of core data structures (like List, Map, Queue, etc.) found in the java.util.concurrent package.
    - CopyOnWriteArrayList, ConcurretnHashMap, CopyOnWriteArraySet, ArrayBlockingQueue
  
20.   What kind of locks do you know? What is the advantage of each lock?
    
      | **Lock Type**      | **Advantages**                                                  |
      |--------------------|-----------------------------------------------------------------|
      | `synchronized`     | Easy to use, reentrant, built-in                                |
      | `ReentrantLock`    | tryLock, timeout, interruptible, fairness                       |
      | `ReadWriteLock`    | Multiple readers, 1 writer, better concurrency                  |
      | `StampedLock`      | Optimistic reads, better performance                            |
      | `LockSupport`      | Low-level control, non-blocking parking                         |

    
21.   What is future and completableFuture? List some main methods of ComplertableFuture.
   - `Future<T>` is an interface used to represent the result of an asynchronous computation
   - `CompletableFuture` is a concrete class in the java.util.concurrent package.It represents a future result and lets you manually complete it or chain multiple asynchronous computations.
     - `join()` `get()` `runAsync(Runnable)` `supplyAsync(Supplier<T>)`
    
22.   Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading) 
23.   Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in
com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
      1. One solution use synchronized and wait notify
      2. One solution use ReentrantLock and await, signal
      ```java
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
```java
package com.chuwa.practice;

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
```
```java
package com.chuwa.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isOddTurn = true;

    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            lock.lock();
            try {
                while (!isOddTurn) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = false;
                condition.signal();
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            lock.lock();
            try {
                while (isOddTurn) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOddTurn = true;
                condition.signal();
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinterLock printer = new OddEvenPrinterLock();
        Thread t1 = new Thread(printer::printOdd);
        Thread t2 = new Thread(printer::printEven);

        t1.start();
        t2.start();
    }
}
```
    
24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run
sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
    ```java
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
```java
package com.chuwa.practice;

public class PrintNumber {
    public static void main(String[] args) throws InterruptedException {
        // Thread to print 1–10
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-0");

        // Thread to print 11–20
        Thread t2 = new Thread(() -> {
            for (int i = 11; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-2");

        // Thread to print 21–30
        Thread t3 = new Thread(() -> {
            for (int i = 21; i <= 30; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-1");

        // Start threads in order with join to control execution
        t1.start();
        t1.join(); // Wait for Thread-0 to finish

        t2.start();
        t2.join(); // Wait for Thread-2 to finish

        t3.start();
        t3.join(); // Wait for Thread-1 to finish
    }
}
```

25. completable future:
    1. Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
    2. Assume there is an online store that needs to fetch data from three APIs: products,
reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
data for further processing. (需要找public api去模拟，)
       1. [Sign In to Developer.BestBuy.com](https://www.bestbuy.com/identity/signin?token=tid%3A03f36b2b-d76b-11ed-ac7a-0a1dedd8f9d3)
       2. [Best Buy Developer API Documentation (bestbuyapis.github.io)](https://www.bestbuy.com/identity/signin?token=tid%3A03f36b2b-d76b-11ed-ac7a-0a1dedd8f9d3)
       3. 可以⽤fake api https://jsonplaceholder.typicode.com/
       4. Github public api: https://api.github.com/users/your-user-name/repos
    3. For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.