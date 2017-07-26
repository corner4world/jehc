package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtFunctioninfoRight;

/**
* 功能分配表 
* 2016-10-08 17:38:19  邓纯杰
*/
public interface XtFunctioninfoRightService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtFunctioninfoRight> getXtFunctioninfoRightListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_functioninfo_right_id 
	* @return
	*/
	public XtFunctioninfoRight getXtFunctioninfoRightById(String xt_functioninfo_right_id);
	/**
	* 添加
	* @param xt_functioninfo_right 
	* @return
	*/
	public int addXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right);
	/**
	* 修改
	* @param xt_functioninfo_right 
	* @return
	*/
	public int updateXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtFunctioninfoRight(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_functioninfo_rightList 
	* @return
	*/
	public int addBatchXtFunctioninfoRight(List<XtFunctioninfoRight> xt_Functioninfo_RightList);
	/**
	* 批量修改
	* @param xt_functioninfo_rightList 
	* @return
	*/
	public int updateBatchXtFunctioninfoRight(List<XtFunctioninfoRight> xt_Functioninfo_RightList);
}
