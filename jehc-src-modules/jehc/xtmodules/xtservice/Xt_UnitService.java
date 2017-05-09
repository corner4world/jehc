package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Unit;

/**
* 商品(产品)单位 
* 2015-05-24 08:43:08  邓纯杰
*/
public interface Xt_UnitService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Unit> getXtUnitListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_unit_id 
	* @return
	*/
	public Xt_Unit getXtUnitById(String xt_unit_id);
	/**
	* 添加
	* @param xt_unit 
	* @return
	*/
	public int addXtUnit(Xt_Unit xt_Unit);
	/**
	* 修改
	* @param xt_unit 
	* @return
	*/
	public int updateXtUnit(Xt_Unit xt_Unit);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtUnit(Map<String,Object> condition);
}
