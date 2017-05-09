package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Generator_ForbidtableDao;
import jehc.xtmodules.xtmodel.Xt_Generator_Forbidtable;

/**
* 禁止使用代码生成器生成的表信息 
* 2016-09-26 10:55:48  邓纯杰
*/
@Repository("xt_Generator_ForbidtableDao")
public class Xt_Generator_ForbidtableDaoImpl  extends BaseDaoImpl implements Xt_Generator_ForbidtableDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Generator_Forbidtable> getXtGeneratorForbidtableListByCondition(Map<String,Object> condition){
		return (List<Xt_Generator_Forbidtable>)this.getList("getXtGeneratorForbidtableListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_generator_forbidtable_id 
	* @return
	*/
	public Xt_Generator_Forbidtable getXtGeneratorForbidtableById(String xt_generator_forbidtable_id){
		return (Xt_Generator_Forbidtable)this.get("getXtGeneratorForbidtableById", xt_generator_forbidtable_id);
	}
	/**
	* 添加
	* @param xt_generator_forbidtable 
	* @return
	*/
	public int addXtGeneratorForbidtable(Xt_Generator_Forbidtable xt_Generator_Forbidtable){
		return this.add("addXtGeneratorForbidtable", xt_Generator_Forbidtable);
	}
	/**
	* 修改
	* @param xt_generator_forbidtable 
	* @return
	*/
	public int updateXtGeneratorForbidtable(Xt_Generator_Forbidtable xt_Generator_Forbidtable){
		return this.update("updateXtGeneratorForbidtable", xt_Generator_Forbidtable);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtGeneratorForbidtable(Map<String,Object> condition){
		return this.del("delXtGeneratorForbidtable", condition);
	}
	/**
	* 批量添加
	* @param xt_generator_forbidtableList 
	* @return
	*/
	public int addBatchXtGeneratorForbidtable(List<Xt_Generator_Forbidtable> xt_Generator_ForbidtableList){
		return this.add("addBatchXtGeneratorForbidtable", xt_Generator_ForbidtableList);
	}
	/**
	* 批量修改
	* @param xt_generator_forbidtableList 
	* @return
	*/
	public int updateBatchXtGeneratorForbidtable(List<Xt_Generator_Forbidtable> xt_Generator_ForbidtableList){
		return this.update("updateBatchXtGeneratorForbidtable", xt_Generator_ForbidtableList);
	}
}
