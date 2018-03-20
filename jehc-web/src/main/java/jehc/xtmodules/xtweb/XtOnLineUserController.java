package jehc.xtmodules.xtweb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseHttpSessionEntity;
import jehc.xtmodules.xtcore.session.OnLinesessionlistener;
import jehc.xtmodules.xtcore.util.constant.SessionConstant;
import jehc.xtmodules.xtmodel.XtUserinfo;

/**
 * 在线用户监控
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtOnLineUserController")
public class XtOnLineUserController extends BaseAction{

	/**
	* 载入监控列表页面
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtOnLineUser",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtNotice(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-online-user/xt-online-user-list");
	}
	
	/**
	 * 读取在线用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/getXtOnLineUserList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtOnLineUserList(HttpServletRequest request){
		HttpSession se = request.getSession(); 
		ServletContext application = se.getServletContext();  
		List<XtUserinfo> xtUserInfoList = new ArrayList<XtUserinfo>();
		List<BaseHttpSessionEntity> baseHttpSessionEntityList = (List<BaseHttpSessionEntity>) application.getAttribute(SessionConstant.XT_ONLINE_USERLIST);  
		for(BaseHttpSessionEntity baseHttpSessionEntity:baseHttpSessionEntityList){
			xtUserInfoList.add(baseHttpSessionEntity.getXTUSERINFO());
		}
		return outItemsStr(xtUserInfoList);
	}
	
	
	/**
	 * 移除所有人员
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/removeAllXtOnLineUser",method={RequestMethod.POST,RequestMethod.GET})
	public String removeAllXtOnLineUser(HttpServletRequest request){
		try {
			 HttpSession session = request.getSession(); 
		     ServletContext application = session.getServletContext(); 
		     if(null != application){
		    	application.removeAttribute(SessionConstant.XT_ONLINE_USERLIST); 
		     }
		} catch (Exception e) {
		}
		try {
		     Iterator iterator = OnLinesessionlistener.getSet(); 
		     while (iterator.hasNext()) { 
		    	 HttpSession se = (HttpSession) iterator.next();
		    	 if(null != se){
	    			 se.invalidate();
		    	 }
		     }
		     return outAudStr(true, "剔除成功");
		} catch (Exception e) {
			return outAudStr(false, "剔除失败");
		}
	}
	
	/**
	 * 剔除指定人
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/removeXtOnLineUser",method={RequestMethod.POST,RequestMethod.GET})
	public String removeXtOnLineUser(HttpServletRequest request,String sessionId){
		String[] sessionIdList = sessionId.split(",");
		HttpSession session = request.getSession(); 
	    ServletContext application = session.getServletContext();
	    List<BaseHttpSessionEntity> baseHttpSessionEntityList = (List<BaseHttpSessionEntity>) application.getAttribute(SessionConstant.XT_ONLINE_USERLIST);  
		for(String sessionIds:sessionIdList){
		     for(int i = 0; i < baseHttpSessionEntityList.size(); i++){
		    	 if(baseHttpSessionEntityList.get(i).getSESSION_ID().equals(sessionIds)){
		    		 Iterator<HttpSession> iterator = OnLinesessionlistener.getSet(); 
				     while (iterator.hasNext()) { 
				    	 HttpSession se = (HttpSession) iterator.next();
				    	 if(se.getId().equals(sessionIds)){
				    		 se.invalidate();
				    		 break;
				    	 }
				     }
				     baseHttpSessionEntityList.remove(i);
		    		 i--;
		    	 }
		     }
		}
		return outAudStr(true, "剔除成功");
	}
	
	/**
	 * 时时显示在线人数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/getXaOnLineUser",method={RequestMethod.POST,RequestMethod.GET})
	public String getXaOnLineUser(HttpServletRequest request){
		try {
			//判断会话是否失效
	    	if(request.isRequestedSessionIdValid()){
	    		HttpSession session = request.getSession(); 
				ServletContext application = session.getServletContext();  
				List<XtUserinfo> xtUserInfoList =(List<XtUserinfo>)application.getAttribute(SessionConstant.XT_ONLINE_USERLIST);
				String xtOnLineUserCount = ""+xtUserInfoList.size();
				return outAudStr(true, xtOnLineUserCount);
	    	}else{
	    		return outAudStr(false, "0");
	    	}
		} catch (Exception e) {
			return outAudStr(false, "0");
		}
	}
}
