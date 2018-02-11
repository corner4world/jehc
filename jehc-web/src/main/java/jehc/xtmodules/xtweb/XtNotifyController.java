package jehc.xtmodules.xtweb;

import java.util.ArrayList;
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
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.XtNotify;
import jehc.xtmodules.xtmodel.XtNotifyReceiver;
import jehc.xtmodules.xtservice.XtNotifyReceiverService;
import jehc.xtmodules.xtservice.XtNotifyService;
import jehc.xtmodules.xtservice.XtUserinfoService;
/**
 * 通知
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtNotifyController")
public class XtNotifyController extends BaseAction {
	@Autowired
	private XtNotifyService xtNotifyService;
	@Autowired
	private XtNotifyReceiverService xtNotifyReceiverService;
	@Autowired
	private XtUserinfoService xtUserinfoService;
	
	/**
	* 载入初始化页面
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtNotify",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtNotify(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-notify/xt-notify-list");
	}
	
	/**
	* 加载初始化列表数据并分页
	* @param xt_encoderqrcode 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtNotifyListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtNotifyListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		condition.put("xt_userinfo_id", getXtUid());
		commonHPager(condition,request);
		List<XtNotify> xtNotifyList = xtNotifyService.getXtNotifyListByCondition(condition);
		PageInfo<XtNotify> page = new PageInfo<XtNotify>(xtNotifyList);
		return outPageBootStr(page,request);
	}
	
	
	/**
	* 添加
	* @param xtNotify 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtNotify",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtNotify(XtNotify xtNotify,HttpServletRequest request){
		int i = 0;
		if(null != xtNotify && !"".equals(xtNotify)){
			List<XtNotifyReceiver> xtNotifyReceiverList = new ArrayList<XtNotifyReceiver>();
			xtNotify.setXt_notify_id(UUID.toUUID());
			xtNotify.setXt_notify_ctime(CommonUtils.getDate());
			String xt_userinfo_id = xtNotify.getXt_userinfo_id();
			if(!StringUtils.isEmpty(xt_userinfo_id)){
				String[] xt_userinfo_idList = xt_userinfo_id.split(",");
				for(int j = 0; j < xt_userinfo_idList.length; j++){
					XtNotifyReceiver xtNotifyReceiver = new XtNotifyReceiver();
					xtNotifyReceiver.setXt_notify_receiver_id(UUID.toUUID());
					xtNotifyReceiver.setXt_userinfo_id(xt_userinfo_idList[j]);
					xtNotifyReceiver.setXt_userinfo_realName(xtUserinfoService.getXtUserinfoById(xt_userinfo_idList[j]).getXt_userinfo_realName());
					xtNotifyReceiver.setReceive_time(xtNotify.getXt_notify_ctime());
					xtNotifyReceiverList.add(xtNotifyReceiver);
				}
				xtNotify.setXtNotifyReceiverList(xtNotifyReceiverList);
			}
			xtNotify.setXt_userinfo_id(CommonUtils.getXtUid());
			xtNotify.setXt_userinfo_realName(getXtU().getXt_userinfo_realName());
			i=xtNotifyService.addXtNotify(xtNotify);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_notify_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtNotify",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtNotify(String xt_notify_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_notify_id && !"".equals(xt_notify_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_notify_id",xt_notify_id.split(","));
			i=xtNotifyService.delXtNotify(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtNotifyAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtNotifyAdd(HttpServletRequest request,Model model){
		Map<String, Object> condition = new HashMap<String, Object>();
		model.addAttribute("XtUserinfoList", xtUserinfoService.getXtUserinfoListAllByCondition(condition));
		return new ModelAndView("pc/xt-view/xt-notify/xt-notify-add");
	}
	
	/**
	* 发送至明细页面
	* @param xt_notify_id 
	* @param request 
	*/
	@RequestMapping(value="/toXtNotifyDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtNotifyDetail(String xt_notify_id,HttpServletRequest request,Model model){
		XtNotify xtNotify = xtNotifyService.getXtNotifyById(xt_notify_id);
		xtNotify.setXtNotifyReceiverList(xtNotifyReceiverService.getXtNotifyReceiverListById(xt_notify_id));
		model.addAttribute("xtNotify",xtNotify);
		return new ModelAndView("pc/xt-view/xt-notify/xt-notify-detail"); 
	}
}
