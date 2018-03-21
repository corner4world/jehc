package jehc.xtmodules.xtweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtmodel.XtNotifyReceiver;
import jehc.xtmodules.xtservice.XtNotifyReceiverService;
/**
 * 通知接收人
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/xtNotifyReceiverController")
public class XtNotifyReceiverController extends BaseAction {
	@Autowired
	private XtNotifyReceiverService xtNotifyReceiverService;
	
	
	/**
	* 加载初始化列表数据并分页
	* @param xt_encoderqrcode 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtNotifyReceiverListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtNotifyReceiverListByCondition(BaseSearch baseSearch,HttpServletRequest request,String receive_status){
		Map<String, Object> condition = baseSearch.convert();
		condition.put("xt_userinfo_id", getXtUid());
		condition.put("receive_status",receive_status);
		commonHPager(condition,request);
		List<XtNotifyReceiver> xtNotifyReceiverList = xtNotifyReceiverService.getXtNotifyReceiverListByCondition(condition);
		PageInfo<XtNotifyReceiver> page = new PageInfo<XtNotifyReceiver>(xtNotifyReceiverList);
		return outPageBootStr(page,request);
	}

	
	/**
	* 删除
	* @param xt_notify_receiver_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtNotifyReceiver",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtNotifyReceiver(String xt_notify_receiver_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_notify_receiver_id && !"".equals(xt_notify_receiver_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_notify_receiver_id",xt_notify_receiver_id.split(","));
			i=xtNotifyReceiverService.delXtNotifyReceiver(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 发送至明细页面
	* @param xt_notify_id 
	* @param request 
	*/
	@RequestMapping(value="/toXtReceiverDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtReceiverDetail(String xt_notify_receiver_id,HttpServletRequest request,Model model){
		XtNotifyReceiver xtNotifyReceiver =xtNotifyReceiverService.getXtNotifyReceiverById(xt_notify_receiver_id);
		if(null != xtNotifyReceiver){
			Boolean flag=0==xtNotifyReceiver.getReceive_status();
			if(flag){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("read_time", getDate());
				condition.put("xt_notify_receiver_id", xt_notify_receiver_id);
				xtNotifyReceiverService.updateXtNotifyReceiver(condition);
			}
			model.addAttribute("xtNotifyReceiver",xtNotifyReceiver);
		}
		return new ModelAndView("pc/xt-view/xt-notify/xt-notify-receiver-detail"); 
	}
}
