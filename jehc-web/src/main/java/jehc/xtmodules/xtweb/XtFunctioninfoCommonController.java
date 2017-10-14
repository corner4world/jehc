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
import jehc.xtmodules.xtmodel.XtFunctioninfoCommon;
import jehc.xtmodules.xtservice.XtFunctioninfoCommonService;

/**
* 公共功能 
* 2016-10-10 13:00:30  邓纯杰
*/
@Controller
@RequestMapping("/xtFunctioninfoCommonController")
public class XtFunctioninfoCommonController extends BaseAction{
	@Autowired
	private XtFunctioninfoCommonService xtFunctioninfoCommonService;
	/**
	* 载入初始化页面
	* @param xt_functioninfo_common 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtFunctioninfoCommon",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtFunctioninfoCommon(XtFunctioninfoCommon xt_Functioninfo_Common,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-functioninfo-common/xt-functioninfo-common-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_functioninfo_common 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtFunctioninfoCommonListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFunctioninfoCommonListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtFunctioninfoCommon> xt_Functioninfo_CommonList = xtFunctioninfoCommonService.getXtFunctioninfoCommonListByCondition(condition);
		PageInfo<XtFunctioninfoCommon> page = new PageInfo<XtFunctioninfoCommon>(xt_Functioninfo_CommonList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_functioninfo_common_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtFunctioninfoCommonById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFunctioninfoCommonById(String xt_functioninfo_common_id,HttpServletRequest request){
		XtFunctioninfoCommon xt_Functioninfo_Common = xtFunctioninfoCommonService.getXtFunctioninfoCommonById(xt_functioninfo_common_id);
		return outDataStr(xt_Functioninfo_Common);
	}
	/**
	* 添加
	* @param xt_functioninfo_common 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtFunctioninfoCommon",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtFunctioninfoCommon(XtFunctioninfoCommon xt_Functioninfo_Common,HttpServletRequest request){
		int i = 0;
		if(null != xt_Functioninfo_Common && !"".equals(xt_Functioninfo_Common)){
			xt_Functioninfo_Common.setXt_functioninfo_common_id(UUID.toUUID());
			xt_Functioninfo_Common.setXt_functioninfo_common_ctime(getSimpleDateFormat());
			xt_Functioninfo_Common.setXt_userinfo_id(getXtUid());
			i=xtFunctioninfoCommonService.addXtFunctioninfoCommon(xt_Functioninfo_Common);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_functioninfo_common 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtFunctioninfoCommon",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtFunctioninfoCommon(XtFunctioninfoCommon xt_Functioninfo_Common,HttpServletRequest request){
		int i = 0;
		if(null != xt_Functioninfo_Common && !"".equals(xt_Functioninfo_Common)){
			xt_Functioninfo_Common.setXt_functioninfo_common_mtime(getSimpleDateFormat());
			xt_Functioninfo_Common.setXt_userinfo_id(getXtUid());
			i=xtFunctioninfoCommonService.updateXtFunctioninfoCommon(xt_Functioninfo_Common);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_functioninfo_common_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtFunctioninfoCommon",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtFunctioninfoCommon(String xt_functioninfo_common_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_functioninfo_common_id && !"".equals(xt_functioninfo_common_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_functioninfo_common_id",xt_functioninfo_common_id.split(","));
			i=xtFunctioninfoCommonService.delXtFunctioninfoCommon(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_functioninfo_common_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtFunctioninfoCommon",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtFunctioninfoCommon(String xt_functioninfo_common_id,HttpServletRequest request){
		int i = 0;
		XtFunctioninfoCommon xt_Functioninfo_Common = xtFunctioninfoCommonService.getXtFunctioninfoCommonById(xt_functioninfo_common_id);
		if(null != xt_Functioninfo_Common && !"".equals(xt_Functioninfo_Common)){
			xt_Functioninfo_Common.setXt_functioninfo_common_id(UUID.toUUID());
			i=xtFunctioninfoCommonService.addXtFunctioninfoCommon(xt_Functioninfo_Common);
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
	@RequestMapping(value="/exportXtFunctioninfoCommon",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtFunctioninfoCommon(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
