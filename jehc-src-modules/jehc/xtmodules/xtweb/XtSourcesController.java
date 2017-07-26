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
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtSources;
import jehc.xtmodules.xtservice.XtSourcesService;

/**
* 平台静态资源 
* 2016-06-16 10:34:06  邓纯杰
*/
@Controller
@RequestMapping("/xtSourcesController")
public class XtSourcesController extends BaseAction{
	@Autowired
	private XtSourcesService xtSourcesService;
	/**
	* 载入初始化页面
	* @param xt_sources 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtSources",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtSources(XtSources xt_Sources,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-sources/xt-sources-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_sources 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtSourcesListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtSourcesListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		if(null != request.getParameter("xt_sources_status") && !"".equals(request.getParameter("xt_sources_status"))){
			condition.put("xt_sources_status",request.getParameter("xt_sources_status"));
		}
		if(null != request.getParameter("xt_sources_type") && !"".equals(request.getParameter("xt_sources_type"))){
			condition.put("xt_sources_type",request.getParameter("xt_sources_type"));
		}
		if(null != request.getParameter("xt_sources_title") && !"".equals(request.getParameter("xt_sources_title"))){
			condition.put("xt_sources_title",request.getParameter("xt_sources_title"));
		}
		List<XtSources> xt_SourcesList = xtSourcesService.getXtSourcesListByCondition(condition);
		String hssources_base_url = CommonUtils.getXtPathCache("hssources_base_url").get(0).getXt_path();
		for(int i = 0; i < xt_SourcesList.size(); i++){
			xt_SourcesList.get(i).setHssources_base_url(hssources_base_url);
		}
		PageInfo<XtSources> page = new PageInfo<XtSources>(xt_SourcesList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_sources_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtSourcesById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtSourcesById(String xt_sources_id,HttpServletRequest request){
		XtSources xt_Sources = xtSourcesService.getXtSourcesById(xt_sources_id);
		return outDataStr(xt_Sources);
	}
	/**
	* 添加
	* @param xt_sources 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtSources",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtSources(XtSources xt_Sources,HttpServletRequest request){
		int i = 0;
		if(null != xt_Sources && !"".equals(xt_Sources)){
			xt_Sources.setXt_sources_id(UUID.toUUID());
			xt_Sources.setXt_sources_ctime(getSimpleDateFormat());
			xt_Sources.setXt_userinfo_id(getXtUid());
			i=xtSourcesService.addXtSources(xt_Sources);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_sources 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtSources",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtSources(XtSources xt_Sources,HttpServletRequest request){
		int i = 0;
		if(null != xt_Sources && !"".equals(xt_Sources)){
			xt_Sources.setXt_sources_mtime(getSimpleDateFormat());
			xt_Sources.setXt_userinfo_id(getXtUid());
			i=xtSourcesService.updateXtSources(xt_Sources);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_sources_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtSources",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtSources(String xt_sources_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_sources_id && !"".equals(xt_sources_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_sources_id",xt_sources_id.split(","));
			i=xtSourcesService.delXtSources(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_sources_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtSources",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtSources(String xt_sources_id,HttpServletRequest request){
		int i = 0;
		XtSources xt_Sources = xtSourcesService.getXtSourcesById(xt_sources_id);
		if(null != xt_Sources && !"".equals(xt_Sources)){
			xt_Sources.setXt_userinfo_id(getXtUid());
			xt_Sources.setXt_sources_id(UUID.toUUID());
			i=xtSourcesService.addXtSources(xt_Sources);
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
	@RequestMapping(value="/exportXtSources",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtSources(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	
	/**
	* 载入视图列表
	* @param xt_sources 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/toXtSourcesDataView",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtSourcesDataView(XtSources xt_Sources,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-sources/xt-sources-dataview-list");
	}
}
