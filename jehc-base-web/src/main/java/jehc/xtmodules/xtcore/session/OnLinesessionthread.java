package jehc.xtmodules.xtcore.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.constant.CacheConstant;
import jehc.xtmodules.xtcore.util.constant.SessionConstant;
import jehc.xtmodules.xtmodel.XtUserinfo;

/**
 * session 用户设置
 * @author Administrator
 *
 */
public class OnLinesessionthread extends Thread{
	private XtUserinfo xtUserinfo;/**平台用户**/
	private HttpSession session;/**session**/
	
	public XtUserinfo getXtUserinfo() {
		return xtUserinfo;
	}

	public void setXtUserinfo(XtUserinfo xtUserinfo) {
		this.xtUserinfo = xtUserinfo;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	/**
	 * 无参构造函数
	 */
	public OnLinesessionthread(){}

	/**
	 * 有参构造函数
	 * @param xtUserinfo
	 * @param session
	 */
	public OnLinesessionthread(XtUserinfo xtUserinfo,HttpSession session){
		this.xtUserinfo = xtUserinfo;
		this.session = session;
	}
	public void run(){
		try {
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 统计在线人数（SESSION监控）
	 * @param xtUserinfo
	 */
	@SuppressWarnings("unchecked")
	public void execute(){
		boolean flag = false;
		ServletContext application = session.getServletContext();  
		List<XtUserinfo> onLineUserList = (List<XtUserinfo>)application.getAttribute(SessionConstant.XT_ONLINE_USERLIST);  
		xtUserinfo.setSessionId(session.getId());
		// 第一次使用前，需要初始化  
		if (null == onLineUserList || onLineUserList.isEmpty() ||onLineUserList.size() == 0) {  
			onLineUserList = new ArrayList<XtUserinfo>(); 
			onLineUserList.add(xtUserinfo);
			application.setAttribute(SessionConstant.XT_ONLINE_USERLIST, onLineUserList); 
		}else{
			if("1".equals(CommonUtils.getXtConstantCache(CacheConstant.ONLINEUSERINFO).getXt_constantValue())){
				for(int i = 0; i < onLineUserList.size(); i++){
					if(onLineUserList.get(i).getXt_userinfo_id().equals(xtUserinfo.getXt_userinfo_id())){
						flag = true;
						onLineUserList.remove(i);
						i--;
						break;
					}
				}
				if(flag == true){
					//干掉上一次的同一用户登录信息
					Iterator<HttpSession> iterator = OnLinesessionlistener.getSet(); 
				     while (iterator.hasNext()) { 
				    	 HttpSession sessions = (HttpSession) iterator.next();
				    	 XtUserinfo xtU = (XtUserinfo)sessions.getAttribute(SessionConstant.XTUSERINFO);
				    	 String userID = xtUserinfo.getXt_userinfo_id();
				    	 if(null != xtU && xtU.getXt_userinfo_id().equals(userID)){
				    		 sessions.removeAttribute(SessionConstant.XTUSERINFO);
				    	 }
				     }
				}
				session.setAttribute(SessionConstant.XTUSERINFO, xtUserinfo);
				onLineUserList.add(xtUserinfo); 
			}else{
				//直接添加（不作任何处理 来一个加一个）
				onLineUserList.add(xtUserinfo); 
			}
		}
	}
}
