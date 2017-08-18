package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BProductImgDao;
import jehc.bmodules.bmodel.BProductImg;
import jehc.bmodules.bservice.BProductImgService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础商品图片 
* 2016-07-07 20:50:43  邓纯杰
*/
@Service("bProductImgService")
public class BProductImgServiceImpl extends BaseService implements BProductImgService{
	@Autowired
	private BProductImgDao bProductImgDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductImg> getBProductImgListByCondition(Map<String,Object> condition){
		try{
			return bProductImgDao.getBProductImgListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_product_img_id 
	* @return
	*/
	public BProductImg getBProductImgById(String b_product_img_id){
		try{
			return bProductImgDao.getBProductImgById(b_product_img_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_product_img 
	* @return
	*/
	public int addBProductImg(BProductImg b_Product_Img){
		int i = 0;
		try {
			i = bProductImgDao.addBProductImg(b_Product_Img);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_product_img 
	* @return
	*/
	public int updateBProductImg(BProductImg b_Product_Img){
		int i = 0;
		try {
			i = bProductImgDao.updateBProductImg(b_Product_Img);
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
	public int delBProductImg(Map<String,Object> condition){
		int i = 0;
		try {
			i = bProductImgDao.delBProductImg(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
