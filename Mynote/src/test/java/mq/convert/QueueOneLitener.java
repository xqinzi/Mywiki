package mq.convert;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
/**
 * 消费者端调用
 *
 */
public class QueueOneLitener implements  MessageListener{
	@Override
	public void onMessage(Message message) {
		System.out.println(" data :" + message.getBody());
	}
}