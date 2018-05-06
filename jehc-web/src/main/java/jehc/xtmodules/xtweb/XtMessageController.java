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
import jehc.xtmodules.xtmodel.XtMessage;
import jehc.xtmodules.xtservice.XtMessageService;

/**
* 短消息 
* 2016-10-20 17:49:40  邓纯杰
*/
@Controller
@RequestMapping("/xtMessageController")
public class XtMessageController extends BaseAction{
	@Autowired
	private XtMessageService xtMessageService;
	/**
	* 载入初始化页面
	* @param xt_message 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMessage(XtMessage xt_Message,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-message/xt-message-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMessageListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMessageListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		if("0".equals(condition.get("type"))){//我发送的消息
			condition.put("from_id", getXtUid());
		}else if("1".equals(condition.get("type"))){
			condition.put("to_id", getXtUid());
		}else{
			condition.put("to_id", "-1");
		}
		commonHPager(condition,request);
		List<XtMessage> xt_MessageList = xtMessageService.getXtMessageListByCondition(condition);
		PageInfo<XtMessage> page = new PageInfo<XtMessage>(xt_MessageList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_message_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMessageById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMessageById(String xt_message_id,String type,HttpServletRequest request){
		if("1".equals(type)){
			Map<String, Object> condition = new HashMap<String,Object>();
			condition.put("isread", 1);
			condition.put("readtime", getDate());
			condition.put("xt_message_id", xt_message_id);
			xtMessageService.updateRead(condition);
		}
		XtMessage xt_Message = xtMessageService.getXtMessageById(xt_message_id);
		return outDataStr(xt_Message);
	}
	/**
	* 添加
	* @param xt_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtMessage(@Valid XtMessage xt_Message,BindingResult bindingResult,HttpServletRequest request){
		if(bindingResult.hasErrors()){
			return outAudStr(false,backFem(bindingResult));
		}
		int i = 0;
		if(null != xt_Message && !"".equals(xt_Message)){
			xt_Message.setXt_message_id(UUID.toUUID());
			xt_Message.setCtime(getDate());
			xt_Message.setFrom_id(getXtUid());
			i=xtMessageService.addXtMessage(xt_Message);
		}
		if(i>0){
			return outAudStr(true,"发送成功");
		}else{
			return outAudStr(false,"发送失败");
		}
	}
	/**
	* 修改
	* @param xt_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtMessage(XtMessage xt_Message,HttpServletRequest request){
		int i = 0;
		if(null != xt_Message && !"".equals(xt_Message)){
			i=xtMessageService.updateXtMessage(xt_Message);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_message_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtMessage(String xt_message_id,String type ,HttpServletRequest request){
		int i = 0;
		if(null != xt_message_id && !"".equals(xt_message_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_message_id",xt_message_id.split(","));
			condition.put("type",type);
			i=xtMessageService.delXtMessage(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_message_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtMessage(String xt_message_id,HttpServletRequest request){
		int i = 0;
		XtMessage xt_Message = xtMessageService.getXtMessageById(xt_message_id);
		if(null != xt_Message && !"".equals(xt_Message)){
			xt_Message.setXt_message_id(UUID.toUUID());
			i=xtMessageService.addXtMessage(xt_Message);
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
	@RequestMapping(value="/exportXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtMessage(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 更新已读状态
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateRead",method={RequestMethod.POST,RequestMethod.GET})
	public String updateRead(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("isread", 1);
		condition.put("from_id", getXtUid());
		condition.put("to_id", request.getParameter("to_id"));
		int i = xtMessageService.updateRead(condition);
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 加载历史列表数据并分页
	* @param xt_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMessageHisListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMessageHisListByCondition(XtMessage xt_Message,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("to_id", xt_Message.getTo_id());
		condition.put("from_id", getXtUid());
		List<XtMessage> xt_MessageList = xtMessageService.getXtMessageListByCondition(condition);
		PageInfo<XtMessage> page = new PageInfo<XtMessage>(xt_MessageList);
		condition = new HashMap<String, Object>();
		condition.put("to_id", xt_Message.getTo_id());
		condition.put("from_id", getXtUid());
		condition.put("isread", 1);
		xtMessageService.updateRead(condition);
		return outPageStr(page,request);
	}
	
	/**
	 * 获取未读消息个数
	 * @param xt_Message
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMessageUnReadCount",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMessageUnReadCount(XtMessage xt_Message,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("to_id", getXtUid());
		condition.put("isread", 0);
		List<XtMessage> xtMessageList = xtMessageService.getXtMessageCountByCondition(condition);
		return outItemsStr(xtMessageList);
	}
	
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtMessageAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtMessageAdd(XtMessage xtMessage,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-message/xt-message-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtMessageUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtMessageUpdate(String xt_message_id,HttpServletRequest request, Model model){
		XtMessage xtMessage = xtMessageService.getXtMessageById(xt_message_id);
		model.addAttribute("xtMessage", xtMessage);
		return new ModelAndView("pc/xt-view/xt-message/xt-message-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtMessageDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtMessageDetail(String xt_message_id,String type,HttpServletRequest request, Model model){
		if("1".equals(type)){
			Map<String, Object> condition = new HashMap<String,Object>();
			condition.put("isread", 1);
			condition.put("readtime", getDate());
			condition.put("xt_message_id", xt_message_id);
			xtMessageService.updateRead(condition);
		}
		XtMessage xtMessage = xtMessageService.getXtMessageById(xt_message_id);
		model.addAttribute("xtMessage", xtMessage);
		return new ModelAndView("pc/xt-view/xt-message/xt-message-detail");
	}
}
