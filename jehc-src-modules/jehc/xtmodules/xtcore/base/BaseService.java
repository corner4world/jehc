package jehc.xtmodules.xtcore.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.Log4jUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtdao.XtDataAuthorityDao;
import jehc.xtmodules.xtdao.XtDataAuthorityDefaultDao;
import jehc.xtmodules.xtdao.XtDataAuthorityDepartDao;
import jehc.xtmodules.xtdao.XtDataAuthorityPostDao;
import jehc.xtmodules.xtdao.XtUserinfoDao;
import jehc.xtmodules.xtmodel.XtDataAuthority;
import jehc.xtmodules.xtmodel.XtDataAuthorityDefault;
import jehc.xtmodules.xtmodel.XtDataAuthorityDepart;
import jehc.xtmodules.xtmodel.XtDataAuthorityPost;
import jehc.xtmodules.xtmodel.XtOperateBusinessLogs;
import jehc.xtmodules.xtmodel.XtUserinfo;
import jehc.xtmodules.xtservice.XtOperateBusinessLogsService;

/**
 * service父类支持
 * @author邓纯杰
 *
 */
public class BaseService extends Log4jUtil{
	@Autowired
	private XtOperateBusinessLogsService xtOperateBusinessLogsService;
	/**
	 * 添加平台业务操作日志通用 采用put方法目的不走事务控制
	 * @param classname
	 * @param method
	 * @param message
	 */
	public void aBLogs(String classname,String method,String message){
		XtOperateBusinessLogs xt_Operate_Business_Logs = new XtOperateBusinessLogs();
		xt_Operate_Business_Logs.setXt_operate_business_logsTime(CommonUtils.getSimpleDateFormat());
		xt_Operate_Business_Logs.setXt_operate_business_logs_id(UUID.toUUID());
		xt_Operate_Business_Logs.setXt_operate_business_logsModules(classname);
		xt_Operate_Business_Logs.setXt_operate_business_logsMethod(method);
		xt_Operate_Business_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
		xt_Operate_Business_Logs.setXt_operate_business_logsResult(message);
		/**
        xt_Operate_Business_LogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
        **/
		//需要走异步操作 为了不影响性能
		new BaseXtOperateBusinessLogsRun(xt_Operate_Business_Logs).run();
	}
	/**
	 * 添加平台业务操作日志通用 采用put方法目的不走事务控制
	 * @param classname
	 * @param method
	 * @param message
	 * @param parm
	 */
	public void aBLogs(String classname,String method,String message,String parm){
		XtOperateBusinessLogs xt_Operate_Business_Logs = new XtOperateBusinessLogs();
		xt_Operate_Business_Logs.setXt_operate_business_logsTime(CommonUtils.getSimpleDateFormat());
		xt_Operate_Business_Logs.setXt_operate_business_logs_id(UUID.toUUID());
		xt_Operate_Business_Logs.setXt_operate_business_logsModules(classname);
		xt_Operate_Business_Logs.setXt_operate_business_logsMethod(method);
		xt_Operate_Business_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
		xt_Operate_Business_Logs.setXt_operate_business_logsResult(message);
		xt_Operate_Business_Logs.setXt_operate_business_logsMethodPar(parm);
		/**
        xtOperateBusinessLogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
        **/
		//需要走异步操作 为了不影响性能
		new BaseXtOperateBusinessLogsRun(xt_Operate_Business_Logs).run();
	}
	
	/**
	 * 统一推送数据权限至执行表中
	 */
	public void addPushDataAuthority(){
		XtDataAuthorityDepartDao xtDataAuthorityDepartDao = (XtDataAuthorityDepartDao)SpringUtil.getBean("xtDataAuthorityDepartDao");
		XtDataAuthorityPostDao xtDataAuthorityPostDao = (XtDataAuthorityPostDao)SpringUtil.getBean("xtDataAuthorityPostDao");
		XtDataAuthorityDefaultDao xtDataAuthorityDefaultDao = (XtDataAuthorityDefaultDao)SpringUtil.getBean("xtDataAuthorityDefaultDao");
		XtUserinfoDao xtUserinfoDao = (XtUserinfoDao)SpringUtil.getBean("xtUserinfoDao");
		XtDataAuthorityDao xtDataAuthorityDao = (XtDataAuthorityDao)SpringUtil.getBean("xtDataAuthorityDao");
		//1推送默认（初始化数据权限）
		Map<String, Object> condition = new HashMap<String, Object>();
		List<XtDataAuthorityDefault> defaultList = xtDataAuthorityDefaultDao.getXtDataAuthorityDefaultListByCondition(condition);
		List<XtUserinfo> userinfoList = xtUserinfoDao.getXtUserinfoListByCondition(condition);
		List<XtDataAuthority> xt_Data_Authority_List = new ArrayList<XtDataAuthority>();
		for(XtDataAuthorityDefault def:defaultList){
			for(XtUserinfo user:userinfoList){
				XtDataAuthority xt_Data_Authority = new XtDataAuthority();
				xt_Data_Authority.setXt_data_authority_id(UUID.toUUID());
				xt_Data_Authority.setXt_data_authorityType("4");
				xt_Data_Authority.setXt_functioninfo_id(def.getXt_functioninfo_id());
				xt_Data_Authority.setXt_menuinfo_id(def.getXt_menuinfo_id());
				xt_Data_Authority.setXt_userinfo_id(user.getXt_userinfo_id());
				xt_Data_Authority.setXtUID(user.getXt_userinfo_id());
				xt_Data_Authority_List.add(xt_Data_Authority);
			}
			//先删除 后添加
			condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", def.getXt_menuinfo_id());
			condition.put("xt_data_authorityType", "4");
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
		}
		if(null != xt_Data_Authority_List && !xt_Data_Authority_List.isEmpty()){
			xtDataAuthorityDao.addBatchXtDataAuthority(xt_Data_Authority_List);
		}
		
		//2推送部门人员
		condition = new HashMap<String, Object>();
		List<XtDataAuthorityDepart> xt_Data_Authority_DepartList = xtDataAuthorityDepartDao.getXtDataAuthorityDepartListByCondition(condition);
		xt_Data_Authority_List = new ArrayList<XtDataAuthority>();
		for(XtDataAuthorityDepart xt_Data_Authority_Depart: xt_Data_Authority_DepartList){
			condition = new HashMap<String, Object>();
			//2.1获取被拥有部门下的所有用户
			condition.put("xt_departinfo_id",xt_Data_Authority_Depart.getXtDID());
			List<XtUserinfo> departUserinfoList = xtUserinfoDao.getXtUserinfoListAllByCondition(condition);
			//2.2获取拥有者部门下的所有用户
			condition = new HashMap<String, Object>();
			condition.put("xt_departinfo_id",xt_Data_Authority_Depart.getXt_departinfo_id());
			List<XtUserinfo> departinfoUserinfoList = xtUserinfoDao.getXtUserinfoListAllByCondition(condition);
			
			//2.3先删除 后添加
			condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_Data_Authority_Depart.getXt_menuinfo_id());
			condition.put("xt_data_authorityType", "2");
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			
			//2.4组装数据
			for(XtUserinfo user:departUserinfoList){
				for(XtUserinfo departinfoUserinfo:departinfoUserinfoList){
					XtDataAuthority xt_Data_Authority = new XtDataAuthority();
					xt_Data_Authority.setXt_data_authority_id(UUID.toUUID());
					xt_Data_Authority.setXt_data_authorityType("2");
					xt_Data_Authority.setXt_functioninfo_id(xt_Data_Authority_Depart.getXt_functioninfo_id());
					xt_Data_Authority.setXt_menuinfo_id(xt_Data_Authority_Depart.getXt_menuinfo_id());
					xt_Data_Authority.setXt_userinfo_id(departinfoUserinfo.getXt_userinfo_id());
					xt_Data_Authority.setXtUID(user.getXt_userinfo_id());
					xt_Data_Authority_List.add(xt_Data_Authority);
				}
			}
		}
		if(null != xt_Data_Authority_List && !xt_Data_Authority_List.isEmpty()){
			xtDataAuthorityDao.addBatchXtDataAuthority(xt_Data_Authority_List);
		}
		
		//3推送岗位
		condition = new HashMap<String, Object>();
		List<XtDataAuthorityPost> xt_Data_Authority_PostList = xtDataAuthorityPostDao.getXtDataAuthorityPostListByCondition(condition);
		xt_Data_Authority_List = new ArrayList<XtDataAuthority>();
		for(XtDataAuthorityPost xt_Data_Authority_Post:xt_Data_Authority_PostList){
			condition = new HashMap<String, Object>();
			//3.1获取被拥有岗位下的所有用户
			condition.put("xt_post_id",xt_Data_Authority_Post.getXtPID());
			List<XtUserinfo> postUserinfoList = xtUserinfoDao.getXtUserinfoListAllByCondition(condition);
			
			//3.2获取拥有者部门下的所有用户
			condition = new HashMap<String, Object>();
			condition.put("xt_post_id",xt_Data_Authority_Post.getXt_post_id());
			List<XtUserinfo> postinfoUserinfoList = xtUserinfoDao.getXtUserinfoListAllByCondition(condition);
			
			//3.3先删除 后添加
			condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_Data_Authority_Post.getXt_menuinfo_id());
			condition.put("xt_data_authorityType", "3");
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			
			//3.4组装数据
			for(XtUserinfo user:postUserinfoList){
				for(XtUserinfo postUserinfo:postinfoUserinfoList){
					XtDataAuthority xt_Data_Authority = new XtDataAuthority();
					xt_Data_Authority.setXt_data_authority_id(UUID.toUUID());
					xt_Data_Authority.setXt_data_authorityType("3");
					xt_Data_Authority.setXt_functioninfo_id(xt_Data_Authority_Post.getXt_functioninfo_id());
					xt_Data_Authority.setXt_menuinfo_id(xt_Data_Authority_Post.getXt_menuinfo_id());
					xt_Data_Authority.setXt_userinfo_id(postUserinfo.getXt_userinfo_id());
					xt_Data_Authority.setXtUID(user.getXt_userinfo_id());
					xt_Data_Authority_List.add(xt_Data_Authority);
				}
			}
		}
		if(null != xt_Data_Authority_List && !xt_Data_Authority_List.isEmpty()){
			xtDataAuthorityDao.addBatchXtDataAuthority(xt_Data_Authority_List);
		}
	}
}
