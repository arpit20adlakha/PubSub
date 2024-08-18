import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTesting {

    ExecutorService executorService = Executors.newFixedThreadPool(3);



    public static void main(String[] args) {

    }


    public void executeRunnable() {
        executorService.execute(() -> {
            System.out.println("Executing Runnable");
        });
    }
}
