package jehc.xtmodules.xtweb;
import java.util.HashMap;
import java.util.List;
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
import jehc.xtmodules.xtmodel.XtPlatform;
import jehc.xtmodules.xtservice.XtPlatformService;

/**
* 平台信息发布 
* 2016-08-30 22:18:44  邓纯杰
*/
@Controller
@RequestMapping("/xtPlatformController")
public class XtPlatformController extends BaseAction{
	@Autowired
	private XtPlatformService xtPlatformService;
	/**
	* 载入初始化页面
	* @param xt_platform 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtPlatform(XtPlatform xt_Platform,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-platform/xt-platform-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_platform 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPlatformListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPlatformListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		condition.put("xt_platform_status",request.getParameter("xt_platform_status"));
		condition.put("xt_platform_title",request.getParameter("xt_platform_title"));
		List<XtPlatform> xt_PlatformList = xtPlatformService.getXtPlatformListByCondition(condition);
		PageInfo<XtPlatform> page = new PageInfo<XtPlatform>(xt_PlatformList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_platform_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPlatformById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPlatformById(String xt_platform_id,HttpServletRequest request){
		XtPlatform xt_Platform = xtPlatformService.getXtPlatformById(xt_platform_id);
		return outDataStr(xt_Platform);
	}
	/**
	* 添加
	* @param xt_platform 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtPlatform(XtPlatform xt_Platform,HttpServletRequest request){
		int i = 0;
		if(null != xt_Platform && !"".equals(xt_Platform)){
			xt_Platform.setXt_platform_id(UUID.toUUID());
			xt_Platform.setXt_platform_ctime(getSimpleDateFormat());
			xt_Platform.setXt_userinfo_id(getXtUid());
			i=xtPlatformService.addXtPlatform(xt_Platform);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_platform 
	* @param request 
	*/
	@RequestMapping(value="/updateXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String updateXtPlatform(XtPlatform xt_Platform,HttpServletRequest request){
		int i = 0;
		if(null != xt_Platform && !"".equals(xt_Platform)){
			i=xtPlatformService.updateXtPlatform(xt_Platform);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_platform_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtPlatform(String xt_platform_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_platform_id && !"".equals(xt_platform_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_platform_id",xt_platform_id.split(","));
			i=xtPlatformService.delXtPlatform(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_platform_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtPlatform(String xt_platform_id,HttpServletRequest request){
		int i = 0;
		XtPlatform xt_Platform = xtPlatformService.getXtPlatformById(xt_platform_id);
		if(null != xt_Platform && !"".equals(xt_Platform)){
			xt_Platform.setXt_platform_id(UUID.toUUID());
			i=xtPlatformService.addXtPlatform(xt_Platform);
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
	@RequestMapping(value="/exportXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtPlatform(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
