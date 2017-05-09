package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Dbtable_StrategyDao;
import jehc.xtmodules.xtmodel.Xt_Dbtable_Strategy;

/**
* 分表策略表 
* 2017-02-14 16:23:48  邓纯杰
*/
@Repository("xt_Dbtable_StrategyDao")
public class Xt_Dbtable_StrategyDaoImpl  extends BaseDaoImpl implements Xt_Dbtable_StrategyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Dbtable_Strategy> getXtDbtableStrategyListByCondition(Map<String,Object> condition){
		return (List<Xt_Dbtable_Strategy>)this.getList("getXtDbtableStrategyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_dbtable_strategy_id 
	* @return
	*/
	public Xt_Dbtable_Strategy getXtDbtableStrategyById(String xt_dbtable_strategy_id){
		return (Xt_Dbtable_Strategy)this.get("getXtDbtableStrategyById", xt_dbtable_strategy_id);
	}
	/**
	* 添加
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int addXtDbtableStrategy(Xt_Dbtable_Strategy xt_Dbtable_Strategy){
		return this.add("addXtDbtableStrategy", xt_Dbtable_Strategy);
	}
	/**
	* 修改
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int updateXtDbtableStrategy(Xt_Dbtable_Strategy xt_Dbtable_Strategy){
		return this.update("updateXtDbtableStrategy", xt_Dbtable_Strategy);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDbtableStrategy(Map<String,Object> condition){
		return this.del("delXtDbtableStrategy", condition);
	}
}
