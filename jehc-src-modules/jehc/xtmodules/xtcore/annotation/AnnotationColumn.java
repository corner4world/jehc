package jehc.xtmodules.xtcore.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 字段注解
 * @author 邓纯杰
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target( { java.lang.annotation.ElementType.FIELD })  
public @interface AnnotationColumn {  
    String Field();  
} 
