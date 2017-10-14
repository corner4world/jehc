package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtFunctioninfoCommonDao;
import jehc.xtmodules.xtmodel.XtFunctioninfoCommon;

/**
* 公共功能 
* 2016-10-10 13:00:30  邓纯杰
*/
@Repository("xtFunctioninfoCommonDao")
public class XtFunctioninfoCommonDaoImpl  extends BaseDaoImpl implements XtFunctioninfoCommonDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtFunctioninfoCommon> getXtFunctioninfoCommonListByCondition(Map<String,Object> condition){
		return (List<XtFunctioninfoCommon>)this.getList("getXtFunctioninfoCommonListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_functioninfo_common_id 
	* @return
	*/
	public XtFunctioninfoCommon getXtFunctioninfoCommonById(String xt_functioninfo_common_id){
		return (XtFunctioninfoCommon)this.get("getXtFunctioninfoCommonById", xt_functioninfo_common_id);
	}
	/**
	* 添加
	* @param xt_functioninfo_common 
	* @return
	*/
	public int addXtFunctioninfoCommon(XtFunctioninfoCommon xt_Functioninfo_Common){
		return this.add("addXtFunctioninfoCommon", xt_Functioninfo_Common);
	}
	/**
	* 修改
	* @param xt_functioninfo_common 
	* @return
	*/
	public int updateXtFunctioninfoCommon(XtFunctioninfoCommon xt_Functioninfo_Common){
		return this.update("updateXtFunctioninfoCommon", xt_Functioninfo_Common);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtFunctioninfoCommon(Map<String,Object> condition){
		return this.del("delXtFunctioninfoCommon", condition);
	}
}
