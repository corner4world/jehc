package jehc.oamodules.oaweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
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
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.oamodules.oamodel.OaNotice;
import jehc.oamodules.oaservice.OaNoticeService;

/**
* 公告 
* 2017-11-16 13:23:07  邓纯杰
*/
@Controller
@RequestMapping("/oaNoticeController")
public class OaNoticeController extends BaseAction{
	@Autowired
	private OaNoticeService oaNoticeService;
	/**
	* 载入初始化页面
	* @param oa_notice 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadOaNotice",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadOaNotice(OaNotice oaNotice,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-notice/oa-notice-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param oa_notice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaNoticeListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaNoticeListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<OaNotice> oaNoticeList = oaNoticeService.getOaNoticeListByCondition(condition);
		PageInfo<OaNotice> page = new PageInfo<OaNotice>(oaNoticeList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param oa_noticeID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaNoticeById",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaNoticeById(String oa_noticeID,HttpServletRequest request){
		OaNotice oaNotice = oaNoticeService.getOaNoticeById(oa_noticeID);
		return outDataStr(oaNotice);
	}
	/**
	* 添加
	* @param oa_notice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addOaNotice",method={RequestMethod.POST,RequestMethod.GET})
	public String addOaNotice(OaNotice oaNotice,HttpServletRequest request){
		int i = 0;
		if(null != oaNotice && !"".equals(oaNotice)){
			oaNotice.setOa_noticeID(UUID.toUUID());
			oaNotice.setOa_noticeCreateTime(getSimpleDateFormat());
			oaNotice.setXt_userinfo_id(getXtUid());
			i=oaNoticeService.addOaNotice(oaNotice);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param oa_notice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateOaNotice",method={RequestMethod.POST,RequestMethod.GET})
	public String updateOaNotice(OaNotice oaNotice,HttpServletRequest request){
		int i = 0;
		if(null != oaNotice && !"".equals(oaNotice)){
			i=oaNoticeService.updateOaNoticeBySelective(oaNotice);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param oa_noticeID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delOaNotice",method={RequestMethod.POST,RequestMethod.GET})
	public String delOaNotice(String oa_noticeID,HttpServletRequest request){
		int i = 0;
		if(null != oa_noticeID && !"".equals(oa_noticeID)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("oa_noticeID",oa_noticeID.split(","));
			i=oaNoticeService.delOaNotice(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param oa_noticeID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyOaNotice",method={RequestMethod.POST,RequestMethod.GET})
	public String copyOaNotice(String oa_noticeID,HttpServletRequest request){
		int i = 0;
		OaNotice oaNotice = oaNoticeService.getOaNoticeById(oa_noticeID);
		if(null != oaNotice && !"".equals(oaNotice)){
			oaNotice.setOa_noticeID(UUID.toUUID());
			i=oaNoticeService.addOaNotice(oaNotice);
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
	@RequestMapping(value="/exportOaNotice",method={RequestMethod.POST,RequestMethod.GET})
	public void exportOaNotice(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoticeAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoticeAdd(OaNotice oaNotice,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-notice/oa-notice-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoticeUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoticeUpdate(String oa_noticeID,HttpServletRequest request, Model model){
		OaNotice oaNotice = oaNoticeService.getOaNoticeById(oa_noticeID);
		model.addAttribute("oaNotice", oaNotice);
		return new ModelAndView("pc/oa-view/oa-notice/oa-notice-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoticeDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoticeDetail(String oa_noticeID,HttpServletRequest request, Model model){
		OaNotice oaNotice = oaNoticeService.getOaNoticeById(oa_noticeID);
		model.addAttribute("oaNotice", oaNotice);
		return new ModelAndView("pc/oa-view/oa-notice/oa-notice-detail");
	}
}
