package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDbtableStrategyDao;
import jehc.xtmodules.xtmodel.XtDbtableStrategy;
import jehc.xtmodules.xtservice.XtDbtableStrategyService;

/**
* 分表策略表 
* 2017-02-14 16:23:48  邓纯杰
*/
@Service("xtDbtableStrategyService")
public class XtDbtableStrategyServiceImpl extends BaseService implements XtDbtableStrategyService{
	@Autowired
	private XtDbtableStrategyDao xtDbtableStrategyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDbtableStrategy> getXtDbtableStrategyListByCondition(Map<String,Object> condition){
		try{
			return xtDbtableStrategyDao.getXtDbtableStrategyListByCondition(condition);
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
	public XtDbtableStrategy getXtDbtableStrategyById(String xt_dbtable_strategy_id){
		try{
			return xtDbtableStrategyDao.getXtDbtableStrategyById(xt_dbtable_strategy_id);
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
	public int addXtDbtableStrategy(XtDbtableStrategy xt_Dbtable_Strategy){
		int i = 0;
		try {
			i = xtDbtableStrategyDao.addXtDbtableStrategy(xt_Dbtable_Strategy);
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
	public int updateXtDbtableStrategy(XtDbtableStrategy xt_Dbtable_Strategy){
		int i = 0;
		try {
			i = xtDbtableStrategyDao.updateXtDbtableStrategy(xt_Dbtable_Strategy);
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
			i = xtDbtableStrategyDao.delXtDbtableStrategy(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
