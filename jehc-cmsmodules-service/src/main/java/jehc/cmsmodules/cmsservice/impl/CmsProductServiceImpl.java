package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsProductService;
import jehc.cmsmodules.cmsdao.CmsProductDao;
import jehc.cmsmodules.cmsmodel.CmsProduct;

/**
* 内容发布平台 产品 
* 2018-06-10 15:05:11  邓纯杰
*/
@Service("cmsProductService")
public class CmsProductServiceImpl extends BaseService implements CmsProductService{
	@Autowired
	private CmsProductDao cmsProductDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsProduct> getCmsProductListByCondition(Map<String,Object> condition){
		try{
			return cmsProductDao.getCmsProductListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_product_id 
	* @return
	*/
	public CmsProduct getCmsProductById(String cms_product_id){
		try{
			CmsProduct cmsProduct = cmsProductDao.getCmsProductById(cms_product_id);
			return cmsProduct;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_product 
	* @return
	*/
	public int addCmsProduct(CmsProduct cmsProduct){
		int i = 0;
		try {
			i = cmsProductDao.addCmsProduct(cmsProduct);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_product 
	* @return
	*/
	public int updateCmsProduct(CmsProduct cmsProduct){
		int i = 0;
		try {
			i = cmsProductDao.updateCmsProduct(cmsProduct);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_product 
	* @return
	*/
	public int updateCmsProductBySelective(CmsProduct cmsProduct){
		int i = 0;
		try {
			i = cmsProductDao.updateCmsProductBySelective(cmsProduct);
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
	public int delCmsProduct(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsProductDao.delCmsProduct(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_productList 
	* @return
	*/
	public int addBatchCmsProduct(List<CmsProduct> cmsProductList){
		int i = 0;
		try {
			i = cmsProductDao.addBatchCmsProduct(cmsProductList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_productList 
	* @return
	*/
	public int updateBatchCmsProduct(List<CmsProduct> cmsProductList){
		int i = 0;
		try {
			i = cmsProductDao.updateBatchCmsProduct(cmsProductList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_productList 
	* @return
	*/
	public int updateBatchCmsProductBySelective(List<CmsProduct> cmsProductList){
		int i = 0;
		try {
			i = cmsProductDao.updateBatchCmsProductBySelective(cmsProductList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
