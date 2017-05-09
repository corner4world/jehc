package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Dbtable_StrategyDao;
import jehc.xtmodules.xtmodel.Xt_Dbtable_Strategy;
import jehc.xtmodules.xtservice.Xt_Dbtable_StrategyService;

/**
* 分表策略表 
* 2017-02-14 16:23:48  邓纯杰
*/
@Service("xt_Dbtable_StrategyService")
public class Xt_Dbtable_StrategyServiceImpl extends BaseService implements Xt_Dbtable_StrategyService{
	@Autowired
	private Xt_Dbtable_StrategyDao xt_Dbtable_StrategyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Dbtable_Strategy> getXtDbtableStrategyListByCondition(Map<String,Object> condition){
		try{
			return xt_Dbtable_StrategyDao.getXtDbtableStrategyListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_dbtable_strategy_id 
	* @return
	*/
	public Xt_Dbtable_Strategy getXtDbtableStrategyById(String xt_dbtable_strategy_id){
		try{
			return xt_Dbtable_StrategyDao.getXtDbtableStrategyById(xt_dbtable_strategy_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int addXtDbtableStrategy(Xt_Dbtable_Strategy xt_Dbtable_Strategy){
		int i = 0;
		try {
			i = xt_Dbtable_StrategyDao.addXtDbtableStrategy(xt_Dbtable_Strategy);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_dbtable_strategy 
	* @return
	*/
	public int updateXtDbtableStrategy(Xt_Dbtable_Strategy xt_Dbtable_Strategy){
		int i = 0;
		try {
			i = xt_Dbtable_StrategyDao.updateXtDbtableStrategy(xt_Dbtable_Strategy);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDbtableStrategy(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Dbtable_StrategyDao.delXtDbtableStrategy(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
