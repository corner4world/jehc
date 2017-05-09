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
import jehc.xtmodules.xtmodel.Xt_Dbinfo;
import jehc.xtmodules.xtservice.Xt_DbinfoService;

/**
* 数据库信息表; InnoDB free: 4096 kB 
* 2015-09-30 16:37:40  邓纯杰
*/
@Controller
@RequestMapping("/xtDbinfoController")
public class Xt_DbinfoController extends BaseAction{
	@Autowired
	private Xt_DbinfoService xt_DbinfoService;
	/**
	* 载入初始化页面
	* @param xt_dbinfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDbinfo(Xt_Dbinfo xt_Dbinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-dbinfo/xt-dbinfo-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_dbinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDbinfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbinfoListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonPager(condition,request);
		List<Xt_Dbinfo> xt_DbinfoList = xt_DbinfoService.getXtDbinfoListByCondition(condition);
		PageInfo<Xt_Dbinfo> page = new PageInfo<Xt_Dbinfo>(xt_DbinfoList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_dbinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDbinfoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbinfoById(String xt_dbinfo_id,HttpServletRequest request){
		Xt_Dbinfo xt_Dbinfo = xt_DbinfoService.getXtDbinfoById(xt_dbinfo_id);
		return outDataStr(xt_Dbinfo);
	}
	/**
	* 添加
	* @param xt_dbinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDbinfo(Xt_Dbinfo xt_Dbinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Dbinfo && !"".equals(xt_Dbinfo)){
			xt_Dbinfo.setXt_dbinfo_id(UUID.toUUID());
			i=xt_DbinfoService.addXtDbinfo(xt_Dbinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_dbinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtDbinfo(Xt_Dbinfo xt_Dbinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Dbinfo && !"".equals(xt_Dbinfo)){
			i=xt_DbinfoService.updateXtDbinfo(xt_Dbinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_dbinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDbinfo(String xt_dbinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_dbinfo_id && !"".equals(xt_dbinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_dbinfo_id",xt_dbinfo_id.split(","));
			i=xt_DbinfoService.delXtDbinfo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_dbinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtDbinfo(String xt_dbinfo_id,HttpServletRequest request){
		int i = 0;
		Xt_Dbinfo xt_Dbinfo = xt_DbinfoService.getXtDbinfoById(xt_dbinfo_id);
		if(null != xt_Dbinfo && !"".equals(xt_Dbinfo)){
			xt_Dbinfo.setXt_dbinfo_id(UUID.toUUID());
			i=xt_DbinfoService.addXtDbinfo(xt_Dbinfo);
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
	@RequestMapping(value="/exportXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtDbinfo(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
