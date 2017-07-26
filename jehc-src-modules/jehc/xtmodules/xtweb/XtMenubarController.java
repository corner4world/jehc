package jehc.xtmodules.xtweb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
/**
 * 个人常用菜单即便捷菜单
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtMenubarController")
public class XtMenubarController extends BaseAction {
	/**
	* 载入初始化页面
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtMenubarView",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMenubarView(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-menubar/xt-menubar-view");
	}
}
