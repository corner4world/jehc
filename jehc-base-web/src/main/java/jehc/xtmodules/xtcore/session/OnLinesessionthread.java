package jehc.xtmodules.xtcore.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import jehc.xtmodules.xtcore.base.BaseHttpSessionEntity;
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
	private BaseHttpSessionEntity baseHttpSessionEntity;/**平台SESSION**/
	private HttpSession session;/**session**/
	
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
	 * @param baseHttpSessionEntity
	 * @param session
	 */
	public OnLinesessionthread(BaseHttpSessionEntity baseHttpSessionEntity,HttpSession session){
		this.baseHttpSessionEntity = baseHttpSessionEntity;
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
		List<BaseHttpSessionEntity> baseHttpSessionEntityList = (List<BaseHttpSessionEntity>)application.getAttribute(SessionConstant.XT_ONLINE_USERLIST);  
		// 第一次使用前，需要初始化  
		if (null == baseHttpSessionEntityList || baseHttpSessionEntityList.isEmpty() ||baseHttpSessionEntityList.size() == 0) {  
			baseHttpSessionEntityList = new ArrayList<BaseHttpSessionEntity>(); 
			baseHttpSessionEntityList.add(baseHttpSessionEntity);
			application.setAttribute(SessionConstant.XT_ONLINE_USERLIST, baseHttpSessionEntityList); 
		}else{
			if("1".equals(CommonUtils.getXtConstantCache(CacheConstant.ONLINEUSERINFO).getXt_constantValue())){
				for(int i = 0; i < baseHttpSessionEntityList.size(); i++){
					if(baseHttpSessionEntityList.get(i).getXTUSERINFO().getXt_userinfo_id().equals(baseHttpSessionEntity.getXTUSERINFO().getXt_userinfo_id())){
						flag = true;
						baseHttpSessionEntityList.remove(i);
						i--;
						break;
					}
				}
				if(flag == true){
					//干掉上一次的同一用户登录信息
					Iterator<HttpSession> iterator = OnLinesessionlistener.getSet(); 
				     while (iterator.hasNext()) { 
				    	 HttpSession sessions = (HttpSession) iterator.next();
				    	 BaseHttpSessionEntity baseHttpSessionEntitys = (BaseHttpSessionEntity) sessions.getAttribute(SessionConstant.BASE_HTTP_SESSION);
				    	 if(null != baseHttpSessionEntity){
				    		 XtUserinfo xtU = baseHttpSessionEntitys.getXTUSERINFO();
					    	 String userID = baseHttpSessionEntity.getXTUSERINFO().getXt_userinfo_id();
					    	 if(null != xtU && xtU.getXt_userinfo_id().equals(userID)){
					    		 sessions.invalidate();
					    	 }
				    	 }
				     }
				}
				baseHttpSessionEntityList.add(baseHttpSessionEntity); 
			}else{
				//直接添加（不作任何处理 来一个加一个）
				baseHttpSessionEntityList.add(baseHttpSessionEntity); 
			}
		}
	}
}
