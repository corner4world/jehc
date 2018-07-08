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
import jehc.oamodules.oamodel.OaSuggestionbox;
import jehc.oamodules.oamodel.OaSuggestionboxReply;
import jehc.oamodules.oaservice.OaSuggestionboxReplyService;
import jehc.oamodules.oaservice.OaSuggestionboxService;

/**
* 意见回复 
* 2018-07-08 10:28:44  邓纯杰
*/
@Controller
@RequestMapping("/oaSuggestionboxReplyController")
public class OaSuggestionboxReplyController extends BaseAction{
	@Autowired
	private OaSuggestionboxReplyService oaSuggestionboxReplyService;
	@Autowired
	private OaSuggestionboxService oaSuggestionboxService;
	/**
	* 载入初始化页面
	* @param oa_suggestionbox_reply 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadOaSuggestionboxReply",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-suggestionbox-reply/oa-suggestionbox-reply-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param oa_suggestionbox_reply 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaSuggestionboxReplyListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaSuggestionboxReplyListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<OaSuggestionbox> oaSuggestionboxList = oaSuggestionboxService.getOaSuggestionboxListByCondition(condition);
		PageInfo<OaSuggestionbox> page = new PageInfo<OaSuggestionbox>(oaSuggestionboxList);
		return outPageBootStr(page,request);
	}
	
	/**
	* 加载初始化列表数据并分页
	* @param oa_suggestionbox_reply 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaSuggestionboxReplyList",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaSuggestionboxReplyList(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<OaSuggestionboxReply> oaSuggestionboxReplyList = oaSuggestionboxReplyService.getOaSuggestionboxReplyListByCondition(condition);
		PageInfo<OaSuggestionboxReply> page = new PageInfo<OaSuggestionboxReply>(oaSuggestionboxReplyList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param oa_suggestionbox_replyID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaSuggestionboxReplyById",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaSuggestionboxReplyById(String oa_suggestionbox_replyID,HttpServletRequest request){
		OaSuggestionboxReply oaSuggestionboxReply = oaSuggestionboxReplyService.getOaSuggestionboxReplyById(oa_suggestionbox_replyID);
		return outDataStr(oaSuggestionboxReply);
	}
	/**
	* 添加
	* @param oa_suggestionbox_reply 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addOaSuggestionboxReply",method={RequestMethod.POST,RequestMethod.GET})
	public String addOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply,HttpServletRequest request){
		int i = 0;
		if(null != oaSuggestionboxReply && !"".equals(oaSuggestionboxReply)){
			oaSuggestionboxReply.setCreatetime(getDate());
			oaSuggestionboxReply.setXt_userinfo_id(getXtUid());
			oaSuggestionboxReply.setOa_suggestionbox_replyID(UUID.toUUID());
			i=oaSuggestionboxReplyService.addOaSuggestionboxReply(oaSuggestionboxReply);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param oa_suggestionbox_reply 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateOaSuggestionboxReply",method={RequestMethod.POST,RequestMethod.GET})
	public String updateOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply,HttpServletRequest request){
		int i = 0;
		if(null != oaSuggestionboxReply && !"".equals(oaSuggestionboxReply)){
			i=oaSuggestionboxReplyService.updateOaSuggestionboxReply(oaSuggestionboxReply);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param oa_suggestionbox_replyID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delOaSuggestionboxReply",method={RequestMethod.POST,RequestMethod.GET})
	public String delOaSuggestionboxReply(String oa_suggestionbox_replyID,HttpServletRequest request){
		int i = 0;
		if(null != oa_suggestionbox_replyID && !"".equals(oa_suggestionbox_replyID)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("oa_suggestionbox_replyID",oa_suggestionbox_replyID.split(","));
			i=oaSuggestionboxReplyService.delOaSuggestionboxReply(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param oa_suggestionbox_replyID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyOaSuggestionboxReply",method={RequestMethod.POST,RequestMethod.GET})
	public String copyOaSuggestionboxReply(String oa_suggestionbox_replyID,HttpServletRequest request){
		int i = 0;
		OaSuggestionboxReply oaSuggestionboxReply = oaSuggestionboxReplyService.getOaSuggestionboxReplyById(oa_suggestionbox_replyID);
		if(null != oaSuggestionboxReply && !"".equals(oaSuggestionboxReply)){
			oaSuggestionboxReply.setOa_suggestionbox_replyID(UUID.toUUID());
			i=oaSuggestionboxReplyService.addOaSuggestionboxReply(oaSuggestionboxReply);
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
	@RequestMapping(value="/exportOaSuggestionboxReply",method={RequestMethod.POST,RequestMethod.GET})
	public void exportOaSuggestionboxReply(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toOaSuggestionboxReplyAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaSuggestionboxReplyAdd(OaSuggestionboxReply oaSuggestionboxReply,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-suggestionbox-reply/oa-suggestionbox-reply-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toOaSuggestionboxReplyUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaSuggestionboxReplyUpdate(String oa_suggestionbox_replyID,HttpServletRequest request, Model model){
		OaSuggestionboxReply oaSuggestionboxReply = oaSuggestionboxReplyService.getOaSuggestionboxReplyById(oa_suggestionbox_replyID);
		model.addAttribute("oaSuggestionboxReply", oaSuggestionboxReply);
		return new ModelAndView("pc/oa-view/oa-suggestionbox-reply/oa-suggestionbox-reply-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toOaSuggestionboxReplyDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaSuggestionboxReplyDetail(String oa_suggestionbox_id,HttpServletRequest request, Model model){
		OaSuggestionbox oaSuggestionbox = oaSuggestionboxService.getOaSuggestionboxById(oa_suggestionbox_id);
		model.addAttribute("oaSuggestionbox", oaSuggestionbox);
		return new ModelAndView("pc/oa-view/oa-suggestionbox-reply/oa-suggestionbox-reply-detail");
	}
}
