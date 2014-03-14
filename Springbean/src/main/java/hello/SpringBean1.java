package hello;

import static org.junit.Assert.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringBean1 {
	private final static Log log = LogFactory.getLog(SpringBean1.class);
    private SpringBean bean;

//    public SpringBean1() {
//	}

    @Autowired
    public SpringBean1(SpringBean bean) {
        log.info("通过有参构造SpringBean1");
        assertNotNull(bean);
        this.bean = bean;
    }
    public SpringBean getBean() {
        return bean;
    }
}