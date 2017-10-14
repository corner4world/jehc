package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtRoleinfo;

/**
* 用户角色表 
* 2015-05-29 11:08:55  邓纯杰
*/
public interface XtRoleinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtRoleinfo> getXtRoleinfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_role_id 
	* @return
	*/
	public XtRoleinfo getXtRoleinfoById(String xt_role_id);
	/**
	* 添加
	* @param xt_roleinfo 
	* @return
	*/
	public int addXtRoleinfo(XtRoleinfo xt_Roleinfo);
	/**
	* 修改
	* @param xt_roleinfo 
	* @return
	*/
	public int updateXtRoleinfo(XtRoleinfo xt_Roleinfo);
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
