package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Functioninfo_RightDao;
import jehc.xtmodules.xtmodel.Xt_Functioninfo_Right;

/**
* 功能分配表 
* 2016-10-08 17:38:19  邓纯杰
*/
@Repository("xt_Functioninfo_RightDao")
public class Xt_Functioninfo_RightDaoImpl  extends BaseDaoImpl implements Xt_Functioninfo_RightDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Functioninfo_Right> getXtFunctioninfoRightListByCondition(Map<String,Object> condition){
		return (List<Xt_Functioninfo_Right>)this.getList("getXtFunctioninfoRightListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_functioninfo_right_id 
	* @return
	*/
	public Xt_Functioninfo_Right getXtFunctioninfoRightById(String xt_functioninfo_right_id){
		return (Xt_Functioninfo_Right)this.get("getXtFunctioninfoRightById", xt_functioninfo_right_id);
	}
	/**
	* 添加
	* @param xt_functioninfo_right 
	* @return
	*/
	public int addXtFunctioninfoRight(Xt_Functioninfo_Right xt_Functioninfo_Right){
		return this.add("addXtFunctioninfoRight", xt_Functioninfo_Right);
	}
	/**
	* 修改
	* @param xt_functioninfo_right 
	* @return
	*/
	public int updateXtFunctioninfoRight(Xt_Functioninfo_Right xt_Functioninfo_Right){
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
	public int addBatchXtFunctioninfoRight(List<Xt_Functioninfo_Right> xt_Functioninfo_RightList){
		return this.add("addBatchXtFunctioninfoRight", xt_Functioninfo_RightList);
	}
	/**
	* 批量修改
	* @param xt_functioninfo_rightList 
	* @return
	*/
	public int updateBatchXtFunctioninfoRight(List<Xt_Functioninfo_Right> xt_Functioninfo_RightList){
		return this.update("updateBatchXtFunctioninfoRight", xt_Functioninfo_RightList);
	}
}
