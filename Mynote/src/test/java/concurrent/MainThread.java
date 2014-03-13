package concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * Future模式的主要使用场景：当前线程需要依赖另一线程的返回数据并且处理数据的线程又相当耗时，
 * 那么Future模式就可以使主线程提交数据请求给另一线程后继续处理业务逻辑，等需要时将数据从另
 * 一线程返回，很好的利用了等待时间。举一个能说明问题又简单的例子，本例使用JDK库的Future相关类实现：
 * @author root
 *
 */
public class MainThread {

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		// TODO Auto-generated method stub
		DataProcessThread dataProcessThread = new DataProcessThread();
		FutureTask<String> future = new FutureTask<String>(dataProcessThread);

		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(future);

		Thread.sleep(10000);//模拟继续处理自身其他业务
		while (true) {
			if (future.isDone()) {
				System.out.println(future.get());
				break;
			}
		}
		executor.shutdown();
	}

}