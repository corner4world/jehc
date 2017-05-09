package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Constant;

/**
* 台平常量 
* 2015-05-24 08:47:31  邓纯杰
*/
public interface Xt_ConstantService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Constant> getXtConstantListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_constant_id 
	* @return
	*/
	public Xt_Constant getXtConstantById(String xt_constant_id);
	/**
	* 添加
	* @param xt_constant 
	* @return
	*/
	public int addXtConstant(Xt_Constant xt_Constant);
	/**
	* 修改
	* @param xt_constant 
	* @return
	*/
	public int updateXtConstant(Xt_Constant xt_Constant);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtConstant(Map<String,Object> condition);
	/**
	 * 读取所有常量
	 * @param condition
	 * @return
	 */
	public List<Xt_Constant> getXtConstantListAllByCondition(Map<String,Object> condition);
}
