package jehc.lcmodules.lcweb;

import java.util.List;
import java.util.Map;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

import jehc.lcmodules.activitiutil.ActivitiUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
/**
 * 流程动态表单
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/lcDynamicFormController")
public class LcDynamicFormController extends BaseAction{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ActivitiUtil activitiUtil;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/loadLcStartForm",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView startForm(String lc_deployment_his_id, Model model) {
		ProcessDefinition processDefinition = activitiUtil.getProcessDefinition(lc_deployment_his_id);
	    Map<String, Map<String, String>> result = Maps.newHashMap();
	    Map<String,String> datePatterns = Maps.newHashMap();
	    StartFormData startFormData = activitiUtil.getStartFormData(processDefinition.getId());
	    List<FormProperty> formProperties = startFormData.getFormProperties();
	    for (FormProperty formProperty : formProperties) {
	    	String typeName = formProperty.getType().getName();
	        if("enum".equals(typeName)){
	            Map<String, String> values;
	            values = (Map<String, String>) formProperty.getType().getInformation("values");
	            if (values != null) {
	                for (Map.Entry<String, String> enumEntry : values.entrySet())
	                    logger.debug("enum, key: {}, value: {}", enumEntry.getKey(), enumEntry.getValue());
	                	result.put("enum_" + formProperty.getId(), values);
	            	}
	        }else if("date".equals(typeName)){
	        	//获取日期格式(String)formProperty.getType().getInformation("datePattern")
	            datePatterns.put("pattern_"+formProperty.getId(), (String)formProperty.getType().getInformation("datePattern"));
	            logger.debug("date,key:{},pattern:{}",formProperty.getId(),formProperty.getType().getInformation("datePattern"));
	        }else if("long".equals(typeName)){
	        	
	        }else if("boolean".equals(typeName)){
	        	
	        }else if("collection".equals(typeName)){
	        	
	        }else{
	        	//string
	        }
	    }
	    model.addAttribute("datePatterns",datePatterns);
	    model.addAttribute("result", result);
	    model.addAttribute("list", formProperties);
	    model.addAttribute("formData", startFormData);
	    return new ModelAndView("pc/lc-view/lc-dynamic-start-form/lc-dynamic-start-form-list");
	}
	
	
}
