package cn.catgod.log4j;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/12
 */
public class Log4jDemo {

    private final static Logger logger = LogManager.getLogger(Log4jDemo.class);

    public static void main(String[] args) {
        logger.trace("Entering application.");
        if (!false) {
            logger.error("Didn't do it.");
        }
        logger.trace("Exiting application.");
    }
}
