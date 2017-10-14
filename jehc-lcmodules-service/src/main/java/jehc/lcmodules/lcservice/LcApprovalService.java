package jehc.lcmodules.lcservice;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.lcmodel.LcApproval;

/**
* 工作流批审表 
* 2017-01-08 17:06:33  邓纯杰
*/
public interface LcApprovalService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcApproval> getLcApprovalListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param lc_approval_id 
	* @return
	*/
	public LcApproval getLcApprovalById(String lc_approval_id);
	/**
	* 添加
	* @param lc_approval 
	* @return
	*/
	public int addLcApproval(LcApproval lc_Approval);
	/**
	* 修改
	* @param lc_approval 
	* @return
	*/
	public int updateLcApproval(LcApproval lc_Approval);
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
	public int addBatchLcApproval(List<LcApproval> lc_ApprovalList);
	/**
	* 批量修改
	* @param lc_approvalList 
	* @return
	*/
	public int updateBatchLcApproval(List<LcApproval> lc_ApprovalList);
}
