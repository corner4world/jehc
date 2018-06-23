package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsProductCategoryDao;
import jehc.cmsmodules.cmsmodel.CmsProductCategory;

/**
* 内容发布平台产品分类 
* 2018-06-10 15:08:21  邓纯杰
*/
@Repository("cmsProductCategoryDao")
public class CmsProductCategoryDaoImpl  extends BaseDaoImpl implements CmsProductCategoryDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsProductCategory> getCmsProductCategoryListByCondition(Map<String,Object> condition){
		return (List<CmsProductCategory>)this.getList("getCmsProductCategoryListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_product_category_id 
	* @return
	*/
	public CmsProductCategory getCmsProductCategoryById(String cms_product_category_id){
		return (CmsProductCategory)this.get("getCmsProductCategoryById", cms_product_category_id);
	}
	/**
	* 添加
	* @param cms_product_category 
	* @return
	*/
	public int addCmsProductCategory(CmsProductCategory cmsProductCategory){
		return this.add("addCmsProductCategory", cmsProductCategory);
	}
	/**
	* 修改
	* @param cms_product_category 
	* @return
	*/
	public int updateCmsProductCategory(CmsProductCategory cmsProductCategory){
		return this.update("updateCmsProductCategory", cmsProductCategory);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_product_category 
	* @return
	*/
	public int updateCmsProductCategoryBySelective(CmsProductCategory cmsProductCategory){
		return this.update("updateCmsProductCategoryBySelective", cmsProductCategory);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsProductCategory(Map<String,Object> condition){
		return this.del("delCmsProductCategory", condition);
	}
	/**
	* 批量添加
	* @param cms_product_categoryList 
	* @return
	*/
	public int addBatchCmsProductCategory(List<CmsProductCategory> cmsProductCategoryList){
		return this.add("addBatchCmsProductCategory", cmsProductCategoryList);
	}
	/**
	* 批量修改
	* @param cms_product_categoryList 
	* @return
	*/
	public int updateBatchCmsProductCategory(List<CmsProductCategory> cmsProductCategoryList){
		return this.update("updateBatchCmsProductCategory", cmsProductCategoryList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_product_categoryList 
	* @return
	*/
	public int updateBatchCmsProductCategoryBySelective(List<CmsProductCategory> cmsProductCategoryList){
		return this.update("updateBatchCmsProductCategoryBySelective", cmsProductCategoryList);
	}
}
