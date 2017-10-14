package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtURDao;
import jehc.xtmodules.xtmodel.XtUR;
import jehc.xtmodules.xtmodel.XtUserinfo;

/**
* 用户角色分配表; InnoDB free: 6144 kB 
* 2015-08-04 16:25:07  邓纯杰
*/
@Repository("xtURDao")
public class XtURDaoImpl  extends BaseDaoImpl implements XtURDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtUserinfo> getXtURListByCondition(Map<String,Object> condition){
		return (List<XtUserinfo>)this.getList("getXtURListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_u_r_id 
	* @return
	*/
	public XtUR getXtURById(String xt_u_r_id){
		return (XtUR)this.get("getXtURById", xt_u_r_id);
	}
	/**
	* 添加
	* @param xt_u_r 
	* @return
	*/
	public int addXtUR(XtUR xt_U_R){
		return this.add("addXtUR", xt_U_R);
	}
	/**
	* 修改
	* @param xt_u_r 
	* @return
	*/
	public int updateXtUR(XtUR xt_U_R){
		return this.update("updateXtUR", xt_U_R);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtUR(Map<String,Object> condition){
		return this.del("delXtUR", condition);
	}
	
	/**
	 * 根据用户ID查找角色
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtUR> getXtURList(Map<String,Object> condition){
		return (List<XtUR>)this.getList("getXtURList",condition);
	}
	
	/**
	 * 根据用户编号查找角色权限集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtUR> getXtRoleinfoListByUserinfoId(Map<String,Object> condition){
		return (List<XtUR>)this.getList("getXtRoleinfoListByUserinfoId",condition);
	}
}
