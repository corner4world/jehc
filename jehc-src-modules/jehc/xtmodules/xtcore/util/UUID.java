package jehc.xtmodules.xtcore.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UUID生成策略
 * @author邓纯杰
 *
 */
public class UUID{
	Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 生成UUID
	 * @return
	 */
	public static String toUUID(){
		try {
			return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
		} catch (Exception e) {
			throw new ExceptionUtil("生成UUID失败-------错误原因："+e.getMessage());
		}
	}
}
