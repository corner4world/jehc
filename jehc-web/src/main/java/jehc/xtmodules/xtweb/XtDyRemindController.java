package jehc.xtmodules.xtweb;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.annotation.NeedLoginUnAuth;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtmodel.XtDyRemind;
import jehc.xtmodules.xtmodel.XtMessage;
import jehc.xtmodules.xtmodel.XtNotifyReceiver;
import jehc.xtmodules.xtservice.XtMessageService;
import jehc.xtmodules.xtservice.XtNotifyReceiverService;
/**
 * 
 * @author 全局统一动态提醒
 *
 */
@Controller
@RequestMapping("/xtDyRemindController")
public class XtDyRemindController extends BaseAction {
	@Autowired
	private XtNotifyReceiverService xtNotifyReceiverService;
	@Autowired
	private XtMessageService xtMessageService;
	
	/**
	* 加载初始化列表数据并分页
	* @param xt_encoderqrcode 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDyRemindList",method={RequestMethod.POST,RequestMethod.GET})
	@NeedLoginUnAuth
	public XtDyRemind getXtDyRemindList(BaseSearch baseSearch,HttpServletRequest request,String receive_status){
		XtDyRemind xtDyRemind = new XtDyRemind();
		
		//处理通知
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		condition.put("xt_userinfo_id", getXtUid());
		condition.put("receive_status",0);
		List<XtNotifyReceiver> xtNotifyReceiverList = xtNotifyReceiverService.getXtNotifyReceiverListByCondition(condition);
		PageInfo<XtNotifyReceiver> page = new PageInfo<XtNotifyReceiver>(xtNotifyReceiverList);
		xtDyRemind.setXtNotifyReceiverList(page.getList());
		
		//处理短消息
		condition.put("type", "1");
		condition.put("to_id", getXtUid());
		condition.put("isread", 0);
		List<XtMessage> xt_MessageList = xtMessageService.getXtMessageListByCondition(condition);
		PageInfo<XtMessage> xtMessage = new PageInfo<XtMessage>(xt_MessageList);
		xtDyRemind.setXtMessageList(xtMessage.getList());
		return xtDyRemind;
	}
}
