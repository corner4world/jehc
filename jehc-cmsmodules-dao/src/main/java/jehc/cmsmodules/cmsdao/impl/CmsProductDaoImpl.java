package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsProductDao;
import jehc.cmsmodules.cmsmodel.CmsProduct;

/**
* 内容发布平台 产品 
* 2018-06-10 15:05:11  邓纯杰
*/
@Repository("cmsProductDao")
public class CmsProductDaoImpl  extends BaseDaoImpl implements CmsProductDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsProduct> getCmsProductListByCondition(Map<String,Object> condition){
		return (List<CmsProduct>)this.getList("getCmsProductListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_product_id 
	* @return
	*/
	public CmsProduct getCmsProductById(String cms_product_id){
		return (CmsProduct)this.get("getCmsProductById", cms_product_id);
	}
	/**
	* 添加
	* @param cms_product 
	* @return
	*/
	public int addCmsProduct(CmsProduct cmsProduct){
		return this.add("addCmsProduct", cmsProduct);
	}
	/**
	* 修改
	* @param cms_product 
	* @return
	*/
	public int updateCmsProduct(CmsProduct cmsProduct){
		return this.update("updateCmsProduct", cmsProduct);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_product 
	* @return
	*/
	public int updateCmsProductBySelective(CmsProduct cmsProduct){
		return this.update("updateCmsProductBySelective", cmsProduct);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsProduct(Map<String,Object> condition){
		return this.del("delCmsProduct", condition);
	}
	/**
	* 批量添加
	* @param cms_productList 
	* @return
	*/
	public int addBatchCmsProduct(List<CmsProduct> cmsProductList){
		return this.add("addBatchCmsProduct", cmsProductList);
	}
	/**
	* 批量修改
	* @param cms_productList 
	* @return
	*/
	public int updateBatchCmsProduct(List<CmsProduct> cmsProductList){
		return this.update("updateBatchCmsProduct", cmsProductList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_productList 
	* @return
	*/
	public int updateBatchCmsProductBySelective(List<CmsProduct> cmsProductList){
		return this.update("updateBatchCmsProductBySelective", cmsProductList);
	}
}
