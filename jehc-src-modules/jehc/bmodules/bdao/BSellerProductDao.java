package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BSellerProduct;

/**
* 卖家商品 
* 2016-02-18 17:20:35  邓纯杰
*/
public interface BSellerProductDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerProduct> getBSellerProductListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_seller_product_id 
	* @return
	*/
	public BSellerProduct getBSellerProductById(String b_seller_product_id);
	/**
	* 添加
	* @param b_seller_product 
	* @return
	*/
	public int addBSellerProduct(BSellerProduct b_Seller_Product);
	/**
	* 修改
	* @param b_seller_product 
	* @return
	*/
	public int updateBSellerProduct(BSellerProduct b_Seller_Product);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerProduct(Map<String,Object> condition);
	
	/**
	 * 查询库存使用商户商品并分页
	 * @param condition
	 * @return
	 */
	public List<BSellerProduct> getBSellerProductStockListByCondition(Map<String,Object> condition);
	
	/**
	* 查询库存使用商户商品并分页统计
	* @param condition 
	* @return
	*/
	public int getBSellerProductStockListCountByCondition(Map<String,Object> condition);
}
