package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Functioninfo;

/**
* 功能表 
* 2015-06-01 20:41:56  邓纯杰
*/
public interface Xt_FunctioninfoService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Functioninfo> getXtFunctioninfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_functioninfo_id 
	* @return
	*/
	public Xt_Functioninfo getXtFunctioninfoById(String xt_functioninfo_id);
	/**
	* 添加
	* @param xt_functioninfo 
	* @return
	*/
	public int addXtFunctioninfo(Xt_Functioninfo xt_Functioninfo);
	/**
	* 修改
	* @param xt_functioninfo 
	* @return
	*/
	public int updateXtFunctioninfo(Xt_Functioninfo xt_Functioninfo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtFunctioninfo(Map<String,Object> condition);
	
	/**
	 * 读取所有功能
	 * @param condition
	 * @return
	 */
	public List<Xt_Functioninfo> getXtFunctioninfoList(Map<String,Object> condition);
	
	////////////////////数据权限///////////////////
	/**
	 * 查询所有功能数据并分组 
	 * @param condition
	 * @return
	 */
	public List<Xt_Functioninfo> getXtFunctioninfoListForData(Map<String,Object> condition);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	public List<Xt_Functioninfo> getXtFunctioninfoAllForData(Map<String,Object> condition);
}
