package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import jehc.xtmodules.xtcore.util.DateUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.Xt_Loadinfo;
import jehc.xtmodules.xtservice.Xt_LoadinfoService;

/**
* 页面加载信息; InnoDB free: 4096 kB 
* 2015-09-30 15:30:38  邓纯杰
*/
@Controller
@RequestMapping("/xtLoadinfoController")
public class Xt_LoadinfoController extends BaseAction{
	@Autowired
	private Xt_LoadinfoService xt_LoadinfoService;
	/**
	* 载入初始化页面
	* @param xt_loadinfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtLoadinfo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtLoadinfo(Xt_Loadinfo xt_Loadinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-loadinfo/xt-loadinfo-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_loadinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtLoadinfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtLoadinfoListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Loadinfo> xt_LoadinfoList = xt_LoadinfoService.getXtLoadinfoListByCondition(condition);
		PageInfo<Xt_Loadinfo> page = new PageInfo<Xt_Loadinfo>(xt_LoadinfoList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_loadinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtLoadinfoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtLoadinfoById(String xt_loadinfo_id,HttpServletRequest request){
		Xt_Loadinfo xt_Loadinfo = xt_LoadinfoService.getXtLoadinfoById(xt_loadinfo_id);
		return outDataStr(xt_Loadinfo);
	}
	/**
	* 添加
	* @param xt_loadinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtLoadinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtLoadinfo(Xt_Loadinfo xt_Loadinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Loadinfo && !"".equals(xt_Loadinfo)){
			xt_Loadinfo.setXt_loadinfo_time((int)(Long.parseLong(xt_Loadinfo.getXt_loadinfo_endtime())-Long.parseLong(xt_Loadinfo.getXt_loadinfo_begtime())));
			xt_Loadinfo.setXt_loadinfo_begtime(DateUtils.convert(Long.parseLong(xt_Loadinfo.getXt_loadinfo_begtime())));
			xt_Loadinfo.setXt_loadinfo_endtime(DateUtils.convert(Long.parseLong(xt_Loadinfo.getXt_loadinfo_endtime())));
			xt_Loadinfo.setXt_loadinfo_id(UUID.toUUID());
			xt_Loadinfo.setXt_userinfo_id(CommonUtils.getXtUid());
			i=xt_LoadinfoService.addXtLoadinfo(xt_Loadinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_loadinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtLoadinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtLoadinfo(Xt_Loadinfo xt_Loadinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Loadinfo && !"".equals(xt_Loadinfo)){
			i=xt_LoadinfoService.updateXtLoadinfo(xt_Loadinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_loadinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtLoadinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtLoadinfo(String xt_loadinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_loadinfo_id && !"".equals(xt_loadinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_loadinfo_id",xt_loadinfo_id.split(","));
			i=xt_LoadinfoService.delXtLoadinfo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_loadinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtLoadinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtLoadinfo(String xt_loadinfo_id,HttpServletRequest request){
		int i = 0;
		Xt_Loadinfo xt_Loadinfo = xt_LoadinfoService.getXtLoadinfoById(xt_loadinfo_id);
		if(null != xt_Loadinfo && !"".equals(xt_Loadinfo)){
			xt_Loadinfo.setXt_loadinfo_id(UUID.toUUID());
			i=xt_LoadinfoService.addXtLoadinfo(xt_Loadinfo);
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
	@RequestMapping(value="/exportXtLoadinfo",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtLoadinfo(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 分组统计
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtLoadingGroupList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtLoadingGroupList(){
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		List<Xt_Loadinfo> xt_LoadinfoList = xt_LoadinfoService.getXtLoadingGroupList();
		for(int i = 0; i < xt_LoadinfoList.size(); i++){
			Xt_Loadinfo xtLoadinfo = xt_LoadinfoList.get(i);
			model.put("menuTitle", xtLoadinfo.getXt_loadinfo_modules());
			model.put("loadingTime", xtLoadinfo.getXt_loadinfo_time());
			jsonArray.add(model);
		}
		return outItemsStr(jsonArray);
	}
}
