package QueueManager;

import Consumer.ConsumerInterface;
import Message.MessageAbstract;
import Queue.Queue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


// POST /queueManager/v1/message
// QueueManager should be a Singleton
public class QueueManager implements QueueManagerInterface {
    Map<String, Queue> queueMap;
    Map<String, Map<String, ConsumerInterface>> consumerManager;
    private static QueueManager queueManager;
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public QueueManager() {
        queueMap = new ConcurrentHashMap<>();
        consumerManager = new ConcurrentHashMap<>();

        executorService.scheduleAtFixedRate(() -> {
//            System.out.println("Process the queue at fixed interval of 1 seconds and send messages to queues");
            while (true) {
                for (Map.Entry<String, Queue> entry : queueMap.entrySet()) {
//                    System.out.println("Processing Queue " + entry.getKey());
                    Queue queue = entry.getValue();
                    if (queue.getQueueSize() > 0) {
                        for (Map.Entry<String, ConsumerInterface> consumerEntry : consumerManager.get(entry.getKey()).entrySet()) {
                            System.out.println("Processing for Consumer " + consumerEntry.getKey());
                            ConsumerInterface consumer = consumerEntry.getValue();
                            MessageAbstract message = queue.removeMessage();
                            sendMessage(consumer, message);
                        }
                    }
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }


    public static synchronized QueueManager getQueueManagerInstance() {
        if (queueManager == null) {
            queueManager = new QueueManager();
        }
        return queueManager;
    }

    public void receiveMessage(MessageAbstract message, String destinationQueueName) {
        Queue destinationQueue = queueMap.get(destinationQueueName);

        if (destinationQueue == null) {
            System.out.println("Queue not found creating the queue");
            destinationQueue = new Queue(100, destinationQueueName);
        }

        try {
            destinationQueue.addMessage(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void sendMessage(ConsumerInterface consumer, MessageAbstract message) {
        consumer.receiveMessage(message);
    }


    // We create the queue if it does not exist
    public void subscribeQueue(ConsumerInterface consumerInterface, String sourceQueue) {
        if (!queueMap.containsKey(sourceQueue)) {
            Queue queue = new Queue(100, sourceQueue);
            queueMap.put(sourceQueue, queue);
            return;
        }

        if (consumerManager.containsKey(sourceQueue)) {
            consumerManager.get(sourceQueue).put(consumerInterface.getName(), consumerInterface);
        } else {
            Map<String, ConsumerInterface> consumerMap = new HashMap<>();
            consumerMap.put(consumerInterface.getName(), consumerInterface);
            consumerManager.put(sourceQueue, consumerMap);
        }
    }
}
