package jehc.cmsmodules.cmsdao;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsProductCategory;

/**
* 内容发布平台产品分类 
* 2018-06-10 15:08:21  邓纯杰
*/
public interface CmsProductCategoryDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsProductCategory> getCmsProductCategoryListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_product_category_id 
	* @return
	*/
	public CmsProductCategory getCmsProductCategoryById(String cms_product_category_id);
	/**
	* 添加
	* @param cms_product_category 
	* @return
	*/
	public int addCmsProductCategory(CmsProductCategory cmsProductCategory);
	/**
	* 修改
	* @param cms_product_category 
	* @return
	*/
	public int updateCmsProductCategory(CmsProductCategory cmsProductCategory);
	/**
	* 修改（根据动态条件）
	* @param cms_product_category 
	* @return
	*/
	public int updateCmsProductCategoryBySelective(CmsProductCategory cmsProductCategory);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsProductCategory(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_product_categoryList 
	* @return
	*/
	public int addBatchCmsProductCategory(List<CmsProductCategory> cmsProductCategoryList);
	/**
	* 批量修改
	* @param cms_product_categoryList 
	* @return
	*/
	public int updateBatchCmsProductCategory(List<CmsProductCategory> cmsProductCategoryList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_product_categoryList 
	* @return
	*/
	public int updateBatchCmsProductCategoryBySelective(List<CmsProductCategory> cmsProductCategoryList);
}
