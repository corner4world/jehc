package jehc.formmodules.formweb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 表单自定义模块
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/formController")
public class FormController {
	/**
	* 载入初始化页面
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadFormDesign",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadFormDesign(HttpServletRequest request){
		return new ModelAndView("pc/form-view/form-design/form-design");
	}
}
