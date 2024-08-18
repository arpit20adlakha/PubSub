package Producer;

public interface ProducerInterface extends Runnable {
    public void produceMessage(Message.MessageAbstract message, String destinationQueue);
}
