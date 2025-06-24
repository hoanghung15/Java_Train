package TestCustom;

public class OddThread implements Runnable {
    public SharedPrinter sharedPrinter;

    public OddThread(SharedPrinter sharedPrinter) {
        this.sharedPrinter = sharedPrinter;
    }
    @Override
    public void run() {
        sharedPrinter.printOdd();
    }
}
