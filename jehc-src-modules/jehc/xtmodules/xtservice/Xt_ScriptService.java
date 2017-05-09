package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Script;

/**
* 平台脚本 
* 2016-06-14 15:08:50  邓纯杰
*/
public interface Xt_ScriptService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Script> getXtScriptListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_script_id 
	* @return
	*/
	public Xt_Script getXtScriptById(String xt_script_id);
	/**
	* 添加
	* @param xt_script 
	* @return
	*/
	public int addXtScript(Xt_Script xt_Script);
	/**
	* 修改
	* @param xt_script 
	* @return
	*/
	public int updateXtScript(Xt_Script xt_Script);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtScript(Map<String,Object> condition);
}
