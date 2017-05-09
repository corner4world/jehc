package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Dbtable_Strategy;

/**
* 分表策略表 
* 2017-02-14 16:23:48  邓纯杰
*/
public interface Xt_Dbtable_StrategyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Dbtable_Strategy> getXtDbtableStrategyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_dbtable_strategy_id 
	* @return
	*/
	public Xt_Dbtable_Strategy getXtDbtableStrategyById(String xt_dbtable_strategy_id);
	/**
	* 添加
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int addXtDbtableStrategy(Xt_Dbtable_Strategy xt_Dbtable_Strategy);
	/**
	* 修改
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int updateXtDbtableStrategy(Xt_Dbtable_Strategy xt_Dbtable_Strategy);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDbtableStrategy(Map<String,Object> condition);
}
