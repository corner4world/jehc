package jehc.lcmodules.lcservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.lcdao.Lc_ApprovalDao;
import jehc.lcmodules.lcmodel.Lc_Approval;
import jehc.lcmodules.lcservice.Lc_ApprovalService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 工作流批审表 
* 2017-01-08 17:06:34  邓纯杰
*/
@Service("lc_ApprovalService")
public class Lc_ApprovalServiceImpl extends BaseService implements Lc_ApprovalService{
	@Autowired
	private Lc_ApprovalDao lc_ApprovalDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Lc_Approval> getLcApprovalListByCondition(Map<String,Object> condition){
		try{
			return lc_ApprovalDao.getLcApprovalListByCondition(condition);
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
	public Lc_Approval getLcApprovalById(String lc_approval_id){
		try{
			return lc_ApprovalDao.getLcApprovalById(lc_approval_id);
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
	public int addLcApproval(Lc_Approval lc_Approval){
		int i = 0;
		try {
			i = lc_ApprovalDao.addLcApproval(lc_Approval);
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
	public int updateLcApproval(Lc_Approval lc_Approval){
		int i = 0;
		try {
			i = lc_ApprovalDao.updateLcApproval(lc_Approval);
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
			i = lc_ApprovalDao.delLcApproval(condition);
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
	public int addBatchLcApproval(List<Lc_Approval> lc_ApprovalList){
		int i = 0;
		try {
			i = lc_ApprovalDao.addBatchLcApproval(lc_ApprovalList);
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
	public int updateBatchLcApproval(List<Lc_Approval> lc_ApprovalList){
		int i = 0;
		try {
			i = lc_ApprovalDao.updateBatchLcApproval(lc_ApprovalList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
