package jehc.xtmodules.xtcore.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback工具类
 * @author 邓纯杰
 *
 */
public class Logback4jUtil extends CommonUtils{
	Logger log = LoggerFactory.getLogger(this.getClass());
	public Logback4jUtil() {
	}

	public void debug(String className, String msg) {
		log.debug(className + " - " + msg);
	}

	public void info(String className, String msg) {
		log.info(className + " - " + msg);
	}

	public void warn(String className, String msg) {
		log.warn(className + " - " + msg);
	}

	public void error(String className, String msg) {
		log.error(className + " - " + msg);
	}
}
