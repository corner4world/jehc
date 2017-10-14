package jehc.xtmodules.xtcore.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author 邓纯杰
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Target(value = {java.lang.annotation.ElementType.METHOD})
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface AuthNeedLogin {
	String desc = "需要登录认证";
}
