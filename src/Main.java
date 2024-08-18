import Consumer.Consumer;
import Consumer.ConsumerInterface;
import Producer.Producer;
import QueueManager.QueueManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ConsumerInterface[] consumers = new Consumer[10];
        QueueManager queueManager = QueueManager.getQueueManagerInstance();
        // Create 10 consumers
        for (int i = 0; i < 10; i++) {
            consumers[i] = new Consumer(queueManager);
            consumers[i].subscribeQueue("Orders");
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // Create 10 producers
        for (int i = 0; i < 10; i++) {
            Producer producer = new Producer(queueManager);
            executorService.execute(producer);
        }

        executorService.shutdown();
    }
}