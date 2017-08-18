package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BProductDao;
import jehc.bmodules.bmodel.BProduct;
import jehc.bmodules.bservice.BProductService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础_产品 
* 2016-01-08 21:03:47  邓纯杰
*/
@Service("bProductService")
public class BProductServiceImpl extends BaseService implements BProductService{
	@Autowired
	private BProductDao bProductDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProduct> getBProductListByCondition(Map<String,Object> condition){
		try{
			return bProductDao.getBProductListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_product_id 
	* @return
	*/
	public BProduct getBProductById(String b_product_id){
		try{
			return bProductDao.getBProductById(b_product_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_product 
	* @return
	*/
	public int addBProduct(BProduct b_Product){
		int i = 0;
		try {
			i = bProductDao.addBProduct(b_Product);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_product 
	* @return
	*/
	public int updateBProduct(BProduct b_Product){
		int i = 0;
		try {
			i = bProductDao.updateBProduct(b_Product);
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
	public int delBProduct(Map<String,Object> condition){
		int i = 0;
		try {
			i = bProductDao.delBProduct(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
