package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_U_RDao;
import jehc.xtmodules.xtmodel.Xt_U_R;
import jehc.xtmodules.xtmodel.Xt_Userinfo;

/**
* 用户角色分配表; InnoDB free: 6144 kB 
* 2015-08-04 16:25:07  邓纯杰
*/
@Repository("xt_U_RDao")
public class Xt_U_RDaoImpl  extends BaseDaoImpl implements Xt_U_RDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Userinfo> getXtURListByCondition(Map<String,Object> condition){
		return (List<Xt_Userinfo>)this.getList("getXtURListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_u_r_id 
	* @return
	*/
	public Xt_U_R getXtURById(String xt_u_r_id){
		return (Xt_U_R)this.get("getXtURById", xt_u_r_id);
	}
	/**
	* 添加
	* @param xt_u_r 
	* @return
	*/
	public int addXtUR(Xt_U_R xt_U_R){
		return this.add("addXtUR", xt_U_R);
	}
	/**
	* 修改
	* @param xt_u_r 
	* @return
	*/
	public int updateXtUR(Xt_U_R xt_U_R){
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
	public List<Xt_U_R> getXtURList(Map<String,Object> condition){
		return (List<Xt_U_R>)this.getList("getXtURList",condition);
	}
	
	/**
	 * 根据用户编号查找角色权限集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_U_R> getXtRoleinfoListByUserinfoId(Map<String,Object> condition){
		return (List<Xt_U_R>)this.getList("getXtRoleinfoListByUserinfoId",condition);
	}
}
