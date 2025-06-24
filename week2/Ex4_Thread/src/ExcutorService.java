import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcutorService {

    public static void main(String[] args) {
//        ExecutorService excutorService = Executors.newFixedThreadPool(3);
        ExecutorService excutorService = Executors.newCachedThreadPool();
//        ExcutorService excutorService = Executors.newScheduledThreadPool();
        Runnable task1= ()->{
            System.out.println("task1");
        };
        Runnable task2= ()->{
            System.out.println("task2");
        };
        Runnable task3= ()->{
            System.out.println("task3");
        };
        excutorService.execute(task1);
        excutorService.execute(task2);
        excutorService.execute(task3);
    }
}
