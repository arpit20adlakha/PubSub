package QueueManager;

import Consumer.ConsumerInterface;
import Message.MessageAbstract;
import Queue.Queue;

import java.util.HashMap;
import java.util.Map;

public interface QueueManagerInterface {
    void receiveMessage(MessageAbstract message, String destinationQueue);
}


