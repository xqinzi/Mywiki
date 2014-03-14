package hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestScope {
    private final static Log log = LogFactory.getLog(TestScope.class);
    @Configuration
    static class Config {
        // 注册一个单例的bean
        @Bean
        public static SpringBean createSpringBean() {
            log.info("初始化SpringBean");
            return new SpringBean();
        }

        // 注册一个多例的bean
        @Bean
        @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
        public static SpringBean1 createSpringBean1() {
            log.info("初始化SpringBean1");
            return new SpringBean1();
        }
    }
    static class SpringBean {
        private long createDate;

        public SpringBean() {
            createDate = System.nanoTime();
        }

        public long getCreateDate() {
            return createDate;
        }
    }
    static class SpringBean1 {
        private long createDate;

        public SpringBean1() {
            createDate = System.nanoTime();
        }

        public long getCreateDate() {
            return createDate;
        }
    }
    
    // 注册一个单例的bean
    @Configuration
    static class SpringBean3 {
        private long createDate;

        public SpringBean3() {
            createDate = System.nanoTime();
        }

        public long getCreateDate() {
            return createDate;
        }
    }

    // 注册一个多例的bean
    @Configuration
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    static class SpringBean4 {
        private long createDate;

        public SpringBean4() {
            createDate = System.nanoTime();
        }

        public long getCreateDate() {
            return createDate;
        }
    }
    // 获取单例bean
    @Autowired
    private SpringBean springBean;
    // 获取多例bean
    @Autowired
    private SpringBean1 springBean1;
    // 获取单例bean
    @Autowired
    private SpringBean3 springBean3;
    // 获取多例bean
    @Autowired
    private SpringBean4 springBean4;

    @Test
    public void test() {
        // 调用单例bean的getCreateDate()方法返回的数据是一样的
        assertTrue(springBean.getCreateDate() == springBean
                .getCreateDate());
        // 调用多例bean的getCreateDate()方法返回的数据是不一样的
        assertFalse(springBean1.getCreateDate() == springBean1
                .getCreateDate());
        // 调用单例bean的getCreateDate()方法返回的数据是一样的
        assertTrue(springBean3.getCreateDate() == springBean3
                .getCreateDate());
        // 调用多例bean的getCreateDate()方法返回的数据是不一样的
        assertFalse(springBean4.getCreateDate() == springBean4
                .getCreateDate());
    }
}
