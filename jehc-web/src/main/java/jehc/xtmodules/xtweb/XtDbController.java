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

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtmodel.XtDb;
import jehc.xtmodules.xtmodel.XtDbinfo;
import jehc.xtmodules.xtservice.XtDbService;
/**
 * 数据库备份
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtDbController")
public class XtDbController extends BaseAction {
	@Autowired
	private XtDbService xtDbService;
	
	/**
	* 载入初始化页面
	* @param xt_dbinfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDb",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDbinfo(XtDbinfo xt_Dbinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-db/xt-db-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_dbinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDbListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonPager(condition,request);
		List<XtDb> xt_DbinfoList = xtDbService.getXtDbListByCondition(condition);
		PageInfo<XtDb> page = new PageInfo<XtDb>(xt_DbinfoList);
		return outPageBootStr(page,request);
	}
}
