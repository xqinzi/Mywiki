package test;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;  
import org.springframework.security.crypto.password.StandardPasswordEncoder; 
/**
 * 在Spring-Security 3.1.0 版本之后，Spring-security-crypto模块中的password包提供了更给力的加密密码的支持，
 * 这个包中也有PasswordEncoder接口。 定义了两个方法，encode方法是对方法加密，而match方法是用来验证密码和加密后密码是否一致的，
 * 如果一致则返回true。和authentication.encoding包中的PasswordEncoder接口相比，简化了许多。
 * 位于org.springframeword.security.crypto.password包中的StandardPasswordEncoder类，是PasswordEncoder接口的(唯一)一个实现类。
 * 它采用SHA-256算法，迭代1024次，使用一个密钥(site-wide secret)以及8位随机盐对原密码进行加密。 随机盐确保相同的密码使用多次时，
 * 产生的哈希都不同； 密钥应该与密码区别开来存放，加密时使用一个密钥即可；对hash算法迭代执行1024次增强了安全性，使暴力破解变得更困难些。 
 * 和上一个版本的PasswordEncoder比较，好处显而易见：盐值不用用户提供，每次随机生成；多重加密————迭代SHA算法+密钥+随机盐来对密码加密，大大增加密码破解难度。
 *
 */
public class Password {
	private static final String SITE_WIDE_SECRET = "my-secret-key";  
	private static final PasswordEncoder encoder = new StandardPasswordEncoder(SITE_WIDE_SECRET);  
   
    public static String encrypt(String rawPassword) {  
         return encoder.encode(rawPassword);  
    }  
   
    public static boolean match(String rawPassword, String password) {  
         return encoder.matches(rawPassword, password);  
    } 
	
    @Test
	public void test01(){
    	String ps1=Password.encrypt("每次结果都不一样伐?");
    	String ps2=Password.encrypt("每次结果都不一样伐?");
    	//但是把每次结果拿出来进行match，你会发现可以得到true。
    	System.out.println(ps1);  
        System.out.println(ps1);

        System.out.println(Password.match("每次结果都不一样伐?",ps1));  
        System.out.println(Password.match("每次结果都不一样伐?",ps2));  
		
	}

}
