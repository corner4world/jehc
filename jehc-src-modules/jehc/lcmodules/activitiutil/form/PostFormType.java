package jehc.lcmodules.activitiutil.form;

import java.util.Arrays;

import org.activiti.engine.form.AbstractFormType;
import org.apache.commons.lang3.StringUtils;
/**
 * 自定义表单岗位类型
 * @author Administrator
 *
 */
public class PostFormType extends AbstractFormType{
	 /**
     * 把字符串的值转换为集合对象
     * @param propertyValue
     * @return
     */
    public Object convertFormValueToModelValue(String propertyValue) {
        String[] split = StringUtils.split(propertyValue, ",");
        return Arrays.asList(split);
    }

    /**
     * 把集合对象的值转换为字符串
     * @param modelValue
     * @return
     */
    public String convertModelValueToFormValue(Object modelValue) {
        if(modelValue==null){
            return null;
        }
        return modelValue.toString();
    }

  /**
     * 定义表单类型的标识符
     * @return
     */
    public String getName() {
        return "post";
    }
}
