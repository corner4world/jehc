package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtFunctioninfoRightDao;
import jehc.xtmodules.xtmodel.XtFunctioninfoRight;

/**
* 功能分配表 
* 2016-10-08 17:38:19  邓纯杰
*/
@Repository("xtFunctioninfoRightDao")
public class XtFunctioninfoRightDaoImpl  extends BaseDaoImpl implements XtFunctioninfoRightDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtFunctioninfoRight> getXtFunctioninfoRightListByCondition(Map<String,Object> condition){
		return (List<XtFunctioninfoRight>)this.getList("getXtFunctioninfoRightListByCondition",condition);
	}
	/**
	 * 初始化分页（for admin all function）
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtFunctioninfoRight> getXtFunctioninfoListForAdmin(Map<String,Object> condition){
		return ((List<XtFunctioninfoRight>)this.getList("getXtFunctioninfoListForAdmin",condition));
	}
	/**
	* 查询对象
	* @param xt_functioninfo_right_id 
	* @return
	*/
	public XtFunctioninfoRight getXtFunctioninfoRightById(String xt_functioninfo_right_id){
		return (XtFunctioninfoRight)this.get("getXtFunctioninfoRightById", xt_functioninfo_right_id);
	}
	/**
	* 添加
	* @param xt_functioninfo_right 
	* @return
	*/
	public int addXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right){
		return this.add("addXtFunctioninfoRight", xt_Functioninfo_Right);
	}
	/**
	* 修改
	* @param xt_functioninfo_right 
	* @return
	*/
	public int updateXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right){
		return this.update("updateXtFunctioninfoRight", xt_Functioninfo_Right);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtFunctioninfoRight(Map<String,Object> condition){
		return this.del("delXtFunctioninfoRight", condition);
	}
	/**
	* 批量添加
	* @param xt_functioninfo_rightList 
	* @return
	*/
	public int addBatchXtFunctioninfoRight(List<XtFunctioninfoRight> xt_Functioninfo_RightList){
		return this.add("addBatchXtFunctioninfoRight", xt_Functioninfo_RightList);
	}
	/**
	* 批量修改
	* @param xt_functioninfo_rightList 
	* @return
	*/
	public int updateBatchXtFunctioninfoRight(List<XtFunctioninfoRight> xt_Functioninfo_RightList){
		return this.update("updateBatchXtFunctioninfoRight", xt_Functioninfo_RightList);
	}
}
