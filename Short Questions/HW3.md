### 2.
```java
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

```

### 3.
we can extend thread, or using thread(runnable target), or create thread pool.

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running: " + getName());
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        
        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();
    }
}
```

### 4.

**Runnable**: Represents a task that can be executed but doesn't return a result. It's used when you just need the task to run and don't expect a return value.

Signature: submit(Runnable task)
``
The Runnable task does not return a result or throw exceptions. If it does, you'll need to handle them inside the task.

**Callable**: Similar to Runnable, but it can return a result and throw exceptions. It’s used when you need to return a value or handle checked exceptions from the task.

Signature: submit(Callable < T > Task)

T is the type of the result that the Callable returns.

### 5.

**start() method:**

The start() method is used to initiate a new thread of execution.

When you call start() on a Thread object, it invokes the run() method in a separate, new thread.

You should not directly call the run() method, as it will not create a new thread. Instead, use start().

**run() method**

The run() method contains the code that will be executed in the thread.

You can override this method to define the tasks the thread will perform.

It is automatically invoked when start() is called. If you invoke run() directly, it will execute in the current thread, not in a new one.


### 6.

Implementing Runnable is generally better than extending Thread because it provides more flexibility, better code organization, and aligns with Java’s best practices.

### 7.
NEW – The thread has been created but has not started yet.

RUNNABLE – The thread is ready to run and is either running or waiting for CPU time.

BLOCKED – The thread is waiting for a monitor lock to enter a synchronized block/method.

WAITING – The thread is waiting indefinitely for another thread to notify it.

TIMED_WAITING – The thread is waiting for a specified period (e.g., using Thread.sleep(), join(time), or wait(time)).

TERMINATED – The thread has finished execution or has been stopped.

### 8.

A deadlock is a situation in concurrent computing where two or more processes are stuck indefinitely, each waiting for a resource that another process holds. This results in a circular dependency, preventing further progress.

```java
class ResourceA {
    synchronized void methodA(ResourceB b) {
        System.out.println("Thread 1: Locked ResourceA, waiting for ResourceB...");
        b.lastMethodB();
    }
    synchronized void lastMethodA() {
        System.out.println("Thread 1: Executing lastMethodA");
    }
}

class ResourceB {
    synchronized void methodB(ResourceA a) {
        System.out.println("Thread 2: Locked ResourceB, waiting for ResourceA...");
        a.lastMethodA();
    }
    synchronized void lastMethodB() {
        System.out.println("Thread 2: Executing lastMethodB");
    }
}

public class DeadlockSolution {
    public static void main(String[] args) {
        final ResourceA resourceA = new ResourceA();
        final ResourceB resourceB = new ResourceB();

        // Thread 1: locks ResourceA and tries to lock ResourceB
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized(resourceA) {  // Lock ResourceA first
                    System.out.println("Thread 1: Locked ResourceA");
                    synchronized(resourceB) {  // Lock ResourceB second
                        System.out.println("Thread 1: Locked ResourceB");
                        resourceA.methodA(resourceB);
                    }
                }
            }
        });

        // Thread 2: locks ResourceA and tries to lock ResourceB
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized(resourceA) {  // Lock ResourceA first
                    System.out.println("Thread 2: Locked ResourceA");
                    synchronized(resourceB) {  // Lock ResourceB second
                        System.out.println("Thread 2: Locked ResourceB");
                        resourceB.methodB(resourceA);
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}

```

### 9.

Shared Memory:

Threads in the same process share the same memory space. This means they can access and modify the same variables. However, this shared memory can lead to race conditions if proper synchronization isn't used.

Synchronization Mechanisms:

Mutexes (Mutual Exclusion Locks): A mutex ensures that only one thread at a time can access a shared resource or piece of code, preventing race conditions.

Semaphores: These are used to manage access to a pool of resources. They allow multiple threads to communicate with each other, signaling when a resource is available or when a task is complete.

Condition Variables: These allow threads to wait for a certain condition to be met (e.g., one thread waits for another to finish its task). Condition variables are often used with mutexes to synchronize threads.

Message Passing:

Threads can communicate via queues or buffers, where one thread sends data to a queue, and another thread can read the data. This is often used in concurrent programming where you have producers (threads producing data) and consumers (threads consuming data).

Event Notifications:

Threads can use events or signals to notify each other when something has happened or when a particular state has been reached, allowing them to synchronize their actions.

Pipes or Sockets:

In some systems, threads may communicate via pipes or sockets (similar to how processes communicate in inter-process communication). This is more common in distributed systems or in systems with multiple processes, but it can also be used between threads in some cases.

Atomic Operations:

Some programming languages provide atomic operations that allow one thread to perform operations on a shared resource in such a way that the operation is guaranteed to be completed without interruption, reducing the need for locks.

### 10.

1. Object Lock (Instance Lock)
Scope: Locks a specific instance of a class.

Usage: Achieved using the synchronized keyword on instance methods or a synchronized block on this.

Effect: Only one thread can access the synchronized method/block on the same instance at a time, but other instances of the class can be accessed concurrently.

2. Class Lock (Static Lock)
Scope: Locks the entire class (all instances).

Usage: Achieved using the synchronized keyword on static methods or synchronizing on the class object (ClassName.class).

Effect: Only one thread can execute synchronized static methods across all instances of the class.

### 11.
Java’s join() method in threads makes one thread wait for another to finish.

### 12.

In Java, yield() is a method in the Thread class that hints to the thread scheduler that the current thread is willing to pause and allow other threads to execute.

### 13.
A Thread Pool is a collection of pre-instantiated worker threads that can be used to execute multiple tasks concurrently, instead of creating new threads for each task. This helps in managing resources efficiently, improving performance, and avoiding the overhead of thread creation and destruction.

1. Fixed Thread Pool
2. Cached Thread Pool
3. Single Thread Pool
4. Scheduled Thread Pool
5. Work Stealing Pool (Java 8+)

A Task Queue in a thread pool is a queue that holds tasks (runnable or callable) that are waiting to be executed by worker threads. When a thread in the pool becomes available, it picks up a task from the queue and executes it.

### 14.

The standard Java library **`java.util.concurrent`** provides built-in support for creating thread pools. The main interface that provides functionality for a thread pool in Java is ExecutorService, which is part of the java.util.concurrent package.

### 15.
 use executor.submit()

### 16.

✔ Reuses threads instead of creating new ones
✔ Manages tasks efficiently
✔ Prevents performance bottlenecks

### 17.

shutdown():

The shutdown() method initiates an orderly shutdown of the executor. This means it stops accepting new tasks but allows any ongoing tasks to finish before it shuts down. It's a graceful termination, where tasks that are currently running are allowed to complete.

It doesn't interrupt any running tasks, so the executor will only fully shut down once all tasks have completed.

shutdownNow():

The shutdownNow() method forces an immediate shutdown of the executor. It stops the executor from accepting new tasks, and it attempts to stop all running tasks as soon as possible, possibly interrupting them.

It's a more abrupt shutdown compared to shutdown(), and may result in tasks being incomplete or lost, depending on how they are managed.

### 18.

An atomic class in Java is part of the java.util.concurrent.atomic package and provides thread-safe operations on variables without using explicit synchronization (synchronized or Lock). These classes ensure atomicity, meaning operations like incrementing, updating, or comparing values happen as a single, indivisible step.


AtomicInteger → Atomic operations on int
AtomicLong → Atomic operations on long
AtomicBoolean → Atomic operations on boolean
AtomicReference< T  >→ Atomic operations on object references
AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray → Atomic operations on arrays

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        // Increment the counter atomically
        System.out.println("Initial value: " + counter.get());
        counter.incrementAndGet(); // Equivalent to ++counter
        System.out.println("After increment: " + counter.get());

        // Atomic update
        counter.addAndGet(5); // Equivalent to counter += 5
        System.out.println("After adding 5: " + counter.get());
    }
}

```


### 19.

Concurrent collections in Java are data structures from the java.util.concurrent package designed to handle multi-threaded access safely and efficiently. Unlike traditional collections (like ArrayList or HashMap), which need external synchronization to be thread-safe, concurrent collections use internal mechanisms to ensure consistency without significant performance degradation.

ConcurrentHashMap – A thread-safe alternative to HashMap that supports high-performance concurrent operations.

CopyOnWriteArrayList – A thread-safe version of ArrayList that creates a new copy of the list for modifications.

CopyOnWriteArraySet – Similar to CopyOnWriteArrayList, but for sets.

ConcurrentLinkedQueue – A non-blocking, thread-safe queue based on linked nodes.

ConcurrentLinkedDeque – A non-blocking, thread-safe double-ended queue.

BlockingQueue (e.g., ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue) – Queues that support blocking operations, useful in producer-consumer scenarios.

BlockingDeque (e.g., LinkedBlockingDeque) – A double-ended blocking queue.

ConcurrentSkipListMap – A thread-safe, sorted map based on a skip list, an alternative to TreeMap.

ConcurrentSkipListSet – A thread-safe, sorted set based on a skip list.


### 20.

**ReentrantLock (java.util.concurrent.locks.ReentrantLock)**:

- A flexible, explicit lock that can be locked and unlocked manually.
    
- It is "reentrant," meaning the same thread can acquire the lock multiple times without causing a deadlock.
    
- It provides additional features like timed locks and the ability to interrupt waiting threads.


**ReadWriteLock (java.util.concurrent.locks.ReadWriteLock)**:

- A lock that allows multiple readers to access the resource simultaneously, but only one writer can access the resource, and it blocks all readers during writing.
    
- `ReentrantReadWriteLock` is the most commonly used implementation.


**Synchronized Keyword**:

- A simpler way to handle locking in Java, by marking a method or block of code as `synchronized`.
    
- Only one thread can execute a synchronized method/block at a time for the same object.

### 21.

Future :  A `Future` represents a task that will eventually complete and return a result. It is part of the `java.util.concurrent` package.

- You can submit tasks to be executed asynchronously, such as in a thread pool.
    
- The `get()` method of `Future` blocks until the task is finished and the result is available (or throws an exception if the computation failed).
    
- You can check if the task is completed with `isDone()` or if it was cancelled with `isCancelled()`.


A `CompletableFuture` is an extension of `Future` that allows more flexibility and advanced capabilities in asynchronous programming. It's also part of the `java.util.concurrent` package and can be used to compose tasks, handle exceptions, and perform other asynchronous operations more easily.

- Unlike `Future`, a `CompletableFuture` can be manually completed using the `complete()` method.
    
- It allows you to chain multiple actions, handle results asynchronously with methods like `thenApply()`, `thenAccept()`, and `thenRun()`.
    
- You can also handle exceptions and combine multiple futures.



### 25.

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        int a = 5, b = 10;
        
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);
        
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));
        
        // Ensure the program doesn't exit before async tasks complete
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}

```