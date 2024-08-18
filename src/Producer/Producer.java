package Producer;

import Message.MessageAbstract;
import Message.Message;
import QueueManager.QueueManager;

import java.util.UUID;


// implement the Runnable to run multiple producers parallelly
public class Producer implements ProducerInterface {
    private final QueueManager queueManager;
    private final UUID producerId;

    // Inject the dependency of QueueManager
    public Producer(QueueManager queueManager) {
        this.queueManager = queueManager;
        this.producerId = UUID.randomUUID();
    }

    public void produceMessage(MessageAbstract message, String destinationQueue) {
        this.queueManager.receiveMessage(message, destinationQueue);
    }

    public String getProducerId() {
        return this.producerId.toString();
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
           Message message = new Message("message" + i + " " + this.getProducerId());
           this.produceMessage(message, "Orders");
        }
    }
}
