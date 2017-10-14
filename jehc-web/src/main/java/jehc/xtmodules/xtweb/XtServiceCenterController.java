package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtServiceCenter;
import jehc.xtmodules.xtmodel.XtServiceCenterParameter;
import jehc.xtmodules.xtservice.XtServiceCenterService;
import jehc.xtmodules.xtservice.XtServiceCenterParameterService;

/**
* 服务中心 
* 2017-03-27 12:32:04  邓纯杰
*/
@Controller
@RequestMapping("/xtServiceCenterController")
public class XtServiceCenterController extends BaseAction{
	@Autowired
	private XtServiceCenterService xtServiceCenterService;
	@Autowired
	private XtServiceCenterParameterService xtServiceCenterParameterService;
	/**
	* 载入初始化页面
	* @param xt_service_center 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtServiceCenter",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtServiceCenter(XtServiceCenter xt_Service_Center,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-service-center/xt-service-center-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_service_center 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtServiceCenterListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtServiceCenterListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtServiceCenter> xt_Service_CenterList = xtServiceCenterService.getXtServiceCenterListByCondition(condition);
		PageInfo<XtServiceCenter> page = new PageInfo<XtServiceCenter>(xt_Service_CenterList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_service_center_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtServiceCenterById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtServiceCenterById(String xt_service_center_id,HttpServletRequest request){
		XtServiceCenter xt_Service_Center = xtServiceCenterService.getXtServiceCenterById(xt_service_center_id);
		return outDataStr(xt_Service_Center);
	}
	/**
	* 添加
	* @param xt_service_center 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtServiceCenter",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtServiceCenter(XtServiceCenter xt_Service_Center,HttpServletRequest request){
		int i = 0;
		if(null != xt_Service_Center && !"".equals(xt_Service_Center)){
			xt_Service_Center.setXt_service_center_id(UUID.toUUID());
			i=xtServiceCenterService.addXtServiceCenter(xt_Service_Center);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_service_center 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtServiceCenter",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtServiceCenter(XtServiceCenter xt_Service_Center,HttpServletRequest request){
		int i = 0;
		if(null != xt_Service_Center && !"".equals(xt_Service_Center)){
			i=xtServiceCenterService.updateXtServiceCenter(xt_Service_Center);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_service_center_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtServiceCenter",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtServiceCenter(String xt_service_center_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_service_center_id && !"".equals(xt_service_center_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_service_center_id",xt_service_center_id.split(","));
			i=xtServiceCenterService.delXtServiceCenter(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_service_center_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtServiceCenter",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtServiceCenter(String xt_service_center_id,HttpServletRequest request){
		int i = 0;
		XtServiceCenter xt_Service_Center = xtServiceCenterService.getXtServiceCenterById(xt_service_center_id);
		if(null != xt_Service_Center && !"".equals(xt_Service_Center)){
			xt_Service_Center.setXt_service_center_id(UUID.toUUID());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_service_center_id", xt_service_center_id);
			List<XtServiceCenterParameter> xt_Service_Center_ParameterList = xtServiceCenterParameterService.getXtServiceCenterParameterListByCondition(condition);
			xt_Service_Center.setXt_Service_Center_Parameter(xt_Service_Center_ParameterList);
			i=xtServiceCenterService.addXtServiceCenter(xt_Service_Center);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportXtServiceCenter",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtServiceCenter(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
