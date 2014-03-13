package concurrent;

import java.util.concurrent.Callable;

public class DataProcessThread implements Callable<String> {
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(10000);//模拟数据处理
		return "数据返回";
	}
}