package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BProductColorDefaultDao;
import jehc.bmodules.bmodel.BProductColorDefault;
import jehc.bmodules.bservice.BProductColorDefaultService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础商品默认颜色 
* 2016-01-19 15:38:05  邓纯杰
*/
@Service("bProductColorDefaultService")
public class BProductColorDefaultServiceImpl extends BaseService implements BProductColorDefaultService{
	@Autowired
	private BProductColorDefaultDao bProductColorDefaultDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductColorDefault> getBProductColorDefaultListByCondition(Map<String,Object> condition){
		try{
			return bProductColorDefaultDao.getBProductColorDefaultListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_product_color_default_id 
	* @return
	*/
	public BProductColorDefault getBProductColorDefaultById(String b_product_color_default_id){
		try{
			return bProductColorDefaultDao.getBProductColorDefaultById(b_product_color_default_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_product_color_default 
	* @return
	*/
	public int addBProductColorDefault(BProductColorDefault b_Product_Color_Default){
		int i = 0;
		try {
			i = bProductColorDefaultDao.addBProductColorDefault(b_Product_Color_Default);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_product_color_default 
	* @return
	*/
	public int updateBProductColorDefault(BProductColorDefault b_Product_Color_Default){
		int i = 0;
		try {
			i = bProductColorDefaultDao.updateBProductColorDefault(b_Product_Color_Default);
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
	public int delBProductColorDefault(Map<String,Object> condition){
		int i = 0;
		try {
			i = bProductColorDefaultDao.delBProductColorDefault(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
