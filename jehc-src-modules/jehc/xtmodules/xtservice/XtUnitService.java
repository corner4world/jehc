package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtUnit;

/**
* 商品(产品)单位 
* 2015-05-24 08:43:08  邓纯杰
*/
public interface XtUnitService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtUnit> getXtUnitListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_unit_id 
	* @return
	*/
	public XtUnit getXtUnitById(String xt_unit_id);
	/**
	* 添加
	* @param xt_unit 
	* @return
	*/
	public int addXtUnit(XtUnit xt_Unit);
	/**
	* 修改
	* @param xt_unit 
	* @return
	*/
	public int updateXtUnit(XtUnit xt_Unit);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtUnit(Map<String,Object> condition);
}
