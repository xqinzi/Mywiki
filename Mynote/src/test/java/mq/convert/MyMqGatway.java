package mq.convert;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者端调用
 * @author root
 *
 */
public class MyMqGatway {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void sendDataToCrQueue(Object obj) {
		amqpTemplate.convertAndSend("queue_one_key", obj);
	}	
}