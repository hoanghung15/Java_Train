package TestCustom;

public class EvenThread implements Runnable {
    public SharedPrinter sharedPrinter;

    public EvenThread(SharedPrinter sharedPrinter) {
        this.sharedPrinter = sharedPrinter;
    }

    @Override
    public void run() {
        sharedPrinter.printEven();
    }
}
