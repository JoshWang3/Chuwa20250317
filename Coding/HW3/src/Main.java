

public class Main {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        //deadlock example
//        Thread t1 = new Thread(() -> {
//            synchronized(lock1) {
//                System.out.println("t1 is holding lock1");
//                synchronized(lock2) {
//                    System.out.println("t1 is holding lock2");
//                }
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            synchronized(lock2) {
//                System.out.println("t2 is holding lock2");
//                synchronized(lock1) {
//                    System.out.println("t2 is holding lock1");
//                }
//            }
//        });

//        Thread t1 = new Thread(() -> {
//            synchronized(lock1) {
//                System.out.println("t1 is holding lock1");
//                synchronized(lock2) {
//                    System.out.println("t1 is holding lock2");
//                }
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            synchronized(lock1) {
//                System.out.println("t2 is holding lock2");
//                synchronized(lock2) {
//                    System.out.println("t2 is holding lock1");
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();
    }
}
