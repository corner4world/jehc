package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtURDao;
import jehc.xtmodules.xtmodel.XtUR;
import jehc.xtmodules.xtmodel.XtUserinfo;
import jehc.xtmodules.xtservice.XtURService;

/**
* 用户角色分配表; InnoDB free: 6144 kB 
* 2015-08-04 16:25:07  邓纯杰
*/
@Service("xtURService")
public class XtURServiceImpl extends BaseService implements XtURService{
	@Autowired
	private XtURDao xtURDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtUserinfo> getXtURListByCondition(Map<String,Object> condition){
		try{
			return xtURDao.getXtURListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_u_r_id 
	* @return
	*/
	public XtUR getXtURById(String xt_u_r_id){
		try{
			return xtURDao.getXtURById(xt_u_r_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_u_r 
	* @return
	*/
	public int addXtUR(List<XtUR> xt_U_RList){
		int i = 0;
		try {
			for(int j = 0; j < xt_U_RList.size(); j++){
				xtURDao.addXtUR(xt_U_RList.get(j));
			}
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_u_r 
	* @return
	*/
	public int updateXtUR(XtUR xt_U_R){
		int i = 0;
		try {
			i = xtURDao.updateXtUR(xt_U_R);
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
	public int delXtUR(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtURDao.delXtUR(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 根据用户ID查找角色
	 * @param condition
	 * @return
	 */
	public List<XtUR> getXtURList(Map<String,Object> condition){
		try{
			return xtURDao.getXtURList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 根据用户编号查找角色权限集合
	 * @param condition
	 * @return
	 */
	public List<XtUR> getXtRoleinfoListByUserinfoId(Map<String,Object> condition){
		try{
			return xtURDao.getXtRoleinfoListByUserinfoId(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
