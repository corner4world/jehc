package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BCategoryDao;
import jehc.bmodules.bmodel.BCategory;
import jehc.bmodules.bservice.BCategoryService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础-品类 
* 2016-01-04 21:24:03  邓纯杰
*/
@Service("bCategoryService")
public class BCategoryServiceImpl extends BaseService implements BCategoryService{
	@Autowired
	private BCategoryDao bCategoryDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BCategory> getBCategoryListByCondition(Map<String,Object> condition){
		try{
			return bCategoryDao.getBCategoryListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_category_id 
	* @return
	*/
	public BCategory getBCategoryById(String b_category_id){
		try{
			return bCategoryDao.getBCategoryById(b_category_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_category 
	* @return
	*/
	public int addBCategory(BCategory b_Category){
		int i = 0;
		try {
			i = bCategoryDao.addBCategory(b_Category);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_category 
	* @return
	*/
	public int updateBCategory(BCategory b_Category){
		int i = 0;
		try {
			i = bCategoryDao.updateBCategory(b_Category);
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
	public int delBCategory(Map<String,Object> condition){
		int i = 0;
		try {
			i = bCategoryDao.delBCategory(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 读取所有数据根据条件
	 * @param condition
	 * @return
	 */
	public List<BCategory> getBCategoryListAllByCondition(Map<String,Object> condition){
		try{
			return bCategoryDao.getBCategoryListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 根据条件查找集合（前端提供）
	 * @param condition
	 * @return
	 */
	public List<BCategory> getBCategoryListForFrontByCondition(Map<String,Object> condition){
		try{
			return bCategoryDao.getBCategoryListForFrontByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
