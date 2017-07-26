package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.XtDataAuthorityDepart;

/**
* 数据权限按部门设置 
* 2017-06-20 14:36:19  邓纯杰
*/
public interface XtDataAuthorityDepartDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDataAuthorityDepart> getXtDataAuthorityDepartListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_data_authority_depart_id 
	* @return
	*/
	public XtDataAuthorityDepart getXtDataAuthorityDepartById(String xt_data_authority_depart_id);
	/**
	* 添加
	* @param xt_data_authority_depart 
	* @return
	*/
	public int addXtDataAuthorityDepart(XtDataAuthorityDepart xt_Data_Authority_Depart);
	/**
	* 修改
	* @param xt_data_authority_depart 
	* @return
	*/
	public int updateXtDataAuthorityDepart(XtDataAuthorityDepart xt_Data_Authority_Depart);
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_depart 
	* @return
	*/
	public int updateXtDataAuthorityDepartBySelective(XtDataAuthorityDepart xt_Data_Authority_Depart);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthorityDepart(Map<String,Object> condition);
	/**
	 * 删除集合根据拥有者及菜单编号
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityDepartList(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_data_authority_departList 
	* @return
	*/
	public int addBatchXtDataAuthorityDepart(List<XtDataAuthorityDepart> xt_Data_Authority_DepartList);
	/**
	* 批量修改
	* @param xt_data_authority_departList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDepart(List<XtDataAuthorityDepart> xt_Data_Authority_DepartList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_departList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDepartBySelective(List<XtDataAuthorityDepart> xt_Data_Authority_DepartList);
}
