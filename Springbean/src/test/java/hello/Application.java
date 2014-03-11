package hello;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @EnableJpaRepositories：告诉JPA找到继承了repository的接口，并为它创建相应的实现类，JpaRepository也继承了repository。
 * DataSource：声明一个存储对象的数据库
 * LocalContainerEntityManagerFactoryBean：对象的操作类，通过lef.setPackagesToScan("hello");避免了创建persistence.xml,它告诉JPA到hello包下面找bean类
 * JpaVendorAdapter：为LocalContainerEntityManagerFactoryBean定义了基于hibernate的JPA适配器
 * PlatformTransactionManager：用于处理事务持久化
 * @author mywiki95@gmail.com
 *
 */
@Configuration
@EnableJpaRepositories
public class Application {
	 	@Bean
	    public DataSource dataSource() {
	        return new EmbeddedDatabaseBuilder().setType(H2).build();
	    }

	    @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
	        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
	        lef.setDataSource(dataSource);
	        lef.setJpaVendorAdapter(jpaVendorAdapter);
	        lef.setPackagesToScan("hello");
	        return lef;
	    }

	    @Bean
	    public JpaVendorAdapter jpaVendorAdapter() {
	        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
	        hibernateJpaVendorAdapter.setShowSql(false);
	        hibernateJpaVendorAdapter.setGenerateDdl(true);
	        hibernateJpaVendorAdapter.setDatabase(Database.H2);
	        return hibernateJpaVendorAdapter;
	    }

	    @Bean
	    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	        return new JpaTransactionManager(entityManagerFactory);
	    }


	    public static void main(String[] args) {
	        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
	        /*AnnotationConfigWebApplicationContext webApplicationContext =  
	                new AnnotationConfigWebApplicationContext();  
	        webApplicationContext.register(Application.class);*/
	        CustomerRepository repository = context.getBean(CustomerRepository.class);

	        // save a couple of customers
	        repository.save(new Customer("Jack", "Bauer"));
	        repository.save(new Customer("Chloe", "O'Brian"));
	        repository.save(new Customer("Kim", "Bauer"));
	        repository.save(new Customer("David", "Palmer"));
	        repository.save(new Customer("Michelle", "Dessler"));

	        // fetch all customers
	        List<Customer> customers = repository.findAll();
	        System.out.println("Customers found with findAll():");
	        System.out.println("-------------------------------");
	        for (Customer customer : customers) {
	            System.out.println(customer);
	        }
	        System.out.println();

	        // fetch an individual customer by ID
	        Customer customer = repository.findOne(1L);
	        System.out.println("Customer found with findOne(1L):");
	        System.out.println("--------------------------------");
	        System.out.println(customer);
	        System.out.println();

	        // fetch customers by last name
	        List<Customer> bauers = repository.findByLastName("Bauer");
	        System.out.println("Customer found with findByLastName('Bauer'):");
	        System.out.println("--------------------------------------------");
	        for (Customer bauer : bauers) {
	            System.out.println(bauer);
	        }

	        context.close();
	    }

}
