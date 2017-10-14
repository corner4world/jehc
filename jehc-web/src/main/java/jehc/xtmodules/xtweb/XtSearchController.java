package jehc.xtmodules.xtweb;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;

/**
 * 平台关键字搜索
 * @author dengcj
 *
 */
@Controller
@RequestMapping("/xtSearchController")
public class XtSearchController extends BaseAction {
	/**
	* 载入初始化页面
	* @param xt_knowledge 
	* @param request 
	* @return
	 * @throws UnsupportedEncodingException 
	*/
	@RequestMapping(value="/loadXtSearch",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtSearch(String keywords,HttpServletRequest request) throws UnsupportedEncodingException{
		if(!StringUtils.isEmpty(keywords)){
			keywords = new String(keywords.getBytes("ISO-8859-1"), "UTF-8");
		}
		request.setAttribute("keywords", keywords);
		return new ModelAndView("pc/xt-view/xt-search/xt-knowledge-list-search");
	}
	
	/**
	* 载入详情页面
	* @param request 
	* @return
	 * @throws UnsupportedEncodingException 
	*/
	@RequestMapping(value="/loadXtSearchForm",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtSearchForm(String searchid,HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		request.setAttribute("searchid", searchid);
		return new ModelAndView("pc/xt-view/xt-search/xt-knowledge-form-search");
	} 
}
