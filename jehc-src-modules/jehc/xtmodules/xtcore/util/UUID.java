package jehc.xtmodules.xtcore.util;


/**
 * UUID生成策略
 * @author邓纯杰
 *
 */
public class UUID{
	
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
