package jehc.xtmodules.xtweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtNotice;
import jehc.xtmodules.xtservice.XtNoticeService;

/**
* 平台公告 
* 2016-06-18 15:45:40  邓纯杰
*/
@Controller
@RequestMapping("/xtNoticeController")
public class XtNoticeController extends BaseAction{
	@Autowired
	private XtNoticeService xtNoticeService;
	/**
	* 载入初始化页面
	* @param xt_notice 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtNotice",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtNotice(XtNotice xt_Notice,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-notice/xt-notice-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_notice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtNoticeListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtNoticeListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		dataAuthForXtUID(request,"xt_userinfo_id", condition);
		List<XtNotice> xt_NoticeList = xtNoticeService.getXtNoticeListByCondition(condition);
		PageInfo<XtNotice> page = new PageInfo<XtNotice>(xt_NoticeList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_notice_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtNoticeById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtNoticeById(String xt_notice_id,HttpServletRequest request){
		XtNotice xt_Notice = xtNoticeService.getXtNoticeById(xt_notice_id);
		return outDataStr(xt_Notice);
	}
	/**
	* 添加
	* @param xt_notice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtNotice",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtNotice(@Valid XtNotice xt_Notice,BindingResult bindingResult,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			return outAudStr(false,backFem(bindingResult));
		}
		int i = 0;
		if(null != xt_Notice && !"".equals(xt_Notice)){
			xt_Notice.setXt_notice_id(UUID.toUUID());
			xt_Notice.setXt_userinfo_id(getXtUid());
			xt_Notice.setXt_createTime(getSimpleDateFormat());
			i=xtNoticeService.addXtNotice(xt_Notice);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_notice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtNotice",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtNotice(@Valid XtNotice xt_Notice,BindingResult bindingResult,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			return outAudStr(false,backFem(bindingResult));
		}
		int i = 0;
		if(null != xt_Notice && !"".equals(xt_Notice)){
			i=xtNoticeService.updateXtNotice(xt_Notice);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_notice_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtNotice",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtNotice(String xt_notice_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_notice_id && !"".equals(xt_notice_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_notice_id",xt_notice_id.split(","));
			i=xtNoticeService.delXtNotice(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_notice_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtNotice",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtNotice(String xt_notice_id,HttpServletRequest request){
		int i = 0;
		XtNotice xt_Notice = xtNoticeService.getXtNoticeById(xt_notice_id);
		if(null != xt_Notice && !"".equals(xt_Notice)){
			xt_Notice.setXt_notice_id(UUID.toUUID());
			i=xtNoticeService.addXtNotice(xt_Notice);
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
	@RequestMapping(value="/exportXtNotice",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtNotice(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtNoticeAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtNoticeAdd(XtNotice xtNotice,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-notice/xt-notice-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtNoticeUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtNoticeUpdate(String xt_notice_id,HttpServletRequest request, Model model){
		XtNotice xtNotice = xtNoticeService.getXtNoticeById(xt_notice_id);
		model.addAttribute("xtNotice", xtNotice);
		return new ModelAndView("pc/xt-view/xt-notice/xt-notice-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtNoticeDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtNoticeDetail(String xt_notice_id,HttpServletRequest request, Model model){
		XtNotice xtNotice = xtNoticeService.getXtNoticeById(xt_notice_id);
		model.addAttribute("xtNotice", xtNotice);
		return new ModelAndView("pc/xt-view/xt-notice/xt-notice-detail");
	}
}
