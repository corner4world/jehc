package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Functioninfo_Right;

/**
* 功能分配表 
* 2016-10-08 17:38:19  邓纯杰
*/
public interface Xt_Functioninfo_RightService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Functioninfo_Right> getXtFunctioninfoRightListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_functioninfo_right_id 
	* @return
	*/
	public Xt_Functioninfo_Right getXtFunctioninfoRightById(String xt_functioninfo_right_id);
	/**
	* 添加
	* @param xt_functioninfo_right 
	* @return
	*/
	public int addXtFunctioninfoRight(Xt_Functioninfo_Right xt_Functioninfo_Right);
	/**
	* 修改
	* @param xt_functioninfo_right 
	* @return
	*/
	public int updateXtFunctioninfoRight(Xt_Functioninfo_Right xt_Functioninfo_Right);
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
	public int addBatchXtFunctioninfoRight(List<Xt_Functioninfo_Right> xt_Functioninfo_RightList);
	/**
	* 批量修改
	* @param xt_functioninfo_rightList 
	* @return
	*/
	public int updateBatchXtFunctioninfoRight(List<Xt_Functioninfo_Right> xt_Functioninfo_RightList);
}
