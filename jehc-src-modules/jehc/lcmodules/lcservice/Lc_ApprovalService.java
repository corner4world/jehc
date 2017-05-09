package jehc.lcmodules.lcservice;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.lcmodel.Lc_Approval;

/**
* 工作流批审表 
* 2017-01-08 17:06:33  邓纯杰
*/
public interface Lc_ApprovalService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Lc_Approval> getLcApprovalListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param lc_approval_id 
	* @return
	*/
	public Lc_Approval getLcApprovalById(String lc_approval_id);
	/**
	* 添加
	* @param lc_approval 
	* @return
	*/
	public int addLcApproval(Lc_Approval lc_Approval);
	/**
	* 修改
	* @param lc_approval 
	* @return
	*/
	public int updateLcApproval(Lc_Approval lc_Approval);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcApproval(Map<String,Object> condition);
	/**
	* 批量添加
	* @param lc_approvalList 
	* @return
	*/
	public int addBatchLcApproval(List<Lc_Approval> lc_ApprovalList);
	/**
	* 批量修改
	* @param lc_approvalList 
	* @return
	*/
	public int updateBatchLcApproval(List<Lc_Approval> lc_ApprovalList);
}
