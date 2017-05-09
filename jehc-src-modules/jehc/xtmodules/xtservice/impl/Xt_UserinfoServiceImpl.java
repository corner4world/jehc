package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.BeanCompareUtil;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_UserinfoDao;
import jehc.xtmodules.xtmodel.Xt_Userinfo;
import jehc.xtmodules.xtservice.Xt_UserinfoService;

/**
* 员工信息表 
* 2015-05-12 11:04:35  邓纯杰
*/
@Service("xt_UserinfoService")
public class Xt_UserinfoServiceImpl extends BaseService implements Xt_UserinfoService{
	@Autowired
	private Xt_UserinfoDao xt_UserinfoDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Userinfo> getXtUserinfoListByCondition(Map<String,Object> condition){
		try {
			return xt_UserinfoDao.getXtUserinfoListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_userinfo_id 
	* @return
	*/
	public Xt_Userinfo getXtUserinfoById(String xt_userinfo_id){
		try {
			return xt_UserinfoDao.getXtUserinfoById(xt_userinfo_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_userinfo 
	* @return
	*/
	public int addXtUserinfo(Xt_Userinfo xt_Userinfo){
		int i = 0;
		try {
			i = xt_UserinfoDao.addXtUserinfo(xt_Userinfo);
			aBLogs("用户业务类", "添加", "执行添加操作");
		} catch (Exception e) {
			i = 0;
			aBLogs("用户业务类", "添加", "执行添加操作--失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_userinfo 
	* @return
	*/
	public int updateXtUserinfo(Xt_Userinfo xt_Userinfo){
		int i = 0;
		try {
			Xt_Userinfo before = xt_UserinfoDao.getXtUserinfoById(xt_Userinfo.getXt_userinfo_id());
			i = xt_UserinfoDao.updateXtUserinfo(xt_Userinfo);
			aBLogs("用户业务类", "修改", "执行修改操作");
			//记录字段变更日志
			aRecord(before, xt_Userinfo, "Xt_Userinfo",xt_Userinfo.getXt_userinfo_id());
		} catch (Exception e) {
			i = 0;
			aBLogs("用户业务类", "修改", "执行修改操作--失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtUserinfo(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_UserinfoDao.delXtUserinfo(condition);
			aBLogs("用户业务类", "删除", "执行删除操作");
		} catch (Exception e) {
			i = 0;
			aBLogs("用户业务类", "删除", "执行删除操作--失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 登录
	 * @param condition
	 * @return
	 */
	public Xt_Userinfo getXtUserinfoByUP(Map<String,Object> condition){
		try {
			return xt_UserinfoDao.getXtUserinfoByUP(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 读取所有用户根据各种情况非分页
	 * @param condition
	 * @return
	 */
	public List<Xt_Userinfo> getXtUserinfoListAllByCondition(Map<String,Object> condition){
		try {
			return xt_UserinfoDao.getXtUserinfoListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 修改密码
	 * @param condition
	 * @return
	 */
	public int updatePwd(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_UserinfoDao.updatePwd(condition);
			aBLogs("用户业务类", "修改密码", "执行修改密码操作");
		} catch (Exception e) {
			i = 0;
			aBLogs("用户业务类", "修改密码", "执行修改密码操作--失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 验证用户名是否存在
	 * @return
	 */
	public int validateUser(Map<String,Object> condition){
		try {
			return xt_UserinfoDao.validateUser(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 已删除用户
	 * @param condition
	 * @return
	 */
	public List<Xt_Userinfo> getXtUserinfoDeletedListByCondition(Map<String,Object> condition){
		try {
			return xt_UserinfoDao.getXtUserinfoDeletedListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 恢复用户
	 * @param condition
	 * @return
	 */
	public int recoverXtUserinfo(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_UserinfoDao.recoverXtUserinfo(condition);
			aBLogs("用户业务类", "恢复用户", "执行恢复用户操作");
		} catch (Exception e) {
			i = 0;
			aBLogs("用户业务类", "恢复用户", "执行恢复用户操作--失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理人 发起人 发起人组等使用）
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Userinfo> getXtUserinfoList(Map<String,Object> condition){
		return xt_UserinfoDao.getXtUserinfoList(condition);
	}
}
