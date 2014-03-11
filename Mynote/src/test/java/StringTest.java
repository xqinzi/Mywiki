import static org.junit.Assert.*;

import org.junit.Test;


public class StringTest {

	@Test
	public void test() {
		String aa="123456789abcdefg";
		System.out.println(aa.substring(0, aa.indexOf("#")));
		System.out.println(aa.substring(aa.indexOf("#")+1));
	}

}
