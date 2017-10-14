package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BBrandDao;
import jehc.bmodules.bmodel.BBrand;
import jehc.bmodules.bservice.BBrandService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础-品牌 
* 2016-01-05 12:46:23  邓纯杰
*/
@Service("bBrandService")
public class BBrandServiceImpl extends BaseService implements BBrandService{
	@Autowired
	private BBrandDao bBrandDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BBrand> getBBrandListByCondition(Map<String,Object> condition){
		try{
			return bBrandDao.getBBrandListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_brand_id 
	* @return
	*/
	public BBrand getBBrandById(String b_brand_id){
		try{
			return bBrandDao.getBBrandById(b_brand_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_brand 
	* @return
	*/
	public int addBBrand(BBrand b_Brand){
		int i = 0;
		try {
			i = bBrandDao.addBBrand(b_Brand);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_brand 
	* @return
	*/
	public int updateBBrand(BBrand b_Brand){
		int i = 0;
		try {
			i = bBrandDao.updateBBrand(b_Brand);
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
	public int delBBrand(Map<String,Object> condition){
		int i = 0;
		try {
			i = bBrandDao.delBBrand(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
