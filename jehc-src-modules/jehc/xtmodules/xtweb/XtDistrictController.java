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
import jehc.xtmodules.xtmodel.XtDistrict;
import jehc.xtmodules.xtservice.XtDistrictService;

/**
* 共用地区表; InnoDB free: 6144 kB 
* 2015-07-27 20:08:27  邓纯杰
*/
@Controller
@RequestMapping("/xtDistrictController")
public class XtDistrictController extends BaseAction{
	@Autowired
	private XtDistrictService xtDistrictService;
	/**
	* 载入初始化页面
	* @param xt_district 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDistrict",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDistrict(XtDistrict xt_District,HttpServletRequest request,Model model){
		String xt_cityID = request.getParameter("xt_cityID");
		String xt_provinceID = request.getParameter("xt_provinceID");
		model.addAttribute("xt_provinceID", xt_provinceID);
		model.addAttribute("xt_cityID", xt_cityID);
		return new ModelAndView("pc/xt-view/xt-district/xt-district-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_district 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDistrictListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDistrictListByCondition(XtDistrict xt_District,HttpServletRequest request){
		String xt_cityID = request.getParameter("xt_cityID");
		if(null == xt_cityID){
			xt_cityID = "0";
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("xt_cityID", xt_cityID);
		List<XtDistrict>XtDistrictList = xtDistrictService.getXtDistrictListByCondition(condition);
		PageInfo<XtDistrict> page = new PageInfo<XtDistrict>(XtDistrictList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_districtID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDistrictById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDistrictById(String xt_districtID,HttpServletRequest request){
		XtDistrict xt_District = xtDistrictService.getXtDistrictById(xt_districtID);
		return outDataStr(xt_District);
	}
	/**
	* 添加
	* @param xt_district 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtDistrict",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDistrict(XtDistrict xt_District,HttpServletRequest request){
		int i = 0;
		if(null != xt_District && !"".equals(xt_District)){
			xt_District.setXt_districtID(UUID.toUUID());
			i=xtDistrictService.addXtDistrict(xt_District);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_district 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtDistrict",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtDistrict(XtDistrict xt_District,HttpServletRequest request){
		int i = 0;
		if(null != xt_District && !"".equals(xt_District)){
			i=xtDistrictService.updateXtDistrict(xt_District);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_districtID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDistrict",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDistrict(String xt_districtID,HttpServletRequest request){
		int i = 0;
		if(null != xt_districtID && !"".equals(xt_districtID)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_districtID",xt_districtID.split(","));
			i=xtDistrictService.delXtDistrict(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}
