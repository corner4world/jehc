package jehc.xtmodules.xtweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.annotation.AuthUneedLogin;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseTreeEntity;
import jehc.xtmodules.xtcore.md5.MD5;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.IndexTree;
import jehc.xtmodules.xtcore.util.SortList;
import jehc.xtmodules.xtcore.util.constant.SessionConstant;
import jehc.xtmodules.xtmodel.XtMenuinfo;
import jehc.xtmodules.xtmodel.XtUserinfo;
import jehc.xtmodules.xtservice.XtKnowledgeService;
import jehc.xtmodules.xtservice.XtKwordsService;
import jehc.xtmodules.xtservice.XtLoginLogsService;
import jehc.xtmodules.xtservice.XtMenuinfoService;
import jehc.xtmodules.xtservice.XtNoticeService;
import jehc.xtmodules.xtservice.XtUserinfoService;

/**
 * 加载首页
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/index")
@Scope("prototype")
public class XtIndexController extends BaseAction{
	@Autowired
	private XtMenuinfoService xtMenuinfoService;
	@Autowired
	private XtUserinfoService xtUserinfoService;
	/**
	 * 载入初始化页面
	 * @param xt_Menuinfo
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/index.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadIndex(Model model,HttpServletRequest request) {
		Map<String, Object> condition = new HashMap<String, Object>();
		commonM(condition,request);
		List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinListForRole(condition);
		SortList<XtMenuinfo> sortList = new SortList<XtMenuinfo>();
		sortList.Sort(xtMenuinfoList, "xt_menuinfo_sort", "asc");
		IndexTree tree = new IndexTree(xtMenuinfoList);  
        model.addAttribute("MenuList", tree.buildTree());
		/*邓纯杰 注释 
		List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinfoRootForRole(condition);
		SortList<XtMenuinfo> sortList = new SortList<XtMenuinfo>();
		sortList.Sort(xtMenuinfoList, "xt_menuinfo_sort", "asc");
		StringBuffer msg = new StringBuffer("[");  
		for(int i = 0; i < xtMenuinfoList.size(); i++){
			XtMenuinfo xtMenuinfo = xtMenuinfoList.get(i);
			if(i==(xtMenuinfoList.size()-1)){
				msg.append("{id:'"+xtMenuinfo.getXt_menuinfo_id()+"',text:'"+xtMenuinfo.getXt_menuinfo_title()+"',icon:'../deng/images/icons/"+xtMenuinfo.getXt_menuinfo_images()+"'}");  
			}else{
				msg.append("{id:'"+xtMenuinfo.getXt_menuinfo_id()+"',text:'"+xtMenuinfo.getXt_menuinfo_title()+"',icon:'../deng/images/icons/"+xtMenuinfo.getXt_menuinfo_images()+"'},");
			}
		}
		msg.append("]");
		request.setAttribute("msg", msg.toString());
		*/
		return new ModelAndView("pc/xt-view/xt-index/xt-index");
	}
	
	
	/**
	 * 加载主页菜单
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value="/index_menu.html",method={RequestMethod.POST,RequestMethod.GET})
	public String loadIndexMenu(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> condition = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(null == id || "".equals(id)){
			commonM(condition,request);
			List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinfoRootForRole(condition);
			SortList<XtMenuinfo> sortList = new SortList<XtMenuinfo>();
			sortList.Sort(xtMenuinfoList, "xt_menuinfo_sort", "asc");
			StringBuffer msg = new StringBuffer("[");  
			for(int i = 0; i < xtMenuinfoList.size(); i++){
				XtMenuinfo xtMenuinfo = xtMenuinfoList.get(i);
				String leaf = "false";
				if(i==(xtMenuinfoList.size()-1)){
					if(xtMenuinfo.getXt_menuinfo_leaf().equals("0")){
						leaf = "false";
					}else{
						leaf = "true";
					}
					msg.append("{id:'"+xtMenuinfo.getXt_menuinfo_id()+"',text:'"+xtMenuinfo.getXt_menuinfo_title()+"',leaf:"+leaf+",icon:'../deng/images/icons/"+xtMenuinfo.getXt_menuinfo_images()+"'}");  
				}else{
					if(xtMenuinfo.getXt_menuinfo_leaf().equals("0")){
						leaf = "false";
					}else{
						leaf = "true";
					}
					msg.append("{id:'"+xtMenuinfo.getXt_menuinfo_id()+"',text:'"+xtMenuinfo.getXt_menuinfo_title()+"',leaf:"+leaf+",icon:'../deng/images/icons/"+xtMenuinfo.getXt_menuinfo_images()+"'},");
				}
			}
			msg.append("]");
			return outRootStr(msg.toString());
		}else{
			condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", id);
			commonM(condition,request);
			List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinfoChildForRole(condition);
			SortList<XtMenuinfo> sortList = new SortList<XtMenuinfo>();
			sortList.Sort(xtMenuinfoList, "xt_menuinfo_sort", "asc");
			StringBuffer msg = new StringBuffer("[");  
			for(int i = 0; i < xtMenuinfoList.size(); i++){
				XtMenuinfo xtMenuinfo = xtMenuinfoList.get(i);
				String leaf = "false";
				if(i==(xtMenuinfoList.size()-1)){
					if(xtMenuinfo.getXt_menuinfo_leaf().equals("0")){
						leaf = "false";
					}else{
						leaf = "true";
					}
					msg.append("{id:'"+xtMenuinfo.getXt_menuinfo_id()+"',text:'"+xtMenuinfo.getXt_menuinfo_title()+"',leaf:"+leaf+",url:'"+xtMenuinfo.getXt_menuinfo_url()+"',icon:'../deng/images/icons/"+xtMenuinfo.getXt_menuinfo_images()+"'}");  
				}else{
					if(xtMenuinfo.getXt_menuinfo_leaf().equals("0")){
						leaf = "false";
					}else{
						leaf = "true";
					}
					msg.append("{id:'"+xtMenuinfo.getXt_menuinfo_id()+"',text:'"+xtMenuinfo.getXt_menuinfo_title()+"',leaf:"+leaf+",url:'"+xtMenuinfo.getXt_menuinfo_url()+"',icon:'../deng/images/icons/"+xtMenuinfo.getXt_menuinfo_images()+"'},");  
				}
			}
			msg.append("]");
			return outStr(msg.toString());
		}
	}
	
	/**
	 * 封装通过
	 * @param condition
	 */
	public void commonM(Map<String, Object> condition,HttpServletRequest request){
		if(!CommonUtils.isAdmin()){
			String xt_role_id = (String)request.getSession(false).getAttribute("xt_role_id");
			condition.put("xt_role_id", xt_role_id.split(","));
		}
	}
	
	@Autowired
	private XtNoticeService xtNoticeService;
	@Autowired
	private XtLoginLogsService xtLoginLogsService;
	@Autowired
	private XtKnowledgeService xtKnowledgeService;
	/**
	 * 载入桌面
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/desk.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadDesk(HttpServletRequest request,Model model) {
		Map<String,Object> condition = new HashMap<String,Object>();
		//在线用户
		HttpSession se = request.getSession(); 
		ServletContext application = se.getServletContext();  
		List<XtUserinfo> xtUserInfoList =((List<XtUserinfo>)application.getAttribute(SessionConstant.XT_ONLINE_USERLIST));
		if(null != xtUserInfoList && !xtUserInfoList.isEmpty() && xtUserInfoList.size() > 0){
			model.addAttribute("xtOnlineUserCount", xtUserInfoList.size());
		}
		//公告数
		model.addAttribute("xtNoticeCount", xtNoticeService.getXtNoticeCountByCondition(condition));
		model.addAttribute("xtNoticeList", xtNoticeService.getXtNoticeListByCondition(condition));
		//个人登录次数
		condition.put("xt_userinfo_id", getXtUid());
		model.addAttribute("xtLoginLogsCount", xtLoginLogsService.getXtLoginLogsCount(condition));
		//平台知识库数
		model.addAttribute("xtKnowledgeCount", xtKnowledgeService.getXtKnowledgeCount(condition));
		return new ModelAndView("pc/xt-view/xt-desk/xt-desk");
	}
	
	
	/**
	 * 载入异常页面
	 * @param request
	 * @return
	 */
	@AuthUneedLogin
	@RequestMapping(value="/error.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadError(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-error/xt-error");
	} 
	
	
	/**
	 * 载入忘记密码即找回密码页面
	 * @param request
	 * @return
	 */
	@AuthUneedLogin
	@RequestMapping(value="/forget_pwd.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadForgetPwd(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-forgetpwd/xt-forgetpwd");
	} 
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updatePwd",method={RequestMethod.POST,RequestMethod.GET})
	public String updatePwd(HttpServletRequest request){
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		XtUserinfo xtUserinfo = CommonUtils.getXtU();
		MD5 md5 = new MD5();
		if(null != oldPwd && !"".equals(oldPwd)){
			oldPwd = md5.getMD5ofStr(oldPwd.trim());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_userinfo_passWord", oldPwd);
			condition.put("xt_userinfo_name", xtUserinfo.getXt_userinfo_name());
			if(null != xtUserinfoService.getXtUserinfoByUP(condition)){
				condition = new HashMap<String, Object>();
				condition.put("xt_userinfo_passWord", md5.getMD5ofStr(newPwd.trim()));
				condition.put("xt_userinfo_id", xtUserinfo.getXt_userinfo_id());
				int i = xtUserinfoService.updatePwd(condition);
				if(i > 0){
					request.getSession(false).invalidate();
					return outAudStr(true, "密码修改成功,请重新登录系统!");
				}else{
					return outAudStr(false, "密码修改失败!");
				}
			}else{
				return outAudStr(false, "原密码错误!");
			}
		}
		return outAudStr(false, "原密码为空!");
	}
	
	/**
	 * 解锁
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/validatePassword",method={RequestMethod.POST,RequestMethod.GET})
	public String validatePassword(HttpServletRequest request){
		String password = request.getParameter("password");
		XtUserinfo xtUserinfo = CommonUtils.getXtU();
		MD5 md5 = new MD5();
		password = md5.getMD5ofStr(password.trim());
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("xt_userinfo_passWord", password);
		condition.put("xt_userinfo_name", xtUserinfo.getXt_userinfo_name());
		if(null != xtUserinfoService.getXtUserinfoByUP(condition)){
			return outAudStr(true,"1");
		}else{
			return outAudStr(true,"2");
		}
	}
	
	/**
	 * 失效页面处理
	 * @param request
	 */
	@AuthUneedLogin
	@RequestMapping(value="/sessionout.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reqsessionout(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-session/xt-session");
	}
	
	/**
	 * 发送至无操作权限页面
	 * @param request
	 */
	@AuthUneedLogin
	@RequestMapping(value="/have_no_permission.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reqNoRolePage(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-no-role/xt-no-role");
	}
	
	/**
	 * 日历页面
	 * @param request
	 */
	@AuthUneedLogin
	@RequestMapping(value="/perpetualcalendar",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView perpetualcalendar(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-perpetualcalendar/xt-perpetualcalendar");
	}
}
