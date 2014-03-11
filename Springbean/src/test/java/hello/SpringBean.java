package hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
@Component
public class SpringBean {
    private final static Log log = LogFactory.getLog(SpringBean.class);
    private long createDate;

    public SpringBean() {
        log.info("构造SpringBean");
        createDate = System.nanoTime();
    }

    public long getCreateDate() {
        return createDate;
    }
}
