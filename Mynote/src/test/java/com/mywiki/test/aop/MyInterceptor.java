package com.mywiki.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;  
import org.aspectj.lang.annotation.After;  
import org.aspectj.lang.annotation.AfterReturning;  
import org.aspectj.lang.annotation.AfterThrowing;  
import org.aspectj.lang.annotation.Around;  
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;  
import org.aspectj.lang.annotation.Pointcut;  
  
/** 
 * 切面 
 */  
@Aspect  
public class MyInterceptor {  
    @Pointcut("execution(* com.mywiki.test.aop.PersonServiceBean.*(..))")  
    private void anyMethod(){}//定义一个切入点   
/*
 * @Pointcut("execution(* com.mywiki.test.aop.PersonServiceBean.*(..))")  
这句话是方法切入点，execution为执行的意思，*代表任意返回值，然后是包名，.*意思是包下面的所有子包。(..)代
表各种方法.然后下面的注解就比较简单了，就是在使用方法前和中，还有环绕拦截 */
    
    
    @Before("anyMethod() && args(name)")  
    public void doAccessCheck(String name){  
        System.out.println("前置通知"+"\t参数为="+name);  
    }  
      
    @AfterReturning("anyMethod()")  
    public void doAfter(){  
        System.out.println("后置通知");  
    }  
      
    @After("anyMethod()")  
    public void after(){  
        System.out.println("最终通知");  
    }  
      
    @AfterThrowing("anyMethod()")  
    public void doAfterThrow(){  
        System.out.println("例外通知");  
    }  
      
    @Around("anyMethod()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
        System.out.println("进入环绕通知");  
        Object object = pjp.proceed();//执行该方法   
        System.out.println("退出方法");  
        return object;  
    }  
}  