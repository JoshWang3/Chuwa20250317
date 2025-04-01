# 1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock

# 2. Write a thread-safe singleton class
```Java
public class singletonExample {
    private volatile SingletonExample instance;

    private singletonExample() {}

    private SingletonExample getInstance() {
        // Perform double-checked locking
        if (instance == null) { // First check, if instance created, skip locking
            synchronized(this) {
                if (instance == null) { // Actual check
                    instance = new SingletonExample();
                }
            }
        }
        return instance;
    }
}
```

# 3. How to create a new thread(Please also consider Thread Pool approach)?
There are several different ways. 
1. Extend `Thread` class.
   ```Java
   public class ThreadExample extends Thread {
        public void run() {
            System.out.println("Thread is running!");
        }        
    }

    ThreadExample t = new ThreadExample();
    t.start();
    ```
2. Implement `Callable` class.
   ```Java
    public class CallableExample implements Callable<Integer> {
        public Integer call() {
            return 1;
        }
    }


    CallableExample c = new CallableExample();
    FutureTask<Integer> ft = new FutureTask<>(c);
    Thread thread = new Thread(ft);
    thread.start();
    System.out.println(ft.get());
    ```

3. Implement `Runnable` class.
    ```Java
   public class RunnableExample implements Runnable {
        @Override
        public void run() {
            System.out.println("I am a runnable");
        }
    }

    RunnableExample r = new RunnableExample();
    Thread thread = new Thread(r);
    thread.start();
    ```

4. Use `ThreadPool`.
```Java
   ExecutorService executor = Executors.newFixedThreadPool(4);
   for (int i = 0; i < 5; i++) {
        executor.execute(new RunnableExample());
    }
    executor.shutdown();
```

# 4. Difference between Runnable and Callable?
`Runnable` has not return. `Callable` has return and throws exceptions that need to be handled.

# 5. What is the difference between t.start() and t.run()?
`t.start()` starts a new thread to execute tasks. 
`t.run()` executes the tasks in current thread. 

# 6. Which way of creating threads is better: Thread class or Runnable interface?
`Runnable` interface would be better. 
1. Java doesn't support multiple inheritance. Creating child class for `Thread` will limit the flexibility. On the other hand, implementing multiple interfaces is possible. Beisdes, it will be extra cost to inheri
2. `Runnable` is more reusable since it separates task logic from actual thread creation.

# 7. What are the thread statuses?
1. NEW – Thread created but not started (`new Thread()`).
2. RUNNABLE – Ready to run or currently running (`start()` called).
3. BLOCKED – Waiting to acquire a lock held by another thread.
4. WAITING – Waiting indefinitely for another thread (`wait()`, `join()`).
5. TIMED_WAITING – Waiting for a specified time (`sleep()`, `wait(1000)`).
6. TERMINATED – Thread has finished execution or exited due to error.

# 8. Demonstrate deadlock and how to resolve it in Java code.
Deadlock happens when two threads are trying to acquire two locks in reverse order.\
For example, thread 1 currently possesses clock A and wants to acquire lock B and thread 2 currently possesses lock B and wants to acquire lock A. Since they both want each other's lock then they will both be blocked.\
Threads need to follow the same order of acquring locks. For example:
```Java
Thread t1 = new Thread(() -> {
   synchronized (LockA) {
        System.out.println("t1: Acquired LockA");
       synchronized (LockB) {
           System.out.println("t1: Acquired LockA & LockB");
       }
   }
});

Thread t2 = new Thread(() -> {
   synchronized (LockA) { // can't acquire lockB here
        System.out.println("t2: Acquired LockA");
       synchronized (LockB) {
           System.out.println("t2: Acquired LockB & LockA");
       }
   }
});

t1.start();
t2.start();
```
# 9. How do threads communicate each other?
Threads can communicate through `notify()`, `notifyAll()` or `wait()`. When `wait()` is called, the thread will release the lock and pause execution. Another thread can call `notify()`/`notifyAll()` on the lock to wake up one/all thread(s) to continue execution.

# 10. What’s the difference between class lock and object lock?
Class lock is tied to a specific class. Threfore, all instances and static methods/fields are locked with class lock. Object lock only locks one instance of a class. Instances can run in parallel.

# 11. What is join() method?
`join()` method is used to have current thread waiting for another thread's execution. It guarantees the waiting thread will receive the output of the thread being joined.

# 12. what is yield() method
`yield()` suggests the thread scheduler that current thread can pause and let other threads with same priority run. Current thread will remain in RUNNABLE status. However, this is just a suggestion and scheduler might ignore it.

# 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
ThreadPool manages fixed number of threads. The management is done automatically and it will reuse threads so it consumes less resource.

There are three types of thread pools in general:
- FixedThreadPool: The number of threads is fixed at initialization.
- CachedThreadPool: The number of threads will be dynamically managed according to tasks.
- SingleThreadExecutor: Only a single thread will run in this threadpool.

TaskQUeue is a waiting area for ThreadPool. If a thread pool is full, the task will be waiting until a thread becomes available and picks next task from TaskQueue
.
# 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
The `java.util.concurrent` package is used for creating Threadpool.

The key interface is `ExecutorService` that extends `Executor`. It is a factory class that creates instances of ThreadPool objects.


# 15. How to submit a task to ThreadPool?
By running `execute()` or `submit()`. `submit()` will return a `Future<?>` object while `execute()` will not return anything. 

# 16. What is the advantage of ThreadPool?
Threadpools have usually have better performance and resource management compared to creating single threads. Threadpools control the number of concurrent tasks, reduce the overhead of thread creation and destruction. Additionally, thread pools can queue tasks waiting to be executed, automatically manage the lifecycle of threads, and provide more flexible error handling mechanisms.

# 17. Difference between shutdown() and shutdownNow() methods of executor
`shutdown()` will wait for current tasks to finish but will not accept new tasks. All submitted tasks in queue will also be completed. `shutdownNow()` will try to stop current running tasks and cancel waiting tasks. However, there is not gaurantee that `shutdownNow()` will be able to stop current running tasks since it also depends on how they handle interruption.

# 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
Atomic class are designed for lock-free and thread-safe operations. They use Compare-And-Swap (CAS) operations provided by CPU internally.

Primitive atomic classes include: AtomicInteger, AtomicLong and AtomicBoolean. 
There are other types of atomic classes like Atomic Reference Classes and Array Atomic Classes.

```Java
    AtomicInteger counter = new AtomicInteger(0);

    Thread t1 = new Thread(() -> {
        for (int i = 0; i < 100; i++) {
            counter.incrementAndGet();
        }
    });

    Thread t2 = new Thread(() -> {
        for (int i = 0; i < 100; i++) {
            counter.incrementAndGet();
        }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Final Counter Value: " + counter.get()); // should print 200
```

# 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
`java.util.concurrent` provides thread-safe versions of some data structures.

`ConcurrentHashMap` and `CopyOnWriteArrayList`.

# 20. What kind of locks do you know? What is the advantage of each lock?
`ReentrantLock`: A thread can reacquire the same lock.\
- Flexible fairness policy.
- Good for fine-grained lock control.

`ReadWriteLock`: Only one thread can write. Multiple threads can read when no writer.
- Better performance for read-heavy tasks.

`StampedLock`: Can read when there is a writer.
- Better performance for mostly-read scenarios.

# 21. What is future and completableFuture? List some main methods of completableFuture.
`Future` wraps the result of an async task. It will block the calling thread when `get()` is called. It cannot be manually completed.\
`CompletableFuture` can be manually completed and can be non-blocking.

- `thenAccept()`: handles normal results
- `exceptionally()`: handles exceptions
- `allOf(...)`: Waits for all futures.
- `anyOf(...)`: Proceeds when any completes.
- `thenApplyAsync(fn)`: Transforms result to another `CompletableFuture`.


# 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
# 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is incom.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
1. One solution use synchronized and wait notify
2. One solution use ReentrantLock and await, signal
   
**Please see coding folder**

# 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
**Please see coding folder**

# 25. completable future:
1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum
and product of two integers, and print the results.
2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,
reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
data for further processing. (需要找public api去模拟，)
   1. Sign In to Developer.BestBuy.com
   2. Best Buy Developer API Documentation (bestbuyapis.github.io)
   3. 可以⽤fake api https://jsonplaceholder.typicode.com/
3. Github public api: https://api.github.com/users/your-user-name/repos
Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API
call, return a default value and log the exception information.

**Please see coding folder**
