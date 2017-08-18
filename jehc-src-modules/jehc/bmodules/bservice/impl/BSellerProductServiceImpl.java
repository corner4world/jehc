package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BSellerProductDao;
import jehc.bmodules.bmodel.BProductPrice;
import jehc.bmodules.bmodel.BSellerProduct;
import jehc.bmodules.bservice.BProductPriceService;
import jehc.bmodules.bservice.BSellerProductService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 卖家商品 
* 2016-02-18 17:20:35  邓纯杰
*/
@Service("bSellerProductService")
public class BSellerProductServiceImpl extends BaseService implements BSellerProductService{
	@Autowired
	private BSellerProductDao bSellerProductDao;
	@Autowired
	private BProductPriceService bProductPriceService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerProduct> getBSellerProductListByCondition(Map<String,Object> condition){
		try{
			return bSellerProductDao.getBSellerProductListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_seller_product_id 
	* @return
	*/
	public BSellerProduct getBSellerProductById(String b_seller_product_id){
		try{
			return bSellerProductDao.getBSellerProductById(b_seller_product_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_seller_product 
	* @return
	*/
	public int addBSellerProduct(BSellerProduct b_Seller_Product,BProductPrice b_Product_Price){
		int i = 0;
		try {
			bSellerProductDao.addBSellerProduct(b_Seller_Product);
			i = bProductPriceService.addBProductPrice(b_Product_Price);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_seller_product 
	* @return
	*/
	public int updateBSellerProduct(BSellerProduct b_Seller_Product,BProductPrice b_Product_Price){
		int i = 0;
		try {
			bSellerProductDao.updateBSellerProduct(b_Seller_Product);
			i = bProductPriceService.updateBProductPrice(b_Product_Price);
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
	public int delBSellerProduct(Map<String,Object> condition){
		int i = 0;
		try {
			i = bSellerProductDao.delBSellerProduct(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 查询库存使用商户商品并分页
	 * @param condition
	 * @return
	 */
	public List<BSellerProduct> getBSellerProductStockListByCondition(Map<String,Object> condition){
		return bSellerProductDao.getBSellerProductStockListByCondition(condition);
	}
	
	/**
	* 查询库存使用商户商品并分页统计
	* @param condition 
	* @return
	*/
	public int getBSellerProductStockListCountByCondition(Map<String,Object> condition){
		return bSellerProductDao.getBSellerProductStockListCountByCondition(condition);
	}
}
