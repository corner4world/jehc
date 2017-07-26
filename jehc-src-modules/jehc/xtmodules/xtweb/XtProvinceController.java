package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
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
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.XtProvince;
import jehc.xtmodules.xtservice.XtProvinceService;

/**
* 共用省份表; InnoDB free: 7168 kB 
* 2015-07-27 19:27:08  邓纯杰
*/
@Controller
@RequestMapping("/xtProvinceController")
public class XtProvinceController extends BaseAction{
	@Autowired
	private XtProvinceService xtProvinceService;
	/**
	* 载入初始化页面
	* @param xt_province 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtProvince",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtProvince(XtProvince xt_Province,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-province/xt-province-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_province 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtProvinceListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtProvinceListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtProvince>XtProvinceList = xtProvinceService.getXtProvinceListByCondition(condition);
		PageInfo<XtProvince> page = new PageInfo<XtProvince>(XtProvinceList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_provinceID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtProvinceById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtProvinceById(String xt_provinceID,HttpServletRequest request){
		XtProvince xt_Province = xtProvinceService.getXtProvinceById(xt_provinceID);
		return outDataStr(xt_Province);
	}
	/**
	* 添加
	* @param xt_province 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtProvince",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtProvince(XtProvince xt_Province,HttpServletRequest request){
		int i = 0;
		if(null != xt_Province && !"".equals(xt_Province)){
			xt_Province.setXt_provinceID(UUID.toUUID());
			i=xtProvinceService.addXtProvince(xt_Province);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_province 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtProvince",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtProvince(XtProvince xt_Province,HttpServletRequest request){
		int i = 0;
		if(null != xt_Province && !"".equals(xt_Province)){
			i=xtProvinceService.updateXtProvince(xt_Province);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_provinceID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtProvince",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtProvince(String xt_provinceID,HttpServletRequest request){
		int i = 0;
		if(null != xt_provinceID && !"".equals(xt_provinceID)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_provinceID",xt_provinceID.split(","));
			i=xtProvinceService.delXtProvince(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}
