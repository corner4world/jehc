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
import jehc.xtmodules.xtmodel.XtSms;
import jehc.xtmodules.xtservice.XtSmsService;

/**
* 短信配置表; InnoDB free: 4096 kB 
* 2015-09-30 16:15:49  邓纯杰
*/
@Controller
@RequestMapping("/xtSmsController")
public class XtSmsController extends BaseAction{
	@Autowired
	private XtSmsService xtSmsService;
	/**
	* 载入初始化页面
	* @param xt_sms 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtSms",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtSms(XtSms xt_Sms,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-sms/xt-sms-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_sms 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtSmsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtSmsListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtSms> xt_SmsList = xtSmsService.getXtSmsListByCondition(condition);
		PageInfo<XtSms> page = new PageInfo<XtSms>(xt_SmsList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_sms_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtSmsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtSmsById(String xt_sms_id,HttpServletRequest request){
		XtSms xt_Sms = xtSmsService.getXtSmsById(xt_sms_id);
		return outDataStr(xt_Sms);
	}
	/**
	* 添加
	* @param xt_sms 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtSms",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtSms(XtSms xt_Sms,HttpServletRequest request){
		int i = 0;
		if(null != xt_Sms && !"".equals(xt_Sms)){
			xt_Sms.setXt_sms_id(UUID.toUUID());
			i=xtSmsService.addXtSms(xt_Sms);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_sms 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtSms",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtSms(XtSms xt_Sms,HttpServletRequest request){
		int i = 0;
		if(null != xt_Sms && !"".equals(xt_Sms)){
			i=xtSmsService.updateXtSms(xt_Sms);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_sms_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtSms",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtSms(String xt_sms_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_sms_id && !"".equals(xt_sms_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_sms_id",xt_sms_id.split(","));
			i=xtSmsService.delXtSms(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_sms_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtSms",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtSms(String xt_sms_id,HttpServletRequest request){
		int i = 0;
		XtSms xt_Sms = xtSmsService.getXtSmsById(xt_sms_id);
		if(null != xt_Sms && !"".equals(xt_Sms)){
			xt_Sms.setXt_sms_id(UUID.toUUID());
			i=xtSmsService.addXtSms(xt_Sms);
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
	@RequestMapping(value="/exportXtSms",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtSms(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
