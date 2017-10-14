package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.annotation.AuthUneedLogin;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtChangePwd;
import jehc.xtmodules.xtservice.XtChangePwdService;

/**
* 密码找回中心 
* 2016-10-21 16:41:55  邓纯杰
*/
@Controller
@RequestMapping("/xtChangePwdController")
public class XtChangePwdController extends BaseAction{
	@Autowired
	private XtChangePwdService xtChangePwdService;
	/**
	* 载入初始化页面
	* @param xt_change_pwd 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtChangePwd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtChangePwd(XtChangePwd xt_Change_Pwd,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-change-pwd/xt-change-pwd-list");
	}
	
	/**
	* 载入工作流表单明细页面
	* @param xt_change_pwd 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtChangePwdDetailApply",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtChangePwdDetailApply(String lc_apply_model_biz_id,HttpServletRequest request,Model model){
		model.addAttribute("lc_apply_model_biz_id", lc_apply_model_biz_id);
		return new ModelAndView("pc/xt-view/xt-change-pwd/xt-change-pwd-detail-apply");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_change_pwd 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtChangePwdListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtChangePwdListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtChangePwd> xt_Change_PwdList = xtChangePwdService.getXtChangePwdListByCondition(condition);
		PageInfo<XtChangePwd> page = new PageInfo<XtChangePwd>(xt_Change_PwdList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_change_pwd_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtChangePwdById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtChangePwdById(String xt_change_pwd_id,HttpServletRequest request){
		XtChangePwd xt_Change_Pwd = xtChangePwdService.getXtChangePwdById(xt_change_pwd_id);
		return outDataStr(xt_Change_Pwd);
	}
	/**
	* 添加
	* @param xt_change_pwd 
	* @param request 
	*/
	@AuthUneedLogin
	@ResponseBody
	@RequestMapping(value="/addXtChangePwd",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtChangePwd(XtChangePwd xt_Change_Pwd,HttpServletRequest request){
		int i = 0;
		if(null != xt_Change_Pwd && !"".equals(xt_Change_Pwd)){
			xt_Change_Pwd.setXt_change_pwd_id(UUID.toUUID());
			i=xtChangePwdService.addXtChangePwd(xt_Change_Pwd);
		}
		if(i>0){
			return outAudStr(true,"您的提交申请已成功，我们会尽快联系人员处理，请您耐心等待!");
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_change_pwd 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtChangePwd",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtChangePwd(XtChangePwd xt_Change_Pwd,HttpServletRequest request){
		int i = 0;
		if(null != xt_Change_Pwd && !"".equals(xt_Change_Pwd)){
			i=xtChangePwdService.updateXtChangePwd(xt_Change_Pwd);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_change_pwd_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtChangePwd",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtChangePwd(String xt_change_pwd_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_change_pwd_id && !"".equals(xt_change_pwd_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_change_pwd_id",xt_change_pwd_id.split(","));
			i=xtChangePwdService.delXtChangePwd(condition);
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
	@RequestMapping(value="/exportXtChangePwd",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtChangePwd(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
