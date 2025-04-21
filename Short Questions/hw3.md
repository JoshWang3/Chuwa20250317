1. Read: <https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock>

2. Write a thread-safe singleton class 

   Using the Double-Checked Locking implementation, which creates the instance only when needed

   ```java
   public class Singleton {
       // Volatile keyword ensures visibility across threads
       private static volatile Singleton instance;
       
       // Private constructor prevents external instantiation
       private Singleton() {
           // Prevents reflection attacks
           if (instance != null) {
               throw new RuntimeException("Use getInstance() method to get the singleton instance");
           }
       }
       
       // Public static method provides global access point
       public static Singleton getInstance() {
           // First check: avoids unnecessary synchronization
           if (instance == null) {
               // Synchronized block ensures thread safety
               synchronized (Singleton.class) {
                   // Second check: prevents multiple threads from initializing
                   if (instance == null) {
                       instance = new Singleton();
                   }
               }
           }
           return instance;
       }
       
       // Business method
       public void doSomething() {
           System.out.println("Singleton business method");
       }
   }
   ```

   

3. How to create a new thread(Please also consider Thread Pool approach)?

   In Java, there are four main ways to create a new thread:

   1. **Extending Thread Class**: Directly inherit from Thread and override the run method.

      ```java
      public class ThreadExample extends Thread {
          public void run() {
              System.out.println("Thread running");
          }
          
          public static void main(String[] args) {
              ThreadExample thread = new ThreadExample();
              thread.start();
          }
      }
      ```

   2. **Implementing Runnable Interface**: More flexible approach as it doesn't consume your inheritance option.

      ```java
      public class RunnableExample {
          public static void main(String[] args) {
              Runnable task = () -> System.out.println("Thread running");
              Thread thread = new Thread(task);
              thread.start();
          }
      }
      ```

   3. **Implementing Callable Interface**: Similar to Runnable but can return results and throw exceptions.

      ```java
      import java.util.concurrent.*;
      
      public class CallableExample {
          public static void main(String[] args) throws Exception {
              ExecutorService executor = Executors.newSingleThreadExecutor();
              Callable<String> task = () -> "Thread result";
              
              Future<String> future = executor.submit(task);
              String result = future.get();
              System.out.println(result);
              
              executor.shutdown();
          }
      }
      ```

   4. **Using Thread Pools**: Best practice for production applications, manages threads efficiently.

      ```java
      import java.util.concurrent.*;
      
      public class ThreadPoolExample {
          public static void main(String[] args) {
              ExecutorService executor = Executors.newFixedThreadPool(3);
              
              for (int i = 0; i < 5; i++) {
                  final int taskId = i;
                  executor.execute(() -> System.out.println("Task " + taskId + " executed"));
              }
              
              executor.shutdown();
          }
      }
      ```

      

4. Difference between `Runnable` and `Callable`?

   | Feature                | Runnable                                       | Callable                                  |
   | ---------------------- | :--------------------------------------------- | ----------------------------------------- |
   | **Return Value**       | `run()`  returns void                          | `call()`  returns a parameterized value   |
   | **Exception Handling** | `run()`  cannot throw checked exceptions       | `call()`  can throw checked exceptions    |
   | **Usage**              | Can be passed directly to a Thread constructor | Must be submitted to an `ExecutorService` |
   | **Implementation**     | Implements `run()` method                      | Implements `call()` method                |

   ```java
   import java.util.concurrent.*;
   
   public class Main {
       public static void main(String[] args) throws Exception {
           // Runnable example
           Runnable runnable = () -> {
               System.out.println("Runnable executing");
               // Cannot return value
               // Cannot throw checked exceptions
           };
           
           // Execute Runnable
           Thread thread = new Thread(runnable);
           thread.start();
           
           // Callable example
           Callable<String> callable = () -> {
               System.out.println("Callable executing");
               // Can throw checked exceptions
               // if (condition) throw new Exception("Error");
               return "Callable result"; // Can return value
           };
           
           // Execute Callable
           ExecutorService executor = Executors.newSingleThreadExecutor();
           Future<String> future = executor.submit(callable);
           
           // Get result from Callable
           String result = future.get();
           System.out.println("Result: " + result);
           
           executor.shutdown();
       }
   }
   ```

   ```tex
   Runnable executing
   Callable executing
   Result: Callable result
   ```

   

5. What is the difference between `t.start()` and `t.run()`?

   **`start()`**:

   - Creates a new thread and executes the code in a new thread.
   - Can only be called once per thread object.

   **`run()`**:

   - Executes the code in the current thread as a normal method call.
   - Can be called multiple times.

   ```java
   public class Main {
       public static void main(String[] args) {
           // Create thread
           Thread t = new Thread(() -> {
               System.out.println("Current thread: " + Thread.currentThread().getName());
           });
           
           // Using start() - creates new thread
           t.start();
           
           // Using run() - runs in main thread
           t.run();
           
           System.out.println("Main thread: " + Thread.currentThread().getName());
       }
   }
   ```

   ```tex
   Current thread: Thread-0
   Current thread: main
   Main thread: main
   ```

   

6. Which way of creating threads is better: Thread class or Runnable interface?

   Using Runnable interface is generally better because: 

   - It follows composition over inheritance principle
   - Java only supports single inheritance, Runnable avoids this limitation
   - Same Runnable object can be shared by multiple threads
   - Better separation of task logic from thread mechanics

   ```java
   public class RunnableExample implements Runnable {
       @Override
       public void run() {
           System.out.println("Running in: " + Thread.currentThread().getName());
       }
       
       public static void main(String[] args) {
           Runnable task = new RunnableExample();
           // Reuse same task in multiple threads
           new Thread(task).start();
           new Thread(task).start();
       }
   }
   ```

   ```java
   public class ThreadExample extends Thread {
       @Override
       public void run() {
           System.out.println("Running in: " + Thread.currentThread().getName());
       }
       
       public static void main(String[] args) {
           // Each thread needs its own subclass instance
           new ThreadExample().start();
           new ThreadExample().start();
       }
   }
   ```

   

7. What are the thread statuses?

   **NEW** - Thread created but not yet started

   **RUNNABLE** - Thread executing in JVM or waiting for system resources

   **BLOCKED** - Thread waiting to acquire a monitor lock

   **WAITING** - Thread waiting indefinitely for another thread to perform an action

   **TIMED_WAITING** - Thread waiting for another thread for a specified time

   **TERMINATED** - Thread has completed execution

   ```java
   public class Main {
       public static void main(String[] args) throws InterruptedException {
           Thread thread = new Thread(() -> {
               try {
                   Thread.sleep(2000); // TIMED_WAITING
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("Thread finished");
           });
   
           System.out.println(thread.getState()); // NEW
           thread.start();
           System.out.println(thread.getState()); // RUNNABLE or TIMED_WAITING
   
           Thread.sleep(500);
           System.out.println(thread.getState()); // TIMED_WAITING
   
           thread.join();
           System.out.println(thread.getState()); // TERMINATED
       }
   }
   ```

   ```tex
   NEW
   RUNNABLE
   TIMED_WAITING
   Thread finished
   TERMINATED
   ```

   

   

8. Demonstrate deadlock and how to resolve it in Java code

   Deadlock is a situation where two or more threads are blocked forever, each waiting for resources held by others. To resolve deadlock in Java:

   1. Avoid nested locks - don't acquire multiple locks at once
   2. Use lock ordering - always acquire locks in the same order
   3. Use lock timeouts - use tryLock() with a timeout
   4. Use deadlock detection - implement detection and recovery mechanisms
   5. Use higher-level concurrency utilities like java.util.concurrent

   ```java
   public class DeadlockExample {
       private static final Object RESOURCE_A = new Object();
       private static final Object RESOURCE_B = new Object();
       
       public static void main(String[] args) {
           Thread thread1 = new Thread(() -> {
               synchronized (RESOURCE_A) {
                   System.out.println("Thread 1: Locked resource A");
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {}
                   
                   synchronized (RESOURCE_B) {
                       System.out.println("Thread 1: Locked resource B");
                   }
               }
           });
           
           Thread thread2 = new Thread(() -> {
               synchronized (RESOURCE_B) {
                   System.out.println("Thread 2: Locked resource B");
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {}
                   
                   synchronized (RESOURCE_A) {
                       System.out.println("Thread 2: Locked resource A");
                   }
               }
           });
           
           thread1.start();
           thread2.start();
       }
   }
   ```

   Solution(acquire locks in the same order):

   ```java
   public class DeadlockSolution {
       private static final Object RESOURCE_A = new Object();
       private static final Object RESOURCE_B = new Object();
       
       public static void main(String[] args) {
           Thread thread1 = new Thread(() -> {
               synchronized (RESOURCE_A) {
                   System.out.println("Thread 1: Locked resource A");
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {}
                   
                   synchronized (RESOURCE_B) {
                       System.out.println("Thread 1: Locked resource B");
                   }
               }
           });
           
           Thread thread2 = new Thread(() -> {
               // Fix: acquire locks in the same order as thread1
               synchronized (RESOURCE_A) {
                   System.out.println("Thread 2: Locked resource A");
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {}
                   
                   synchronized (RESOURCE_B) {
                       System.out.println("Thread 2: Locked resource B");
                   }
               }
           });
           
           thread1.start();
           thread2.start();
       }
   }
   ```

   

9. How do threads communicate each other?

   Threads can communicate with each other through several mechanisms:

   1. Shared Objects - Threads can share data through common objects
   2. `wait()`, `notify()`, `notifyAll()` - Basic thread signaling mechanisms
   3. Volatile Variables - Ensures visibility of changes across threads
   4. Blocking Queues - Thread-safe data structures for producer-consumer patterns
   5. Condition Variables - More flexible signaling with ReentrantLock
   6. CountDownLatch, CyclicBarrier, Phaser - Synchronization aids
   7. Exchanger - Allows two threads to exchange objects at a synchronization point
   8. Future and CompletableFuture - For asynchronous result handling
   9. ThreadLocal - For thread-confined data that isn't shared

   ```java
   import java.util.concurrent.*;
   
   public class Main {
       public static void main(String[] args) throws Exception {
           // 1. Shared Object Communication
           SharedData sharedData = new SharedData();
           
           Thread producer = new Thread(() -> {
               sharedData.produce("Hello from another thread");
           });
           
           Thread consumer = new Thread(() -> {
               String message = sharedData.consume();
               System.out.println("Received: " + message);
           });
           
           producer.start();
           consumer.start();
           
           producer.join();
           consumer.join();
       }
       
       // Using wait/notify for thread communication
       static class SharedData {
           private String message;
           private boolean empty = true;
           
           public synchronized void produce(String message) {
               // Wait until consumer has consumed the previous message
               while (!empty) {
                   try {
                       wait();
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                   }
               }
               
               // Store new message
               this.message = message;
               empty = false;
               
               // Notify consumer
               notify();
           }
           
           public synchronized String consume() {
               // Wait until producer has produced a message
               while (empty) {
                   try {
                       wait();
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                   }
               }
               
               // Get the message
               String result = message;
               empty = true;
               
               // Notify producer
               notify();
               return result;
           }
       }
   }
   ```

   ```tex
   Received: Hello from another thread
   ```

   

10. What’s the difference between class lock and object lock?

    **Class lock** (static lock):

    - Locks the Class object itself
    - Uses the synchronized static method or synchronized(ClassName.class) block
    - Only one thread can execute any synchronized static method in the class at a time
    - All instances of the class share the same class lock

    **Object lock** (instance lock):

    - Locks a specific instance of a class
    - Uses synchronized instance method or synchronized(this) block
    - Only one thread can execute any synchronized instance method on the same object
    - Different object instances have independent locks

    ```java
    class ClassLockDemo {
        // synchronized static method (Class Lock)
        public static synchronized void printNumbers(String threadName) {
            System.out.println(threadName + " started (class lock).");
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + ": " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) { }
            }
            System.out.println(threadName + " ended (class lock).\n");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Thread t1 = new Thread(() -> ClassLockDemo.printNumbers("Thread 1"));
            Thread t2 = new Thread(() -> ClassLockDemo.printNumbers("Thread 2"));
    
            t1.start();
            t2.start();
        }
    }
    ```

    ```tex
    Thread 1 started (class lock).
    Thread 1: 1
    Thread 1: 2
    Thread 1: 3
    Thread 1: 4
    Thread 1: 5
    Thread 1 ended (class lock).
    
    Thread 2 started (class lock).
    Thread 2: 1
    Thread 2: 2
    Thread 2: 3
    Thread 2: 4
    Thread 2: 5
    Thread 2 ended (class lock).
    ```

    ```java
    class ObjectLockDemo {
        // synchronized instance method (Object Lock)
        public synchronized void printNumbers(String threadName) {
            System.out.println(threadName + " started (object lock).");
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + ": " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) { }
            }
            System.out.println(threadName + " ended (object lock).\n");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            ObjectLockDemo obj1 = new ObjectLockDemo();
            ObjectLockDemo obj2 = new ObjectLockDemo();
    
            Thread t1 = new Thread(() -> obj1.printNumbers("Thread 1"));
            Thread t2 = new Thread(() -> obj2.printNumbers("Thread 2"));
    
            t1.start();
            t2.start();
        }
    }
    ```

    ```tex
    Thread 2 started (object lock).
    Thread 1 started (object lock).
    Thread 2: 1
    Thread 1: 1
    Thread 2: 2
    Thread 1: 2
    Thread 2: 3
    Thread 1: 3
    Thread 2: 4
    Thread 1: 4
    Thread 2: 5
    Thread 1: 5
    Thread 2 ended (object lock).
    
    Thread 1 ended (object lock).
    ```

    

11. What is `join()` method?

    The `join()` method causes the current thread to pause execution until the thread it is called on completes its execution. It's used to ensure that one thread completes before another thread continues.

    ```java
    public class Main {
        public static void main(String[] args) {
            Thread worker = new Thread(() -> {
                System.out.println("Worker thread started");
                try {
                    Thread.sleep(2000); // Simulate work
                    System.out.println("Worker thread finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            
            System.out.println("Main thread starting worker");
            worker.start();
            
            try {
                System.out.println("Main thread waiting for worker to complete");
                worker.join(); // Main thread will wait here until worker completes
                System.out.println("Main thread continues after worker is done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    ```

    ```tex
    Main thread starting worker
    Main thread waiting for worker to complete
    Worker thread started
    Worker thread finished
    Main thread continues after worker is done
    ```

    

12. What is `yield()` method?

    The `yield()` method causes the currently executing thread to temporarily pause and allow other threads of the same priority to execute.

    ```java
    public class Main {
        public static void main(String[] args) {
            Thread worker = new Thread(() -> {
                System.out.println("Worker thread started");
                for (int i = 0; i < 3; i++) {
                    System.out.println("Worker doing task " + i);
                    // Give a hint to scheduler to let other threads run
                    Thread.yield();
                }
                System.out.println("Worker thread finished");
            });
            
            System.out.println("Main thread starting worker");
            worker.start();
            
            // Main thread also does some work
            for (int i = 0; i < 3; i++) {
                System.out.println("Main thread doing task " + i);
                Thread.yield(); // Main also yields
            }
            
            System.out.println("Main thread completed its tasks");
        }
    }
    ```

    ```tex
    Main thread starting worker
    Worker thread started
    Main thread doing task 0
    Main thread doing task 1
    Main thread doing task 2
    Worker doing task 0
    Worker doing task 1
    Worker doing task 2
    Worker thread finished
    Main thread completed its tasks
    ```

    

13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

    - A ThreadPool is a collection of pre-initialized worker threads that efficiently execute submitted tasks without the overhead of constant thread creation and destruction.

    - Java provides several types of thread pools through the Executors factory class:

      - **FixedThreadPool**: Maintains a fixed number of threads with an unbounded task queue
      - **CachedThreadPool**: Creates new threads as needed and reuses idle threads
      - **SingleThreadExecutor**: Uses a single worker thread with an unbounded queue
      - **ScheduledThreadPool**: Designed for delayed or periodic task execution
      - **WorkStealingPool**: Employs the fork/join framework with work-stealing algorithm

    - The TaskQueue in a ThreadPool is a BlockingQueue implementation that holds tasks waiting to be executed. Common queue types include:

      - **ArrayBlockingQueue**: Bounded queue based on arrays
      - **LinkedBlockingQueue**: Optionally bounded queue based on linked nodes
      - **SynchronousQueue**: Handoff queue with no internal capacity
      - **PriorityBlockingQueue**: Priority-ordered queue

      ```java
      import java.util.concurrent.*;
      
      public class Main {
          public static void main(String[] args) {
              // Create different types of thread pools
              ExecutorService fixedPool = Executors.newFixedThreadPool(3);
              ExecutorService cachedPool = Executors.newCachedThreadPool();
              ExecutorService singlePool = Executors.newSingleThreadExecutor();
              ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
              
              // Custom thread pool with specific task queue
              ThreadPoolExecutor customPool = new ThreadPoolExecutor(
                  2,                         // Core pool size
                  5,                         // Maximum pool size
                  60, TimeUnit.SECONDS,      // Keep-alive time
                  new LinkedBlockingQueue<>(10),  // Task queue (bounded)
                  Executors.defaultThreadFactory(),
                  new ThreadPoolExecutor.AbortPolicy()  // Rejection policy
              );
              
              // Submit tasks to the custom pool
              for (int i = 0; i < 10; i++) {
                  final int taskId = i;
                  customPool.execute(() -> {
                      System.out.println("Task " + taskId + " executed by " + 
                                        Thread.currentThread().getName());
                      try {
                          Thread.sleep(500);  // Simulate work
                      } catch (InterruptedException e) {
                          Thread.currentThread().interrupt();
                      }
                  });
              }
              
              // Schedule a delayed task
              scheduledPool.schedule(
                  () -> System.out.println("Delayed task executed"),
                  2, TimeUnit.SECONDS
              );
              
              // Shutdown all pools when done
              customPool.shutdown();
              fixedPool.shutdown();
              cachedPool.shutdown();
              singlePool.shutdown();
              scheduledPool.shutdown();
          }
      }
      ```

      ```tex
      Task 0 executed by pool-5-thread-1
      Task 1 executed by pool-5-thread-2
      Task 3 executed by pool-5-thread-1
      Task 2 executed by pool-5-thread-2
      Task 4 executed by pool-5-thread-1
      Task 5 executed by pool-5-thread-2
      Task 6 executed by pool-5-thread-1
      Task 7 executed by pool-5-thread-2
      Delayed task executed
      Task 8 executed by pool-5-thread-1
      Task 9 executed by pool-5-thread-2
      ```

      

14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

    The main library used to create ThreadPools in Java is the `java.util.concurrent` package.

    The primary interface that defines the functionality of a thread pool is the `ExecutorService` interface, which extends the more basic `Executor` interface. This interface provides methods for:

    - Task submission (`submit()`, `execute()`)
    - Shutdown operations (`shutdown()`, `shutdownNow()`)
    - Status checking (`isShutdown()`, `isTerminated()`)
    - Awaiting termination (`awaitTermination()`)

    ```java
    import java.util.concurrent.*;
    
    public class Main {
        public static void main(String[] args) throws InterruptedException {
            // Creating a thread pool using the ExecutorService interface
            ExecutorService executor = Executors.newFixedThreadPool(4);
            
            // Submitting tasks
            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " executed by " + 
                                      Thread.currentThread().getName());
                    return "Result of Task " + taskId;
                });
            }
            
            // Demonstrating ExecutorService interface methods
            executor.shutdown();  // Initiates an orderly shutdown
            
            // Check if shutdown was initiated
            System.out.println("Is shutdown: " + executor.isShutdown());
            
            // Wait for all tasks to complete (with timeout)
            boolean terminated = executor.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("All tasks completed: " + terminated);
            
            // If needed, can force immediate shutdown
            if (!terminated) {
                System.out.println("Forcing shutdown...");
                executor.shutdownNow();
            }
        }
    }
    ```

    ```tex
    Is shutdown: true
    Task 0 executed by pool-1-thread-1
    Task 3 executed by pool-1-thread-4
    Task 2 executed by pool-1-thread-3
    Task 1 executed by pool-1-thread-2
    Task 4 executed by pool-1-thread-2
    Task 7 executed by pool-1-thread-3
    Task 5 executed by pool-1-thread-1
    Task 6 executed by pool-1-thread-4
    Task 8 executed by pool-1-thread-2
    Task 9 executed by pool-1-thread-3
    All tasks completed: true
    ```

    

    

15. How to submit a task to ThreadPool?

    Tasks can be submitted to a ThreadPool in Java using the `ExecutorService` interface, which provides two main methods:

    1. **execute(Runnable command)**: Submits a Runnable task with no return value
    2. **submit(...)**: Submits a task (Runnable or Callable) and returns a Future object

    The `submit` method has three overloaded versions:

    - `submit(Runnable task)`: Returns a Future representing the task completion status
    - `submit(Runnable task, T result)`: Returns a Future that will return the given result upon completion
    - `submit(Callable<T> task)`: Returns a Future representing the pending result of the Callable task

    ```java
    import java.util.concurrent.*;
    
    public class Main {
        public static void main(String[] args) throws Exception {
            // Create a thread pool
            ExecutorService executor = Executors.newFixedThreadPool(3);
            
            // Method 1: execute (for Runnable tasks with no return value)
            executor.execute(() -> {
                System.out.println("Simple task executed by " + 
                                  Thread.currentThread().getName());
            });
            
            // Method 2: submit with Runnable (returns a Future<Void>)
            Future<?> futureRunnable = executor.submit(() -> {
                System.out.println("Runnable task executed by " + 
                                  Thread.currentThread().getName());
            });
            
            // Check if completed
            System.out.println("Is Runnable task done? " + futureRunnable.isDone());
            
            // Method 3: submit with Callable (returns a Future with result)
            Future<String> futureCallable = executor.submit(() -> {
                System.out.println("Callable task executed by " + 
                                  Thread.currentThread().getName());
                return "Task result";
            });
            
            // Wait for the result
            String result = futureCallable.get();  // Blocks until completed
            System.out.println("Callable result: " + result);
            
            // Shutdown the executor
            executor.shutdown();
        }
    }
    ```

    ```tex
    Runnable task executed by pool-1-thread-2
    Simple task executed by pool-1-thread-1
    Is Runnable task done? false
    Callable task executed by pool-1-thread-3
    Callable result: Task result
    ```

    

16. What is the advantage of ThreadPool?

    **Performance improvement**: Reduces the overhead of thread creation and destruction by reusing existing threads

    **Resource management**: Limits the number of concurrent threads to prevent resource exhaustion

    **Improved responsiveness**: Tasks can be executed immediately by available threads without delay

    **Load balancing**: Distributes tasks evenly across worker threads

    **Thread management**: Provides lifecycle management facilities (startup, shutdown, monitoring)

    **Stability**: Prevents system degradation under heavy loads through controlled concurrency

    **Configurability**: Allows customization of pool size, task queue capacity, and rejection policies

    ```java
    import java.util.concurrent.*;
    import java.util.concurrent.atomic.AtomicInteger;
    
    public class Main {
        public static void main(String[] args) throws Exception {
            // Demonstrate performance advantage with many short tasks
            final int TASK_COUNT = 1000;
            
            // 1. Without thread pool - creating new thread for each task
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < TASK_COUNT; i++) {
                Thread thread = new Thread(() -> {
                    // Simulate a quick calculation
                    int sum = 0;
                    for (int j = 0; j < 1000; j++) {
                        sum += j;
                    }
                });
                thread.start();
                thread.join(); // Wait for completion
            }
            long noPoolTime = System.currentTimeMillis() - startTime;
            
            // 2. With thread pool - reusing threads
            ExecutorService executor = Executors.newFixedThreadPool(4);
            AtomicInteger completedTasks = new AtomicInteger(0);
            
            startTime = System.currentTimeMillis();
            for (int i = 0; i < TASK_COUNT; i++) {
                executor.execute(() -> {
                    // Same work as above
                    int sum = 0;
                    for (int j = 0; j < 1000; j++) {
                        sum += j;
                    }
                    completedTasks.incrementAndGet();
                });
            }
            
            // Wait for all tasks to complete
            while (completedTasks.get() < TASK_COUNT) {
                Thread.sleep(10);
            }
            long poolTime = System.currentTimeMillis() - startTime;
            executor.shutdown();
            
            // Compare results
            System.out.println("Time without pool: " + noPoolTime + "ms");
            System.out.println("Time with pool: " + poolTime + "ms");
            System.out.println("Performance improvement: " + 
                              (noPoolTime - poolTime) * 100.0 / noPoolTime + "%");
        }
    }
    ```

    ```tex
    Time without pool: 132ms
    Time with pool: 58ms
    Performance improvement: 56.06060606060606%
    ```

    

17. Difference between `shutdown()` and `shutdownNow()` methods of executor

    | Feature             | `shutdown()`      | `shutdownNow()`             |
    | ------------------- | ----------------- | --------------------------- |
    | **Task Acceptance** | Rejects new tasks | Rejects new tasks           |
    | **Running Tasks**   | Allows completion | Attempts to interrupt       |
    | **Pending Tasks**   | Allows execution  | Cancels and returns list    |
    | **Return Value**    | void              | List of pending tasks       |
    | **Speed**           | Gradual shutdown  | Immediate shutdown attempt  |
    | **Interruption**    | No interruption   | Sends interrupts to threads |

    ```java
    import java.util.List;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.concurrent.TimeUnit;
    
    public class Main {
        public static void main(String[] args) throws InterruptedException {
            System.out.println("Testing shutdown():");
            testShutdown();
    
            System.out.println("\nTesting shutdownNow():");
            testShutdownNow();
        }
    
        // Demonstrate shutdown()
        private static void testShutdown() throws InterruptedException {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            
            executor.submit(task("Task 1"));
            executor.submit(task("Task 2"));
            
            // Initiates an orderly shutdown (waits for tasks to complete)
            executor.shutdown();
            
            boolean terminated = executor.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Executor terminated gracefully: " + terminated);
        }
    
        // Demonstrate shutdownNow()
        private static void testShutdownNow() throws InterruptedException {
            ExecutorService executor = Executors.newFixedThreadPool(2);
    
            executor.submit(task("Task 1"));
            executor.submit(task("Task 2"));
            executor.submit(task("Task 3"));
            
            // Attempts immediate shutdown (may interrupt tasks)
            List<Runnable> notStartedTasks = executor.shutdownNow();
            
            System.out.println("Tasks never started: " + notStartedTasks.size());
            
            boolean terminated = executor.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Executor terminated immediately: " + terminated);
        }
    
        // Simple task simulation
        private static Runnable task(String name) {
            return () -> {
                try {
                    System.out.println(name + " started.");
                    Thread.sleep(2000); // simulate work
                    System.out.println(name + " completed.");
                } catch (InterruptedException e) {
                    System.out.println(name + " interrupted.");
                }
            };
        }
    }
    ```

    ```tex
    Testing shutdown():
    Task 2 started.
    Task 1 started.
    Task 1 completed.
    Task 2 completed.
    Executor terminated gracefully: true
    
    Testing shutdownNow():
    Task 1 started.
    Task 2 started.
    Task 1 interrupted.
    Task 2 interrupted.
    Tasks never started: 1
    Executor terminated immediately: true
    ```

    

18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic  classes and its main methods. when to use it?

    Atomic classes in Java are thread-safe classes that support lock-free, atomic operations on single variables. They are part of the `java.util.concurrent.atomic` package and use low-level processor instructions like Compare-And-Swap (CAS) instead of synchronization blocks for better performance. The main types of Atomic classes in Java are:

    1. **Basic atomic types**: `AtomicInteger`, `AtomicLong`, `AtomicBoolean`
    2. **Array atomic types**: `AtomicIntegerArray`, `AtomicLongArray`, `AtomicReferenceArray`
    3. **Reference atomic types**: `AtomicReference`, `AtomicStampedReference`, `AtomicMarkableReference`
    4. **Field updater types**: `AtomicIntegerFieldUpdater`, `AtomicLongFieldUpdater`, `AtomicReferenceFieldUpdater`
    5. **Accumulators** (Java 8): `DoubleAccumulator`, `DoubleAdder`, `LongAccumulator`, `LongAdder`
    
    ```java
    import java.util.concurrent.atomic.*;
    import java.util.concurrent.*;
    
    public class Main {
        public static void main(String[] args) throws InterruptedException {
            // 1. AtomicInteger example
            AtomicInteger counter = new AtomicInteger(0);
            
            // Create threads to increment counter
            ExecutorService executor = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 10000; i++) {
                executor.submit(() -> {
                    counter.incrementAndGet(); // Atomic increment
                });
            }
            
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("Final count: " + counter.get()); // Should be 10000
            
            // 2. AtomicReference example
            AtomicReference<String> ref = new AtomicReference<>("Initial");
            boolean success = ref.compareAndSet("Initial", "Updated");
            System.out.println("Update successful: " + success);
            System.out.println("New value: " + ref.get());
            
            // 3. AtomicStampedReference example (solving ABA problem)
            AtomicStampedReference<Integer> stamped = 
                new AtomicStampedReference<>(100, 0);
            int[] stampHolder = new int[1];
            
            Integer value = stamped.get(stampHolder);
            int stamp = stampHolder[0];
            
            System.out.println("Before: value = " + value + 
                              ", stamp = " + stamp);
            
            // Update with new stamp
            boolean updated = stamped.compareAndSet(
                value, 200, stamp, stamp + 1);
            
            System.out.println("After: value = " + stamped.getReference() + 
                              ", stamp = " + stamped.getStamp() + 
                              ", update successful: " + updated);
            
            // 4. LongAdder example (high contention scenario)
            LongAdder adder = new LongAdder();
            for (int i = 0; i < 100; i++) {
                adder.increment();
            }
            System.out.println("Adder sum: " + adder.sum());
        }
    }
    ```
    
    ```tex
    Final count: 10000
    Update successful: true
    New value: Updated
    Before: value = 100, stamp = 0
    After: value = 200, stamp = 1, update successful: true
    Adder sum: 100
    ```
    
    Main methods of Atomic classes include:

    - `get()`: Returns current value
    - `set(newValue)`: Sets to new value
    - `getAndSet(newValue)`: Atomically sets and returns old value
    - `compareAndSet(expect, update)`: Atomic compare-and-set operation
    - `incrementAndGet()`, `decrementAndGet()`: Atomic increment/decrement with result
    - `getAndIncrement()`, `getAndDecrement()`: Atomic increment/decrement with old value
    - `addAndGet(delta)`, `getAndAdd(delta)`: Atomic add operations
    
      Use Atomic classes when:

      - You need thread-safe counters or sequence generators

      - You need to update object fields atomically

      - You need high-performance alternatives to synchronized blocks

      - You need to address the ABA problem in concurrent algorithms

     

19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

    Concurrent collections are thread-safe data structures designed for use in multithreaded applications. Java provides several thread-safe collections in the `java.util.concurrent` package, offering better performance than traditional synchronized collections by using fine-grained locking, lock-free algorithms, or copy-on-write techniques.

    **ConcurrentHashMap**: A thread-safe hash table supporting full concurrency of retrievals and high concurrency for updates.

    **CopyOnWriteArrayList**: A thread-safe variant of ArrayList where all mutative operations are implemented by creating a fresh copy.

    **CopyOnWriteArraySet**: A thread-safe Set implementation backed by CopyOnWriteArrayList.

    **ConcurrentLinkedQueue**: A thread-safe unbounded queue based on linked nodes.

    **ConcurrentLinkedDeque**: A concurrent implementation of a double-ended queue.

    **BlockingQueue** implementations:

    - **ArrayBlockingQueue**: Bounded blocking queue backed by an array
    - **LinkedBlockingQueue**: Optionally bounded blocking queue based on linked nodes
    - **PriorityBlockingQueue**: Unbounded blocking priority queue
    - **DelayQueue**: Blocking queue of delayed elements
    - **SynchronousQueue**: Blocking queue with no internal capacity

    **BlockingDeque** implementation:

    - **LinkedBlockingDeque**: Optionally bounded blocking deque based on linked nodes

    - **ConcurrentSkipListMap**: A concurrent NavigableMap implementation based on skip lists.

    - **ConcurrentSkipListSet**: A concurrent NavigableSet implementation based on ConcurrentSkipListMap.

    ```java
    import java.util.concurrent.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws InterruptedException {
            // ConcurrentHashMap example
            ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
            map.put("one", 1);
            map.put("two", 2);
            
            // Multiple threads can safely read and update
            map.compute("one", (k, v) -> v + 1);
            System.out.println("Map: " + map);
            
            // CopyOnWriteArrayList example
            CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
            list.add("A");
            list.add("B");
            
            // Creates a new copy for modifications
            list.add("C");
            System.out.println("List: " + list);
            
            // BlockingQueue example
            BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
            
            // Producer thread
            new Thread(() -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        queue.put("Item " + i);
                        System.out.println("Produced: Item " + i);
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
            
            // Consumer thread
            new Thread(() -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        String item = queue.take();
                        System.out.println("Consumed: " + item);
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
            
            // Wait for completion
            Thread.sleep(1500);
            
            // ConcurrentSkipListMap example (sorted map)
            ConcurrentSkipListMap<Integer, String> sortedMap = new ConcurrentSkipListMap<>();
            sortedMap.put(3, "Three");
            sortedMap.put(1, "One");
            sortedMap.put(2, "Two");
            
            // Always maintains order
            System.out.println("Sorted Map: " + sortedMap);
        }
    }
    ```

    ```tex
    Map: {one=2, two=2}
    List: [A, B, C]
    Produced: Item 0
    Consumed: Item 0
    Produced: Item 1
    Consumed: Item 1
    Produced: Item 2
    Produced: Item 3
    Consumed: Item 2
    Produced: Item 4
    Consumed: Item 3
    Consumed: Item 4
    Sorted Map: {1=One, 2=Two, 3=Three}
    ```

    

20. What kind of locks do you know? What is the advantage of each lock?

    **Intrinsic/Synchronized Lock**

    - Simple to use with synchronized keyword
    - Automatic release when exiting synchronized block
    - Built-in reentrant capability

    **ReentrantLock**

    - More flexible than synchronized blocks
    - Supports timed lock attempts (tryLock)
    - Supports interruption while waiting
    - Fair/unfair locking options
    - Ability to get pending threads list

    **ReadWriteLock**

    - Allows multiple concurrent readers
    - Exclusive access for writers
    - Increased throughput for read-heavy workloads

    **StampedLock**

    - Supports optimistic reading
    - Higher throughput than ReadWriteLock
    - Can be converted between modes

    **Semaphore**

    - Controls access to limited resources
    - Supports multiple permits
    - Useful for rate limiting

    **CountDownLatch**

    - One-time barrier for threads
    - Allows one thread to wait for multiple threads

    **CyclicBarrier**

    - Reusable synchronization point

    - Allows execution of a task when all threads reach barrier

      

21. What is `future` and `completableFuture`? List some main methods of `ComplertableFuture`.

    A `Future` is an interface introduced in Java 5 that represents the result of an asynchronous computation. It provides methods to check if the computation is complete, wait for its completion, and retrieve the computation result.

    `CompletableFuture`, introduced in Java 8, is an implementation of the `Future` interface that offers significant enhancements for asynchronous programming. It combines the functionality of a `Future` with completion callbacks and composition capabilities, enabling a more functional and reactive programming style.

    Main methods of `CompletableFuture` include:

    1. Creation methods:
       - `supplyAsync(Supplier<U> supplier)`: Creates a CompletableFuture that runs asynchronously using a supplier
       - `runAsync(Runnable runnable)`: Creates a CompletableFuture that runs a Runnable asynchronously
       - `completedFuture(U value)`: Returns a CompletableFuture completed with a given value
    2. Completion methods:
       - `complete(T value)`: Completes this CompletableFuture with the given value
       - `completeExceptionally(Throwable ex)`: Completes this CompletableFuture with an exception
    3. Transformation methods:
       - `thenApply(Function<T,U> fn)`: Transforms the result when completed
       - `thenAccept(Consumer<T> action)`: Consumes the result without returning a value
       - `thenRun(Runnable action)`: Runs an action when completed, ignoring the result
    4. Combination methods:
       - `thenCompose(Function<T,CompletableFuture<U>> fn)`: Chains two async operations sequentially
       - `thenCombine(CompletableFuture<U> other, BiFunction<T,U,V> fn)`: Combines results of two CompletableFutures
       - `allOf(CompletableFuture<?>... cfs)`: Waits for all CompletableFutures to complete
       - `anyOf(CompletableFuture<?>... cfs)`: Waits for any CompletableFuture to complete
    5. Exception handling:
       - `exceptionally(Function<Throwable,T> fn)`: Handles exceptions by providing an alternative result
       - `handle(BiFunction<T,Throwable,U> fn)`: Processes both the result and exception (if any)

    ```java
    import java.util.concurrent.CompletableFuture;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.concurrent.TimeUnit;
    
    public class Main {
        public static void main(String[] args) throws Exception {
            ExecutorService executor = Executors.newFixedThreadPool(3);
            
            // 1. Create CompletableFuture
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    return "Result of async computation";
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }, executor);
            
            // 2. Transform the result
            CompletableFuture<String> transformed = future.thenApply(result -> {
                return "Transformed: " + result.toUpperCase();
            });
            
            // 3. Chain operations with thenCompose
            CompletableFuture<Integer> chainedFuture = future.thenCompose(result -> {
                return CompletableFuture.supplyAsync(() -> {
                    return result.length();
                }, executor);
            });
            
            // 4. Combine two futures
            CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> "Second result", executor);
            
            CompletableFuture<String> combined = future.thenCombine(future2, 
                (result1, result2) -> result1 + " + " + result2);
            
            // 5. Handle exceptions
            CompletableFuture<String> withErrorHandling = future.exceptionally(ex -> {
                System.out.println("Error occurred: " + ex.getMessage());
                return "Default value on error";
            });
            
            // Wait for and print results
            System.out.println("Original result: " + future.get());
            System.out.println("Transformed result: " + transformed.get());
            System.out.println("Chained result: " + chainedFuture.get());
            System.out.println("Combined result: " + combined.get());
            
            executor.shutdown();
        }
    }
    ```

    ```tex
    Original result: Result of async computation
    Transformed result: Transformed: RESULT OF ASYNC COMPUTATION
    Chained result: 27
    Combined result: Result of async computation + Second result
    ```

    

22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in  com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)

    1. One solution use `synchronized` and `wait` , `notify`

       ```java
       public class Main {
           private static final Object lock = new Object();
           private static int num = 1;
       
           public static void main(String[] args) {
               Thread oddThread = new Thread(() -> printNumbers(1));
               Thread evenThread = new Thread(() -> printNumbers(0));
               
               oddThread.start();
               evenThread.start();
           }
       
           private static void printNumbers(int remainder) {
               synchronized (lock) {
                   while (num <= 10) {
                       if (num % 2 == remainder) {
                           System.out.println(Thread.currentThread().getName() + ": " + num++);
                           lock.notify();
                       } else {
                           try {
                               lock.wait();
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                   }
                   lock.notify();
               }
           }
       }
       ```
       
       ```tex
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
       ```
       
    2. One solution use `ReentrantLock` and `await`, `signal`
    
       ```java
       import java.util.concurrent.locks.Condition;
       import java.util.concurrent.locks.Lock;
       import java.util.concurrent.locks.ReentrantLock;
       
       public class Main {
           private static final Lock lock = new ReentrantLock();
           private static final Condition condition = lock.newCondition();
           private static int num = 1;
       
           public static void main(String[] args) {
               Thread oddThread = new Thread(() -> printNumbers(1));
               Thread evenThread = new Thread(() -> printNumbers(0));
               
               oddThread.start();
               evenThread.start();
           }
       
           private static void printNumbers(int remainder) {
               lock.lock();
               try {
                   while (num <= 10) {
                       if (num % 2 == remainder) {
                           System.out.println(Thread.currentThread().getName() + ": " + num++);
                           condition.signal();
                       } else {
                           condition.await();
                       }
                   }
                   condition.signal(); 
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } finally {
                   lock.unlock();
               }
           }
       }
       ```
       
       ```tex
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
       ```
    
24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-30. threads run  sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)

    ```java
    public class Main {
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

    ```tex
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

    

25. completable future:

    1. Homework 1: Write a simple program that uses `CompletableFuture` to asynchronously get the sum  and product of two integers, and print the results.
    
       ```java
       import java.util.concurrent.CompletableFuture;
       
       public class Main {
           public static void main(String[] args) {
               int a = 5, b = 10;
       
               // Asynchronously calculate sum
               CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
                   System.out.println("Sum calculation started...");
                   sleep(2000);  // simulate a task that takes 2 seconds
                   System.out.println("Sum calculation finished.");
                   return a + b;
               });
       
               // Asynchronously calculate product
               CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
                   System.out.println("Product calculation started...");
                   sleep(1000);  // simulate a task that takes 1 second
                   System.out.println("Product calculation finished.");
                   return a * b;
               });
       
               // Process the results when the asynchronous tasks complete
               sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
               productFuture.thenAccept(product -> System.out.println("Product: " + product));
       
               // Main thread continues immediately without waiting for above tasks
               System.out.println("Main thread continues...");
       
               // Wait for both tasks to complete before ending the program
               CompletableFuture.allOf(sumFuture, productFuture).join();
           }
       
           // Helper method to simulate delay (sleep)
           private static void sleep(int millis) {
               try {
                   Thread.sleep(millis);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
       ```
    
       ```tex
       Sum calculation started...
       Product calculation started...
       Main thread continues...
       Product calculation finished.
       Product: 50
       Sum calculation finished.
       Sum: 15
       ```
    
    2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,  reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched  data for further processing. 
    
       ```java
       import java.net.URI;
       import java.net.http.HttpClient;
       import java.net.http.HttpRequest;
       import java.net.http.HttpResponse;
       import java.util.concurrent.CompletableFuture;
       
       public class Main {
       
           private static final HttpClient httpClient = HttpClient.newHttpClient();
       
           public static void main(String[] args) {
       
               // API endpoints simulating products, reviews, and inventory data
               String productsApi = "https://jsonplaceholder.typicode.com/posts/1";
               String reviewsApi = "https://jsonplaceholder.typicode.com/comments/1";
               String inventoryApi = "https://jsonplaceholder.typicode.com/albums/1";
       
               // Asynchronously fetch data from all APIs
               CompletableFuture<String> productsFuture = fetchAsync(productsApi);
               CompletableFuture<String> reviewsFuture = fetchAsync(reviewsApi);
               CompletableFuture<String> inventoryFuture = fetchAsync(inventoryApi);
       
               // Combine results after all async calls finish
               CompletableFuture<Void> allFutures =
                       CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);
       
               // Process combined results
               allFutures.thenRun(() -> {
                   try {
                       String products = productsFuture.get();
                       String reviews = reviewsFuture.get();
                       String inventory = inventoryFuture.get();
       
                       // Simplified result printing (could be JSON parsing in real case)
                       System.out.println("Products Data:\n" + products);
                       System.out.println("\nReviews Data:\n" + reviews);
                       System.out.println("\nInventory Data:\n" + inventory);
       
                   } catch (Exception e) {
                       System.err.println("Error retrieving API data: " + e.getMessage());
                   }
               }).join(); // Wait for completion before exiting main
           }
       
           // Helper method to asynchronously fetch data from a given URL
           private static CompletableFuture<String> fetchAsync(String url) {
               HttpRequest request = HttpRequest.newBuilder()
                       .uri(URI.create(url))
                       .GET()
                       .build();
       
               return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                       .thenApply(HttpResponse::body);
           }
       }
       ```
    
       ```css
       Products Data:
       {
         "userId": 1,
         "id": 1,
         "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
         "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
       }
       
       Reviews Data:
       {
         "postId": 1,
         "id": 1,
         "name": "id labore ex et quam laborum",
         "email": "Eliseo@gardner.biz",
         "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
       }
       
       Inventory Data:
       {
         "userId": 1,
         "id": 1,
         "title": "quidem molestiae enim"
       }
       ```
    
    3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API  call, return a default value and log the exception information.
    
       ```java
       import java.net.URI;
       import java.net.http.HttpClient;
       import java.net.http.HttpRequest;
       import java.net.http.HttpResponse;
       import java.util.concurrent.CompletableFuture;
       
       public class Main {
       
           private static final HttpClient httpClient = HttpClient.newHttpClient();
       
           public static void main(String[] args) {
               //String productsApi = "https://jsonplaceholder.typicode.com/posts/1";
               String productsApi = "https://jsonplaceholder.typicode-INVALID.com/posts/1";
               //String reviewsApi = "https://jsonplaceholder.typicode.com/comments/1";
               String reviewsApi = "https://jsonplaceholder.typicode-INVALID.com/comments/1";
               //String inventoryApi = "https://jsonplaceholder.typicode.com/albums/1";
               String inventoryApi = "https://jsonplaceholder.typicode-INVALID.com/albums/1";
       
               // Fetch data asynchronously with exception handling
               CompletableFuture<String> productsFuture = fetchAsync(productsApi)
                   .exceptionally(e -> {
                       System.err.println("Failed to fetch products: " + e.getMessage());
                       return "{ 'products': 'default product data' }"; // fallback default data
                   });
       
               CompletableFuture<String> reviewsFuture = fetchAsync(reviewsApi)
                   .exceptionally(e -> {
                       System.err.println("Failed to fetch reviews: " + e.getMessage());
                       return "{ 'reviews': 'default reviews data' }";
                   });
       
               CompletableFuture<String> inventoryFuture = fetchAsync(inventoryApi)
                   .exceptionally(e -> {
                       System.err.println("Failed to fetch inventory: " + e.getMessage());
                       return "{ 'inventory': 'default inventory data' }";
                   });
       
               // Wait for all futures to complete and combine results
               CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);
       
               allFutures.thenRun(() -> {
                   try {
                       String products = productsFuture.get();
                       String reviews = reviewsFuture.get();
                       String inventory = inventoryFuture.get();
       
                       System.out.println("Products Data:\n" + products);
                       System.out.println("\nReviews Data:\n" + reviews);
                       System.out.println("\nInventory Data:\n" + inventory);
                   } catch (Exception e) {
                       System.err.println("Unexpected error: " + e.getMessage());
                   }
               }).join();
           }
       
           // Asynchronous fetch with potential exception
           private static CompletableFuture<String> fetchAsync(String url) {
               HttpRequest request = HttpRequest.newBuilder()
                       .uri(URI.create(url))
                       .GET()
                       .build();
       
               return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                       .thenApply(HttpResponse::body);
           }
       }
       ```
    
       ```bash
       Failed to fetch inventory: java.net.ConnectException
       Failed to fetch reviews: java.net.ConnectException
       Failed to fetch products: java.net.ConnectException
       Products Data:
       { 'products': 'default product data' }
       
       Reviews Data:
       { 'reviews': 'default reviews data' }
       
       Inventory Data:
       { 'inventory': 'default inventory data' }
       ```
    
       
