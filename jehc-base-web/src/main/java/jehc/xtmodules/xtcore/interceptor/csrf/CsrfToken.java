package jehc.xtmodules.xtcore.interceptor.csrf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义csrfToke注解
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsrfToken {
    boolean create() default false;
    boolean remove() default false;
}
