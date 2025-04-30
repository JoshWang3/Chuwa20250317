1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lo
   ck
2. Write a thread-safe singleton class
   public class MySingleton {
       private static volatile MySingleton instance;
       private MySingleton() {}
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
3. How to create a new thread(Please also consider Thread Pool approach)?
   Extend Thread class;
   Implements Runnable;
   Thread Pool
4. Difference between Runnable and Callable?
   Runnable: no return result
   Callable: return a result, can throw checked exceptions.
5. What is the difference between t.start() and t.run()?
   start: create a new thread in JVM, calls run automatically on that thread. 
   run: runs in the current thread, block the current thread.
6. Which way of creating threads is better: Thread class or Runnable interface?
   Runnable. More flexible, since Java do not allow extend mulitple class, but allows implements mulitple interface.
7. What are the thread statuses?
   NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
8. Demonstrate deadlock and how to resolve it in Java code.
   A deadlock in Java occurs when two or more threads are blocked forever, each waiting on a resource the other thread holds.
   Resolution, make both threads lock objects in the same order.
9. How do threads communicate each other?
   Share data safely by synchronized where only allow one thread access or change the data at a time
   Signal each other by wait() and notify()
   Share latest data updates by volatile which ensures that all threads see the latest value when they check it
10. What’s the difference between class lock and object lock?
    Object Lock:
    Locks an instance of a class. Used when synchronized is on an instance method or synchronized(this). Only affects that one object.
    Class Lock:
    Locks the class itself. Used with static synchronized methods or synchronized(ClassName.class). Affects all instances of the class.
    Key difference:
    Object lock → per object
    Class lock → shared across all instances
11. What is join() method?
    One thread waits for another thread finish to continue
12. what is yield() method
    Suggest CPU to pauze current thread to let other threads run first, but if no other threads are ready, will continue run current thread
13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
    Thread Pool manages a group of worker threads, Four types of ThreadPool and their task queue：
    Fixed thread pool, task queue used
    Catched thread pool, no task queue, create new thread for new submitted task
    Single thread pool executor, use task queue
    Scheduled thrad pool. use delayed work queue
    TaskQueue is a BlockingQueue<Runnable>  that temporarily holds tasks before a thread executes them.
14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
    Library java.util.concurrent
    Executors Factory class that provides ready-made thread pools via static methods like newFixedThreadPool()
    ExecutorService Interface that defines the contract (submit, shutdown, etc.) for managing thread pools
15. How to submit a task to ThreadPool?
    execute() or submit().
16. What is the advantage of ThreadPool?
    Better performance:
    Threads are reused instead of created and destroyed repeatedly — reduces overhead.
    Resource management:
    Limits the number of concurrent threads, preventing system overload.
    Simplified concurrency:
    Easy to manage and schedule tasks using APIs like ExecutorService.
    Scalability:
    Efficiently handles large numbers of tasks with fewer resources.
    Improved response time:
    Pre-created threads reduce delay in task execution.
17. Difference between shutdown() and shutdownNow() methods of executor
    shutdown() Stops accepting new tasks. Waits for already submitted tasks to complete. Does not interrupt running tasks.
    shutdownNow() Forceful shutdown. Stops accepting new tasks. Attempts to interrupt running tasks.
18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic
    classes and its main methods. when to use it?
    Atomic classes in Java provide lock-free, thread-safe operations on single variables. They are part of java.util.concurrent.atomic package.
    Use Atomic classes when you need to safely update shared variables in a multi-threaded environment without using synchronized.
    AtomicInteger, AtomicLong, AtomicBoolean
19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
    Concurrent collections are data structures in Java designed for safe use in multithreaded environments without needing external synchronization (like synchronized blocks).
    ConcurrentHashMap, CopyOnWriteArrayList, CopyOnWriteArraySet, ConcurrentLinkedQueue
20. What kind of locks do you know? What is the advantage of each lock?
    synchronized	Easy to use, reentrant, built-in
    ReentrantLock	tryLock, timeout, interruptible, fairness
    ReadWriteLock	Multiple readers, 1 writer, better concurrency
    StampedLock	    Optimistic reads, better performance
    LockSupport	L   ow-level control, non-blocking parking
21. What is future and completableFuture? List some main methods of ComplertableFuture.
    Future<T> is an interface used to represent the result of an asynchronous computation
    CompletableFuture is a concrete class in the java.util.concurrent package.It represents a future result and lets you manually complete it or chain multiple asynchronous computations.
    join() get()
22.Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
   
23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in
    com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
    1. One solution use synchronized and wait notify
    2. One solution use ReentrantLock and await, signal
24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run
    sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
    
25. completable future:
1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum
   and product of two integers, and print the results.
   import java.util.concurrent.CompletableFuture;
public class AsyncCalculation {
        public static void main(String[] args) {
        int a = 5;
        int b = 3;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));

        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}

2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,
   reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
   data for further processing. (需要找public api去模拟，)
1. Sign In to Developer.BestBuy.com
2. Best Buy Developer API Documentation (bestbuyapis.github.io)
3. 可以⽤fake api https://jsonplaceholder.typicode.com/
4. Github public api: https://api.github.com/users/your-user-name/repos
3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API
   call, return a default value and log the exception information.