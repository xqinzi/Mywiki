package hello;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//使用@Configuration将本类作为一个Spring配置类
@Configuration
public class TestConfiguration {
    public static class SpringBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // 构造一个SpringBean对象，并注册到Spring容器中
    @Bean
    public SpringBean createSpringBean() {
        SpringBean bean = new SpringBean();
        bean.setName("spring bean name");
        return bean;
    }

    @Test
    public void test() {
        @SuppressWarnings("resource")
        // 使用基于注解配置的ApplicationContext启动Spring。
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // 将配置类注册给Spring
        ctx.register(TestConfiguration.class);
        //刷新Spring，使得配置生效
        ctx.refresh();
        // 从Spring容器中获取SpringBean实例
        SpringBean bean = ctx.getBean(SpringBean.class);
        assertEquals(bean.getName(), "spring bean name");
    }

}