package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtDataAuthorityDefaultDao;
import jehc.xtmodules.xtmodel.XtDataAuthorityDefault;

/**
* 数权限初始化默认设置 
* 2017-06-20 14:38:52  邓纯杰
*/
@Repository("xtDataAuthorityDefaultDao")
public class XtDataAuthorityDefaultDaoImpl  extends BaseDaoImpl implements XtDataAuthorityDefaultDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtDataAuthorityDefault> getXtDataAuthorityDefaultListByCondition(Map<String,Object> condition){
		return (List<XtDataAuthorityDefault>)this.getList("getXtDataAuthorityDefaultListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_data_authority_default_id 
	* @return
	*/
	public XtDataAuthorityDefault getXtDataAuthorityDefaultById(String xt_data_authority_default_id){
		return (XtDataAuthorityDefault)this.get("getXtDataAuthorityDefaultById", xt_data_authority_default_id);
	}
	/**
	* 添加
	* @param xt_data_authority_default 
	* @return
	*/
	public int addXtDataAuthorityDefault(XtDataAuthorityDefault xt_Data_Authority_Default){
		return this.add("addXtDataAuthorityDefault", xt_Data_Authority_Default);
	}
	/**
	* 修改
	* @param xt_data_authority_default 
	* @return
	*/
	public int updateXtDataAuthorityDefault(XtDataAuthorityDefault xt_Data_Authority_Default){
		return this.update("updateXtDataAuthorityDefault", xt_Data_Authority_Default);
	}
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_default 
	* @return
	*/
	public int updateXtDataAuthorityDefaultBySelective(XtDataAuthorityDefault xt_Data_Authority_Default){
		return this.update("updateXtDataAuthorityDefaultBySelective", xt_Data_Authority_Default);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthorityDefault(Map<String,Object> condition){
		return this.del("delXtDataAuthorityDefault", condition);
	}
	/**
	 * 根据情况删除
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityDefaultAllByCondition(Map<String,Object> condition){
		return this.del("delXtDataAuthorityDefaultAllByCondition", condition);
	}
	/**
	* 批量添加
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int addBatchXtDataAuthorityDefault(List<XtDataAuthorityDefault> xt_Data_Authority_DefaultList){
		return this.add("addBatchXtDataAuthorityDefault", xt_Data_Authority_DefaultList);
	}
	/**
	* 批量修改
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDefault(List<XtDataAuthorityDefault> xt_Data_Authority_DefaultList){
		return this.update("updateBatchXtDataAuthorityDefault", xt_Data_Authority_DefaultList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDefaultBySelective(List<XtDataAuthorityDefault> xt_Data_Authority_DefaultList){
		return this.update("updateBatchXtDataAuthorityDefaultBySelective", xt_Data_Authority_DefaultList);
	}
}
