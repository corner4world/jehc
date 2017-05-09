package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_ScriptDao;
import jehc.xtmodules.xtmodel.Xt_Script;

/**
* 平台脚本 
* 2016-06-14 15:08:50  邓纯杰
*/
@Repository("xt_ScriptDao")
public class Xt_ScriptDaoImpl  extends BaseDaoImpl implements Xt_ScriptDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Script> getXtScriptListByCondition(Map<String,Object> condition){
		return (List<Xt_Script>)this.getList("getXtScriptListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_script_id 
	* @return
	*/
	public Xt_Script getXtScriptById(String xt_script_id){
		return (Xt_Script)this.get("getXtScriptById", xt_script_id);
	}
	/**
	* 添加
	* @param xt_script 
	* @return
	*/
	public int addXtScript(Xt_Script xt_Script){
		return this.add("addXtScript", xt_Script);
	}
	/**
	* 修改
	* @param xt_script 
	* @return
	*/
	public int updateXtScript(Xt_Script xt_Script){
		return this.update("updateXtScript", xt_Script);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtScript(Map<String,Object> condition){
		return this.del("delXtScript", condition);
	}
}
