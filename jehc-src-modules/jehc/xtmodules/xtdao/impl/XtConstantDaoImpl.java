package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtConstantDao;
import jehc.xtmodules.xtmodel.XtConstant;

/**
* 台平常量 
* 2015-05-24 08:47:31  邓纯杰
*/
@Repository("xtConstantDao")
public class XtConstantDaoImpl  extends BaseDaoImpl implements XtConstantDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtConstant> getXtConstantListByCondition(Map<String,Object> condition){
		return (List<XtConstant>)this.getList("getXtConstantListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_constant_id 
	* @return
	*/
	public XtConstant getXtConstantById(String xt_constant_id){
		return (XtConstant)this.get("getXtConstantById", xt_constant_id);
	}
	/**
	* 添加
	* @param xt_constant 
	* @return
	*/
	public int addXtConstant(XtConstant xt_Constant){
		return this.add("addXtConstant", xt_Constant);
	}
	/**
	* 修改
	* @param xt_constant 
	* @return
	*/
	public int updateXtConstant(XtConstant xt_Constant){
		return this.update("updateXtConstant", xt_Constant);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtConstant(Map<String,Object> condition){
		return this.del("delXtConstant", condition);
	}
	
	/**
	 * 读取所有常量
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtConstant> getXtConstantListAllByCondition(Map<String,Object> condition){
		return (List<XtConstant>)this.getList("getXtConstantListAllByCondition",condition);
	}
}
