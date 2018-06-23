package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsMerchantsDao;
import jehc.cmsmodules.cmsmodel.CmsMerchants;

/**
* 内容发布平台招商加盟 
* 2018-06-10 14:47:12  邓纯杰
*/
@Repository("cmsMerchantsDao")
public class CmsMerchantsDaoImpl  extends BaseDaoImpl implements CmsMerchantsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsMerchants> getCmsMerchantsListByCondition(Map<String,Object> condition){
		return (List<CmsMerchants>)this.getList("getCmsMerchantsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_merchants_id 
	* @return
	*/
	public CmsMerchants getCmsMerchantsById(String cms_merchants_id){
		return (CmsMerchants)this.get("getCmsMerchantsById", cms_merchants_id);
	}
	/**
	* 添加
	* @param cms_merchants 
	* @return
	*/
	public int addCmsMerchants(CmsMerchants cmsMerchants){
		return this.add("addCmsMerchants", cmsMerchants);
	}
	/**
	* 修改
	* @param cms_merchants 
	* @return
	*/
	public int updateCmsMerchants(CmsMerchants cmsMerchants){
		return this.update("updateCmsMerchants", cmsMerchants);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_merchants 
	* @return
	*/
	public int updateCmsMerchantsBySelective(CmsMerchants cmsMerchants){
		return this.update("updateCmsMerchantsBySelective", cmsMerchants);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsMerchants(Map<String,Object> condition){
		return this.del("delCmsMerchants", condition);
	}
	/**
	* 批量添加
	* @param cms_merchantsList 
	* @return
	*/
	public int addBatchCmsMerchants(List<CmsMerchants> cmsMerchantsList){
		return this.add("addBatchCmsMerchants", cmsMerchantsList);
	}
	/**
	* 批量修改
	* @param cms_merchantsList 
	* @return
	*/
	public int updateBatchCmsMerchants(List<CmsMerchants> cmsMerchantsList){
		return this.update("updateBatchCmsMerchants", cmsMerchantsList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_merchantsList 
	* @return
	*/
	public int updateBatchCmsMerchantsBySelective(List<CmsMerchants> cmsMerchantsList){
		return this.update("updateBatchCmsMerchantsBySelective", cmsMerchantsList);
	}
}
