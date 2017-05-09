package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Functioninfo_CommonDao;
import jehc.xtmodules.xtmodel.Xt_Functioninfo_Common;

/**
* 公共功能 
* 2016-10-10 13:00:30  邓纯杰
*/
@Repository("xt_Functioninfo_CommonDao")
public class Xt_Functioninfo_CommonDaoImpl  extends BaseDaoImpl implements Xt_Functioninfo_CommonDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Functioninfo_Common> getXtFunctioninfoCommonListByCondition(Map<String,Object> condition){
		return (List<Xt_Functioninfo_Common>)this.getList("getXtFunctioninfoCommonListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_functioninfo_common_id 
	* @return
	*/
	public Xt_Functioninfo_Common getXtFunctioninfoCommonById(String xt_functioninfo_common_id){
		return (Xt_Functioninfo_Common)this.get("getXtFunctioninfoCommonById", xt_functioninfo_common_id);
	}
	/**
	* 添加
	* @param xt_functioninfo_common 
	* @return
	*/
	public int addXtFunctioninfoCommon(Xt_Functioninfo_Common xt_Functioninfo_Common){
		return this.add("addXtFunctioninfoCommon", xt_Functioninfo_Common);
	}
	/**
	* 修改
	* @param xt_functioninfo_common 
	* @return
	*/
	public int updateXtFunctioninfoCommon(Xt_Functioninfo_Common xt_Functioninfo_Common){
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
