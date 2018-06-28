package jehc.crmmodules.crmdao;
import java.util.List;
import java.util.Map;
import jehc.crmmodules.crmmodel.CrmFollowup;

/**
* 客户跟进日志 
* 2018-06-27 16:55:12  邓纯杰
*/
public interface CrmFollowupDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmFollowup> getCrmFollowupListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param followupId 
	* @return
	*/
	public CrmFollowup getCrmFollowupById(String followupId);
	/**
	* 添加
	* @param crm_followup 
	* @return
	*/
	public int addCrmFollowup(CrmFollowup crmFollowup);
	/**
	* 修改
	* @param crm_followup 
	* @return
	*/
	public int updateCrmFollowup(CrmFollowup crmFollowup);
	/**
	* 修改（根据动态条件）
	* @param crm_followup 
	* @return
	*/
	public int updateCrmFollowupBySelective(CrmFollowup crmFollowup);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmFollowup(Map<String,Object> condition);
	/**
	* 批量添加
	* @param crm_followupList 
	* @return
	*/
	public int addBatchCrmFollowup(List<CrmFollowup> crmFollowupList);
	/**
	* 批量修改
	* @param crm_followupList 
	* @return
	*/
	public int updateBatchCrmFollowup(List<CrmFollowup> crmFollowupList);
	/**
	* 批量修改（根据动态条件）
	* @param crm_followupList 
	* @return
	*/
	public int updateBatchCrmFollowupBySelective(List<CrmFollowup> crmFollowupList);
}
