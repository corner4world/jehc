package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BCategoryDao;
import jehc.bmodules.bmodel.BCategory;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础-品类 
* 2016-01-04 21:24:03  邓纯杰
*/
@Repository("bCategoryDao")
public class BCategoryDaoImpl  extends BaseDaoImpl implements BCategoryDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BCategory> getBCategoryListByCondition(Map<String,Object> condition){
		return (List<BCategory>)this.getList("getBCategoryListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_category_id 
	* @return
	*/
	public BCategory getBCategoryById(String b_category_id){
		return (BCategory)this.get("getBCategoryById", b_category_id);
	}
	/**
	* 添加
	* @param b_category 
	* @return
	*/
	public int addBCategory(BCategory b_Category){
		return this.add("addBCategory", b_Category);
	}
	/**
	* 修改
	* @param b_category 
	* @return
	*/
	public int updateBCategory(BCategory b_Category){
		return this.update("updateBCategory", b_Category);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCategory(Map<String,Object> condition){
		return this.del("delBCategory", condition);
	}
	/**
	 * 读取所有数据根据条件
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BCategory> getBCategoryListAllByCondition(Map<String,Object> condition){
		return (List<BCategory>)this.getList("getBCategoryListAllByCondition",condition);
	}
	
	/**
	 * 根据条件查找集合（前端提供）
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BCategory> getBCategoryListForFrontByCondition(Map<String,Object> condition){
		return (List<BCategory>)this.getList("getBCategoryListForFrontByCondition",condition);
	}
}
