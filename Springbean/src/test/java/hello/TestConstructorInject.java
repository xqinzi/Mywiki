package hello;

import static org.junit.Assert.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestConstructorInject {
    @Configuration
    @ComponentScan("hello")
//	@EnableAspectJAutoProxy(proxyTargetClass = true)
//	@Aspect
	static class Config {
//		@Pointcut("execution(* hello.*.*(..))")
//		public void pointcut() {
//		}
//
//		@Around("pointcut()")
//		public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//			return joinPoint.proceed();
//		}
	}

    @Autowired
    private SpringBean springBean;
    @Autowired
    private SpringBean1 springBean1;

    @Test
    public void test() {
    	assertEquals(springBean.getCreateDate(), springBean1.getBean().getCreateDate());
    }
}
