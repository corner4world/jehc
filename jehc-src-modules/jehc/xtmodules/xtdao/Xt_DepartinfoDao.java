package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Departinfo;

/**
* 部门信息表(departInfo) 
* 2015-05-13 15:46:38  邓纯杰
*/
public interface Xt_DepartinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Departinfo> getXtDepartinfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_departinfo_id 
	* @return
	*/
	public Xt_Departinfo getXtDepartinfoById(String xt_departinfo_id);
	/**
	* 添加
	* @param xt_departinfo 
	* @return
	*/
	public int addXtDepartinfo(Xt_Departinfo xt_Departinfo);
	/**
	* 修改
	* @param xt_departinfo 
	* @return
	*/
	public int updateXtDepartinfo(Xt_Departinfo xt_Departinfo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDepartinfo(Map<String,Object> condition);
	
	/**
	 * 部门根目录集合
	 * @return
	 */
	public List<Xt_Departinfo> getXtDepartinfoList();
	
	/**
	 * 查找子集合
	 * @param condition
	 * @return
	 */
	public List<Xt_Departinfo> getXtDepartinfoListChild(Map<String,Object> condition);
	
	/**
	 * 查找所有集合
	 * @param condition
	 * @return
	 */
	public List<Xt_Departinfo> getXtDepartinfoListAll(Map<String,Object> condition);
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理组 发起组等使用）
	 * @param condition
	 * @return
	 */
	public List<Xt_Departinfo> queryXtDepartinfoList(Map<String,Object> condition);
}
