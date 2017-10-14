package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BProductPriceDao;
import jehc.bmodules.bmodel.BProductPrice;
import jehc.bmodules.bservice.BProductPriceService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础商品价格 
* 2016-03-18 20:18:13  邓纯杰
*/
@Service("bProductPriceService")
public class BProductPriceServiceImpl extends BaseService implements BProductPriceService{
	@Autowired
	private BProductPriceDao bProductPriceDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductPrice> getBProductPriceListByCondition(Map<String,Object> condition){
		try{
			return bProductPriceDao.getBProductPriceListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_product_price_id 
	* @return
	*/
	public BProductPrice getBProductPriceById(String b_product_price_id){
		try{
			return bProductPriceDao.getBProductPriceById(b_product_price_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_product_price 
	* @return
	*/
	public int addBProductPrice(BProductPrice b_Product_Price){
		int i = 0;
		try {
			i = bProductPriceDao.addBProductPrice(b_Product_Price);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_product_price 
	* @return
	*/
	public int updateBProductPrice(BProductPrice b_Product_Price){
		int i = 0;
		try {
			i = bProductPriceDao.updateBProductPrice(b_Product_Price);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductPrice(Map<String,Object> condition){
		int i = 0;
		try {
			i = bProductPriceDao.delBProductPrice(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据卖家商品编号查询对象
	 * @param b_seller_product_id
	 * @return
	 */
	public BProductPrice getBProductPriceByBSellerProductId(String b_seller_product_id){
		try{
			return bProductPriceDao.getBProductPriceByBSellerProductId(b_seller_product_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
	}
}
