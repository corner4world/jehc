package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BProductPrice;

/**
* 基础商品价格 
* 2016-03-18 20:18:13  邓纯杰
*/
public interface BProductPriceService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductPrice> getBProductPriceListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_product_price_id 
	* @return
	*/
	public BProductPrice getBProductPriceById(String b_product_price_id);
	/**
	* 添加
	* @param b_product_price 
	* @return
	*/
	public int addBProductPrice(BProductPrice b_Product_Price);
	/**
	* 修改
	* @param b_product_price 
	* @return
	*/
	public int updateBProductPrice(BProductPrice b_Product_Price);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductPrice(Map<String,Object> condition);
	
	/**
	 * 根据卖家商品编号查询对象
	 * @param b_seller_product_id
	 * @return
	 */
	public BProductPrice getBProductPriceByBSellerProductId(String b_seller_product_id);
}
