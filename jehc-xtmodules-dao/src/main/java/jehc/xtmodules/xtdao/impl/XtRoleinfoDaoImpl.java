package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtRoleinfoDao;
import jehc.xtmodules.xtmodel.XtRoleinfo;

/**
* 用户角色表 
* 2015-05-29 11:08:55  邓纯杰
*/
@Repository("xtRoleinfoDao")
public class XtRoleinfoDaoImpl  extends BaseDaoImpl implements XtRoleinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtRoleinfo> getXtRoleinfoListByCondition(Map<String,Object> condition){
		return (List<XtRoleinfo>)this.getList("getXtRoleinfoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_role_id 
	* @return
	*/
	public XtRoleinfo getXtRoleinfoById(String xt_role_id){
		return (XtRoleinfo)this.get("getXtRoleinfoById", xt_role_id);
	}
	/**
	* 添加
	* @param xt_roleinfo 
	* @return
	*/
	public int addXtRoleinfo(XtRoleinfo xt_Roleinfo){
		return this.add("addXtRoleinfo", xt_Roleinfo);
	}
	/**
	* 修改
	* @param xt_roleinfo 
	* @return
	*/
	public int updateXtRoleinfo(XtRoleinfo xt_Roleinfo){
		return this.update("updateXtRoleinfo", xt_Roleinfo);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtRoleinfo(Map<String,Object> condition){
		return this.del("delXtRoleinfo", condition);
	}
	/**
	 * 恢复
	 * @param condition
	 * @return
	 */
	public int recoverXtRoleinfo(Map<String,Object> condition){
		return this.del("recoverXtRoleinfo", condition);
	}
}
