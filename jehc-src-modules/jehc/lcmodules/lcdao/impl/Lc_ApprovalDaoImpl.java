package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.Lc_ApprovalDao;
import jehc.lcmodules.lcmodel.Lc_Approval;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 工作流批审表 
* 2017-01-08 17:06:33  邓纯杰
*/
@Repository("lc_ApprovalDao")
public class Lc_ApprovalDaoImpl  extends BaseDaoImpl implements Lc_ApprovalDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Lc_Approval> getLcApprovalListByCondition(Map<String,Object> condition){
		return (List<Lc_Approval>)this.getList("getLcApprovalListByCondition",condition);
	}
	/**
	* 查询对象
	* @param lc_approval_id 
	* @return
	*/
	public Lc_Approval getLcApprovalById(String lc_approval_id){
		return (Lc_Approval)this.get("getLcApprovalById", lc_approval_id);
	}
	/**
	* 添加
	* @param lc_approval 
	* @return
	*/
	public int addLcApproval(Lc_Approval lc_Approval){
		return this.add("addLcApproval", lc_Approval);
	}
	/**
	* 修改
	* @param lc_approval 
	* @return
	*/
	public int updateLcApproval(Lc_Approval lc_Approval){
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
	public int addBatchLcApproval(List<Lc_Approval> lc_ApprovalList){
		return this.add("addBatchLcApproval", lc_ApprovalList);
	}
	/**
	* 批量修改
	* @param lc_approvalList 
	* @return
	*/
	public int updateBatchLcApproval(List<Lc_Approval> lc_ApprovalList){
		return this.update("updateBatchLcApproval", lc_ApprovalList);
	}
}
