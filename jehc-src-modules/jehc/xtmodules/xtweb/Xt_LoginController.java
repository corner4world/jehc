package jehc.xtmodules.xtweb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.annotation.AuthUneedLogin;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.md5.MD5;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.Xt_Functioninfo_Right;
import jehc.xtmodules.xtmodel.Xt_Login_Logs;
import jehc.xtmodules.xtmodel.Xt_U_R;
import jehc.xtmodules.xtmodel.Xt_Userinfo;
import jehc.xtmodules.xtservice.Xt_Functioninfo_RightService;
import jehc.xtmodules.xtservice.Xt_Login_LogsService;
import jehc.xtmodules.xtservice.Xt_U_RService;
import jehc.xtmodules.xtservice.Xt_UserinfoService;
/**
 * 登录
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/login")
@Scope("prototype")
public class Xt_LoginController extends BaseAction{
	@Autowired
	private Xt_Login_LogsService xt_Login_LogsService;
	@Autowired
	private Xt_UserinfoService xt_UserinfoService;
	@Autowired
	private Xt_U_RService xt_U_RService;
	@Autowired
	private Xt_Functioninfo_RightService xt_Functioninfo_RightService;
	/**
	 * 载入登录页面
	 * @param request
	 * @return
	 */
	@AuthUneedLogin
	@RequestMapping(value="/login.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLogin(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-login/xt-login");
	}
	/**
	 * 登录
	 * @param request
	 */
	@AuthUneedLogin
	@ResponseBody
	@RequestMapping(value="/login",method={RequestMethod.POST,RequestMethod.GET})
	public String login(HttpServletRequest request){
		int flag = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String code = request.getParameter("validateCode");
		String xt_userinfo_name = request.getParameter("userName");
		String xt_userinfo_passWord = request.getParameter("password");
		MD5 md5 = new MD5();
		//获得的当前正确的验证码
		String rand = (String) request.getSession(false).getAttribute("validateCode");
		StringBuffer sbf = new StringBuffer();
		if((null != code && !"".equals(code))){
			code = code.trim();
			if(null != code && !"".equals(code)){
				code = code.toLowerCase();
				if(null != rand && !"".equals(rand)){
					rand = rand.toLowerCase();
				}
				if(!code.equals(rand)){
					flag = 1;
				}
			}
		}
		if(null != xt_userinfo_name && !"".equals(xt_userinfo_name)){
			xt_userinfo_name = xt_userinfo_name.trim();
			condition.put("xt_userinfo_name", xt_userinfo_name);
		}
		if(null != xt_userinfo_passWord && !"".equals(xt_userinfo_passWord)){
			xt_userinfo_passWord = md5.getMD5ofStr(xt_userinfo_passWord.trim());
			condition.put("xt_userinfo_passWord", xt_userinfo_passWord);
		}
		if(flag != 1){
			Xt_Userinfo xtUserinfo = xt_UserinfoService.getXtUserinfoByUP(condition);
			if(null != xtUserinfo){
				condition = new HashMap<String, Object>();
				condition.put("xt_userinfo_id", xtUserinfo.getXt_userinfo_id());
				List<Xt_U_R> xtURList = xt_U_RService.getXtURList(condition);
				for(int i = 0; i < xtURList.size(); i++){
					Xt_U_R xtUR = xtURList.get(i);
					if(null != sbf && !"".equals(sbf.toString()) && null != (sbf.toString())){
						sbf.append(","+xtUR.getXt_roleinfo_id());
					}else{
						sbf.append(xtUR.getXt_roleinfo_id());
					}
				}
				String xt_role_id = sbf.toString();
				/////////////根据角色集合查找该用户下所有功能
				condition = new HashMap<String, Object>();
				condition.put("xt_role_id", xt_role_id.split(","));
				StringBuffer sbfURL = new StringBuffer();
				StringBuffer sbfMethod = new StringBuffer();
				List<Xt_Functioninfo_Right> xt_Functioninfo_RightList = xt_Functioninfo_RightService.getXtFunctioninfoRightListByCondition(condition);
				for(Xt_Functioninfo_Right xt_Functioninfo_Right:xt_Functioninfo_RightList){
					if(null != sbfURL && !"".equals(sbfURL.toString()) && null != (sbfURL.toString())){
						sbfURL.append(xt_Functioninfo_Right.getXt_functioninfoURL()+",");
					}else{
						sbfURL.append(","+xt_Functioninfo_Right.getXt_functioninfoURL()+",");
					}
					if(null != sbfMethod && !"".equals(sbfMethod.toString()) && null != (sbfMethod.toString())){
						sbfMethod.append(xt_Functioninfo_Right.getXt_functioninfoMethod()+",");
					}else{
						sbfMethod.append(","+xt_Functioninfo_Right.getXt_functioninfoMethod()+",");
					}
				}
				request.getSession(false).setAttribute("xtUserinfo", xtUserinfo);
				request.getSession(false).setAttribute("xt_role_id", xt_role_id);
				request.getSession(false).setAttribute("xt_functioninfoURL", sbfURL.toString());
				request.getSession(false).setAttribute("xt_functioninfoMethod", sbfMethod.toString());
			}else{
				flag = 2;
			}
		}
		//1操作登录日志
		Xt_Login_Logs xt_Login_Logs = new Xt_Login_Logs();
		xt_Login_Logs.setXt_login_log_id(UUID.toUUID());
		xt_Login_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
		if(flag == 0){
			xt_Login_Logs.setXt_login_logContent("登录成功");
		}else if(flag == 1){
			xt_Login_Logs.setXt_login_logContent("验证码输入错误，登录失败!");
		}else if(flag == 2){
			xt_Login_Logs.setXt_login_logContent("用户名或密码，登录失败!");
		}
		xt_Login_Logs.setXt_login_logtime(sdf.format(new Date()));
		xt_Login_Logs.setXt_login_logIP(request.getRemoteAddr());
		
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));  
        Browser browser = userAgent.getBrowser();  
        OperatingSystem os = userAgent.getOperatingSystem();
		xt_Login_Logs.setXt_login_log_browser_name(browser.getName());
		xt_Login_Logs.setXt_login_log_browser_type(browser.getBrowserType().getName());
		xt_Login_Logs.setXt_login_log_system(os.getName());
		if(null != userAgent.getBrowserVersion()){
			xt_Login_Logs.setXt_login_log_browser_version(userAgent.getBrowserVersion().getVersion());
		}
		addXtLoginLogs(xt_Login_Logs);
		if(flag == 0){
			return outAudStr(true, "登陆成功");
		}else if(flag == 1){
			return outAudStr(false, "验证码错误，请重新输入!");
		}else if(flag == 2){
			return outAudStr(false, "用户名或密码错误，请重新输入!");
		}
		return outAudStr(false, "登录失败!");
	}
	
	/**
	 * 添加操作日志
	 * @param xt_Login_Logs
	 */
	public void addXtLoginLogs(Xt_Login_Logs xt_Login_Logs){
		xt_Login_LogsService.addXtLoginLogs(xt_Login_Logs);
	}
	
	/**
	 * 注销
	 * @param request
	 */
	@AuthUneedLogin
	@ResponseBody
	@RequestMapping(value="/loginout",method={RequestMethod.POST,RequestMethod.GET})
	public String loginout(HttpServletRequest request){
		request.getSession(false).invalidate();
		return outAudStr(true, "注销平台成功");
	}
}
