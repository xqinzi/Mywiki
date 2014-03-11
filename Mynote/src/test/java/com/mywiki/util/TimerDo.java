package com.mywiki.util;
import java.util.Date;   
import java.util.Timer;   
import java.util.TimerTask;   
/**
 * 定时器
 * @author mywiki95@gmail.com
 *
 */
public class TimerDo {   
    public static void main(String[] args) {   
        Integer cacheTime = 1000 * 3;

        Timer timer = new Timer();
        // (TimerTask task任务, long delay延迟时间, long period多久执行一次)  
        timer.schedule(new TimerTask() {   
            @Override  
            public void run() {   
                System.out.println(new Date());   
            }
        }, 1000, cacheTime);   
    }   
}