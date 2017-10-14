package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtDbtableStrategy;

/**
* 分表策略表 
* 2017-02-14 16:23:48  邓纯杰
*/
public interface XtDbtableStrategyService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDbtableStrategy> getXtDbtableStrategyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_dbtable_strategy_id 
	* @return
	*/
	public XtDbtableStrategy getXtDbtableStrategyById(String xt_dbtable_strategy_id);
	/**
	* 添加
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int addXtDbtableStrategy(XtDbtableStrategy xt_Dbtable_Strategy);
	/**
	* 修改
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int updateXtDbtableStrategy(XtDbtableStrategy xt_Dbtable_Strategy);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDbtableStrategy(Map<String,Object> condition);
}
