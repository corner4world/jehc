package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtConstant;

/**
* 台平常量 
* 2015-05-24 08:47:31  邓纯杰
*/
public interface XtConstantDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtConstant> getXtConstantListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_constant_id 
	* @return
	*/
	public XtConstant getXtConstantById(String xt_constant_id);
	/**
	* 添加
	* @param xt_constant 
	* @return
	*/
	public int addXtConstant(XtConstant xt_Constant);
	/**
	* 修改
	* @param xt_constant 
	* @return
	*/
	public int updateXtConstant(XtConstant xt_Constant);
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
	public List<XtConstant> getXtConstantListAllByCondition(Map<String,Object> condition);
}
