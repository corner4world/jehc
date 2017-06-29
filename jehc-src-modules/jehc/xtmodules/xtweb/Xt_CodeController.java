package jehc.xtmodules.xtweb;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;

/**
 * code
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtCodeController")
public class Xt_CodeController extends BaseAction{
	/**
	* 载入页面
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtCode",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtCode(HttpServletRequest request,Model model){
		return new ModelAndView("pc/xt-view/xt-code/xt-code-list");
	} 
	
	/**
	* 载入Js编辑器页面
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtCodeJsEditor",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtCodeJsEditor(HttpServletRequest request,Model model){
		return new ModelAndView("pc/xt-view/xt-code/xt-codeJsEditor");
	} 
	
	/**
	* 运行
	* @param request 
	* @return
	 * @throws UnsupportedEncodingException 
	*/
	@RequestMapping(value="/jsRun",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView jsRun(String jsRunContent,Model model) throws UnsupportedEncodingException{
		model.addAttribute("jsRunContent", jsRunContent);
		return new ModelAndView("pc/xt-view/xt-code/xt-code-run");
	} 
}
