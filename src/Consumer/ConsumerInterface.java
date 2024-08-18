package Consumer;

import Message.MessageAbstract;

public interface ConsumerInterface {

   public void receiveMessage(MessageAbstract messageAbstract);

   public String getName();

   public void subscribeQueue(String queueName);
}
