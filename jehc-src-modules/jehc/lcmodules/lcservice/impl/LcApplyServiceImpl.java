package jehc.lcmodules.lcservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.lcdao.LcApplyDao;
import jehc.lcmodules.lcmodel.LcApply;
import jehc.lcmodules.lcservice.LcApplyService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 流程申请 
* 2017-01-08 14:55:11  邓纯杰
*/
@Service("lcApplyService")
public class LcApplyServiceImpl extends BaseService implements LcApplyService{
	@Autowired
	private LcApplyDao lcApplyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcApply> getLcApplyListByCondition(Map<String,Object> condition){
		try{
			return lcApplyDao.getLcApplyListByCondition(condition);
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
	public LcApply getLcApplyById(String lc_apply_id){
		try{
			return lcApplyDao.getLcApplyById(lc_apply_id);
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
	public int addLcApply(LcApply lc_Apply){
		int i = 0;
		try {
			i = lcApplyDao.addLcApply(lc_Apply);
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
	public int updateLcApply(LcApply lc_Apply){
		int i = 0;
		try {
			i = lcApplyDao.updateLcApply(lc_Apply);
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
			i = lcApplyDao.delLcApply(condition);
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
	public int addBatchLcApply(List<LcApply> lc_ApplyList){
		int i = 0;
		try {
			i = lcApplyDao.addBatchLcApply(lc_ApplyList);
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
	public int updateBatchLcApply(List<LcApply> lc_ApplyList){
		int i = 0;
		try {
			i = lcApplyDao.updateBatchLcApply(lc_ApplyList);
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
		return lcApplyDao.getLcApplyList(condition);
	}
}
