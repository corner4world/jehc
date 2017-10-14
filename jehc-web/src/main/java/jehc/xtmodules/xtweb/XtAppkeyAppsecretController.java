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

import jehc.xtmodules.xtcore.allutils.MD5;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtAppkeyAppsecret;
import jehc.xtmodules.xtservice.XtAppkeyAppsecretService;

/**
* xt_appkey_appsecret 
* 2016-08-28 14:37:16  邓纯杰
*/
@Controller
@RequestMapping("/xtAppkeyAppsecretController")
public class XtAppkeyAppsecretController extends BaseAction{
	@Autowired
	private XtAppkeyAppsecretService xtAppkeyAppsecretService;
	/**
	* 载入初始化页面
	* @param xt_appkey_appsecret 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtAppkeyAppsecret",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtAppkeyAppsecret(XtAppkeyAppsecret xt_Appkey_Appsecret,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-appkey-appsecret/xt-appkey-appsecret-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_appkey_appsecret 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtAppkeyAppsecretListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtAppkeyAppsecretListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtAppkeyAppsecret> xt_Appkey_AppsecretList = xtAppkeyAppsecretService.getXtAppkeyAppsecretListByCondition(condition);
		PageInfo<XtAppkeyAppsecret> page = new PageInfo<XtAppkeyAppsecret>(xt_Appkey_AppsecretList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_appkey_appsecret_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtAppkeyAppsecretById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtAppkeyAppsecretById(String xt_appkey_appsecret_id,HttpServletRequest request){
		XtAppkeyAppsecret xt_Appkey_Appsecret = xtAppkeyAppsecretService.getXtAppkeyAppsecretById(xt_appkey_appsecret_id);
		return outDataStr(xt_Appkey_Appsecret);
	}
	/**
	* 添加
	* @param xt_appkey_appsecret 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtAppkeyAppsecret",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtAppkeyAppsecret(XtAppkeyAppsecret xt_Appkey_Appsecret,HttpServletRequest request){
		int i = 0;
		if(null != xt_Appkey_Appsecret && !"".equals(xt_Appkey_Appsecret)){
			xt_Appkey_Appsecret.setXt_appkey_appsecret_id(UUID.toUUID());
			xt_Appkey_Appsecret.setXt_ctime(getSimpleDateFormat());
			xt_Appkey_Appsecret.setXt_appkey(UUID.toUUID());
			xt_Appkey_Appsecret.setXt_appsecret(MD5.md5(UUID.toUUID()).toUpperCase());
			xt_Appkey_Appsecret.setXt_userinfo_id(getXtUid());
			i=xtAppkeyAppsecretService.addXtAppkeyAppsecret(xt_Appkey_Appsecret);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_appkey_appsecret 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtAppkeyAppsecret",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtAppkeyAppsecret(XtAppkeyAppsecret xt_Appkey_Appsecret,HttpServletRequest request){
		int i = 0;
		if(null != xt_Appkey_Appsecret && !"".equals(xt_Appkey_Appsecret)){
			xt_Appkey_Appsecret.setXt_mtime(getSimpleDateFormat());
			xt_Appkey_Appsecret.setXt_appsecret(MD5.md5(UUID.toUUID()).toUpperCase());
			xt_Appkey_Appsecret.setXt_userinfo_id(getXtUid());
			i=xtAppkeyAppsecretService.updateXtAppkeyAppsecret(xt_Appkey_Appsecret);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_appkey_appsecret_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtAppkeyAppsecret",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtAppkeyAppsecret(String xt_appkey_appsecret_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_appkey_appsecret_id && !"".equals(xt_appkey_appsecret_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_appkey_appsecret_id",xt_appkey_appsecret_id.split(","));
			i=xtAppkeyAppsecretService.delXtAppkeyAppsecret(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_appkey_appsecret_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtAppkeyAppsecret",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtAppkeyAppsecret(String xt_appkey_appsecret_id,HttpServletRequest request){
		int i = 0;
		XtAppkeyAppsecret xt_Appkey_Appsecret = xtAppkeyAppsecretService.getXtAppkeyAppsecretById(xt_appkey_appsecret_id);
		if(null != xt_Appkey_Appsecret && !"".equals(xt_Appkey_Appsecret)){
			xt_Appkey_Appsecret.setXt_appkey_appsecret_id(UUID.toUUID());
			xt_Appkey_Appsecret.setXt_appkey(UUID.toUUID());
			xt_Appkey_Appsecret.setXt_appsecret(MD5.md5(UUID.toUUID()).toUpperCase());
			i=xtAppkeyAppsecretService.addXtAppkeyAppsecret(xt_Appkey_Appsecret);
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
	@RequestMapping(value="/exportXtAppkeyAppsecret",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtAppkeyAppsecret(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
