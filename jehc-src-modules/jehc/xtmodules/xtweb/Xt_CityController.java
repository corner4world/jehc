package jehc.xtmodules.xtweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.Xt_City;
import jehc.xtmodules.xtservice.Xt_CityService;

/**
* 共用城市表; InnoDB free: 7168 kB 
* 2015-07-27 19:57:27  邓纯杰
*/
@Controller
@RequestMapping("/xtCityController")
public class Xt_CityController extends BaseAction{
	@Autowired
	private Xt_CityService xt_CityService;
	/**
	* 载入初始化页面
	* @param xt_city 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtCity",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtCity(Xt_City xt_City,HttpServletRequest request,Model model){
		String xt_provinceID = request.getParameter("xt_provinceID");
		model.addAttribute("xt_provinceID", xt_provinceID);
		return new ModelAndView("pc/xt-view/xt-city/xt-city-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_city 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtCityListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtCityListByCondition(Xt_City xt_City,HttpServletRequest request){
		String xt_provinceID = request.getParameter("xt_provinceID");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(null == xt_provinceID){
			xt_provinceID = "0";
		}
		condition.put("xt_provinceID", xt_provinceID);
		commonHPager(condition,request);
		List<Xt_City> XtCityList = xt_CityService.getXtCityListByCondition(condition);
		PageInfo<Xt_City> page = new PageInfo<Xt_City>(XtCityList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_cityID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtCityById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtCityById(String xt_cityID,HttpServletRequest request){
		Xt_City xt_City = xt_CityService.getXtCityById(xt_cityID);
		return outDataStr(xt_City);
	}
	/**
	* 添加
	* @param xt_city 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtCity",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtCity(Xt_City xt_City,HttpServletRequest request){
		int i = 0;
		if(null != xt_City && !"".equals(xt_City)){
			xt_City.setXt_cityID(UUID.toUUID());
			i=xt_CityService.addXtCity(xt_City);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_city 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtCity",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtCity(Xt_City xt_City,HttpServletRequest request){
		int i = 0;
		if(null != xt_City && !"".equals(xt_City)){
			i=xt_CityService.updateXtCity(xt_City);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_cityID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtCity",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtCity(String xt_cityID,HttpServletRequest request){
		int i = 0;
		if(null != xt_cityID && !"".equals(xt_cityID)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_cityID",xt_cityID.split(","));
			i=xt_CityService.delXtCity(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}
