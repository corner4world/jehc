package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtDbtableStrategyDao;
import jehc.xtmodules.xtmodel.XtDbtableStrategy;

/**
* 分表策略表 
* 2017-02-14 16:23:48  邓纯杰
*/
@Repository("xtDbtableStrategyDao")
public class XtDbtableStrategyDaoImpl  extends BaseDaoImpl implements XtDbtableStrategyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtDbtableStrategy> getXtDbtableStrategyListByCondition(Map<String,Object> condition){
		return (List<XtDbtableStrategy>)this.getList("getXtDbtableStrategyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_dbtable_strategy_id 
	* @return
	*/
	public XtDbtableStrategy getXtDbtableStrategyById(String xt_dbtable_strategy_id){
		return (XtDbtableStrategy)this.get("getXtDbtableStrategyById", xt_dbtable_strategy_id);
	}
	/**
	* 添加
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int addXtDbtableStrategy(XtDbtableStrategy xt_Dbtable_Strategy){
		return this.add("addXtDbtableStrategy", xt_Dbtable_Strategy);
	}
	/**
	* 修改
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int updateXtDbtableStrategy(XtDbtableStrategy xt_Dbtable_Strategy){
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
