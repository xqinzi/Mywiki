package com.yxpai.webapps.jifen.util;

import javax.servlet.ServletRegistration;

import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 将SpringBean工厂设置到Web环境中
 * 同时将SpringMVC工厂设置到Web环境中，通过Spring的类配置功能初始化SpringBean，然后注入到Web环境中
 * @author mywiki95@gmail.com
 *
 */
public class WebAppContextUtil2 extends WebAppContext implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	WebAppContextUtil2.applicationContext = applicationContext;

    	// SpringMVC工厂设置到Web环境中
    	AnnotationConfigWebApplicationContext webApplicationContext =
                new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebAppConfig.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic dynamic = super.getServletContext().addServlet("dispatcherServlet", dispatcherServlet);
        dynamic.addMapping("/");
    	
    	// 将Bean设置到Web环境中
    	super.setClassLoader(WebAppContextUtil2.applicationContext.getClassLoader());
		XmlWebApplicationContext xmlWebAppContext = new XmlWebApplicationContext();
		xmlWebAppContext.setParent(WebAppContextUtil2.applicationContext);
		xmlWebAppContext.setConfigLocation("");
		xmlWebAppContext.setServletContext(super.getServletContext());
		xmlWebAppContext.refresh();
		super.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				xmlWebAppContext);
    }
    /**
     * 获得Bean工厂
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext; 
    }
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T>T getBean(String beanName , Class<T>clazz) {
        return applicationContext.getBean(beanName , clazz);
    }
}