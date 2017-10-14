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
import jehc.xtmodules.xtmodel.XtFunctioninfoRight;
import jehc.xtmodules.xtservice.XtFunctioninfoRightService;

/**
* 功能分配表 
* 2016-10-08 17:38:19  邓纯杰
*/
@Controller
@RequestMapping("/xtFunctioninfoRightController")
public class XtFunctioninfoRightController extends BaseAction{
	@Autowired
	private XtFunctioninfoRightService xtFunctioninfoRightService;
	/**
	* 载入初始化页面
	* @param xt_functioninfo_right 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtFunctioninfoRight",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-functioninfo-right/xt-functioninfo-right-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_functioninfo_right 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtFunctioninfoRightListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFunctioninfoRightListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtFunctioninfoRight> xt_Functioninfo_RightList = xtFunctioninfoRightService.getXtFunctioninfoRightListByCondition(condition);
		PageInfo<XtFunctioninfoRight> page = new PageInfo<XtFunctioninfoRight>(xt_Functioninfo_RightList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_functioninfo_right_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtFunctioninfoRightById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFunctioninfoRightById(String xt_functioninfo_right_id,HttpServletRequest request){
		XtFunctioninfoRight xt_Functioninfo_Right = xtFunctioninfoRightService.getXtFunctioninfoRightById(xt_functioninfo_right_id);
		return outDataStr(xt_Functioninfo_Right);
	}
	/**
	* 添加
	* @param xt_functioninfo_right 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtFunctioninfoRight",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right,HttpServletRequest request){
		int i = 0;
		if(null != xt_Functioninfo_Right && !"".equals(xt_Functioninfo_Right)){
			xt_Functioninfo_Right.setXt_functioninfo_right_id(UUID.toUUID());
			i=xtFunctioninfoRightService.addXtFunctioninfoRight(xt_Functioninfo_Right);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_functioninfo_right 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtFunctioninfoRight",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right,HttpServletRequest request){
		int i = 0;
		if(null != xt_Functioninfo_Right && !"".equals(xt_Functioninfo_Right)){
			i=xtFunctioninfoRightService.updateXtFunctioninfoRight(xt_Functioninfo_Right);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_functioninfo_right_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtFunctioninfoRight",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtFunctioninfoRight(String xt_functioninfo_right_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_functioninfo_right_id && !"".equals(xt_functioninfo_right_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_functioninfo_right_id",xt_functioninfo_right_id.split(","));
			i=xtFunctioninfoRightService.delXtFunctioninfoRight(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_functioninfo_right_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtFunctioninfoRight",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtFunctioninfoRight(String xt_functioninfo_right_id,HttpServletRequest request){
		int i = 0;
		XtFunctioninfoRight xt_Functioninfo_Right = xtFunctioninfoRightService.getXtFunctioninfoRightById(xt_functioninfo_right_id);
		if(null != xt_Functioninfo_Right && !"".equals(xt_Functioninfo_Right)){
			xt_Functioninfo_Right.setXt_functioninfo_right_id(UUID.toUUID());
			i=xtFunctioninfoRightService.addXtFunctioninfoRight(xt_Functioninfo_Right);
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
	@RequestMapping(value="/exportXtFunctioninfoRight",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtFunctioninfoRight(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
