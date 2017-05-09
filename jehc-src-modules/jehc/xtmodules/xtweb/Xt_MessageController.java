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
import jehc.xtmodules.xtmodel.Xt_Message;
import jehc.xtmodules.xtservice.Xt_MessageService;

/**
* 短消息 
* 2016-10-20 17:49:40  邓纯杰
*/
@Controller
@RequestMapping("/xtMessageController")
public class Xt_MessageController extends BaseAction{
	@Autowired
	private Xt_MessageService xt_MessageService;
	/**
	* 载入初始化页面
	* @param xt_message 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMessage(Xt_Message xt_Message,HttpServletRequest request){
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
		commonHPager(condition,request);
		List<Xt_Message> xt_MessageList = xt_MessageService.getXtMessageListByCondition(condition);
		PageInfo<Xt_Message> page = new PageInfo<Xt_Message>(xt_MessageList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_message_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMessageById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMessageById(String xt_message_id,HttpServletRequest request){
		Xt_Message xt_Message = xt_MessageService.getXtMessageById(xt_message_id);
		return outDataStr(xt_Message);
	}
	/**
	* 添加
	* @param xt_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtMessage(Xt_Message xt_Message,HttpServletRequest request){
		int i = 0;
		System.out.println(xt_Message.getXt_message_id());
		if(null != xt_Message && !"".equals(xt_Message)){
			xt_Message.setXt_message_id(UUID.toUUID());
			xt_Message.setCtime(getSimpleDateFormat());
			xt_Message.setFrom_id(getXtUid());
			i=xt_MessageService.addXtMessage(xt_Message);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtMessage(Xt_Message xt_Message,HttpServletRequest request){
		int i = 0;
		if(null != xt_Message && !"".equals(xt_Message)){
			i=xt_MessageService.updateXtMessage(xt_Message);
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
	public String delXtMessage(String xt_message_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_message_id && !"".equals(xt_message_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_message_id",xt_message_id.split(","));
			i=xt_MessageService.delXtMessage(condition);
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
		Xt_Message xt_Message = xt_MessageService.getXtMessageById(xt_message_id);
		if(null != xt_Message && !"".equals(xt_Message)){
			xt_Message.setXt_message_id(UUID.toUUID());
			i=xt_MessageService.addXtMessage(xt_Message);
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
		int i = xt_MessageService.updateRead(condition);
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
	public String getXtMessageHisListByCondition(Xt_Message xt_Message,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("to_id", xt_Message.getTo_id());
		condition.put("from_id", getXtUid());
		List<Xt_Message> xt_MessageList = xt_MessageService.getXtMessageListByCondition(condition);
		PageInfo<Xt_Message> page = new PageInfo<Xt_Message>(xt_MessageList);
		condition = new HashMap<String, Object>();
		condition.put("to_id", xt_Message.getTo_id());
		condition.put("from_id", getXtUid());
		condition.put("isread", 1);
		xt_MessageService.updateRead(condition);
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
	public String getXtMessageUnReadCount(Xt_Message xt_Message,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("to_id", getXtUid());
		condition.put("isread", 0);
		List<Xt_Message> xtMessageList = xt_MessageService.getXtMessageCountByCondition(condition);
		return outItemsStr(xtMessageList);
	}
}
