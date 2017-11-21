package jehc.xtmodules.xtweb;

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
		List<XtUserinfo> saUserInfoList =((List<XtUserinfo>)application.getAttribute(SessionConstant.XT_ONLINE_USERLIST));
		return outItemsStr(saUserInfoList);
	}
	
	
	/**
	 * 移除所有人员
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/removeAllXtOnLineUser",method={RequestMethod.POST,RequestMethod.GET})
	public String removeAllXtOnLineUser(HttpServletRequest request){
		try {
			 HttpSession session = request.getSession(); 
		     ServletContext application = session.getServletContext(); 
		     List<XtUserinfo> onLineUserList = (List<XtUserinfo>) application.getAttribute(SessionConstant.XT_ONLINE_USERLIST);  
		     onLineUserList.clear();
		     Iterator iterator = OnLinesessionlistener.getSet(); 
		     while (iterator.hasNext()) { 
		    	 HttpSession se = (HttpSession) iterator.next();
		    	 try {
		    		 if(null != se && null != se.getAttribute(SessionConstant.XTUSERINFO)){
			    		 se.removeAttribute(SessionConstant.XTUSERINFO);
			    	 }
				} catch (Exception e) {
					//用异常处理类就是为了让while循环继续
					//e.printStackTrace();
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
		for(String sessionIds:sessionIdList){
			HttpSession session = request.getSession(); 
		     ServletContext application = session.getServletContext(); 
		     List<XtUserinfo> onLineUserList = (List<XtUserinfo>) application.getAttribute(SessionConstant.XT_ONLINE_USERLIST);  
		     int number = 1;
		     for(int i = 0; i < onLineUserList.size(); i++){
		    	 if(onLineUserList.get(i).getSessionId().equals(sessionIds)){
		    		 onLineUserList.remove(i);
		    		 Iterator<HttpSession> iterator = OnLinesessionlistener.getSet(); 
				     while (iterator.hasNext()) { 
				    	 HttpSession se = (HttpSession) iterator.next();
				    	 XtUserinfo onlineUser = (XtUserinfo)se.getAttribute(SessionConstant.XTUSERINFO);
				    	 if(null != onlineUser){
				    		 if(onlineUser.getSessionId().equals(sessionIds)) {
				    			 se.removeAttribute(SessionConstant.XTUSERINFO);
				    			 break;
				    		 }
				    	 }
				     }
				     i--;
		    	 }else{
		    		 number++;
		    	 }
		     }
		     if(number == onLineUserList.size()){
		    	 return outAudStr(false, "剔除失败,该用户可能已经退出系统");
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
