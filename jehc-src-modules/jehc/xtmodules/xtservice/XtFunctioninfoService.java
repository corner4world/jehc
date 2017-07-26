package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtFunctioninfo;

/**
* 功能表 
* 2015-06-01 20:41:56  邓纯杰
*/
public interface XtFunctioninfoService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtFunctioninfo> getXtFunctioninfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_functioninfo_id 
	* @return
	*/
	public XtFunctioninfo getXtFunctioninfoById(String xt_functioninfo_id);
	/**
	* 添加
	* @param xt_functioninfo 
	* @return
	*/
	public int addXtFunctioninfo(XtFunctioninfo xt_Functioninfo);
	/**
	* 修改
	* @param xt_functioninfo 
	* @return
	*/
	public int updateXtFunctioninfo(XtFunctioninfo xt_Functioninfo);
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
	public List<XtFunctioninfo> getXtFunctioninfoList(Map<String,Object> condition);
	
	////////////////////数据权限///////////////////
	/**
	 * 查询所有功能数据并分组 
	 * @param condition
	 * @return
	 */
	public List<XtFunctioninfo> getXtFunctioninfoListForData(Map<String,Object> condition);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	public List<XtFunctioninfo> getXtFunctioninfoAllForData(Map<String,Object> condition);
}
