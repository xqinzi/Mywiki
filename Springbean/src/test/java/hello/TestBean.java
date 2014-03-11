package hello;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class TestBean {
    static class SpringBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //注册SpringBean
    @Bean
    public static SpringBean createSpringBean() {
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
        ctx.register(TestBean.class);
        // 刷新Spring，使得配置生效
        ctx.refresh();
        // 从Spring容器中获取SpringBean实例
        SpringBean bean = ctx.getBean(SpringBean.class);
        assertEquals(bean.getName(), "spring bean name");
    }
}