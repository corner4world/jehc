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
import jehc.xtmodules.xtmodel.Xt_Version;
import jehc.xtmodules.xtservice.Xt_VersionService;

/**
* 平台版本 
* 2017-04-16 20:05:24  邓纯杰
*/
@Controller
@RequestMapping("/xtVersionController")
public class Xt_VersionController extends BaseAction{
	@Autowired
	private Xt_VersionService xt_VersionService;
	/**
	* 载入初始化页面
	* @param xt_version 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtVersion",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtVersion(Xt_Version xt_Version,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-version/xt-version-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_version 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtVersionListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtVersionListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Version> xt_VersionList = xt_VersionService.getXtVersionListByCondition(condition);
		PageInfo<Xt_Version> page = new PageInfo<Xt_Version>(xt_VersionList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_version_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtVersionById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtVersionById(String xt_version_id,HttpServletRequest request){
		Xt_Version xt_Version = xt_VersionService.getXtVersionById(xt_version_id);
		return outDataStr(xt_Version);
	}
	/**
	* 添加
	* @param xt_version 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtVersion",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtVersion(Xt_Version xt_Version,HttpServletRequest request){
		int i = 0;
		if(null != xt_Version && !"".equals(xt_Version)){
			xt_Version.setXt_version_id(UUID.toUUID());
			xt_Version.setXt_version_ctime(getSimpleDateFormat());
			xt_Version.setXt_userinfo_id(getXtUid());
			i=xt_VersionService.addXtVersion(xt_Version);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_version 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtVersion",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtVersion(Xt_Version xt_Version,HttpServletRequest request){
		int i = 0;
		if(null != xt_Version && !"".equals(xt_Version)){
			xt_Version.setXt_userinfo_id(getXtUid());
			xt_Version.setXt_version_mtime(getSimpleDateFormat());
			i=xt_VersionService.updateXtVersion(xt_Version);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_version_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtVersion",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtVersion(String xt_version_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_version_id && !"".equals(xt_version_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_version_id",xt_version_id.split(","));
			i=xt_VersionService.delXtVersion(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_version_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtVersion",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtVersion(String xt_version_id,HttpServletRequest request){
		int i = 0;
		Xt_Version xt_Version = xt_VersionService.getXtVersionById(xt_version_id);
		if(null != xt_Version && !"".equals(xt_Version)){
			xt_Version.setXt_version_id(UUID.toUUID());
			i=xt_VersionService.addXtVersion(xt_Version);
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
	@RequestMapping(value="/exportXtVersion",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtVersion(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
