package jehc.lcmodules.lcservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.lcdao.Lc_ApplyDao;
import jehc.lcmodules.lcmodel.Lc_Apply;
import jehc.lcmodules.lcservice.Lc_ApplyService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 流程申请 
* 2017-01-08 14:55:11  邓纯杰
*/
@Service("lc_ApplyService")
public class Lc_ApplyServiceImpl extends BaseService implements Lc_ApplyService{
	@Autowired
	private Lc_ApplyDao lc_ApplyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Lc_Apply> getLcApplyListByCondition(Map<String,Object> condition){
		try{
			return lc_ApplyDao.getLcApplyListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param lc_apply_id 
	* @return
	*/
	public Lc_Apply getLcApplyById(String lc_apply_id){
		try{
			return lc_ApplyDao.getLcApplyById(lc_apply_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param lc_apply 
	* @return
	*/
	public int addLcApply(Lc_Apply lc_Apply){
		int i = 0;
		try {
			i = lc_ApplyDao.addLcApply(lc_Apply);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param lc_apply 
	* @return
	*/
	public int updateLcApply(Lc_Apply lc_Apply){
		int i = 0;
		try {
			i = lc_ApplyDao.updateLcApply(lc_Apply);
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
	public int delLcApply(Map<String,Object> condition){
		int i = 0;
		try {
			i = lc_ApplyDao.delLcApply(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param lc_applyList 
	* @return
	*/
	public int addBatchLcApply(List<Lc_Apply> lc_ApplyList){
		int i = 0;
		try {
			i = lc_ApplyDao.addBatchLcApply(lc_ApplyList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param lc_applyList 
	* @return
	*/
	public int updateBatchLcApply(List<Lc_Apply> lc_ApplyList){
		int i = 0;
		try {
			i = lc_ApplyDao.updateBatchLcApply(lc_ApplyList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据实例编号查找集合
	 * @param condition
	 * @return
	 */
	public List getLcApplyList(Map<String,Object> condition){
		return lc_ApplyDao.getLcApplyList(condition);
	}
}
