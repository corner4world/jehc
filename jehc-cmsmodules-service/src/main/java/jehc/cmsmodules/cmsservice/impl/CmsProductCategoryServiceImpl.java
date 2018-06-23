package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsProductCategoryService;
import jehc.cmsmodules.cmsdao.CmsProductCategoryDao;
import jehc.cmsmodules.cmsmodel.CmsProductCategory;

/**
* 内容发布平台产品分类 
* 2018-06-10 15:08:21  邓纯杰
*/
@Service("cmsProductCategoryService")
public class CmsProductCategoryServiceImpl extends BaseService implements CmsProductCategoryService{
	@Autowired
	private CmsProductCategoryDao cmsProductCategoryDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsProductCategory> getCmsProductCategoryListByCondition(Map<String,Object> condition){
		try{
			return cmsProductCategoryDao.getCmsProductCategoryListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_product_category_id 
	* @return
	*/
	public CmsProductCategory getCmsProductCategoryById(String cms_product_category_id){
		try{
			CmsProductCategory cmsProductCategory = cmsProductCategoryDao.getCmsProductCategoryById(cms_product_category_id);
			return cmsProductCategory;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_product_category 
	* @return
	*/
	public int addCmsProductCategory(CmsProductCategory cmsProductCategory){
		int i = 0;
		try {
			i = cmsProductCategoryDao.addCmsProductCategory(cmsProductCategory);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_product_category 
	* @return
	*/
	public int updateCmsProductCategory(CmsProductCategory cmsProductCategory){
		int i = 0;
		try {
			i = cmsProductCategoryDao.updateCmsProductCategory(cmsProductCategory);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_product_category 
	* @return
	*/
	public int updateCmsProductCategoryBySelective(CmsProductCategory cmsProductCategory){
		int i = 0;
		try {
			i = cmsProductCategoryDao.updateCmsProductCategoryBySelective(cmsProductCategory);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsProductCategory(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsProductCategoryDao.delCmsProductCategory(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_product_categoryList 
	* @return
	*/
	public int addBatchCmsProductCategory(List<CmsProductCategory> cmsProductCategoryList){
		int i = 0;
		try {
			i = cmsProductCategoryDao.addBatchCmsProductCategory(cmsProductCategoryList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_product_categoryList 
	* @return
	*/
	public int updateBatchCmsProductCategory(List<CmsProductCategory> cmsProductCategoryList){
		int i = 0;
		try {
			i = cmsProductCategoryDao.updateBatchCmsProductCategory(cmsProductCategoryList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_product_categoryList 
	* @return
	*/
	public int updateBatchCmsProductCategoryBySelective(List<CmsProductCategory> cmsProductCategoryList){
		int i = 0;
		try {
			i = cmsProductCategoryDao.updateBatchCmsProductCategoryBySelective(cmsProductCategoryList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
