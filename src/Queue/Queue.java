package Queue;

import Message.MessageAbstract;

import java.util.AbstractQueue;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class Queue {
    final int capacity;
    final UUID uuid;
    final String queueName;
    final AbstractQueue<MessageAbstract> deque;

    public Queue(int capacity, String queueName) {
        this.capacity = capacity;
        this.uuid = UUID.randomUUID();
        this.queueName = queueName;
        deque = new ArrayBlockingQueue<>(capacity);
    }

    public void addMessage(MessageAbstract message) throws Exception {
        if (deque.size() == capacity) {
            throw new Exception("Queue is full");
        }
        deque.add(message);
    }

    public MessageAbstract removeMessage() {
        return deque.poll();
    }

    public int getQueueSize() {
        return deque.size();
    }

    public String getQueueName() {
        return queueName;
    }

}
