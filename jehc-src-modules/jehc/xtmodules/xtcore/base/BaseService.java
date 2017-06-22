package jehc.xtmodules.xtcore.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.Log4jUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtdao.Xt_Data_AuthorityDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_DefaultDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_DepartDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_PostDao;
import jehc.xtmodules.xtdao.Xt_UserinfoDao;
import jehc.xtmodules.xtmodel.Xt_Data_Authority;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Default;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Depart;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Post;
import jehc.xtmodules.xtmodel.Xt_Operate_Business_Logs;
import jehc.xtmodules.xtmodel.Xt_Userinfo;
import jehc.xtmodules.xtservice.Xt_Operate_Business_LogsService;

/**
 * service父类支持
 * @author邓纯杰
 *
 */
public class BaseService extends Log4jUtil{
	@Autowired
	private Xt_Operate_Business_LogsService xt_Operate_Business_LogsService;
	@Autowired
	private Xt_Data_Authority_DepartDao xt_Data_Authority_DepartDao;
	@Autowired
	private Xt_Data_Authority_PostDao xt_Data_Authority_PostDao;
	@Autowired
	private Xt_Data_Authority_DefaultDao xt_Data_Authority_DefaultDao;
	@Autowired
	private Xt_UserinfoDao xt_UserinfoDao;
	@Autowired
	private Xt_Data_AuthorityDao xt_Data_AuthorityDao;
	/**
	 * 添加平台业务操作日志通用 采用put方法目的不走事务控制
	 * @param classname
	 * @param method
	 * @param message
	 */
	public void aBLogs(String classname,String method,String message){
		Xt_Operate_Business_Logs xt_Operate_Business_Logs = new Xt_Operate_Business_Logs();
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
		Xt_Operate_Business_Logs xt_Operate_Business_Logs = new Xt_Operate_Business_Logs();
		xt_Operate_Business_Logs.setXt_operate_business_logsTime(CommonUtils.getSimpleDateFormat());
		xt_Operate_Business_Logs.setXt_operate_business_logs_id(UUID.toUUID());
		xt_Operate_Business_Logs.setXt_operate_business_logsModules(classname);
		xt_Operate_Business_Logs.setXt_operate_business_logsMethod(method);
		xt_Operate_Business_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
		xt_Operate_Business_Logs.setXt_operate_business_logsResult(message);
		xt_Operate_Business_Logs.setXt_operate_business_logsMethodPar(parm);
		/**
        xt_Operate_Business_LogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
        **/
		//需要走异步操作 为了不影响性能
		new BaseXtOperateBusinessLogsRun(xt_Operate_Business_Logs).run();
	}
	
	/**
	 * 统一推送数据权限至执行表中
	 */
	public void addPushDataAuthority(){
		//1推送默认（初始化数据权限）
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Xt_Data_Authority_Default> defaultList = xt_Data_Authority_DefaultDao.getXtDataAuthorityDefaultListByCondition(condition);
		List<Xt_Userinfo> userinfoList = xt_UserinfoDao.getXtUserinfoListByCondition(condition);
		List<Xt_Data_Authority> xt_Data_Authority_List = new ArrayList<Xt_Data_Authority>();
		for(Xt_Data_Authority_Default def:defaultList){
			for(Xt_Userinfo user:userinfoList){
				Xt_Data_Authority xt_Data_Authority = new Xt_Data_Authority();
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
			xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
		}
		if(null != xt_Data_Authority_List && !xt_Data_Authority_List.isEmpty()){
			xt_Data_AuthorityDao.addBatchXtDataAuthority(xt_Data_Authority_List);
		}
		
		//2推送部门人员
		condition = new HashMap<String, Object>();
		List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList = xt_Data_Authority_DepartDao.getXtDataAuthorityDepartListByCondition(condition);
		xt_Data_Authority_List = new ArrayList<Xt_Data_Authority>();
		for(Xt_Data_Authority_Depart xt_Data_Authority_Depart: xt_Data_Authority_DepartList){
			condition = new HashMap<String, Object>();
			//2.1获取被拥有部门下的所有用户
			condition.put("xt_departinfo_id",xt_Data_Authority_Depart.getXtDID());
			List<Xt_Userinfo> departUserinfoList = xt_UserinfoDao.getXtUserinfoListAllByCondition(condition);
			//2.2获取拥有者部门下的所有用户
			condition = new HashMap<String, Object>();
			condition.put("xt_departinfo_id",xt_Data_Authority_Depart.getXt_departinfo_id());
			List<Xt_Userinfo> departinfoUserinfoList = xt_UserinfoDao.getXtUserinfoListAllByCondition(condition);
			
			//2.3先删除 后添加
			condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_Data_Authority_Depart.getXt_menuinfo_id());
			condition.put("xt_data_authorityType", "2");
			xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
			
			//2.4组装数据
			for(Xt_Userinfo user:departUserinfoList){
				for(Xt_Userinfo departinfoUserinfo:departinfoUserinfoList){
					Xt_Data_Authority xt_Data_Authority = new Xt_Data_Authority();
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
			xt_Data_AuthorityDao.addBatchXtDataAuthority(xt_Data_Authority_List);
		}
		
		//3推送岗位
		condition = new HashMap<String, Object>();
		List<Xt_Data_Authority_Post> xt_Data_Authority_PostList = xt_Data_Authority_PostDao.getXtDataAuthorityPostListByCondition(condition);
		xt_Data_Authority_List = new ArrayList<Xt_Data_Authority>();
		for(Xt_Data_Authority_Post xt_Data_Authority_Post:xt_Data_Authority_PostList){
			condition = new HashMap<String, Object>();
			//3.1获取被拥有岗位下的所有用户
			condition.put("xt_post_id",xt_Data_Authority_Post.getXtPID());
			List<Xt_Userinfo> postUserinfoList = xt_UserinfoDao.getXtUserinfoListAllByCondition(condition);
			
			//3.2获取拥有者部门下的所有用户
			condition = new HashMap<String, Object>();
			condition.put("xt_post_id",xt_Data_Authority_Post.getXt_post_id());
			List<Xt_Userinfo> postinfoUserinfoList = xt_UserinfoDao.getXtUserinfoListAllByCondition(condition);
			
			//3.3先删除 后添加
			condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_Data_Authority_Post.getXt_menuinfo_id());
			condition.put("xt_data_authorityType", "3");
			xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
			
			//3.4组装数据
			for(Xt_Userinfo user:postUserinfoList){
				for(Xt_Userinfo postUserinfo:postinfoUserinfoList){
					Xt_Data_Authority xt_Data_Authority = new Xt_Data_Authority();
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
			xt_Data_AuthorityDao.addBatchXtDataAuthority(xt_Data_Authority_List);
		}
	}
}
