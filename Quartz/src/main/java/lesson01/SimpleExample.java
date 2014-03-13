package lesson01;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleExample {

	public void run() throws Exception {
		// 调度工厂
		SchedulerFactory sf = new StdSchedulerFactory();
		// 从工厂中，获取一个任务调度实体
		Scheduler sched = sf.getScheduler();

		// 定义任务运行时间，这里的话，你需要改成你想要任务在什么时候执行
		Date runTime = DateUtil.buildDate(2014,3,12,21,12,11);
		System.out.println("任务将在：" + DateUtil.fromDate2String(runTime) + "执行");

		// 初始化任务实体
		JobDetail job = JobBuilder
							.newJob(HelloJob.class)
							.withIdentity("job1", "group1")
							.build();

		// 初始化触发器
		Trigger trigger = TriggerBuilder
							.newTrigger()
							.withIdentity("trigger1", "group1")
							.startAt(runTime)
							.build();

		// 设置定时任务
		sched.scheduleJob(job, trigger);
/**
		// define the job and tie it to our HelloJob class
	    JobDetail job = JobBuilder
	    				.newJob(HelloJob.class)
	        			.withIdentity("job1", "group1")
	        			.build();

	    // Trigger the job to run now, and then repeat every 40 seconds
	    Trigger trigger = TriggerBuilder
	    				.newTrigger()
	        			.withIdentity("trigger1", "group1")
	        			.startNow()
	        			.withSchedule(simpleSchedule()
	                	.withIntervalInSeconds(40)
	                	.repeatForever())            
	        			.build();

	    // Tell quartz to schedule the job using our trigger
	    scheduler.scheduleJob(job, trigger);
 */
		
		// 启动定时任务
		sched.start();
		try {
			Thread.sleep(300000L);
		} catch (Exception e) {
		}
		// 停止
		sched.shutdown(true);
	}

	public static void main(String[] args) throws Exception {
		new SimpleExample().run();
	}
}
