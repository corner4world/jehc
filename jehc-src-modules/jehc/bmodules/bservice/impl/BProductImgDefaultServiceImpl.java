package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BProductImgDefaultDao;
import jehc.bmodules.bmodel.BProductImgDefault;
import jehc.bmodules.bservice.BProductImgDefaultService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础商品默认图片 
* 2016-01-09 09:06:38  邓纯杰
*/
@Service("bProductImgDefaultService")
public class BProductImgDefaultServiceImpl extends BaseService implements BProductImgDefaultService{
	@Autowired
	private BProductImgDefaultDao bProductImgDefaultDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductImgDefault> getBProductImgDefaultListByCondition(Map<String,Object> condition){
		try{
			return bProductImgDefaultDao.getBProductImgDefaultListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_product_img_default_id 
	* @return
	*/
	public BProductImgDefault getBProductImgDefaultById(String b_product_img_default_id){
		try{
			return bProductImgDefaultDao.getBProductImgDefaultById(b_product_img_default_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_product_img_default 
	* @return
	*/
	public int addBProductImgDefault(BProductImgDefault b_Product_Img_Default){
		int i = 0;
		try {
			i = bProductImgDefaultDao.addBProductImgDefault(b_Product_Img_Default);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_product_img_default 
	* @return
	*/
	public int updateBProductImgDefault(BProductImgDefault b_Product_Img_Default){
		int i = 0;
		try {
			i = bProductImgDefaultDao.updateBProductImgDefault(b_Product_Img_Default);
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
	public int delBProductImgDefault(Map<String,Object> condition){
		int i = 0;
		try {
			i = bProductImgDefaultDao.delBProductImgDefault(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
