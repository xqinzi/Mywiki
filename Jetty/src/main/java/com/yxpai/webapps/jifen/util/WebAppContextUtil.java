package com.yxpai.webapps.jifen.util;

import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 将SpringBean工厂设置到Web环境中
 * @author mywiki95@gmail.com
 *
 */
public class WebAppContextUtil extends WebAppContext implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	WebAppContextUtil.applicationContext = applicationContext;
    	
    	// 将Bean设置到Web环境中
    	super.setClassLoader(WebAppContextUtil.applicationContext.getClassLoader());
		XmlWebApplicationContext xmlWebAppContext = new XmlWebApplicationContext();
		xmlWebAppContext.setParent(WebAppContextUtil.applicationContext);
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