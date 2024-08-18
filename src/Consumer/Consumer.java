package Consumer;

import Message.MessageAbstract;
import QueueManager.QueueManager;

import java.util.UUID;

// implement the Runnable to run multiple consumers parallely
public class Consumer implements ConsumerInterface {
    UUID consumerId;
    QueueManager queueManager;

    // Inject the dependency of QueueManager
    public Consumer(QueueManager queueManager) {
        this.consumerId = UUID.randomUUID();
        this.queueManager = queueManager;
    }

    public void receiveMessage(MessageAbstract message) {
        System.out.println("Message received consumer " + this.consumerId + " is " + message.getMessage());
    }

    public void subscribeQueue(String queueName) {
        queueManager.subscribeQueue(this, queueName);
    }

    public String getName() {
        return this.consumerId.toString();
    }
}
