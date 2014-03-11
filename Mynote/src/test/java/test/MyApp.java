package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApp {
    final static Logger logger = LoggerFactory.getLogger("MyApp.class");
    public static void main(String[] args) {
        
        logger.trace("trace");
        logger.debug("debug str");
        logger.info("info str");
        logger.warn("warn");
        logger.error("error");
    	
    	logger.debug("message",new Object[]{"arg0","arg1","arg2","arg3"});
    }
}
