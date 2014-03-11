package com.mywiki.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * 
 * @author mywiki95@gmail.com
 * 阻塞队列 BlockingQueue
 * BlockingQueue的使用
 * 一个BlockingQueue的典型使用是一个线程不断生产对象往队列里放，另一个线程往队列里取对象进行消费。生产线程会保持一致产生新的对象
 * 并且插入到队列中，直到队列到达了它的存储上限，生产线程再往队列插入时会发现阻塞，并且一直保持阻塞直到消费线程从队列中取出一个对象。
 * 消费线程会保持从队列中取出对象并且进行处理，如果消费线程试图从一个空的队列中取出对象，那么消费线程将会阻塞直到生产线程往队列中放
 * 入了新的对象。
 * 
 * BlockingQueue的方法
 * BlockingQueue由4中不同的方法来添加，删除和检测。每组方法有不同的特点泳衣满足不能立即执行响应请求的操作。
 * 操作				抛出异常		返回操作结果	阻塞		超时
 * 插入				add(o)		offer(o)	put(o)	offer(o,timeout,timeunit)
 * 删除				remove(o)	poll(o)		take(o)	poll(timeout,timeunit)
 * 检测element(o)	peek(o)	
 * 1.抛出异常：如果对应方法没有立即执行则抛出异常。
 * 2.返回操作结果：如果对应方法立即执行返回true，否则则返回false（超出队列容量）。
 * 3.阻塞：如果对应方法无法立即执行，则将阻塞等待直到方法完成。
 * 4.超时：如果对应方法无法立即执行，则会一直等待但不超过给定的超时范围，返回相应的结果。
 * BlockingQueue的实现类
 * ArrayBlockingQueue
 * DelayQueue
 * LinkedBlockingDeque
 * PriorityBlockingQueue
 * SynchronousQueue
 * 
 */
@SuppressWarnings("rawtypes")
public class BlockingQueueExample {
	public static void main(String[] args) throws Exception {
		BlockingQueue queue = new ArrayBlockingQueue(1024);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		new Thread(producer).start();
		new Thread(consumer).start();
		Thread.sleep(4000);
	}
}
// 生产者
@SuppressWarnings("rawtypes")
class Producer implements Runnable {
	protected BlockingQueue queue = null;
	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			System.out.println("生产者\t put 100");
			queue.put("100");
			System.out.println("生产者\t put 200");
			queue.put("200");
			Thread.sleep(2000);
			System.out.println("生产者\t put 300");
			queue.put("300");
			Thread.sleep(5000);
			System.out.println("生产者\t put 400");
			queue.put("400");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
// 消费者
@SuppressWarnings("rawtypes")
class Consumer implements Runnable {
	protected BlockingQueue queue = null;
	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}
	public void run() {
		try {
			while(true)
				System.out.println("消费者\t take "+queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}