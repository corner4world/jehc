package jehc.lcmodules.lcservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.lcdao.LcApprovalDao;
import jehc.lcmodules.lcmodel.LcApproval;
import jehc.lcmodules.lcservice.LcApprovalService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 工作流批审表 
* 2017-01-08 17:06:34  邓纯杰
*/
@Service("lcApprovalService")
public class LcApprovalServiceImpl extends BaseService implements LcApprovalService{
	@Autowired
	private LcApprovalDao lcApprovalDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcApproval> getLcApprovalListByCondition(Map<String,Object> condition){
		try{
			return lcApprovalDao.getLcApprovalListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param lc_approval_id 
	* @return
	*/
	public LcApproval getLcApprovalById(String lc_approval_id){
		try{
			return lcApprovalDao.getLcApprovalById(lc_approval_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param lc_approval 
	* @return
	*/
	public int addLcApproval(LcApproval lc_Approval){
		int i = 0;
		try {
			i = lcApprovalDao.addLcApproval(lc_Approval);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param lc_approval 
	* @return
	*/
	public int updateLcApproval(LcApproval lc_Approval){
		int i = 0;
		try {
			i = lcApprovalDao.updateLcApproval(lc_Approval);
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
	public int delLcApproval(Map<String,Object> condition){
		int i = 0;
		try {
			i = lcApprovalDao.delLcApproval(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param lc_approvalList 
	* @return
	*/
	public int addBatchLcApproval(List<LcApproval> lc_ApprovalList){
		int i = 0;
		try {
			i = lcApprovalDao.addBatchLcApproval(lc_ApprovalList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param lc_approvalList 
	* @return
	*/
	public int updateBatchLcApproval(List<LcApproval> lc_ApprovalList){
		int i = 0;
		try {
			i = lcApprovalDao.updateBatchLcApproval(lc_ApprovalList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
