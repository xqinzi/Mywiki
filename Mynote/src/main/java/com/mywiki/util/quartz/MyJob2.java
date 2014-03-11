package com.mywiki.util.quartz;

import java.util.Date;  
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.springframework.scheduling.quartz.QuartzJobBean;  
  
/**  
 * 具体的任务，需要继承QuartzJobBean，其实QuartzJobBean实现了quartz框架中的Job接口  
 *  
 */  
public class MyJob2 extends QuartzJobBean {  
  
    @Override  
    protected void executeInternal(JobExecutionContext jobexecutioncontext)  
            throws JobExecutionException {  
            System.err.println("测试Quartz[集成继承QuartzJobBean]\t"+new Date());  
    }  
  
} 