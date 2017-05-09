package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Roleinfo;

/**
* 用户角色表 
* 2015-05-29 11:08:55  邓纯杰
*/
public interface Xt_RoleinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Roleinfo> getXtRoleinfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_role_id 
	* @return
	*/
	public Xt_Roleinfo getXtRoleinfoById(String xt_role_id);
	/**
	* 添加
	* @param xt_roleinfo 
	* @return
	*/
	public int addXtRoleinfo(Xt_Roleinfo xt_Roleinfo);
	/**
	* 修改
	* @param xt_roleinfo 
	* @return
	*/
	public int updateXtRoleinfo(Xt_Roleinfo xt_Roleinfo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtRoleinfo(Map<String,Object> condition);
	/**
	 * 恢复
	 * @param condition
	 * @return
	 */
	public int recoverXtRoleinfo(Map<String,Object> condition);
}
