package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtFunctioninfoDao;
import jehc.xtmodules.xtmodel.XtFunctioninfo;

/**
* 功能表 
* 2015-06-01 20:41:56  邓纯杰
*/
@Repository("xtFunctioninfoDao")
public class XtFunctioninfoDaoImpl  extends BaseDaoImpl implements XtFunctioninfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtFunctioninfo> getXtFunctioninfoListByCondition(Map<String,Object> condition){
		return (List<XtFunctioninfo>)this.getList("getXtFunctioninfoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_functioninfo_id 
	* @return
	*/
	public XtFunctioninfo getXtFunctioninfoById(String xt_functioninfo_id){
		return (XtFunctioninfo)this.get("getXtFunctioninfoById", xt_functioninfo_id);
	}
	/**
	* 添加
	* @param xt_functioninfo 
	* @return
	*/
	public int addXtFunctioninfo(XtFunctioninfo xt_Functioninfo){
		return this.add("addXtFunctioninfo", xt_Functioninfo);
	}
	/**
	* 修改
	* @param xt_functioninfo 
	* @return
	*/
	public int updateXtFunctioninfo(XtFunctioninfo xt_Functioninfo){
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
	public List<XtFunctioninfo> getXtFunctioninfoList(Map<String,Object> condition){
		return (List<XtFunctioninfo>)this.getList("getXtFunctioninfoList",condition);
	}
	
	////////////////////数据权限///////////////////
	/**
	 * 查询所有功能数据并分组 
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtFunctioninfo> getXtFunctioninfoListForData(Map<String,Object> condition){
		return (List<XtFunctioninfo>)this.getList("getXtFunctioninfoListForData", condition);
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
	public List<XtFunctioninfo> getXtFunctioninfoAllForData(Map<String,Object> condition){
		return (List<XtFunctioninfo>)this.getList("getXtFunctioninfoAllForData", condition); 
	}
}
