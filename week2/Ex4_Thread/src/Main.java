import TestCustom.EvenThread;
import TestCustom.OddThread;
import TestCustom.SharedPrinter;

public class Main {
    private static final Object lock = new Object();
    private static int number = 1;
    private static final int MAX = 20;

    public static void main(String[] args) {
//        Thread oddThread = new Thread(() -> {
//            while (number <= MAX) {
//                synchronized (lock) {
//                    if (number % 2 != 0) {
//                        System.out.println("Odd: " + number);
//                        number++;
//                        lock.notify();
//                    } else {
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//
//        Thread evenThread = new Thread(() -> {
//            while (number <= MAX) {
//                synchronized (lock) {
//                    if (number % 2 == 0) {
//                        System.out.println("Even: " + number);
//                        number++;
//                        lock.notify();
//                    } else {
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//
//        oddThread.start();
//        evenThread.start();

        SharedPrinter sharedPrinter = new SharedPrinter();

        Thread oddThread = new Thread(new OddThread(sharedPrinter));
        Thread evenThread = new Thread(new EvenThread(sharedPrinter));

        oddThread.start();
        evenThread.start();
    }
}
