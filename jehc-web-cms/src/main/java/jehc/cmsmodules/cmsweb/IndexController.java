package jehc.cmsmodules.cmsweb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;
@Controller
@RequestMapping("/")
public class IndexController extends BaseAction{
	/**
	* 载入初始化页面
	* @param cms_case 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/index",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsCase(HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/index");
		}else{
			return new ModelAndView("pc/index");
		}
	}
}
