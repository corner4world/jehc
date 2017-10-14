package jehc.xtmodules.xtweb;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.allutils.JdomXmlUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.constant.PathConstant;

/**
 * Logback日志
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/xtLogbackController")
public class XtLogbackController extends BaseAction{
	
	/**
	* 载入初始化页面
	* @param request 
	* @return
	 * @throws IOException 
	*/
	@RequestMapping(value="/loadXtLogback",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtLogback(HttpServletRequest request,Model model) throws IOException{
		return new ModelAndView("pc/xt-view/xt-logback/xt-logback-list");
	}
	
	/**
	* 载入编辑页面
	* @param request 
	* @return
	 * @throws IOException 
	*/
	@RequestMapping(value="/loadXtLogbackEditor",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtLogbackEditor(HttpServletRequest request,Model model) throws IOException{
		String path = request.getSession().getServletContext().getRealPath("/")+PathConstant.LOGBACK_PATH;
		JdomXmlUtil jdomXmlUtil = new JdomXmlUtil(path);
		String xtLogbackContent = jdomXmlUtil.XmlToString();
		model.addAttribute("xtLogbackContent", xtLogbackContent);
		return new ModelAndView("pc/xt-view/xt-logback/xt-logback-editor");
	} 
}
