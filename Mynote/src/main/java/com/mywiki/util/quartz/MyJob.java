package com.mywiki.util.quartz;

import java.util.Date;

/**
 * 需要执行的任务
 */
public class MyJob {

	// 把要执行的操作，
	public void execute() {
		System.err.println("测试Quartz\t" + new Date());
	}
}