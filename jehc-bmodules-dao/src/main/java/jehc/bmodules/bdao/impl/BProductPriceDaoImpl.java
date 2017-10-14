package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BProductPriceDao;
import jehc.bmodules.bmodel.BProductPrice;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础商品价格 
* 2016-03-18 20:18:13  邓纯杰
*/
@Repository("bProductPriceDao")
public class BProductPriceDaoImpl  extends BaseDaoImpl implements BProductPriceDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BProductPrice> getBProductPriceListByCondition(Map<String,Object> condition){
		return (List<BProductPrice>)this.getList("getBProductPriceListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_product_price_id 
	* @return
	*/
	public BProductPrice getBProductPriceById(String b_product_price_id){
		return (BProductPrice)this.get("getBProductPriceById", b_product_price_id);
	}
	/**
	* 添加
	* @param b_product_price 
	* @return
	*/
	public int addBProductPrice(BProductPrice b_Product_Price){
		return this.add("addBProductPrice", b_Product_Price);
	}
	/**
	* 修改
	* @param b_product_price 
	* @return
	*/
	public int updateBProductPrice(BProductPrice b_Product_Price){
		return this.update("updateBProductPrice", b_Product_Price);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductPrice(Map<String,Object> condition){
		return this.del("delBProductPrice", condition);
	}
	/**
	 * 根据卖家商品编号查询对象
	 * @param b_seller_product_id
	 * @return
	 */
	public BProductPrice getBProductPriceByBSellerProductId(String b_seller_product_id){
		return (BProductPrice)this.get("getBProductPriceByBSellerProductId", b_seller_product_id);
	}
}
