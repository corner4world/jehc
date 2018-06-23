package jehc.cmsmodules.cmsservice;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsMerchants;

/**
* 内容发布平台招商加盟 
* 2018-06-10 14:47:12  邓纯杰
*/
public interface CmsMerchantsService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsMerchants> getCmsMerchantsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_merchants_id 
	* @return
	*/
	public CmsMerchants getCmsMerchantsById(String cms_merchants_id);
	/**
	* 添加
	* @param cms_merchants 
	* @return
	*/
	public int addCmsMerchants(CmsMerchants cmsMerchants);
	/**
	* 修改
	* @param cms_merchants 
	* @return
	*/
	public int updateCmsMerchants(CmsMerchants cmsMerchants);
	/**
	* 修改（根据动态条件）
	* @param cms_merchants 
	* @return
	*/
	public int updateCmsMerchantsBySelective(CmsMerchants cmsMerchants);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsMerchants(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_merchantsList 
	* @return
	*/
	public int addBatchCmsMerchants(List<CmsMerchants> cmsMerchantsList);
	/**
	* 批量修改
	* @param cms_merchantsList 
	* @return
	*/
	public int updateBatchCmsMerchants(List<CmsMerchants> cmsMerchantsList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_merchantsList 
	* @return
	*/
	public int updateBatchCmsMerchantsBySelective(List<CmsMerchants> cmsMerchantsList);
}
