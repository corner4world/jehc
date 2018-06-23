package jehc.cmsmodules.cmsservice;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsProduct;

/**
* 内容发布平台 产品 
* 2018-06-10 15:05:11  邓纯杰
*/
public interface CmsProductService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsProduct> getCmsProductListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_product_id 
	* @return
	*/
	public CmsProduct getCmsProductById(String cms_product_id);
	/**
	* 添加
	* @param cms_product 
	* @return
	*/
	public int addCmsProduct(CmsProduct cmsProduct);
	/**
	* 修改
	* @param cms_product 
	* @return
	*/
	public int updateCmsProduct(CmsProduct cmsProduct);
	/**
	* 修改（根据动态条件）
	* @param cms_product 
	* @return
	*/
	public int updateCmsProductBySelective(CmsProduct cmsProduct);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsProduct(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_productList 
	* @return
	*/
	public int addBatchCmsProduct(List<CmsProduct> cmsProductList);
	/**
	* 批量修改
	* @param cms_productList 
	* @return
	*/
	public int updateBatchCmsProduct(List<CmsProduct> cmsProductList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_productList 
	* @return
	*/
	public int updateBatchCmsProductBySelective(List<CmsProduct> cmsProductList);
}
