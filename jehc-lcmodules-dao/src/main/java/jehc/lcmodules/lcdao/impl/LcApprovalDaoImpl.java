package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.LcApprovalDao;
import jehc.lcmodules.lcmodel.LcApproval;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 工作流批审表 
* 2017-01-08 17:06:33  邓纯杰
*/
@Repository("lcApprovalDao")
public class LcApprovalDaoImpl  extends BaseDaoImpl implements LcApprovalDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<LcApproval> getLcApprovalListByCondition(Map<String,Object> condition){
		return (List<LcApproval>)this.getList("getLcApprovalListByCondition",condition);
	}
	/**
	* 查询对象
	* @param lc_approval_id 
	* @return
	*/
	public LcApproval getLcApprovalById(String lc_approval_id){
		return (LcApproval)this.get("getLcApprovalById", lc_approval_id);
	}
	/**
	* 添加
	* @param lc_approval 
	* @return
	*/
	public int addLcApproval(LcApproval lc_Approval){
		return this.add("addLcApproval", lc_Approval);
	}
	/**
	* 修改
	* @param lc_approval 
	* @return
	*/
	public int updateLcApproval(LcApproval lc_Approval){
		return this.update("updateLcApproval", lc_Approval);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcApproval(Map<String,Object> condition){
		return this.del("delLcApproval", condition);
	}
	/**
	* 批量添加
	* @param lc_approvalList 
	* @return
	*/
	public int addBatchLcApproval(List<LcApproval> lc_ApprovalList){
		return this.add("addBatchLcApproval", lc_ApprovalList);
	}
	/**
	* 批量修改
	* @param lc_approvalList 
	* @return
	*/
	public int updateBatchLcApproval(List<LcApproval> lc_ApprovalList){
		return this.update("updateBatchLcApproval", lc_ApprovalList);
	}
}
