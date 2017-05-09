package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Functioninfo_Common;

/**
* 公共功能 
* 2016-10-10 13:00:30  邓纯杰
*/
public interface Xt_Functioninfo_CommonService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Functioninfo_Common> getXtFunctioninfoCommonListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_functioninfo_common_id 
	* @return
	*/
	public Xt_Functioninfo_Common getXtFunctioninfoCommonById(String xt_functioninfo_common_id);
	/**
	* 添加
	* @param xt_functioninfo_common 
	* @return
	*/
	public int addXtFunctioninfoCommon(Xt_Functioninfo_Common xt_Functioninfo_Common);
	/**
	* 修改
	* @param xt_functioninfo_common 
	* @return
	*/
	public int updateXtFunctioninfoCommon(Xt_Functioninfo_Common xt_Functioninfo_Common);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtFunctioninfoCommon(Map<String,Object> condition);
}
