package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtScriptDao;
import jehc.xtmodules.xtmodel.XtScript;

/**
* 平台脚本 
* 2016-06-14 15:08:50  邓纯杰
*/
@Repository("xtScriptDao")
public class XtScriptDaoImpl  extends BaseDaoImpl implements XtScriptDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtScript> getXtScriptListByCondition(Map<String,Object> condition){
		return (List<XtScript>)this.getList("getXtScriptListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_script_id 
	* @return
	*/
	public XtScript getXtScriptById(String xt_script_id){
		return (XtScript)this.get("getXtScriptById", xt_script_id);
	}
	/**
	* 添加
	* @param xt_script 
	* @return
	*/
	public int addXtScript(XtScript xt_Script){
		return this.add("addXtScript", xt_Script);
	}
	/**
	* 修改
	* @param xt_script 
	* @return
	*/
	public int updateXtScript(XtScript xt_Script){
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
