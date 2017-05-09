package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_FunctioninfoDao;
import jehc.xtmodules.xtmodel.Xt_Functioninfo;

/**
* 功能表 
* 2015-06-01 20:41:56  邓纯杰
*/
@Repository("xt_FunctioninfoDao")
public class Xt_FunctioninfoDaoImpl  extends BaseDaoImpl implements Xt_FunctioninfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Functioninfo> getXtFunctioninfoListByCondition(Map<String,Object> condition){
		return (List<Xt_Functioninfo>)this.getList("getXtFunctioninfoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_functioninfo_id 
	* @return
	*/
	public Xt_Functioninfo getXtFunctioninfoById(String xt_functioninfo_id){
		return (Xt_Functioninfo)this.get("getXtFunctioninfoById", xt_functioninfo_id);
	}
	/**
	* 添加
	* @param xt_functioninfo 
	* @return
	*/
	public int addXtFunctioninfo(Xt_Functioninfo xt_Functioninfo){
		return this.add("addXtFunctioninfo", xt_Functioninfo);
	}
	/**
	* 修改
	* @param xt_functioninfo 
	* @return
	*/
	public int updateXtFunctioninfo(Xt_Functioninfo xt_Functioninfo){
		return this.update("updateXtFunctioninfo", xt_Functioninfo);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtFunctioninfo(Map<String,Object> condition){
		return this.del("delXtFunctioninfo", condition);
	}
	
	/**
	 * 读取所有功能
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Functioninfo> getXtFunctioninfoList(Map<String,Object> condition){
		return (List<Xt_Functioninfo>)this.getList("getXtFunctioninfoList",condition);
	}
	
	////////////////////数据权限///////////////////
	/**
	 * 查询所有功能数据并分组 
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Functioninfo> getXtFunctioninfoListForData(Map<String,Object> condition){
		return (List<Xt_Functioninfo>)this.getList("getXtFunctioninfoListForData", condition);
	}
	/**
	 * 统计所有功能数据并分组
	 * @param condition
	 * @return
	 */
	public int getXtFunctioninfoListCountForData(Map<String,Object> condition){
		return new Integer(this.get("getXtFunctioninfoListCountForData", condition).toString());
	}
	/**
	 * 
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Functioninfo> getXtFunctioninfoAllForData(Map<String,Object> condition){
		return (List<Xt_Functioninfo>)this.getList("getXtFunctioninfoAllForData", condition); 
	}
}
