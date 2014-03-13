package lesson01;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * 任务需要实现Job接口
 */
public class HelloJob implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		//这里简单输出一句话，和当前的系统时间
		System.out.println("Hello World. " + DateUtil.getCurrentDate());
	}

}
