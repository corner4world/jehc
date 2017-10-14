package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtRoleinfoDao;
import jehc.xtmodules.xtmodel.XtRoleinfo;
import jehc.xtmodules.xtservice.XtRoleinfoService;

/**
* 用户角色表 
* 2015-05-29 11:08:55  邓纯杰
*/
@Service("xtRoleinfoService")
public class XtRoleinfoServiceImpl extends BaseService implements XtRoleinfoService{
	@Autowired
	private XtRoleinfoDao xtRoleinfoDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtRoleinfo> getXtRoleinfoListByCondition(Map<String,Object> condition){
		try {
			return xtRoleinfoDao.getXtRoleinfoListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		
	}
	/**
	* 查询对象
	* @param xt_role_id 
	* @return
	*/
	public XtRoleinfo getXtRoleinfoById(String xt_role_id){
		try {
			return xtRoleinfoDao.getXtRoleinfoById(xt_role_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		
	}
	/**
	* 添加
	* @param xt_roleinfo 
	* @return
	*/
	public int addXtRoleinfo(XtRoleinfo xt_Roleinfo){
		int i = 0;
		try {
			i = xtRoleinfoDao.addXtRoleinfo(xt_Roleinfo);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_roleinfo 
	* @return
	*/
	public int updateXtRoleinfo(XtRoleinfo xt_Roleinfo){
		int i = 0;
		try {
			i = xtRoleinfoDao.updateXtRoleinfo(xt_Roleinfo);
		} catch (Exception e) {
			i = 0;
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
	public int delXtRoleinfo(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtRoleinfoDao.delXtRoleinfo(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 恢复
	 * @param condition
	 * @return
	 */
	public int recoverXtRoleinfo(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtRoleinfoDao.recoverXtRoleinfo(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
