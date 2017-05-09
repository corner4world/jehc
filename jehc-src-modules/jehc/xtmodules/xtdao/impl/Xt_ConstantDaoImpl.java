package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_ConstantDao;
import jehc.xtmodules.xtmodel.Xt_Constant;

/**
* 台平常量 
* 2015-05-24 08:47:31  邓纯杰
*/
@Repository("xt_ConstantDao")
public class Xt_ConstantDaoImpl  extends BaseDaoImpl implements Xt_ConstantDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Constant> getXtConstantListByCondition(Map<String,Object> condition){
		return (List<Xt_Constant>)this.getList("getXtConstantListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_constant_id 
	* @return
	*/
	public Xt_Constant getXtConstantById(String xt_constant_id){
		return (Xt_Constant)this.get("getXtConstantById", xt_constant_id);
	}
	/**
	* 添加
	* @param xt_constant 
	* @return
	*/
	public int addXtConstant(Xt_Constant xt_Constant){
		return this.add("addXtConstant", xt_Constant);
	}
	/**
	* 修改
	* @param xt_constant 
	* @return
	*/
	public int updateXtConstant(Xt_Constant xt_Constant){
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
	public List<Xt_Constant> getXtConstantListAllByCondition(Map<String,Object> condition){
		return (List<Xt_Constant>)this.getList("getXtConstantListAllByCondition",condition);
	}
}
