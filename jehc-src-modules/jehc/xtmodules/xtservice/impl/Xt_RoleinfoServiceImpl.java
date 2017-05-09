package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_RoleinfoDao;
import jehc.xtmodules.xtmodel.Xt_Roleinfo;
import jehc.xtmodules.xtservice.Xt_RoleinfoService;

/**
* 用户角色表 
* 2015-05-29 11:08:55  邓纯杰
*/
@Service("xt_RoleinfoService")
public class Xt_RoleinfoServiceImpl extends BaseService implements Xt_RoleinfoService{
	@Autowired
	private Xt_RoleinfoDao xt_RoleinfoDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Roleinfo> getXtRoleinfoListByCondition(Map<String,Object> condition){
		try {
			return xt_RoleinfoDao.getXtRoleinfoListByCondition(condition);
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
	public Xt_Roleinfo getXtRoleinfoById(String xt_role_id){
		try {
			return xt_RoleinfoDao.getXtRoleinfoById(xt_role_id);
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
	public int addXtRoleinfo(Xt_Roleinfo xt_Roleinfo){
		int i = 0;
		try {
			i = xt_RoleinfoDao.addXtRoleinfo(xt_Roleinfo);
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
	public int updateXtRoleinfo(Xt_Roleinfo xt_Roleinfo){
		int i = 0;
		try {
			i = xt_RoleinfoDao.updateXtRoleinfo(xt_Roleinfo);
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
			i = xt_RoleinfoDao.delXtRoleinfo(condition);
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
			i = xt_RoleinfoDao.recoverXtRoleinfo(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
