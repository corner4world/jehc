package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BCategory;

/**
* 基础-品类 
* 2016-01-04 21:24:03  邓纯杰
*/
public interface BCategoryDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BCategory> getBCategoryListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_category_id 
	* @return
	*/
	public BCategory getBCategoryById(String b_category_id);
	/**
	* 添加
	* @param b_category 
	* @return
	*/
	public int addBCategory(BCategory b_Category);
	/**
	* 修改
	* @param b_category 
	* @return
	*/
	public int updateBCategory(BCategory b_Category);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCategory(Map<String,Object> condition);
	/**
	 * 读取所有数据根据条件
	 * @param condition
	 * @return
	 */
	public List<BCategory> getBCategoryListAllByCondition(Map<String,Object> condition);
	
	/**
	 * 根据条件查找集合（前端提供）
	 * @param condition
	 * @return
	 */
	public List<BCategory> getBCategoryListForFrontByCondition(Map<String,Object> condition);
}
