package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BSellerProductDao;
import jehc.bmodules.bmodel.BSellerProduct;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 卖家商品 
* 2016-02-18 17:20:35  邓纯杰
*/
@Repository("bSellerProductDao")
public class BSellerProductDaoImpl  extends BaseDaoImpl implements BSellerProductDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BSellerProduct> getBSellerProductListByCondition(Map<String,Object> condition){
		return (List<BSellerProduct>)this.getList("getBSellerProductListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_seller_product_id 
	* @return
	*/
	public BSellerProduct getBSellerProductById(String b_seller_product_id){
		return (BSellerProduct)this.get("getBSellerProductById", b_seller_product_id);
	}
	/**
	* 添加
	* @param b_seller_product 
	* @return
	*/
	public int addBSellerProduct(BSellerProduct b_Seller_Product){
		return this.add("addBSellerProduct", b_Seller_Product);
	}
	/**
	* 修改
	* @param b_seller_product 
	* @return
	*/
	public int updateBSellerProduct(BSellerProduct b_Seller_Product){
		return this.update("updateBSellerProduct", b_Seller_Product);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerProduct(Map<String,Object> condition){
		return this.del("delBSellerProduct", condition);
	}
	
	/**
	 * 查询库存使用商户商品并分页
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BSellerProduct> getBSellerProductStockListByCondition(Map<String,Object> condition){
		return (List<BSellerProduct>)this.getList("getBSellerProductStockListByCondition",condition);
	}
	
	/**
	* 查询库存使用商户商品并分页统计
	* @param condition 
	* @return
	*/
	public int getBSellerProductStockListCountByCondition(Map<String,Object> condition){
		return new Integer(this.get("getBSellerProductStockListCountByCondition",condition).toString());
	}
}
