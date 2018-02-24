package jehc.xtmodules.xtweb;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.annotation.NeedLoginUnAuth;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtmodel.XtNumber;
import jehc.xtmodules.xtservice.XtNumberService;


/**
 * 单号生成
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtNumberController")
public class XtNumberController extends BaseAction {
	@Autowired
	private XtNumberService xtNumberService;
	
	/**
	* 载入初始化页面
	* @param request 
	* @return
	*/
	@NeedLoginUnAuth
	@RequestMapping(value="/loadXtNumber",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtNumber(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-number/xt-number-list");
	}
	
	/**
	* 加载初始化列表数据并分页
	* @param request 
	*/
	@NeedLoginUnAuth
	@ResponseBody
	@RequestMapping(value="/getXtNumberListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtNumberListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtNumber> xtNumberList = xtNumberService.getXtNumberListByCondition(condition);
		PageInfo<XtNumber> page = new PageInfo<XtNumber>(xtNumberList);
		return outPageBootStr(page,request);
	}
}
