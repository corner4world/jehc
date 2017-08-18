package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BProductColorDao;
import jehc.bmodules.bmodel.BProductColor;
import jehc.bmodules.bservice.BProductColorService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础商品商户所选颜色 
* 2016-07-02 16:54:11  邓纯杰
*/
@Service("bProductColorService")
public class BProductColorServiceImpl extends BaseService implements BProductColorService{
	@Autowired
	private BProductColorDao bProductColorDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductColor> getBProductColorListByCondition(Map<String,Object> condition){
		try{
			return bProductColorDao.getBProductColorListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_product_color_id 
	* @return
	*/
	public BProductColor getBProductColorById(String b_product_color_id){
		try{
			return bProductColorDao.getBProductColorById(b_product_color_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_product_color 
	* @return
	*/
	public int addBProductColor(BProductColor b_Product_Color){
		int i = 0;
		try {
			i = bProductColorDao.addBProductColor(b_Product_Color);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_product_color 
	* @return
	*/
	public int updateBProductColor(BProductColor b_Product_Color){
		int i = 0;
		try {
			i = bProductColorDao.updateBProductColor(b_Product_Color);
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
	public int delBProductColor(Map<String,Object> condition){
		int i = 0;
		try {
			i = bProductColorDao.delBProductColor(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
