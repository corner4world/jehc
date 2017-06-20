package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Depart;

/**
* 数据权限按部门设置 
* 2017-06-20 14:36:19  邓纯杰
*/
public interface Xt_Data_Authority_DepartService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Data_Authority_Depart> getXtDataAuthorityDepartListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_data_authority_depart_id 
	* @return
	*/
	public Xt_Data_Authority_Depart getXtDataAuthorityDepartById(String xt_data_authority_depart_id);
	/**
	* 添加
	* @param xt_data_authority_depart 
	* @return
	*/
	public int addXtDataAuthorityDepart(Xt_Data_Authority_Depart xt_Data_Authority_Depart);
	/**
	* 修改
	* @param xt_data_authority_depart 
	* @return
	*/
	public int updateXtDataAuthorityDepart(Xt_Data_Authority_Depart xt_Data_Authority_Depart);
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_depart 
	* @return
	*/
	public int updateXtDataAuthorityDepartBySelective(Xt_Data_Authority_Depart xt_Data_Authority_Depart);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthorityDepart(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_data_authority_departList 
	* @return
	*/
	public int addBatchXtDataAuthorityDepart(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList,String xt_departinfo_id,String id,String xt_menuinfo_id);
	/**
	* 批量修改
	* @param xt_data_authority_departList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDepart(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_departList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDepartBySelective(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList);
}
