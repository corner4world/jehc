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
import jehc.xtmodules.xtmodel.XtServiceCenterParameter;
import jehc.xtmodules.xtservice.XtServiceCenterParameterService;

/**
* 服务中心参数 
* 2017-03-27 12:32:04  邓纯杰
*/
@Controller
@RequestMapping("/xtServiceCenterParameterController")
public class XtServiceCenterParameterController extends BaseAction{
	@Autowired
	private XtServiceCenterParameterService xtServiceCenterParameterService;
	/**
	* 载入初始化页面
	* @param xt_service_center_parameter 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtServiceCenterParameter",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtServiceCenterParameter(XtServiceCenterParameter xt_Service_Center_Parameter,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-service-center-parameter/xt-service-center-parameter-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_service_center_parameter 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtServiceCenterParameterListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtServiceCenterParameterListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		condition.put("xt_service_center_status",request.getParameter("xt_service_center_status"));
		condition.put("xt_service_center_type",request.getParameter("xt_service_center_type"));
		condition.put("xt_service_center_name",request.getParameter("xt_service_center_name"));
		condition.put("xt_service_center_id",request.getParameter("xt_service_center_id"));
		List<XtServiceCenterParameter> xt_Service_Center_ParameterList = xtServiceCenterParameterService.getXtServiceCenterParameterListByCondition(condition);
		PageInfo<XtServiceCenterParameter> page = new PageInfo<XtServiceCenterParameter>(xt_Service_Center_ParameterList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_service_center_parameter_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtServiceCenterParameterById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtServiceCenterParameterById(String xt_service_center_parameter_id,HttpServletRequest request){
		XtServiceCenterParameter xt_Service_Center_Parameter = xtServiceCenterParameterService.getXtServiceCenterParameterById(xt_service_center_parameter_id);
		return outDataStr(xt_Service_Center_Parameter);
	}
	/**
	* 添加
	* @param xt_service_center_parameter 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtServiceCenterParameter",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtServiceCenterParameter(XtServiceCenterParameter xt_Service_Center_Parameter,HttpServletRequest request){
		int i = 0;
		if(null != xt_Service_Center_Parameter && !"".equals(xt_Service_Center_Parameter)){
			xt_Service_Center_Parameter.setXt_service_center_parameter_id(UUID.toUUID());
			i=xtServiceCenterParameterService.addXtServiceCenterParameter(xt_Service_Center_Parameter);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_service_center_parameter 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtServiceCenterParameter",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtServiceCenterParameter(XtServiceCenterParameter xt_Service_Center_Parameter,HttpServletRequest request){
		int i = 0;
		if(null != xt_Service_Center_Parameter && !"".equals(xt_Service_Center_Parameter)){
			i=xtServiceCenterParameterService.updateXtServiceCenterParameter(xt_Service_Center_Parameter);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_service_center_parameter_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtServiceCenterParameter",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtServiceCenterParameter(String xt_service_center_parameter_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_service_center_parameter_id && !"".equals(xt_service_center_parameter_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_service_center_parameter_id",xt_service_center_parameter_id.split(","));
			i=xtServiceCenterParameterService.delXtServiceCenterParameter(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_service_center_parameter_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtServiceCenterParameter",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtServiceCenterParameter(String xt_service_center_parameter_id,HttpServletRequest request){
		int i = 0;
		XtServiceCenterParameter xt_Service_Center_Parameter = xtServiceCenterParameterService.getXtServiceCenterParameterById(xt_service_center_parameter_id);
		if(null != xt_Service_Center_Parameter && !"".equals(xt_Service_Center_Parameter)){
			xt_Service_Center_Parameter.setXt_service_center_parameter_id(UUID.toUUID());
			i=xtServiceCenterParameterService.addXtServiceCenterParameter(xt_Service_Center_Parameter);
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
	@RequestMapping(value="/exportXtServiceCenterParameter",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtServiceCenterParameter(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
