package jehc.xtmodules.xtcore.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * 需要登录但无需拦截
 * @author 邓纯杰
 *
 */
@Target(value = {java.lang.annotation.ElementType.METHOD})
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface NeedLoginUnAuth {
	String desc = "需要登录无需拦截";
}