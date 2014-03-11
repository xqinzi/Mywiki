package hello;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * CustomerRepository继承了JpaRepository，除了能使用基本的增删改查外，还可以自己定义查找方法，
 * 如：findByLastName，关键在于findBy后面的属性要与Customer中的属性对应，
 * 在eclipse中编写的时候如果没有对应的属性eclipse也会提示你属性找不到。
 * @author mywiki95@gmail.com
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

}