package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Data_Authority_DepartDao;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Depart;

/**
* 数据权限按部门设置 
* 2017-06-20 14:36:19  邓纯杰
*/
@Repository("xt_Data_Authority_DepartDao")
public class Xt_Data_Authority_DepartDaoImpl  extends BaseDaoImpl implements Xt_Data_Authority_DepartDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Data_Authority_Depart> getXtDataAuthorityDepartListByCondition(Map<String,Object> condition){
		return (List<Xt_Data_Authority_Depart>)this.getList("getXtDataAuthorityDepartListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_data_authority_depart_id 
	* @return
	*/
	public Xt_Data_Authority_Depart getXtDataAuthorityDepartById(String xt_data_authority_depart_id){
		return (Xt_Data_Authority_Depart)this.get("getXtDataAuthorityDepartById", xt_data_authority_depart_id);
	}
	/**
	* 添加
	* @param xt_data_authority_depart 
	* @return
	*/
	public int addXtDataAuthorityDepart(Xt_Data_Authority_Depart xt_Data_Authority_Depart){
		return this.add("addXtDataAuthorityDepart", xt_Data_Authority_Depart);
	}
	/**
	* 修改
	* @param xt_data_authority_depart 
	* @return
	*/
	public int updateXtDataAuthorityDepart(Xt_Data_Authority_Depart xt_Data_Authority_Depart){
		return this.update("updateXtDataAuthorityDepart", xt_Data_Authority_Depart);
	}
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_depart 
	* @return
	*/
	public int updateXtDataAuthorityDepartBySelective(Xt_Data_Authority_Depart xt_Data_Authority_Depart){
		return this.update("updateXtDataAuthorityDepartBySelective", xt_Data_Authority_Depart);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthorityDepart(Map<String,Object> condition){
		return this.del("delXtDataAuthorityDepart", condition);
	}
	/**
	 * 删除集合根据拥有者及菜单编号
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityDepartList(Map<String,Object> condition){
		return this.del("delXtDataAuthorityDepartList", condition);
	}
	/**
	* 批量添加
	* @param xt_data_authority_departList 
	* @return
	*/
	public int addBatchXtDataAuthorityDepart(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList){
		return this.add("addBatchXtDataAuthorityDepart", xt_Data_Authority_DepartList);
	}
	/**
	* 批量修改
	* @param xt_data_authority_departList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDepart(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList){
		return this.update("updateBatchXtDataAuthorityDepart", xt_Data_Authority_DepartList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_departList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDepartBySelective(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList){
		return this.update("updateBatchXtDataAuthorityDepartBySelective", xt_Data_Authority_DepartList);
	}
}
